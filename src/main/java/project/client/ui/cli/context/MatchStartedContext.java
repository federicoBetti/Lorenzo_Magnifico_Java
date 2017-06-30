package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 24/06/17.
 */
public class MatchStartedContext extends AbstractContext {
    public MatchStartedContext(Cli cli) {
        super(cli);
        printHelp();
    }

    @Override
    public void printHelp() {
        pBlue.println("MATCH STARTED!");
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
