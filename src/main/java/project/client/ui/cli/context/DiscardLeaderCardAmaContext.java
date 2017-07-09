package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context opened when the player wants to discard a Leader card after the main action
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

    /**
     * This method prints the player's leader cards
     */
    private void showLeaderCards() {
        for ( LeaderCard leaderCard : leaderCards ) {
            pRed.println(leaderCard.getName());
            pBlue.print("Requirements: ");pYellow.println(leaderCard.getRequirementsDescription());
            pBlue.print("Effect Description:");pYellow.println(leaderCard.getCardDescription());
            pYellow.println("");
        }
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {

        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.print("Type the ");pRed.print("[leaderCardName] ");pYellow.print("that you want to discard");
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
     * If the string in input does not correspond with no key, this method is called and it calls discardLeaderCard and actionOk
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.discardLeaderCard(action);
        cli.actionOk();
    }
}
