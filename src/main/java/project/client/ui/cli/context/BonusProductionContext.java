package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.messages.BonusProductionOrHarvesterAction;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */
public class BonusProductionContext extends AbstractContext {
    BonusProductionOrHarvesterAction bonusProd;

    public BonusProductionContext(BonusProductionOrHarvesterAction bonusProd, Cli cli) {
        super(cli);
        this.bonusProd = bonusProd;
        bonusProd.actionString();
        printHelp();
    }

    @Override
    public void printHelp() {

        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey().toString());

        pRed.println("The main action is:");
        pYellow.println(bonusProd.actionString());
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.bonusProductionParameters(action);
    }
}
