package project.client.ui.gui.controller;

import javafx.scene.control.TextField;
import project.client.ui.ClientSetter;

/**
 * Created by federico on 15/06/17.
 */
public class MainController {
    private static MainController instance;
    private ClientSetter clientSetter;
    private LoginBuilder loginBuilder;
    private TextField chatText;


    private MainController(){

    }
    public static MainController getInstance(){
        if (instance == null) {
            instance = new MainController();
            return instance;
        }
        else
            return instance;
    }

    public void setClientSetter(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void switchScene(){
        loginBuilder.switchScene();
    }

    public void setConnectionType(String connectionType) {
        clientSetter.setConnectionType(connectionType);
    }

    public void connect(String usernameChosen, String passwordChosen) {
        clientSetter.connect(usernameChosen,passwordChosen);
    }

    public void showMainGame() {
        loginBuilder.startMainGame();
    }



    //TODO FARE DUE CONTROLLER DIVERSI, UNO PER COMUNCIARE CON L'ESTERNO E UNO ADIBITO A CAMBIARE SCHERMATE
}
