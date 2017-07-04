package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.BuildingCard;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */
public class BonusProductionContext extends AbstractContext {
    private BonusProductionOrHarvesterAction bonusProd;
    List<BuildingCard> myBuildingCards;

    public BonusProductionContext(BonusProductionOrHarvesterAction bonusProd, Cli cli, List<BuildingCard> myBuildingCards ) {
        super(cli);
        this.bonusProd = bonusProd;
        this.myBuildingCards = myBuildingCards;
        bonusProd.actionString();
        printHelp();
    }

    @Override
    public void printHelp() {

        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println(bonusProd.actionString());
    }

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

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusProductionParameters(action);
    }
}
