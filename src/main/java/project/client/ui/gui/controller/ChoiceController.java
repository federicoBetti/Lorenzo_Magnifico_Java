package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by federico on 19/06/17.
 */
public class ChoiceController {
    @FXML
    private  Button buttonChoiche1;
    @FXML
    private  Button buttonChoiche2;
    @FXML
    private  Label message;

    private MainController mainController;
    private LoginBuilder loginBuilder;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setChoicheController(this);
    }



    public void choice1(ActionEvent actionEvent) {

        loginBuilder.setChoiceDone(0);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void choice2(ActionEvent actionEvent) {
        loginBuilder.setChoiceDone(1);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void closeStage(){
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        mainController.setChoicheController(null);
        stage.hide();
    }

    public void setLabel(String message) {
        this.message.setText(message);
    }

    public void setChoice1(String choice1) {
        buttonChoiche1.setText(choice1);
    }

    public void setCoiche2(String choice2) {
        buttonChoiche2.setText(choice2);
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }
}
