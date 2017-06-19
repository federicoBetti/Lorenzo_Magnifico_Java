package project.client.ui;

import javafx.application.Application;
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
        mainController.takeBonusCard(towerAction.getKindOfCard(),towerAction.printBonusAction());
    }

    @Override
    public void bothPaymentsAvailable() {

    }


    @Override
    public void mainContext() {
    }

    @Override
    public void choicePe() {

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

    }

    @Override
    public void cantDoAction() {

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
}
