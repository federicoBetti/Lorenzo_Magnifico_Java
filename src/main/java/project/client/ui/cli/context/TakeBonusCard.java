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

    public TakeBonusCard(Cli cli, TowerAction towerAction) {
        super(cli);
        this.towerAction = towerAction;
        map.put(CliConstants.EXIT, this::exitFromBonus );
        printHelp();
    }

    private void exitFromBonus() throws IOException, InputException {
        exit();
        cli.sendExitToBonusAction();
    }

    private void showTowers() {
        cli.showTowers();
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:\ntake the bonus card with these characteristics:");
        towerAction.printBonusAction();
        pYellow.println("floor: 0, 1, 2, 3\n" +
                "tower colour: green, yellow, purple, blue");

        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if( parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) <= 3 )
            throw new InputException();

        if(!( parameters.length == 2 ))
            throw new InputException();

        checkTowerColour(parameters[1]);

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.takeBonusCardParameters(action);
    }
}
