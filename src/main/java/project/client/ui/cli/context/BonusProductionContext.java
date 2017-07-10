package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.BuildingCard;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class represent a context opened when there is a bonus production to do
 */
public class BonusProductionContext extends AbstractContext {
    private BonusProductionOrHarvesterAction bonusProd;
    List<BuildingCard> myBuildingCards;

    /**
     * Constructor
     *
     * @param bonusProd object that contains the characteristics of the bonus production
     * @param cli cli's reference
     * @param myBuildingCards list of my buildings card
     */
    public BonusProductionContext(BonusProductionOrHarvesterAction bonusProd, Cli cli, List<BuildingCard> myBuildingCards ) {
        super(cli);
        map.put(CliConstants.SHOW_BUILDING_CARDS, this:: showBuildingCards );
        this.bonusProd = bonusProd;
        this.myBuildingCards = myBuildingCards;
        bonusProd.actionString();
        printHelp();
    }

    /**
     * This method prints the player's building cards
     */
    private void showBuildingCards() {
        int count1 = 1;
        for (BuildingCard card : myBuildingCards) {
            pBlue.print(count1 + ") Card name: ");
            pRed.println(card.getName());
            pBlue.println("Permanent Effects: ");
            int count2 = 1;
            for (Effects effect : card.getPermanentCardEffects()) {
                pBlue.print(count2 + ") ");
                pYellow.println(effect.toScreen());
                count2++;
            }
            count1++;
        }
    }

    /**
     * This method print the help context's menu
     */
    @Override
    public void printHelp() {

        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println(bonusProd.actionString());
        pRed.print("buildingCard: ");pYellow.println("name1 - name2 - ... ");
        pYellow.println("");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");
        boolean cardExist = false;
        for ( int i = 0; i < parameters.length; i++ ) {
            for (BuildingCard card : myBuildingCards) {
                if (card.getName().equals(parameters[i]))
                    cardExist = true;
            }
            if (cardExist)
                cardExist = false;
            else
                throw new InputException();
        }
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls bonusProductionParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusProductionParameters(action);
    }
}
