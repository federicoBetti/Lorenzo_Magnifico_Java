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
        pBlue.println("type [leaderCardName] among these leaders avaible:\n");
        for ( LeaderCard leaderCard : leaders ) {
            pRed.println(leaderCard.getName());
            pBlue.print("Requirements: ");pYellow.println(leaderCard.getRequirementsDescription());
            pBlue.print("Effect Description:");pYellow.println(leaderCard.getCardDescription());
            pYellow.println("");
        }
    }

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

    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {

    }
}
