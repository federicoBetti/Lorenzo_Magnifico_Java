package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;


import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MarketContext extends AbstractContext {
    Cli cli;

    public MarketContext( Cli cli ){
        super(cli);
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[position-familiarColour] \n " +
                "\n position: 0, 1, 2, 3 \n familiarColour: black, neutral, orange, white ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        if( parameters[0].length() == 1 && Character.isDigit(parameters[1].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[1]) >= 0 && Integer.parseInt(parameters[1]) <= 3 )
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException, ClassNotFoundException {
        cli.chooseMarketActionParameters(action);
    }
}
