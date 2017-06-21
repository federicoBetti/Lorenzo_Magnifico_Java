package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 16/06/17.
 */
public class DiscardLeaderCardAmaContext extends AbstractContext {

    public DiscardLeaderCardAmaContext(Cli cli) {
        super(cli);
        map.put(CliConstants.SHOW_LEADER_CARDS, this::showLeaderCards );
        map.put(CliConstants.EXIT, cli::actionOk);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    //todo
    private void showLeaderCards() {
        //to implement
    }

    @Override
    public void printHelp() {

        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.print("Type the ");pRed.print("[leaderCardName] ");pYellow.print("that you want to discard");
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.discardLeaderCard(action);
        cli.actionOk();
    }
}
