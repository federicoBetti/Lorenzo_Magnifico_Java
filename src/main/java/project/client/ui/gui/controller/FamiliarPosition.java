package project.client.ui.gui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.model.FamilyMember;


/**
 * Created by federico on 18/06/17.
 */
class FamiliarPosition {
    private ImageView familiarPosition;
    private String familiarName;

    FamiliarPosition(ImageView imageView){
        this.familiarPosition = imageView;
        familiarName = null;
    }

    FamiliarPosition(FamiliarPosition toClone){
        this.familiarPosition = toClone.getImage();
        familiarName = null;
    }
    public String getFamiliarName() {
        return familiarName;
    }

    public ImageView getImage() {
        return familiarPosition;
    }

    public void setImage(Image trueFamiliarImage) {
        familiarPosition.setImage(trueFamiliarImage);
    }

    public void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }
}
