package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.effects.realeffects.Effects;
import project.model.FamilyMember;
import project.model.Harvester;
import project.model.Tile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context openend when the player wants to perform an harvester action
 */
public class HarvesterContext extends AbstractContext {
    List<Harvester> harvesterZone;
    Tile bonusTile;
    List<TerritoryCard> territories;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param harvesterZone harvester's zone
     * @param bonusTile bonusTile's reference
     * @param territories territories' reference
     */
    public HarvesterContext(Cli cli, List<Harvester> harvesterZone, Tile bonusTile, List<TerritoryCard> territories ){
        super(cli);
        this.harvesterZone = harvesterZone;
        this.bonusTile = bonusTile;
        this.territories = territories;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp );
        map.put(CliConstants.SHOW_TERRITORIES, this:: showTerritories );
        map.put(CliConstants.SHOW_HARVESTER_ZONE, this::showHarvesterZone );
        map.put(CliConstants.SHOW_BONUS_TILE, this:: showBonusTile );
        printHelp();
    }

    /**
     * This method prints all the player's territory cards
     */
    private void showTerritories() {
        super.showTerritoriesAbstract(territories);
    }

    /**
     * This method prints the player's bonus tile effects
     */
    private void showBonusTile() {
        super.showBonusTileAbstract(bonusTile);
    }

    /**
     * This method prints the familiar already placed in the harvester zone
     */
    private void showHarvesterZone() {
        int i = 1;
        pRed.println("Harvester zone: ");
        for ( Harvester harvester : harvesterZone ){
            pBlue.print(i + ") ");pRed.print("Player colour: ");pYellow.println(harvester.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print("   Familiar colour: ");pYellow.println(harvester.getFamiliarOnThisPosition().getMyColour());
            i++;
        }
    }


    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pBlue.println("[familiarColour-servantsNumber(int)]");
        pRed.print("Familiar Colour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pRed.print("Servants number: ");pYellow.println("any number");
    }


    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        checkFamilyMemberColour(parameters[0]);

        if( !(Character.isDigit(parameters[1].charAt(0))))
            throw new InputException();

    }


    /**
     * If the string in input does not correspond with no key, this method is called and it calls chooseHarversterParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseHarversterParameters(action);
    }
}
