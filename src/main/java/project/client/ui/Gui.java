package project.client.ui;

import javafx.application.Application;
import project.client.ui.cli.InputException;
import project.client.ui.gui.controller.LoginBuilder;
import project.client.ui.gui.controller.MainController;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;

public class Gui extends AbstractUI {
    LoginBuilder loginBuilder;
    ClientSetter clientSetter;
    MainController mainController;

    public Gui(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    @Override
    public void startUI() {
        mainController = MainController.getInstance();
        mainController.setClientSetter(clientSetter);
        Application.launch(LoginBuilder.class);
    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {
        mainController.takeBonusCard(towerAction.getKindOfCard(),"ciao" ); //todo towerAction.printBonusAction());
    }

    @Override
    public void bothPaymentsAvailable() {
        mainController.bothPaymentAvaiable();
    }


    @Override
    public void mainContext() {
    }

    @Override
    public void choicePe() {
        mainController.choosePermanentEffect();
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
    public void askForPraying() {
        mainController.askForPraying();
    }

    @Override
    public void actionOk() {
        mainController.endTurnContext();
    }

    @Override
    public void cantDoAction() {
        mainController.sendChat("you can't perform this action");
    }

    public void startGame(int numberOfPlayer) {
        mainController.showMainGame();
    }

    public void boardUpdate() {
        mainController.boardUpdate();
    }

    public void scoreUpdate() {
        mainController.scoreUpdate();
    }

    public void personalBoardUpdate() {
        mainController.personalBoardUpdate();
    }

    public void familyMemberUpdate() {
        mainController.familyMemberUpdate();

    }

    @Override
    public void nicknameAlreadyUsed() {

    }

    @Override
    public void skipTurn() {

    }

    @Override
    public void waitingForYourTurn() {

    }

    @Override
    public void setConnectionType(String kindOfConnection) throws InputException {

    }

    @Override
    public void loginSucceded() {

    }
}
