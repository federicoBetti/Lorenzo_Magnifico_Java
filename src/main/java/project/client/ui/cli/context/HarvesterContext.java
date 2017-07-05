package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.effects.realeffects.Effects;
import project.model.FamilyMember;
import project.model.Harvester;
import project.model.Tile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class HarvesterContext extends AbstractContext {
    List<Harvester> harvesterZone;
    Tile bonusTile;

    public HarvesterContext(Cli cli, List<Harvester> harvesterZone, Tile bonusTile){
        super(cli);
        this.harvesterZone = harvesterZone;
        this.bonusTile = bonusTile;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp );
        map.put(CliConstants.SHOW_HARVESTER_ZONE, this::showHarvesterZone );
        map.put(CliConstants.SHOW_BONUS_TILE, this:: showBonusTile );
        printHelp();
    }

    private void showBonusTile() {
        pBlue.print(bonusTile.getTileNumber() + ") ");pRed.println("Production effects: ");

        int i = 1;
        for ( Effects effect : bonusTile.takeProductionResource() ){
            pYellow.println( i + ") " + effect.toScreen());
            i++;
        }
        pRed.println("\nHarvester Bonus: ");
        int j = 1;
        for ( Effects effect : bonusTile.takeHarvesterResource() ){
            pYellow.println( j + ") " + effect.toScreen());
            j++;
        }
    }

    private void showHarvesterZone() {
        int i = 1;
        pRed.println("Harvester zone: ");
        for ( Harvester harvester : harvesterZone ){
            pBlue.print(i + ") ");pRed.print("Player colour: ");pYellow.println(harvester.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print("   Familiar colour: ");pYellow.println(harvester.getFamiliarOnThisPosition().getMyColour());
            i++;
        }
    }

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

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        checkFamilyMemberColour(parameters[0]);

        if( !(Character.isDigit(parameters[1].charAt(0))))
            throw new InputException();

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseHarversterParameters(action);
    }
}
