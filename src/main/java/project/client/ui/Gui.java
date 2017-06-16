package project.client.ui;

import javafx.application.Application;
import project.client.ui.cli.InputException;
import project.client.ui.gui.controller.LoginBuilder;
import project.client.ui.gui.controller.MainController;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
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
        //verso ui
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

    }


    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd)  {

    }


    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {

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
}
