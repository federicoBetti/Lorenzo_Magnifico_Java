package project.client.ui;

import javafx.application.Application;
import project.client.ui.cli.InputException;
import project.client.ui.gui.login.LoginBuilder;
import project.client.ui.gui.maingame.MainGameBuilder;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class Gui extends AbstractUI {
    LoginBuilder loginBuilder;

    public Gui(ClientSetter clientSetter) {
        super();
        LoginBuilder.setClientSetter(clientSetter);
        Application.launch(LoginBuilder.class);
    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {

    }

    @Override
    public void bothPaymentsAvailable() {

    }

    @Override
    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {

    }

    @Override
    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {

    }

    @Override
    public void prayOrNot(String action) throws IOException, ClassNotFoundException {

    }

    @Override
    public void mainContext() {
    }

    @Override
    public void choicePe() {

    }

    @Override
    public void sendChoicePe(String input) throws  IOException, ClassNotFoundException {

    }

    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {

    }

    @Override
    public void bonusHarvesterParameters(String input) throws  IOException, ClassNotFoundException {

    }

    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) throws IOException, ClassNotFoundException {

    }

    @Override
    public void bonusProductionParameters(String action) throws  IOException, ClassNotFoundException {

    }

    @Override
    public void takeBonusCardParameters(String action) throws InputException, IOException, ClassNotFoundException {

    }

    @Override
    public void immediatePriviledgeAction(String action) throws InputException, IOException, ClassNotFoundException {

    }

    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {

    }
}
