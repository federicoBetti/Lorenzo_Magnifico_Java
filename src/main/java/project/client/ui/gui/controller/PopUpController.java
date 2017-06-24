package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.controller.cardsfactory.LeaderCard;

import java.util.List;

/**
 * Created by federico on 24/06/17.
 */
public class PopUpController {

    @FXML
    private Label message;

    public void setLabel(String labelMessage) {
        message.setText(labelMessage);
    }


    public void ok(ActionEvent actionEvent) {
        Stage stage = (Stage) message.getScene().getWindow();
        stage.hide();
    }
}