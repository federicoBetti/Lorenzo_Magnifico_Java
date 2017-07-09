package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * controller for the scene that make the zoom on a special card
 */
public class CardZoomController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button exit;

    /**
     * method used to place the image in the stage
     * @param image image to zoom
     */
    public void setImage(Image image) {
        imageView.setImage(image);
    }

    /**
     * methos used to close the stage, called by a gui button
     */
    public void close() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
