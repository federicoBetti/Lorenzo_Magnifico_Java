package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class HarvesterContext extends AbstractContext {

    public HarvesterContext(Cli cli ){
        super(cli);
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp );
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 3 ))
            throw new InputException();

        if( parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[0]) > 0 && Integer.parseInt(parameters[0]) < 3 )
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);

        if( parameters[2].length() == 1 && Character.isDigit(parameters[2].charAt(0)))
            throw new InputException();
    }


    @Override
    public void defaultContextMethod(String action) throws InputException {
        cli.chooseHarversterParameters();
    }
}
