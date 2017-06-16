package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */


//serve oppure non va scelto nulla??? cioè non si possono aggiungere schiavi in sostanza? chiedere
public class BonusHarvesterContext extends AbstractContext {
    BonusProductionOrHarvesterAction bonusHarv;

    public BonusHarvesterContext(BonusProductionOrHarvesterAction bonusHarv, Cli cli) {
        super(cli);
        this.bonusHarv = bonusHarv;
    }

    @Override
    public void printHelp() {
        bonusHarv.printAction();
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 1 ))
            throw new InputException();

        if( parameters[1].length() == 1 && Character.isDigit(parameters[1].charAt(0)))
            throw new InputException();
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusHarvesterParameters(action);
    }
}
