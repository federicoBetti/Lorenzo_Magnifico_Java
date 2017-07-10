package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class is a context opened when the player wants to play a leader card
 */
public class LeaderCardContext extends AbstractContext {
    List<LeaderCard> myLeadersCard;

    /**
     * Constrcutor
     *
     * @param cli cli's reference
     * @param myLeadersCard list of my leader cards
     */
    public LeaderCardContext(Cli cli, List<LeaderCard> myLeadersCard){
        super(cli);
        this.myLeadersCard = myLeadersCard;
        map.put(CliConstants.SHOW_LEADER_CARDS, this::showLeaderCards );
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.print("Choose the "); pRed.print("[leaderCardName] ");pYellow.println("that you want to play.");

    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        boolean cardExist = false;
        for ( LeaderCard card : myLeadersCard ){
            if ( card.getName().equals(input))
                cardExist = true;
        }
        if ( !cardExist )
            throw new InputException();
    }

    /**
     * This method prints the player's leader cards
     */
    private void showLeaderCards() {
        super.showLeaderCardsAbstract(myLeadersCard);
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls chooseLeaderCardToPlay
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseLeaderCardToPlay(action);
    }
}
