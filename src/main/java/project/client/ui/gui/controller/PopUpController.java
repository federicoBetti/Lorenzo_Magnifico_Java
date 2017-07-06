package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by federico on 24/06/17.
 */
public class PopUpController {

    public HBox boxMessage;
    public Button button;
    public HBox boxButton;
    private Text message;
    private MainController mainController;

    public void setLabel(String labelMessage) {
        message = new Text(labelMessage);
        message.setFont(new Font("Lucida Blackletter", 25.0));
        boxMessage.getChildren().add(message);
    }


    public void ok() {
        Stage stage = (Stage) message.getScene().getWindow();
        if (message.getText().equals("you are disconnected, click ok to reconnect"))
            mainController.reconnect();
        stage.hide();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        setButton();
    }

    private void setButton() {
        button = new Button("ok");
        button.setFont(new Font("Lucida Blackletter", 25.0));
        button.setOnAction(event -> ok());
        boxButton.getChildren().add(button);
    }
}
