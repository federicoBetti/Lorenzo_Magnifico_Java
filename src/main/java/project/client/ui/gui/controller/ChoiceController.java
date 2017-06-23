package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by federico on 19/06/17.
 */
public class ChoiceController {
    public Button buttonChoiche1;
    public Button buttonChoiche2;
    public Label message;

    MainController mainController;
    private LoginBuilder loginBuilder;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }



    public void choice1(ActionEvent actionEvent) {

        loginBuilder.setChoiceDone(1);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void choice2(ActionEvent actionEvent) {
        loginBuilder.setChoiceDone(2);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void setLabel(String message) {
        this.message.setText(message);
    }

    public void setChoice1(String choice1) {
        buttonChoiche1.setText(choice1);
        buttonChoiche1.
    }

    public void setCoiche2(String choice2) {
        buttonChoiche2.setText(choice2);
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }
}
