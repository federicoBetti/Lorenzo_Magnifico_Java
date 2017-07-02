package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 02/07/17.
 */
public class TimerDelayedContext extends AbstractContext {

    public TimerDelayedContext(Cli cli) {
        super(cli);
        map.put(Constants.RECONNECT, this:: reconnect );
        map.put(CliConstants.HELP, this:: printHelp);
        printHelp();
    }

    private void reconnect() {
        cli.reconnect();
    }

    @Override
    public void printHelp() {
        pRed.println("You didn't perform any actions and you have been suspended. The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {

    }
}
