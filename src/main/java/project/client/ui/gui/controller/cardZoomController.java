package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by federico on 12/06/17.
 */
public class cardZoomController {

    private Image image;

    @FXML
    private ImageView imageView;
    @FXML
    private Button exit;

    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
