package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * This class is a context opened when during a production action there is a card that has two possible
 * permanent effects
 */
public class ChoicePeContext extends AbstractContext {

    public ChoicePeContext(Cli cli) {
        super(cli);
        map.put(CliConstants.HELP, this::printHelp );
        printHelp();
    }

    /**
     * This method print the help context's menu
     */
    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println("Choose the permanent effect to use:" +
                "\n[0] for the first PE;" +
                "\n[1] for the second PE");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {

        if ( input.equals("-1"))
            return;

        if( !(input.length() < 2))
            throw new InputException();

        if( !(Character.isDigit(input.charAt(0))) )
            throw new InputException();

        try {
            if (!(Integer.parseInt(input) == 0) && !(Integer.parseInt(input) == 1))
                throw new InputException();
        }catch (NumberFormatException e ){
            printHelp();
        }
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
