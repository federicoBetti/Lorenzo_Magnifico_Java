package project.client.ui;

import project.messages.TowerAction;

import java.io.IOException;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public abstract class AbstractUI {

    int ciao;

    public void startUI() {
        //to implement
    }

    public abstract void takeBonusCard(TowerAction towerAction);

    public abstract void mainContext();
}
