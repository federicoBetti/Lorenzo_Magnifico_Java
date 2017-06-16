package project.client.ui.gui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by federico on 12/06/17.
 */
public class cardZoomController {
    public Image image;
    public ImageView imageView;

    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }
}
