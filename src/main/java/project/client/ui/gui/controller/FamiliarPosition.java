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
        familiarName = "";
    }

    FamiliarPosition(FamiliarPosition toClone){
        this.familiarPosition = toClone.getImage();
        familiarName = "";
    }

    FamiliarPosition(String type){
        this.familiarPosition = new ImageView();
        familiarPosition.setFitWidth(94);
        familiarPosition.setFitHeight(86);
        familiarName = "";
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
