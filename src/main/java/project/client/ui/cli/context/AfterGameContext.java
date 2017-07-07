package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 03/07/17.
 */
public class AfterGameContext extends AbstractContext {
    public AfterGameContext(Cli cli) {
        super(cli);
        map.put(Constants.SHOW_RANKING, this::showRanking );
        map.put(Constants.SHOW_STATISTICS, this:: showStatistic );
        map.put(Constants.NEW_GAME, this::newGame);
        map.put(Constants.CLOSE_THE_GAME, this:: terminate );
        printHelp();
    }

    private void terminate() {
        cli.terminate();
    }

    private void newGame() {
        cli.newGameRequest();
    }

    private void showStatistic() {
        cli.showStatistic();
    }

    private void showRanking() {
        cli.showRanking();
    }

    @Override
    public void printHelp() {
        pRed.println("The match is finished! What do you want to do now?");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
    }

    @Override
    public void checkValidInput(String input) throws InputException {
    }

    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
