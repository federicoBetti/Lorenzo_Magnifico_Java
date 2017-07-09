package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * This class is a context opened when the player's turn starts
 */
public class MainContext extends AbstractContext {

    public MainContext(Cli cli){
        super(cli);
        cli.setFirstRound(false);
        map.put(CliConstants.SHOW_POINTS, this::showPoints );
        map.put(CliConstants.SHOW_EXCOMUNICATION_TILES, this:: showExcomunicationsTiles );
        map.put(CliConstants.SHOW_PERSONAL_BOARD, this:: showPersonalBoard );
        map.put(CliConstants.SHOW_DICES_VALUE, this::showDicesValue );
        map.put(Constants.SKIP_TURN, this::jumpTurn );
        map.put(Constants.TAKE_DEV_CARD, this::takeDevCard );
        map.put(Constants.GO_TO_COUNCIL_PALACE, this::goToCouncil);
        map.put(Constants.PRODUCTION, this::production );
        map.put(Constants.HARVESTER, this::harvester);
        map.put(Constants.PLAY_LEADER_CARD, this:: leaderCard );
        map.put(Constants.DISCARD_LEADER_CARD, this:: dLeaderCard );
        map.put(Constants.GO_TO_MARKET, this::marketContext );
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_TURNS, this:: showTurns );
        printHelp();
    }

    /**
     * This method calls showTurns on the cli
     */
    private void showTurns() {
        cli.showTurns();
    }

    /**
     * This method calls marketContext on the cli
     */
    private void marketContext() {
        cli.marketContext();
    }

    /**
     * This method calls dLeaderCard on the cli
     */
    private void dLeaderCard() {
        cli.discardLeaderCardContext();
    }

    /**
     * This method calls leaderCard on the cli
     */
    private void leaderCard() {
        cli.leaderCardContext();
    }

    /**
     * This method calls harvester on the cli
     */
    private void harvester() {
        cli.harvester();
    }

    /**
     * This method calls production on the cli
     */
    private void production() {
        cli.production();
    }

    /**
     * This method calls goToCouncil on the cli
     */
    private void goToCouncil() {
        cli.goToCouncil();
    }


    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("You are in the main context! The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
        pRed.println("Type a command");
    }

    /**
     * This method calls takeDevCard on the cli
     */
    private void takeDevCard() {
        cli.takeDevCard();
    }

    /**
     * This method calls jumpTurn on the cli
     */
    private void jumpTurn() {
        cli.skipTurn();
    }

    /**
     * This method calls showExcomunicationsTiles on the cli
     */
    private void showExcomunicationsTiles() {
        cli.showExcomunicationsTiles();
    }

    /**
     * This methods prints the informations contained in the personal boards
     */
    private void showPersonalBoard() {
        cli.showPersonalBoard();
        pYellow.print("Type ");pRed.print("[help] ");pYellow.println("for watch the list of commands available");
    }

    /**
     * This method calls showDicesValue on the cli
     */
    private void showDicesValue() {
        cli.showDicesValue();
    }

    /**
     * This method calls showPoints on the cli
     */
    private void showPoints() {
        cli.showPoints();
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

    /**
     * If the string in input does not correspond with no key, this method is called and it calls printHelp
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }

}
