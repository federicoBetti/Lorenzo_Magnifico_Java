package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by federico on 08/06/17.
 */
public class InitialLogin {
    @FXML
    private  TextField username;
    @FXML
    private  TextField password;
    @FXML
    private  RadioButton socket;
    @FXML
    private  RadioButton rmi;
    @FXML
    private  Button connect;
    @FXML
    private  ToggleGroup connection;
    @FXML
    private  Button aia;
    private int i = 0;

    private String connectionType;
    private String usernameChosen;
    private String passwordChosen;

    private MainController mainController;
    private LoginBuilder loginBuilder;


    public InitialLogin(){
        connectionType = "RMI";
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni

    public void initialize(){
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setInitialLoginController(this);
    }

    public void socketClicked() {
        connectionType = "socket";
    }

    public void rmiClicked() {
        connectionType = "RMI";
    }

    public void doConnection() {
            usernameChosen = username.getText();
            System.out.println("invio richiesta connessione");
            mainController.setConnectionType(connectionType,usernameChosen);
    }


    public void nicknameUsed() {
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usernameChosen = username.getText();
                mainController.takeNickname(usernameChosen);
            }
        });
    }
}
