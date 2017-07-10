package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class represent a context opened when the player takes a venture card that has two possible costs
 */
public class BothPaymentsVentureCardsContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public BothPaymentsVentureCardsContext(Cli cli) {
        super(cli);
        printHelp();
    }

    /**
     * This method print the help context's menu
     */
    @Override
    public void printHelp() {
        pRed.println("The main action is:");
        pBlue.print("[0]");pYellow.println(" for choosing the first cost");
        pBlue.print("[1]");pYellow.println(" for choosing the second cost");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    public void checkValidInput( String input ) throws InputException{
        String[] parameters = input.split("-");

        if ( (!(parameters.length == 1) && !Character.isDigit(Integer.parseInt(parameters[0]))))
           throw new InputException();

        if ( !(parameters[0].equals("0") || parameters[0].equals("1")))
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
