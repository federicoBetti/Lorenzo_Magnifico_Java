package project.client.ui;

import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.Updates;

import java.io.IOException;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public abstract class AbstractUI {

    public void startUI() {
        //to implement
    }

    public abstract void takeBonusCard(TowerAction towerAction);

    public abstract void bothPaymentsAvailable();

    public abstract void loginRequest(String loginParameter) throws IOException, ClassNotFoundException;

    public abstract void discardLeaderCard(String name) throws IOException, ClassNotFoundException;

    public abstract void prayOrNot(String action) throws IOException, ClassNotFoundException;

    public void mainContext(){}
}
