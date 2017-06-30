package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MainContext extends AbstractContext {

    public MainContext(Cli cli){
        super(cli);
        cli.setFirstRound(false);
        //map.put(CliConstants.CHAT, this:: chat );
        //map.put(CliConstants.GAME_REPORT, this::gameReport);
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
        printHelp();
    }

    private void marketContext() {
        cli.marketContext();
    }

    private void dLeaderCard() {
        cli.discardLeaderCardContext();
    }

    private void leaderCard() {
        cli.leaderCardContext();
    }

    private void harvester() {
        cli.harvester();
    }

    private void production() {
        cli.production();
    }

    private void goToCouncil() {
        cli.goToCouncil();
    }

    @Override
    public void printHelp() {
        pRed.println("You are in the main context! The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());
        pRed.println("Type a command");
    }

    private void takeDevCard() {
        cli.takeDevCard();
    }

    private void jumpTurn() {
        cli.skipTurn();
    }


    private void showExcomunicationsTiles() {
        cli.showExcomunicationsTiles();
    }

    private void showPersonalBoard() {
        cli.showPersonalBoard();
        pYellow.print("Type ");pRed.print("[help] ");pYellow.println("for watch the list of commands available");
    }

    private void showDicesValue() {
        cli.showDicesValue();
    }

    private void showPoints() {
        cli.showPoints();
    }

  /*  private void gameReport() {
        //to implement
    }


    private void chat(){
        //to implement
    }
    */


    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }

}
