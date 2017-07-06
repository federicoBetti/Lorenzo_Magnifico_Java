package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 20/06/17.
 */
public class WaitingForMatchStart extends AbstractContext {


    public WaitingForMatchStart(Cli cli) {
       super(cli);
       printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("Waiting for start match...");
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
