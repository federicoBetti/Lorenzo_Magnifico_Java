package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context opened when a mani context method has been performed with success
 */
public class AfterMainActionContext extends AbstractContext {
    List<LeaderCard> leaderCards;

    public AfterMainActionContext(Cli cli, List<LeaderCard> leaderCards) {
        super(cli);
        this.leaderCards = leaderCards;
        map.put(Constants.SKIP_TURN, this::skipTurn );
        map.put(Constants.PLAY_LEADER_CARD_AMA, this:: playLeaderCardAma );
        map.put(Constants.DISCARD_LEADER_CARD_AMA, this:: discardLeaderCardAma );
        map.put(CliConstants.SHOW_POINTS, this::showPoints );
        printHelp();
    }

    /**
     * This method calls showPoints on the cli
     */
    private void showPoints() {
        cli.showPoints();
    }

    /**
     * This method calls discardLeaderCardAma on the cli
     */
    private void discardLeaderCardAma() {
        cli.discardLeaderCardAma();
    }

    /**
     * This method calls playLeaderCardAma on the cli
     */
    private void playLeaderCardAma() {
        cli.playLeaderCardAma(leaderCards);
    }

    /**
     * This method calls skipTurn on the cli
     */
    private void skipTurn() {
        cli.skipTurn();
    }

    /**
     * This method print the help context's menu
     */
    @Override
    public void printHelp() {
        pRed.println("You have done successfully your main action. The currents available actions are:\n");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());
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
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
