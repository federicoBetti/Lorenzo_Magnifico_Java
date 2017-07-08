package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when is asked to the player is pray or not
 */
public class ExcomunicationContext extends AbstractContext{

    public ExcomunicationContext (Cli cli ){
        super(cli);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    public void printHelp() {
        pRed.println("The available action is:");
        pYellow.println("\nDo you want to pray? ");pBlue.println("[0 = yes / 1 = no]");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        if  (!(input.equals("0") || input.equals("1")) )
                throw new InputException();
    }

    /**
     * If the string in input does not correspond with no key, this method is called
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
