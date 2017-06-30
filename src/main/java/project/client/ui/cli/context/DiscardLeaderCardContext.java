package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 08/06/17.
 */
public class DiscardLeaderCardContext extends AbstractContext {

    public DiscardLeaderCardContext(Cli cli) {
        super(cli);
        map.put(CliConstants.SHOW_LEADER_CARDS, this:: showLeaderCards );
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    //todo implement
    private void showLeaderCards() {

    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.print("Choose the "); pRed.print("[leaderCardName]"); pYellow.print(" that you want to play");

    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String name) throws InputException, IOException {
        cli.discardLeaderCard(name);
    }
}
