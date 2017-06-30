package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.controller.effects.realeffects.Effects;
import project.model.FamilyMember;
import project.model.Tower;

import java.io.IOException;
import java.util.Map;


/**
 * Created by raffaelebongo on 05/06/17.
 */
public class TowersContext extends AbstractContext {

    Tower[][] allTowers;    // allTowers[TORRE][PIANO];
    public TowersContext(Cli cli, Tower[][] allTowers) {
        super(cli);
        this.allTowers = allTowers;
        map.put(CliConstants.SHOW_TOWERS_CARDS_COST, this:: showCardsCost );
        map.put(CliConstants.SHOW_TOWERS_CARDS_EFFECTS, this::showCardsEffects);
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    private void showCardsEffects() {
        for (int i = 0; i < Constants.NUMBER_OF_TOWERS; i++ ) {
            pBlue.print("Tower: "); pRed.println(allTowers[i][i].getColour());
            for (int j = 0; j < Constants.NUMBER_OF_FLOORS; j++) {
                if ( allTowers[i][j].getCardOnThisFloor() == null ){
                    pBlue.print("Floor: "); pRed.println( j );pBlue.println("The card has been taken");
                    continue;
                }

                pBlue.print("Floor: "); pRed.println( j );
                pBlue.print("Card name: "); pRed.println(allTowers[i][j].getCardOnThisFloor().getName());
                pBlue.print("Immediate Effects: \n"); int count1 = 1;
                for (Effects effect : allTowers[i][j].getCardOnThisFloor().getImmediateCardEffects() ){
                    pBlue.print( count1 + ") ");pYellow.println(effect.toScreen());
                    count1++;
                }
                pBlue.print("Permanent Effects: \n"); int count2 = 1;
                for (Effects effect : allTowers[i][j].getCardOnThisFloor().getPermanentCardEffects() ){
                    pBlue.print( count2 + ") ");pYellow.println(effect.toScreen());
                    count2++;
                }
                pRed.println("");
            }
        }
        pRed.println("Type help for see the available commands.");

    }

    //todo controllare questa show
    private void showCardsCost() {
        for (int i = 0; i < Constants.NUMBER_OF_TOWERS; i++ ) {
            pBlue.print("Tower: "); pRed.println(allTowers[i][i].getColour());
            for (int j = 0; j < Constants.NUMBER_OF_FLOORS; j++) {
                if ( allTowers[i][j].getCardOnThisFloor() == null ){
                    pBlue.print("Floor: "); pRed.println( j );pBlue.println("The card has been taken");
                    continue;
                }

                pBlue.print("Floor: "); pRed.println( j );
                pBlue.print("Card name: "); pRed.println(allTowers[i][j].getCardOnThisFloor().getName());
                pBlue.print("Card cost: ");pYellow.println(allTowers[i][j].getCardOnThisFloor().getCost().toScreen());
                pYellow.println("");
            }
        }

        pRed.println("Type ");pBlue.print("[help]");pRed.println("for watching the other commands.");
    }

    @Override
    public void printHelp() {
        pRed.println("You are in the Tower Context! The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());

        pRed.println("The main Action is:");
        pBlue.println("[towerColour-floor-familiarColour] ");
        pRed.print("towerColour: "); pYellow.println("green, yellow, purple, blue");
        pRed.print("floor: ");  pYellow.println("0, 1, 2, 3");
        pRed.print("Familiar Colour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pYellow.println("");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 3 ))
            throw new InputException();

        if (!(Integer.parseInt(parameters[1]) >= 0 && Integer.parseInt(parameters[1]) <= 3) )
            throw new InputException();

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException{
        cli.choseAndTakeDevCard(action);
    }
}

