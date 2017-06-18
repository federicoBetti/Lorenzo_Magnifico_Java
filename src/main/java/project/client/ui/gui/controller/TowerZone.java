package project.client.ui.gui.controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by federico on 18/06/17.
 */
public class TowerZone {
    ImageView card;
    ImageView familiar;
    String cardName;
    String familiarName;

    public TowerZone(ImageView card, ImageView familiar){
        this.card = card;
        this.familiar = familiar;
    }

    public String getCardName() {
        return cardName;
    }

    public String getFamiliarName() {
        return familiarName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardImage(String cardName) {
        if (cardName == null)
            card.setImage(null);
        else
            card.setImage(new Image(String.valueOf(getClass().getResource("/images/card/" + cardName +".png"))));
    }

    public void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    public void setFamiliarImage(String s) {
        if (familiarName == null)
            familiar.setImage(null);
        else
            familiar.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + cardName +".png"))));

    }

    public ImageView getFamiliarImage() {
        return familiar;
    }

    public void setFamiliarImage(Image trueFamiliarImage) {
        familiar.setImage(trueFamiliarImage);
    }
}
