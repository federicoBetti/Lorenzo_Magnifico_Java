package project.client.ui;

import javafx.application.Application;
import project.client.ui.gui.login.LoginBuilder;
import project.client.ui.gui.maingame.MainGameBuilder;
import project.messages.TowerAction;

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
    public void mainContext() {
    }
}
