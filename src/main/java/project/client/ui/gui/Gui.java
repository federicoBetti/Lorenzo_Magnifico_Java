package project.client.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import project.client.ui.AbstractUI;
import project.client.ClientSetter;
import project.server.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.gui.controller.LoginBuilder;
import project.client.ui.gui.controller.MainController;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.Tile;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * gui class that extends abstract ui. this is the class that launch javafx application and recive calls from ClientSetter
 */
public class Gui extends AbstractUI {
    private ClientSetter clientSetter;
    private MainController mainController;

    /**
     * constructor
     * @param clientSetter client setter
     */
    public Gui(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    /**
     * method calle dto launch UI
     */
    @Override
    public void startUI() {
        mainController = MainController.getInstance();
        mainController.setClientSetter(clientSetter);
        uploadFont();
        Application.launch(LoginBuilder.class);
        Platform.setImplicitExit(true);
    }

    /**
     * method used to upload a special font in the project
     */
    private void uploadFont() {
        try {
            //create the font to use. Specify the size!
            Font customFont = getFont("LucidaBlackletterRegular.ttf");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            UnixColoredPrinter.Logger.print(Constants.FONT_EXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * methos used to get fot
     * @param fileName
     * @return
     * @throws Exception
     */
    public Font getFont(String fileName) throws Exception {
        String path = "/customFont/" + fileName;
        URL url = getClass().getResource(path);
        return Font.createFont(Font.TRUETYPE_FONT, new File(url.toURI()));
    }

    /**
     * method that notify javafx application to make a bonus card action
     * @param towerAction
     */
    @Override
    public void takeBonusCard(TowerAction towerAction) {
        mainController.takeBonusCard(towerAction.getKindOfCard(),towerAction.toString() );
    }

    /**
     * method that notify client that it's his turn
     */
    @Override
    public void mainContext() {
        //chiamato quando è il tuo turno
        mainController.startTurn();
    }

    /**
     * method that asks for a choice on building's permanent effect
     * @return choice done
     */
    @Override
    public int choicePe() {
        return mainController.choosePermanentEffect();
    }

    /**
     * method that notify javafx application to make a bonus harvester action
     * @param bonusHarv parameter of the bonus action
     */
    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        mainController.bonusHarvester(bonusHarv.getDiceValue());
    }

    /**
     * method that notify javafx application to make a bonus production action
     * @param bonusProd parameter of the bonus action
     */
    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd)  {
        mainController.bonusProduction(bonusProd.getDiceValue());
    }

    /**
     * method that notify javafx application to make a bonus take privileges action
     * @param privilegesAction parameter of the bonus action
     */
    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {
        mainController.takePrivilege(privilegesAction.getQuantityOfDifferentPrivileges());
    }

    /**
     * method called to ask if pray or not
     * @return client answer
     */
    @Override
    public int askForPraying() {
        return mainController.askForPraying();
    }

    /**
     * method used to notify that the action has been done correctly
     */
    @Override
    public void actionOk() {
        mainController.endTurnContext();
    }

    /**
     * method used to notify that the action hasn't been done
     */
    @Override
    public void cantDoAction() {
        mainController.cantDoAction();
    }

    /**
     * method called when a board update arrive from the server
     * @param update update
     */
    public void boardUpdate(Updates update) {
        mainController.boardUpdate();
    }

    /**
     * method called when a score update arrive from the server
     * @param updates update
     */
    @Override
    public void scoreUpdate(Updates updates) {
        mainController.scoreUpdate();

    }

    /**
     * method called when a personal board update arrive from the server
     * @param updates update
     */
    @Override
    public void personalBoardUpdate(Updates updates) {
        mainController.personalBoardUpdate();

    }

    /**
     * method called when a family memebers update arrive from the server
     * @param updates update
     */
    @Override
    public void familyMemberUpdate(Updates updates) {
        mainController.familyMemberUpdate();

    }

    /**
     * method called when the nickname chosen is already used
     */
    @Override
    public void nicknameAlreadyUsed() {
        mainController.nicknameAlreadyUsed();
    }

    /**
     * method called when your turn is finished
     */
    @Override
    public void waitingForYourTurn() {
        mainController.waitForYourTurn();
    }

    /**
     * method called when the login has been done correctly
     */
    @Override
    public void goToLogin() {
        mainController.waitingLogin();
    }

    /**
     * method used to notify gui to initialize match scenes
     * @param roomPlayers number of players in the game
     * @param familyColour your familiar colour
     */
    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        mainController.matchStarted(roomPlayers,familyColour);
    }

    /**
     * method called to show tile draft
     * @param tiles tiles to draft
     * @return index of tile chosen
     */
    @Override
    public int tileDraft(List<Tile> tiles) {
        return mainController.tileDraft(tiles);
    }


    /**
     * method used to notify that the pray has been done successfully
     */
    @Override
    public void prayed() {
        mainController.prayed();
    }

    /**
     * method used to notify who has taken the excommunication
     * @param update update
     */
    @Override
    public void excommunicationTaken(ExcommunicationTaken update) {
        mainController.excommunicationTaken(update);
    }

    /**
     * method called when the timer is expired
     */
    @Override
    public void timerDelayed() {
        mainController.timerDelayed();
    }


    /**
     * method used to show after game scene
     */
    @Override
    public void afterGame() {
        mainController.afterGame();
        clientSetter.showStatistic();
        clientSetter.showRanking();
    }


    /**
     * method that has recived statistics from the server
     * @param statistics statistic object
     */
    @Override
    public void receiveStatistics(PlayerFile statistics) {
        mainController.setStatistics(statistics);
    }

    /**
     * method that has recived rankingsfrom the server
     * @param ranking ranking objects
     */
    @Override
    public void ranking(List<PlayerFile> ranking) {
        mainController.setRanking(ranking);
    }

    /**
     * method that notify client that someone is disconnected
     * @param message
     */
    @Override
    public void disconnesionMessage(String message) {
        mainController.disconnectionMessage(message);
    }

    /**
     * method used to notify who is the winner
     * @param winner winner name
     */
    @Override
    public void winnerComunication(String winner) {
        if (winner.equals(clientSetter.getNickname()))
            mainController.youWin();
        else
            mainController.youLose();
    }

    /**
     * method called when the login is succeed
     */
    @Override
    public void loginSucceded() {
        mainController.loginSucceeded();
    }

    /**
     * method called when the client have to chose between two different payment methods
     * @return client's choice
     */
    @Override
    public int bothPaymentsAvailable() {
        return mainController.bothPaymentAvaiable();
    }

    /**
     * method used for the leader draft
     * @param leaders leaders to draft
     * @return leader chosen
     */
    @Override
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        return mainController.getLeaderCardChosen(leaders);
    }


}
