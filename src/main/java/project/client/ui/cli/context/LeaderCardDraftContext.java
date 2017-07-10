package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;

import java.io.IOException;
import java.util.List;

/**
 * This class is a context opened during the leader cards' draft
 */
public class LeaderCardDraftContext extends AbstractContext {
    List<LeaderCard> leaders;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param leaders leader cards's list
     */
    public LeaderCardDraftContext(Cli cli, List<LeaderCard> leaders) {
        super(cli);
        this.leaders = leaders;
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pBlue.println("Choose a leader card for the draft!");
        pBlue.println("type [leaderCardName] among these leaders avaible:\n");
        for ( LeaderCard leaderCard : leaders ) {
            pRed.println(leaderCard.getName());
            pBlue.print("Requirements: ");pYellow.println(leaderCard.getRequirementsDescription());
            pBlue.print("Effect Description:");pYellow.println(leaderCard.getCardDescription());
            pYellow.println("");
        }
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
        for ( LeaderCard card : leaders ){
            if ( card.getName().equals(input))
                cardExist = true;
        }
        if ( input.equals("-1"))
            return;

        if ( !cardExist )
            throw new InputException();
    }

    /**
     * If the string in input does not correspond with no key, this method is called
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {

    }
}
