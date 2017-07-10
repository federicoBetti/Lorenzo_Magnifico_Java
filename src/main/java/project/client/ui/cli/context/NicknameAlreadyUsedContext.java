package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when the client choose a nickname already used
 */
public class NicknameAlreadyUsedContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public NicknameAlreadyUsedContext(Cli cli) {
        super(cli);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Nickanme already used. Please type another nickname.");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        //no check needed
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls newNickname
     *
     * @param nickname string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    protected void mainContextMethod(String nickname) throws InputException, IOException {
        cli.newNickname(nickname);
    }
}
