package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.effects.realeffects.Effects;
import project.messages.BonusProductionOrHarvesterAction;
import project.model.Harvester;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */

public class BonusHarvesterContext extends AbstractContext {
    private BonusProductionOrHarvesterAction bonusHarv;
    List<TerritoryCard> territories;

    public BonusHarvesterContext(BonusProductionOrHarvesterAction bonusHarv, Cli cli, List<TerritoryCard> territories ) {
        super(cli);
        this.territories = territories;
        this.bonusHarv = bonusHarv;
        map.put(CliConstants.SHOW_TERRITORIES, this:: showTerritories );
        printHelp();
    }

    private void showTerritories() {

        int count1 = 1;
        for (TerritoryCard card : territories) {
            pRed.print(count1 + ") "); pBlue.println( "Card name: ");
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

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println(bonusHarv.actionString());

    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 1 ))
            throw new InputException();

        for ( int i = 0; i < parameters[0].length(); i++ ) {
                if (!Character.isDigit(parameters[0].charAt(i)))
                    throw new InputException();
        }
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusHarvesterParameters(action);
    }
}
