package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;

/**
 * Created by raffaelebongo on 20/06/17.
 */
public class WaitingForYourTurnContext extends AbstractContext {

    public WaitingForYourTurnContext(Cli cli) {
        super(cli);
        printHelp();
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        //todo vedere cosa fare
    }
}
