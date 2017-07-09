package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * This class is a context opened when the turn time is up
 */
public class TimerDelayedContext extends AbstractContext {

    public TimerDelayedContext(Cli cli) {
        super(cli);
        map.put(Constants.RECONNECT, this:: reconnect );
        map.put(CliConstants.HELP, this:: printHelp);
        printHelp();
    }

    /**
     * This method calls the method reconnect on the cli
     */
    private void reconnect() {
        cli.reconnect();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("You didn't perform any actions and you have been suspended. The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
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
    protected void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
