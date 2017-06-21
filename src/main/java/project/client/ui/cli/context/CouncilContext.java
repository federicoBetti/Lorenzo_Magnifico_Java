package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class CouncilContext extends AbstractContext {

    public CouncilContext( Cli cli ){
        super(cli);
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[priviledgeNumber(int)-familiarColour] " +
                "\n priviledgeNumber: 0, 1, 2, 3, 4, 5 " +
                "\n familiarColour: black, neutral, orange, white  ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        if( parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) < 6 )
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);
    }

    @Override
    public void mainContextMethod(String action ) throws InputException, IOException {
        cli.chooseCouncilParameters(action);
    }


}
