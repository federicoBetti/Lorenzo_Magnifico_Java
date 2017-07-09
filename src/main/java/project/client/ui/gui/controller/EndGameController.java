package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * this is the controller of the end game scene
 */
public class EndGameController {
    public ImageView sfondo;
    public Text winner;
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
        sfondo.setImage(new Image(String.valueOf(getClass().getResource("/endMatchFile/ponteVecchio.jpg"))));
        if (mainController.isWin())
            winner.setText("YOU WIN!!");
        else
            winner.setText("you loose...");

    }

    public void close(ActionEvent actionEvent) {
    }

    public void showStatistics(ActionEvent actionEvent) {
        loginBuilder.showStatistics();
    }

    public void showStandings(ActionEvent actionEvent) {
        loginBuilder.showStandings();
    }

    public void ringraziamenti(ActionEvent actionEvent) {
        loginBuilder.ringraziamenti();
    }
}
