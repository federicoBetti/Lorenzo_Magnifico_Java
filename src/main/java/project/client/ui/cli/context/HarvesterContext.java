package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

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

    }

    @Override
    public void defaultContextMethod(String action){
        cli.
    }
}
