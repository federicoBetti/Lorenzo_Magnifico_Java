package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.model.Tower;

import java.io.IOException;

/**
 * Created by raffaelebongo on 22/06/17.
 */
public class ShowTowersContext extends AbstractContext {
    Tower[][] towers;

    public ShowTowersContext(Cli cli, Tower[][] towers) {
        super(cli);
        map.put(CliConstants.SHOW_TOWERS_CARDS_COST, this::showTowersCardCost);
        map.put(CliConstants.SHOW_TOWERS_CARDS_EFFECTS, this:: showTowersCardEffects );
        this.towers = towers;
    }

    private void showTowersCardEffects() {
    }

    private void showTowersCardCost() {

    }

    @Override
    public void printHelp() {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
