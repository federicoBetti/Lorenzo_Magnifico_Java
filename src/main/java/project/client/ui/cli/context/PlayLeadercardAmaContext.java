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
public class PlayLeadercardAmaContext extends AbstractContext {
    List<LeaderCard> leaderCards;

    public PlayLeadercardAmaContext(Cli cli, List<LeaderCard> leaderCards) {
        super(cli);
        this.leaderCards = leaderCards;

        map.put(CliConstants.SHOW_LEADER_CARDS, this::showLeaderCards );
        map.put(CliConstants.EXIT, cli::actionOk);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    //todo
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
        pRed.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());
        pRed.println("The main action is:");
        pYellow.println("Type the leader card's name to play:\n[leaderCardName]");

    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseLeaderCardToPlay(action);
        cli.actionOk();
    }
}
