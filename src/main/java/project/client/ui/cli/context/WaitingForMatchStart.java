package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when the player has done the login and he is waiting for the match starting
 */
public class WaitingForMatchStart extends AbstractContext {

    public WaitingForMatchStart(Cli cli) {
       super(cli);
       printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Waiting for start match...");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {

    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls loginRequest
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
