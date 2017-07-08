package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened during the login session
 */
public class LoginContext extends AbstractContext {

    public LoginContext( Cli cli ){
        super(cli);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("You are in the Login Context. Write your nickname");
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
        cli.loginRequest(action);
    }

}

