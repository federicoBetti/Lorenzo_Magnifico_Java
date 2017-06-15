package project.client.ui;

import project.client.ui.cli.InputException;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TowerAction;

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

    public abstract void choicePe();

    public abstract void sendChoicePe(String input) throws InputException, IOException, ClassNotFoundException;

    public abstract void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv);

    public abstract void bonusHarvesterParameters(String input) throws InputException, IOException, ClassNotFoundException;

    public abstract void bonusProduction(BonusProductionOrHarvesterAction bonusProd) throws InputException, IOException, ClassNotFoundException;

    public abstract void bonusProductionParameters(String action) throws InputException, IOException, ClassNotFoundException;
}
