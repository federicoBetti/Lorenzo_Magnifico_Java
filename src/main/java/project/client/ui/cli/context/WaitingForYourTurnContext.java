package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when the player during the match has to wait his next turn
 */
public class WaitingForYourTurnContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public WaitingForYourTurnContext(Cli cli) {
        super(cli);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Waiting for your turn...");
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls pritHelp
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        return;
    }
}
