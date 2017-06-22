package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;

/**
 * Created by raffaelebongo on 20/06/17.
 */
public class WaitingForYourTurnContext extends AbstractContext {

    public WaitingForYourTurnContext(Cli cli) {
        super(cli);
        map.put(CliConstants.SHOW_COUNCIL_ZONE, this:: showCouncilZone);
        map.put(CliConstants.SHOW_DICES_VALUE, this:: showDicesValue);
        map.put(CliConstants.SHOW_EXCOMUNICATION_TILES, this::showExcomunicationsTiles);
        map.put(CliConstants.SHOW_HARVESTER_ZONE, this::showHarvesterZone);
        map.put(CliConstants.SHOW_MARKET_ZONE, this:: showMarketZone);
        map.put(CliConstants.SHOW_PRODUCTION_ZONE, this:: showProductionZone);
        map.put(CliConstants.SHOW_TOWERS, this:: showTowers);
        map.put(CliConstants.SHOW_LEADER_CARDS, this:: showLeaderCards);
        map.put(CliConstants.SHOW_PERSONAL_BOARD, this:: showPersonalBoard);
        map.put(CliConstants.SHOW_POINTS, this:: showPoints);
        printHelp();
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


    @Override
    public void printHelp() {
        pRed.println("Waiting for your turn...");
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        printHelp();
    }
}
