package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class LeaderCardContext extends AbstractContext {

    public LeaderCardContext( Cli cli ){
        super(cli);
        map.put(CliConstants.SHOW_LEADER_CARDS, this::showLeaderCards );
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[leaderCardName]");
    }


    //todo implement
    private void showLeaderCards() {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseLeaderCardToPlay(action);
    }
}
