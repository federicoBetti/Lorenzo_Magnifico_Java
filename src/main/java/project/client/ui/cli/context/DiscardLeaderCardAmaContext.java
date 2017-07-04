package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by raffaelebongo on 16/06/17.
 */
public class DiscardLeaderCardAmaContext extends AbstractContext {
    List<LeaderCard> leaderCards;

    public DiscardLeaderCardAmaContext(Cli cli, List<LeaderCard> leaderCards) {
        super(cli);
        this.leaderCards = leaderCards;
        map.put(CliConstants.SHOW_LEADER_CARDS, this::showLeaderCards );
        map.put(CliConstants.EXIT, cli::actionOk);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    private void showLeaderCards() {
        for ( LeaderCard leaderCard : leaderCards ) {
            pRed.println(leaderCard.getName());
            pBlue.print("Requirements: ");pYellow.println(leaderCard.getRequirementsDescription());
            pBlue.print("Effect Description:");pYellow.println(leaderCard.getCardDescription());
            pYellow.println("");
        }
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
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.discardLeaderCard(action);
        cli.actionOk();
    }
}
