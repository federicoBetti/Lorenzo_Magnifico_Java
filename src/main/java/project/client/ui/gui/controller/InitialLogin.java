package project.client.ui.gui.controller;

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
    }

    public void socketClicked() {
        connectionType = "socket";
    }

    public void rmiClicked() {
        connectionType = "RMI";
    }

    public void doConnection() {
        if (i == 0) {
            usernameChosen = username.getText();
            System.out.println("invio richiesta connessione");
            mainController.setConnectionType(connectionType,usernameChosen);
            i++;
        }else if (i==1){

            //loginBuilder.initalizeMainGame();
            System.out.println("ho finito di inizializzare");
            i++;
        }
        else {/*
            usernameChosen = username.getText();
            passwordChosen = password.getText();
            username.setText("ciaovecchio");
            password.setText("ciaomerda");
            loginBuilder.waitingScene();
            mainController.takeNickname();
            System.out.println("ciao");
            */
        }
    }


}
