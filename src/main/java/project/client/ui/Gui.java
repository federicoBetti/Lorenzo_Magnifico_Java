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
    public void loginRequest(String loginParameter)  {

    }

    @Override
    public void discardLeaderCard(String name)  {

    }

    @Override
    public void prayOrNot(String action)  {

    }

    @Override
    public void mainContext() {
    }

    @Override
    public void choicePe() {

    }

    @Override
    public void sendChoicePe(String input)  {

    }

    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {

    }

    @Override
    public void bonusHarvesterParameters(String input)  {

    }

    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd)  {

    }

    @Override
    public void bonusProductionParameters(String action)  {

    }

    @Override
    public void takeBonusCardParameters(String action)  {

    }

    @Override
    public void immediatePriviledgeAction(String action)  {

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
}
