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
    }

    //todo implement
    private void showLeaderCards() {

    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[leaderCardName]");

    }

    @Override
    public void mainContextMethod(String name) throws InputException, IOException, ClassNotFoundException {
        cli.discardLeaderCard(name);
    }
}
