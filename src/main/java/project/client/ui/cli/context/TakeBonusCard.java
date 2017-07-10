package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.controller.cardsfactory.VenturesCard;
import project.controller.cardsfactory.VenturesCost;
import project.controller.effects.realeffects.Effects;
import project.messages.TowerAction;
import project.model.Tower;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context that is opened when a card taken by the player has like immediate effects a bonus tower
 * action
 */
public class TakeBonusCard extends AbstractContext {
    private TowerAction towerAction;
    Tower[][] allTowers;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param towerAction object that contains the bonus tower action characteristics
     * @param allTowers towers' reference
     */
    public TakeBonusCard(Cli cli, TowerAction towerAction, Tower[][] allTowers ) {
        super(cli);
        this.towerAction = towerAction;
        this.allTowers = allTowers;
        map.put(CliConstants.SHOW_TOWERS_CARDS_COST, this:: showCardsCost );
        map.put(CliConstants.SHOW_CARDS_EFFECTS, this:: showCardsEffects );
        map.put(CliConstants.EXIT, this::exitFromBonus );
        printHelp();
    }

    /**
     * This method prints the card of the tower's effects
     */
    private void showCardsEffects() {
        super.showCardsEffectsTower(allTowers);
        pRed.println("Type help for see the available commands.");

    }

    /**
     * This method prints the card of the tower's costs
     */
    private void showCardsCost() {
        super.showCardCostTower(allTowers);
        pRed.println("Type ");pBlue.print("[help]");pRed.println("for watching the other commands.");
    }

    /**
     * This method call exit method that open the mainContext and call sendExitToBonusAction
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred
     * @throws InputException exception thrown when the client type an invalid input
     */
    private void exitFromBonus() throws IOException, InputException {
        exit();
        cli.sendExitToBonusAction();
    }

    /**
     * This method prints the help menu
     */
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

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        String[] parameters = input.split("-");

        if ( !(parameters.length == 2) )
            throw new InputException();

        if( !(parameters[0].length() == 1) && !(Character.isDigit(parameters[0].charAt(0))))
            throw new InputException();

        try {
            if (Integer.parseInt(parameters[0]) < 0 || Integer.parseInt(parameters[0]) > 3)
                throw new InputException();
        } catch ( NumberFormatException e ){
            throw new InputException();
        }
        checkTowerColour(parameters[1]);

    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls takeBonusCardParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.takeBonusCardParameters(action);
    }
}
