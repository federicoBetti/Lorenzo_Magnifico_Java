package project.client.ui;

import javafx.application.Application;
import javafx.application.Platform;
import project.PlayerFile;
import project.client.ui.cli.InputException;
import project.client.ui.gui.controller.LoginBuilder;
import project.client.ui.gui.controller.MainController;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.Tile;

import java.awt.*;
import java.io.*;
import java.util.List;

public class Gui extends AbstractUI {
    private ClientSetter clientSetter;
    private MainController mainController;
    private boolean matchStarted;

    public Gui(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    @Override
    public void startUI() {
        matchStarted = false;
        mainController = MainController.getInstance();
        mainController.setClientSetter(clientSetter);
        uploadFont();
        Application.launch(LoginBuilder.class);
        Platform.setImplicitExit(true);
    }

    private void uploadFont() {


        InputStream is = getClass().getResourceAsStream("/customFont/Aro.TTF");

        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {
        mainController.takeBonusCard(towerAction.getKindOfCard(),towerAction.toString() );
    }

    //todo ho rinominato both payment availableRMI con bothPaymentAvailable visto che lo dovevo usare pure io ma mi sa che ce l'avevi già
    //todo cmq ce l'avevi in todo sotto. non ho cancellato nulla, solo cambiato il nome. controlla un secondo la situa.
   /* @Override
    public void bothPaymentsAvailable() {
        mainController.bothPaymentAvaiable();
    }   */


    @Override
    public void mainContext() {
        //chiamato quando è il tuo turno
        mainController.startTurn();
    }

    @Override
    public int choicePe() {
        return mainController.choosePermanentEffect();
    }


    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        mainController.bonusHarvester(bonusHarv.getDiceValue());
    }


    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd)  {
        mainController.bonusProduction(bonusProd.getDiceValue());
    }


    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {
        mainController.takePrivilege(privilegesAction.getQuantityOfDifferentPrivileges());
    }

    @Override
    public int askForPraying() {
        return mainController.askForPraying();
    }

    @Override
    public void actionOk() {
        mainController.endTurnContext();
    }

    @Override
    public void cantDoAction() {
        mainController.cantDoAction();
    }

    public void startGame(int numberOfPlayer) {
        mainController.showMainGame();
    }

    public void boardUpdate(Updates update) {
        mainController.boardUpdate();
    }


    @Override
    public void scoreUpdate(Updates updates) {
        mainController.scoreUpdate();

    }

    @Override
    public void personalBoardUpdate(Updates updates) {
        mainController.personalBoardUpdate();

    }

    @Override
    public void familyMemberUpdate(Updates updates) {
        mainController.familyMemberUpdate();

    }

    @Override
    public void nicknameAlreadyUsed() {
        mainController.nicknameAlreadyUsed();
    }

    @Override
    public void skipTurn() {

    }

    @Override
    public void waitingForYourTurn() {
        mainController.waitForYourTurn();
    }

    @Override
    public void setConnectionType(String kindOfConnection) throws InputException {

    }

    @Override
    public void goToLogin() {
        mainController.waitingLogin();
      //  mainController.takeNickname(usernameChosen);
    }

    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        mainController.matchStarted(roomPlayers,familyColour);
    }

    @Override
    public int tileDraft(List<Tile> tiles) {
        return mainController.tileDraft(tiles);
    }

    @Override
    public void newNickname(String nickname) {
        // //lo devo fare per reiterare la richiesta di un nuovo nickname. non posso in socket rimandare lo stesso
        //metodo di login perchè di default manda la stringa LOGIN_REQUEST e devo mandare solo il nickname
    }

    @Override
    public void prayed() {
        mainController.prayed();
    }

    @Override
    public void excommunicationTaken(ExcommunicationTaken update) {
        mainController.excommunicationTaken(update);
    }

    @Override
    public void timerDelayed() {
        mainController.timerDelayed();
    }

    @Override
    public void reconnect() {

    }

    @Override
    public void afterGame() {
        mainController.afterGame();
        clientSetter.showStatistic();
        clientSetter.showRanking();
    }

    @Override
    public void newGameRequest() {

    }

    @Override
    public void receiveStatistics(PlayerFile statistics) {
        mainController.setStatistics(statistics);
    }

    @Override
    public void ranking(List<PlayerFile> ranking) {
        mainController.setRanking(ranking);
    }

    @Override
    public void disconnesionMessage(String message) {

    }

    @Override
    public void winnerComunication(String winner) {
        if (winner.equals(clientSetter.getNickname()))
            mainController.youWin();
        else
            mainController.youLose();
    }

    @Override
    public void loginSucceded() {
        mainController.loginSucceeded();
    }

    public String draftChoice(List<String> leaderName){
        //return mainController.startDraft(leaderName);
        return null;
    }
    @Override
    public int bothPaymentsAvailable() {
        return mainController.bothPaymentAvaiable();
    }


    @Override
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        return mainController.getLeaderCardChosen(leaders);
    }


}
