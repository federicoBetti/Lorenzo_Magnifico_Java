package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */

public class BonusHarvesterContext extends AbstractContext {
    private BonusProductionOrHarvesterAction bonusHarv;

    public BonusHarvesterContext(BonusProductionOrHarvesterAction bonusHarv, Cli cli) {
        super(cli);
        this.bonusHarv = bonusHarv;
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println(bonusHarv.actionString());

    }

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

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusHarvesterParameters(action);
    }
}
