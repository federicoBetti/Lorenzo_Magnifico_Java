package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 16/06/17.
 */
public class AfterMainActionContext extends AbstractContext {

    public AfterMainActionContext(Cli cli) {
        super(cli);
        map.put(Constants.SKIP_TURN, this::skipTurn );
        map.put(Constants.PLAY_LEADER_CARD_AMA, this:: playLeaderCardAma );
        map.put(Constants.DISCARD_LEADER_CARD_AMA, this:: discardLeaderCardAma );
        map.put(CliConstants.SHOW_POINTS, this::showPoints );
        printHelp();
    }

    private void showPoints() {
        cli.showPoints();
    }

    private void discardLeaderCardAma() {
        cli.discardLeaderCardAma();
    }

    private void playLeaderCardAma() {
        cli.playLeaderCardAma();
    }

    private void skipTurn() {
        cli.skipTurn();
    }

    @Override
    public void printHelp() {
        pRed.println("You have done successfully your main action. The currents available actions are:\n");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        System.out.println();
        printHelp();
    }
}
