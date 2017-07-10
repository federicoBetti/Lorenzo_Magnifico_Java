package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context openend when is asked to the player to choose the kind of connection
 */
public class ConnectionContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public ConnectionContext(Cli cli) {
        super(cli);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Choose the type of connection:\n" +
                "type: socket | RMI ");
    }


    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        if ( !(input.equals("socket") || input.equals("RMI")))
            throw new InputException();
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls setConnectionType
     *
     * @param kindOfConnection string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String kindOfConnection) throws InputException, IOException {
        cli.setConnectionType(kindOfConnection);
    }
}
