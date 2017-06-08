package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class ExcomunicationContext extends AbstractContext {

    public ExcomunicationContext (Cli cli ){
        super(cli);
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException, ClassNotFoundException {

    }
}
