package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class HarvesterContext extends AbstractContext {

    public HarvesterContext(Cli cli ){
        super(cli);
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp );
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println("[position(int)-familiarColour-servantsNumber(int)]\nposition: 0, 1, 2, 3 " +
                "\ntowerColour: green, yellow, purple, blue\nservantsNumber: any number ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 3 ))
            throw new InputException();

        if( parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) <= 3 )
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);

        if( parameters[2].length() == 1 && Character.isDigit(parameters[2].charAt(0)))
            throw new InputException();
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseHarversterParameters(action);
    }
}
