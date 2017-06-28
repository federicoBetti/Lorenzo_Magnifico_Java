package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;

/**
 * Created by raffaelebongo on 28/06/17.
 */
public class LeaderCardDraftContext extends AbstractContext {
    List<LeaderCard> leaders;

    public LeaderCardDraftContext(Cli cli, List<LeaderCard> leaders) {
        super(cli);
        this.leaders = leaders;
        printHelp();
    }

    @Override
    public void printHelp() {
        pBlue.println("Choose a leader card for the draft!");
        pYellow.println("type [leaderCardName] among these leaders avaible:");
        for ( LeaderCard leaderCard : leaders )
            pRed.println(leaderCard.getName());
    }

    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {

    }
}
