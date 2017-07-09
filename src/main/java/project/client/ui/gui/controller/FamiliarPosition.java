package project.client.ui.gui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * class used to store a familiar for gui scenes. there are its name and its imageview
 */
class FamiliarPosition {
    private ImageView familiarImage;
    private String familiarName;

    /**
     * constructor
     * @param imageView imageview to set
     */
    FamiliarPosition(ImageView imageView){
        this.familiarImage = imageView;
        familiarName = "";
    }

    /**
     * void constructor
     */
    FamiliarPosition(){
        this.familiarImage = new ImageView();
        familiarImage.setFitWidth(94);
        familiarImage.setFitHeight(86);
        familiarName = "";
    }

    /**
     * getter
     * @return familiar name
     */
    String getFamiliarName() {
        return familiarName;
    }

    /**
     * getter
     * @return familiar image
     */
    public ImageView getImage() {
        return familiarImage;
    }

    /**
     * setter
     * @param trueFamiliarImage image to set
     */
    public void setImage(Image trueFamiliarImage) {
        familiarImage.setImage(trueFamiliarImage);
    }

    /**
     * setter
     * @param familiarName familiar name to set
     */
    void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }
}
