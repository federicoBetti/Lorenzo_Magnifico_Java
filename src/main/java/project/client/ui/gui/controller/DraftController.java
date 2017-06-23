package project.client.ui.gui.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.util.List;

/**
 * Created by federico on 23/06/17.
 */
public class DraftController {
    public Label title;
    public HBox cardBox;
    private MainController mainController;
    private LoginBuilder loginBuilder;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void setLabel(String labelMessage) {
        title.setText(labelMessage);
    }

    public void uploadImages(List<String> leaderName) {
        for (String s: leaderName){
            ImageView imageView = new ImageView();
            imageView.setFitWidth(128);
            imageView.setFitHeight(210);
            imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCard/" + s + ".png"))));
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
