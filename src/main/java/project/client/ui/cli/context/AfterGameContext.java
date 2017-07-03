package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;

/**
 * Created by raffaelebongo on 03/07/17.
 */
public class AfterGameContext extends AbstractContext {
    public AfterGameContext(Cli cli) {
        super(cli);
        map.put(Constants.SHOW_RANKING, this::showRanking );
        map.put(Constants.SHOW_STATISTICS, this:: showStatistic );
    }

    private void showStatistic() {
        cli.showStatistic();
    }

    private void showRanking() {

    }

    @Override
    public void printHelp() {
        pRed.println("You have finished your match! What do you want to do now?");

    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {

    }
}
