package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.messages.TowerAction;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class TakeBonusCard extends AbstractContext {
    TowerAction towerAction;
    TowersContext supportContext;

    public TakeBonusCard(Cli cli, TowerAction towerAction) {
        super(cli);
        this.towerAction = towerAction;
        map.put(CliConstants.EXIT, this::exitFromBonus );

        try {
            supportContext = new TowersContext(cli);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InputException e) {
            e.printStackTrace();
        }

    }

    private void exitFromBonus() throws IOException, ClassNotFoundException, InputException {
        exit();
        cli.sendExitToBonusAction();
    }

    private void showTowers() {
        cli.showTowers();
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are: \ntake the bonus card with these characteristics:");
        towerAction.printBonusAction();

        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException, ClassNotFoundException {
        supportContext.mainContextMethod(action);
    }
}
