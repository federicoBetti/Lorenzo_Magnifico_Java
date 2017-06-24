package project.client.ui.gui.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public void uploadImages(List<LeaderCard> leaderName) {
        for (LeaderCard l: leaderName){
            String s = l.getName();
            ImageView imageView = new ImageView();
            imageView.setFitWidth(128);
            imageView.setFitHeight(210);
            imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCards/" + s + ".png"))));
            imageView.setOnMouseClicked(event -> imageClicked(s));
            cardBox.getChildren().addAll(imageView);
        }
    }

    private void imageClicked(String s) {
        mainController.addStringQueue(s);
        Stage stage = (Stage) title.getScene().getWindow();
        stage.hide();

    }
}
