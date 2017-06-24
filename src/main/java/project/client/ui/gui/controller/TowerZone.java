package project.client.ui.gui.controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by federico on 18/06/17.
 */
class TowerZone {
    private ImageView card;
    private ImageView familiar;
    private String cardName;
    private String familiarName;

    TowerZone(ImageView card, ImageView familiar){
        this.card = card;
        this.familiar = familiar;
        cardName = null;
        familiarName = null;
    }

    String getCardName() {
        return cardName;
    }

    String getFamiliarName() {
        return familiarName;
    }

    void setCardName(String cardName) {
        this.cardName = cardName;
    }

    void setCardImage(String cardName) {
        if (cardName == null)
            card.setImage(null);
        else
            card.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + cardName +".png"))));
    }

    void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    void setFamiliarImage(String s) {
        if (familiarName == null)
            familiar.setImage(null);
        else
            familiar.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + s +".png"))));

    }

    ImageView getFamiliarImage() {
        return familiar;
    }

    void setFamiliarImage(Image trueFamiliarImage) {
        familiar.setImage(trueFamiliarImage);
    }
}
