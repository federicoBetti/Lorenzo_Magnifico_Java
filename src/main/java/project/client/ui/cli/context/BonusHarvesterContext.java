package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context opened when there is a bonus Harvester to do
 */
public class BonusHarvesterContext extends AbstractContext {
    private BonusProductionOrHarvesterAction bonusHarv;
    List<TerritoryCard> territories;

    /**
     * Constructor
     *
     * @param bonusHarv object that contains the bonus harvester's characteristics
     * @param cli cli's reference
     * @param territories list of territory cards
     */
    public BonusHarvesterContext(BonusProductionOrHarvesterAction bonusHarv, Cli cli, List<TerritoryCard> territories ) {
        super(cli);
        this.territories = territories;
        this.bonusHarv = bonusHarv;
        map.put(CliConstants.SHOW_TERRITORIES, this:: showTerritories );
        printHelp();
    }

    /**
     * This method prints the player's territory cards
     */
    private void showTerritories() {
super.showTerritoriesAbstract(territories);
    }

    /**
     * This method print the help context's menu
     */
    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println(bonusHarv.actionString());
        pBlue.print("Type the number of servants that you want ot use: ");pRed.println("[numberOfServants]");

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

        if(!( parameters.length == 1 ))
            throw new InputException();

        for ( int i = 0; i < parameters[0].length(); i++ ) {
                if (!Character.isDigit(parameters[0].charAt(i)))
                    throw new InputException();
        }
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls bonusHarvesterParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusHarvesterParameters(action);
    }
}
