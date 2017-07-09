package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Ccontroller for popUps
 */
public class PopUpController {

    @FXML
    private HBox boxMessage;
    @FXML
    private HBox boxButton;
    private Text message;
    private MainController mainController;

    /**
     * method that sets the message in the main label
     * @param labelMessage message
     */
    public void setLabel(String labelMessage) {
        message = new Text(labelMessage);
        message.setFont(new Font("Lucida Blackletter", 25.0));
        boxMessage.getChildren().add(message);
    }

    /**
     * method that close the stage, in case this is a stage that notify the player disconnection, it notify to the server that the player wants to reconnect
     */
    public void ok() {
        Stage stage = (Stage) message.getScene().getWindow();
        if (message.getText().equals("you are disconnected, click ok to reconnect"))
            mainController.reconnect();
        stage.hide();
    }

    /**
     * setter
     * @param mainController main controller used to communicate with the client setter
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        setButton();
    }

    /**
     * set button in the middle of the stage
     */
    private void setButton() {
        Button button = new Button("ok");
        button.setFont(new Font("Lucida Blackletter", 25.0));
        button.setOnAction(event -> ok());
        boxButton.getChildren().add(button);
    }
}
