package project.client.ui.gui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by federico on 08/06/17.
 */
public class LoginIniziale {
    public TextField username;
    public TextField password;
    public RadioButton socket;
    public RadioButton rmi;
    public Button connect;
    public ToggleGroup connection;
    public Button aia;

    private ConnectionType connectionType;
    private String usernameChosen;
    private String passwordChosen;

    private LoginBuilder mainController;


    public LoginIniziale(){
        System.out.print("sono nel controller");
        connectionType = ConnectionType.RMI;
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    private void initialize(){
    }

    public void setMainController(LoginBuilder mainController) {
        this.mainController = mainController;
    }

    public void socketClicked(ActionEvent actionEvent) {
        connectionType = ConnectionType.SOCKET;
    }

    public void rmiClicked(ActionEvent actionEvent) {
        connectionType = ConnectionType.RMI;
    }

    public void doConnection(ActionEvent actionEvent) {
        usernameChosen = username.getText();
        passwordChosen = password.getText();
        username.setText("ciaovecchio");
        password.setText("ciaomerda");
        mainController.setScene(Finestre.SECONDO);
        //connectClient(usernameChosen,passwordChosen,connectionType);
    }


}
