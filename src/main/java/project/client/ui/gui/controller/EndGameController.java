package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * this is the controller of the end game scene
 */
public class EndGameController {

    @FXML
    private ImageView sfondo;
    @FXML
    private Text winner;
    private LoginBuilder loginBuilder;
    private MainController mainController;

    /**
     * method called by new game button to start a new game
     */
    public void newGame() {
        mainController.newGame();
    }

    /**
     * setter
     * @param loginBuilder parameter to set
     */
    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    /**
     * setter
     * @param mainController parameter to set
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * method used to upload images on the scene
     */
    public void uploadImages() {
        sfondo.setImage(new Image(String.valueOf(getClass().getResource("/endMatchFile/ponteVecchio.jpg"))));
        if (mainController.isWin())
            winner.setText("YOU WIN!!");
        else
            winner.setText("you loose...");

    }

    /**
     * method used to close the window and the match
     */
    public void close() {
        mainController.terminate();
    }

    /**
     * method called to show statistics
     */
    public void showStatistics() {
        loginBuilder.showStatistics();
    }

    /**
     * method called to show rankings
     */
    public void showStandings() {
        loginBuilder.showStandings();
    }

    /**
     * method called to show thanksgiving
     */
    public void ringraziamenti() {
        loginBuilder.showThanksgiving();
    }
}
