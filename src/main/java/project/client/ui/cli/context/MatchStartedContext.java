package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when the match starts
 */
public class MatchStartedContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public MatchStartedContext(Cli cli) {
        super(cli);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pBlue.println("MATCH STARTED! Wait for your turn...");
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
     * If the string in input does not correspond with no key, this method is called and it calls printHelp
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
