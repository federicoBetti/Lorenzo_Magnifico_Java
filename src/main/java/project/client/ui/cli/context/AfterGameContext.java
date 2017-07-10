package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Context opened when the match is finished
 */
public class AfterGameContext extends AbstractContext {

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    public AfterGameContext(Cli cli) {
        super(cli);
        map.put(Constants.SHOW_RANKING, this::showRanking );
        map.put(Constants.SHOW_STATISTICS, this:: showStatistic );
        map.put(Constants.NEW_GAME, this::newGame);
        map.put(Constants.CLOSE_THE_GAME, this:: terminate );
        printHelp();
    }
    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("The match is finished! What do you want to do now?");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
    }

    /**
     * This method calls terminate on the cli
     */
    private void terminate() {
        cli.terminate();
    }

    /**
     * This method calls showStatistic on the cli
     */
    private void showStatistic() {
        cli.showStatistic();
    }


    /**
     * If the string in input does not correspond with no key, this method is called and it calls printHelp
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    protected void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }

    /**
     * This method calls showRanking on the cli
     */
    private void showRanking() {
        cli.showRanking();
    }


    /**
     * This method calls newGame on the cli
     */
    private void newGame() {
        cli.newGameRequest();
    }



    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
    }

}
