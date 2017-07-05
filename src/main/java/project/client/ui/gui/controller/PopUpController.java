package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by federico on 24/06/17.
 */
public class PopUpController {

    @FXML
    private Label message;
    private MainController mainController;

    public void setLabel(String labelMessage) {
        message.setText(labelMessage);
    }


    public void ok(ActionEvent actionEvent) {
        Stage stage = (Stage) message.getScene().getWindow();
        if (message.getText().equals("you are disconnected, click ok to reconnect"))
            mainController.reconnect();
        stage.hide();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
