package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.controller.effects.realeffects.Effects;
import project.messages.TowerAction;
import project.model.Tower;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class TakeBonusCard extends AbstractContext {
    private TowerAction towerAction;
    Tower[][] allTowers;
    
    public TakeBonusCard(Cli cli, TowerAction towerAction, Tower[][] allTowers ) {
        super(cli);
        this.towerAction = towerAction;
        this.allTowers = allTowers;
        map.put(CliConstants.SHOW_TOWERS_CARDS_COST, this:: showCardsCost );
        map.put(CliConstants.SHOW_CARDS_EFFECTS, this:: showCardsEffects );
        map.put(CliConstants.EXIT, this::exitFromBonus );
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
            }
        }

        pBlue.print("If you want to see the cards'effects type ");pRed.println("[show-cards-effects]");
    }

    private void exitFromBonus() throws IOException, InputException {
        exit();
        cli.sendExitToBonusAction();
    }


    @Override
    public void printHelp() {
        pRed.println("The available actions are:\ntake the bonus card with these characteristics:");
        towerAction.printBonusAction();
        pYellow.println("floor: 0, 1, 2, 3\n" +
                "tower colour: green, yellow, purple, blue");

        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());
        pYellow.println("");
    }

    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        String[] parameters = input.split("-");

        if ( !(parameters.length == 2) )
            throw new InputException();

        if( !(parameters[0].length() == 1) && !(Character.isDigit(parameters[0].charAt(0))))
            throw new InputException();

        if (! (Integer.parseInt(parameters[0]) >= 0) && !(Integer.parseInt(parameters[0]) <= 3 ))
            throw new InputException();

        checkTowerColour(parameters[1]);

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.takeBonusCardParameters(action);
    }
}
