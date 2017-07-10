package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.BuildingCard;
import project.controller.effects.realeffects.Effects;
import project.model.FamilyMember;
import project.model.Production;
import project.model.Tile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context opened when the player wants to perform a production
 */
public class ProductionContext extends AbstractContext {
    List<Production> productionZone;
    Tile bonusTile;
    List<BuildingCard> buildings;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param productionZone production zone's reference
     * @param bonusTile bonus tile's reference
     * @param buildings buildi cards' reference
     */
    public ProductionContext(Cli cli, List<Production> productionZone, Tile bonusTile, List<BuildingCard> buildings){
        super(cli);
        this.productionZone = productionZone;
        this.bonusTile = bonusTile;
        this.buildings = buildings;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_PRODUCTION_ZONE, this:: showProductionZone );
        map.put(CliConstants.SHOW_BONUS_TILE, this:: showBonusTile );
        map.put(CliConstants.SHOW_BUILDING_CARDS, this:: showBuildingCards );
        printHelp();
    }

    /**
     * This method prints the player's building cards
     */
    private void showBuildingCards() {
        super.showBuildingCardsAbstract(buildings);
    }

    /**
     * This method prints the player's bonus tile effects
     */
    private void showBonusTile() {
        super.showBonusTileAbstract(bonusTile);
    }

    /**
     * This method prints the familiars already placed in the production zone
     */
    private void showProductionZone() {
        int i = 1;
        pRed.println("Production zone: ");
        for ( Production production : productionZone ){
            pBlue.print(i + ") ");pRed.print("Player colour: ");pYellow.println(production.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print("   Familiar colour: ");pRed.print("Familiar Colour: ");
            for (FamilyMember familyMember : cli.getMyFamilymembers() ){
                if ( !familyMember.isPlayed() )
                    pYellow.print(familyMember.getMyColour() + "   ");
            }
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

        pBlue.println("[familiarColour-buildingCard-...(max 6 buildingCard in your personal board]");
        pRed.print("familiarColour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pRed.println("");
        pRed.print("buildingCard: ");pYellow.println("name1 - name2 - ... ");
        pYellow.println("");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        boolean cardAvailable = false;
        String[] parameters = input.split("-");

        if ( !(parameters.length < 8 ))
            throw new InputException();

        checkFamilyMemberColour(parameters[0]);

        for ( int j = 1; j < parameters.length; j++  ) {
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).getName().equals(parameters[j])) {
                    cardAvailable = true;
                    break;
                }
            }
            if (!cardAvailable )
                throw new InputException();
            cardAvailable = false;
        }
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls chooseProductionParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseProductionParameters(action);
    }
}
