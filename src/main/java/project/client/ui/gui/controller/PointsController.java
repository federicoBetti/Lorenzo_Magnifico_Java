package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.model.Score;

/**
 * controller of points stage
 */
public class PointsController {
    @FXML
    private Label faithLabel;
    @FXML
    private Label victoryLabel;
    @FXML
    private Label militaryLabel;
    @FXML
    private Button ok;

    /**
     * method used to update points in the scene
     * @param uiScore score updated arrived from server
     */
    void updatePoints(Score uiScore) {
        faithLabel.setContentDisplay(ContentDisplay.CENTER);
        militaryLabel.setContentDisplay(ContentDisplay.CENTER);
        victoryLabel.setContentDisplay(ContentDisplay.CENTER);

        faithLabel.setText(String.valueOf(uiScore.getFaithPoints()));
        victoryLabel.setText(String.valueOf(uiScore.getVictoryPoints()));
        militaryLabel.setText(String.valueOf(uiScore.getMilitaryPoints()));

    }

    /**
     * method called to close the stage
     */
    public void done() {
        Stage s = (Stage) ok.getScene().getWindow();
        s.hide();
    }
}
