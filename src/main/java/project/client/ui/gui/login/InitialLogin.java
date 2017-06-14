package project.client.ui.gui.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by federico on 08/06/17.
 */
public class InitialLogin {
    public TextField username;
    public TextField password;
    public RadioButton socket;
    public RadioButton rmi;
    public Button connect;
    public ToggleGroup connection;
    public Button aia;

    private String connectionType;
    private String usernameChosen;
    private String passwordChosen;

    private LoginBuilder mainController;


    public InitialLogin(){
        connectionType = "rmi";
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    private void initialize(){
    }

    public void setMainController(LoginBuilder mainController) {
        this.mainController = mainController;
    }

    public void socketClicked() {
        connectionType = "socket";
    }

    public void rmiClicked() {
        connectionType = "rmi";
    }

    public void doConnection() {
        usernameChosen = username.getText();
        passwordChosen = password.getText();
        username.setText("ciaovecchio");
        password.setText("ciaomerda");
        mainController.setConnectionType(connectionType);
        mainController.connect(usernameChosen,passwordChosen);
        mainController.switchScene();
        //connectClient(usernameChosen,passwordChosen,connectionType);
    }


}
