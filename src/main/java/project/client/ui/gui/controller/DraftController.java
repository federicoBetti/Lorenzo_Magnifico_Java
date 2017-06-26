package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import project.controller.cardsfactory.LeaderCard;


import java.util.List;

/**
 * this is the controller class of draft view on the grafical user interface
 */
public class DraftController {

    @FXML
    private Label title;
    @FXML
    private HBox cardBox;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLabel(String labelMessage) {
        title.setText(labelMessage);
    }


    public void uploadImagesTile(List<String> name) {
        for (String s: name){
            ImageView imageView = new ImageView();
            imageView.setFitWidth(49);
            imageView.setFitHeight(400);
            imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/risorse/bonusTile" + s + ".png"))));
            imageView.setOnMouseClicked(event -> imageClickedInt(s));
            cardBox.getChildren().addAll(imageView);
        }

    }

    private void imageClickedInt(String s) {

        mainController.addIntQueue(Integer.parseInt(s));
        Stage stage = (Stage) title.getScene().getWindow();
        stage.hide();
    }

    public void uploadImagesLeader(List<String> name) {
        for (String s: name){
            ImageView imageView = new ImageView();
            imageView.setFitWidth(128);
            imageView.setFitHeight(210);
            imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCards/" + s + ".png"))));
            imageView.setOnMouseClicked(event -> imageClickedLeader(s));
            cardBox.getChildren().addAll(imageView);
        }
    }


    private void imageClickedLeader(String s) {
        mainController.addStringQueue(s);
        Stage stage = (Stage) title.getScene().getWindow();
        stage.hide();

    }
}
