package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.controller.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MainContext extends AbstractContext {
    Map<String,Actioner> map;

    public MainContext(Cli cli){
        this.cli = cli;
        map = new HashMap<>();
        map.put(CliConstants.CHAT, this::chat );
        map.put(CliConstants.SHOW_ALL_PLAYERS, this::showAllPlayers );
        map.put(CliConstants.GAME_REPORT, this::gameReport);
        map.put(CliConstants.SHOW_POINTS, this::showPoints );
        map.put(CliConstants.SHOW_TOWERS, this:: showTowers );
        map.put(CliConstants.SHOW_HARVESTER_ZONE, this::showHarvesterZone );
        map.put(CliConstants.SHOW_PRODUCTION_ZONE, this:: showProductionZone );
        map.put(CliConstants.SHOW_COUNCIL_ZONE, this:: showCouncilZone );
        map.put(CliConstants.SHOW_MARKET_ZONE, this:: showMarketZone );
        map.put(CliConstants.SHOW_EXCOMUNICATION_TILES, this:: showExcomunicationsTiles );
        map.put(CliConstants.SHOW_PERSONAL_BOARD, this:: showPersonalBoard );
        map.put(CliConstants.SHOW_LEADER_CARDS, this:: showLeaderCards );
        map.put(CliConstants.SHOW_DICES_VALUE, this::showDicesValue );
        map.put(CliConstants.JUMP_TURN, this::jumpTurn );
        map.put(Constants.TAKE_DEV_CARD, this::takeDevCard );

    }

    private void takeDevCard() {
        cli.takeDevCard();
    }

    public void jumpTurn() {
    }

    public void showTowers() {
        //to implement
    }

    public void showProductionZone() {
        //to implement
    }

    public void showCouncilZone() {
        //to implement
    }

    public void showMarketZone() {
        //to implement
    }

    public void showExcomunicationsTiles() {
        //to implement
    }

    public void showPersonalBoard() {
        //to implement
    }

    public void showLeaderCards() {
        //to implement
    }

    public void showDicesValue() {
        //to implement
    }

    public void showPoints() {
        //to implement
    }

    public void showHarvesterZone() {
        //to implement
    }

    public void gameReport() {
        //to implement
    }

    public void showAllPlayers() {
        //to implement
    }

    void chat(){
        //to implement
    }

    public void login(){};

}
