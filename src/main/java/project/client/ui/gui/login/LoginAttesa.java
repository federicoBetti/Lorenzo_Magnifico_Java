package project.client.ui.gui.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * Created by federico on 09/06/17.
 */
public class LoginAttesa {

    @FXML
    Button bottone;
    LoginBuilder mainController;

    public void setMainController(LoginBuilder mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void actionButton(){
        mainController.setScene(Finestre.PRIMO);
    }
}
