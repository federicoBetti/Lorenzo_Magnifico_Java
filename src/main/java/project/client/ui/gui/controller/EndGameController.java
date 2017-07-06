package project.client.ui.gui.controller;

import javafx.event.ActionEvent;

/**
 * this is the controller of the end game scene
 */
public class EndGameController {
    private LoginBuilder loginBuilder;
    private MainController mainController;

    public void newGame(ActionEvent actionEvent) {
        mainController.newGame();
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void uploadImages() {

    }
}
