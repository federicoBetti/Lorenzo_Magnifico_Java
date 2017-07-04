package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.model.Score;

/**
 * Created by federico on 04/07/17.
 */
public class PointsController {
    public Label faithLabel;
    public Label victoryLabel;
    public Label militaryLabel;
    public Button ok;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updatePoints(Score uiScore) {
        faithLabel.setContentDisplay(ContentDisplay.CENTER);
        militaryLabel.setContentDisplay(ContentDisplay.CENTER);
        victoryLabel.setContentDisplay(ContentDisplay.CENTER);

        faithLabel.setText(String.valueOf(uiScore.getFaithPoints()));
        victoryLabel.setText(String.valueOf(uiScore.getVictoryPoints()));
        militaryLabel.setText(String.valueOf(uiScore.getMilitaryPoints()));

    }

    public void done(ActionEvent actionEvent) {
        Stage s = (Stage) ok.getScene().getWindow();
        s.hide();
    }
}
