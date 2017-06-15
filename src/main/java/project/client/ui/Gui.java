package project.client.ui;

import javafx.application.Application;
import project.client.ui.cli.InputException;
import project.client.ui.gui.login.LoginBuilder;
import project.client.ui.gui.maingame.MainGameBuilder;
import project.messages.TowerAction;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class Gui extends AbstractUI {
    LoginBuilder loginBuilder;
    ClientSetter clientSetter;

    public Gui(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    @Override
    public void startUI() {
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
    public void sendChoicePe(String input) throws InputException, IOException, ClassNotFoundException {

    }

    public void startGame(int numberOfPlayer) {


        Application.launch(MainGameBuilder.class);
        MainGameBuilder.setClientSetter(clientSetter);
        MainGameBuilder.initializeMainGame(numberOfPlayer);
    }
}
