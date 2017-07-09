package project.client.ui.gui.controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class useful to contains the images and the names of cards and familiars on a single tower position
 */
class TowerZone {
    private ImageView card;
    private ImageView familiar;
    private String cardName;
    private String familiarName;

    /**
     * constructor
     * @param card image of the card
     * @param familiar image of the familiar
     */
    TowerZone(ImageView card, ImageView familiar){
        this.card = card;
        this.familiar = familiar;
        cardName = "";
        familiarName = "";
    }

    /**
     * getter
     * @return card name
     */
    String getCardName() {
        return cardName;
    }

    /**
     * getter
     * @return familiar name
     */
    String getFamiliarName() {
        return familiarName;
    }

    /**
     * setter
     * @param cardName card name
     */
    void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * setter
     * @param cardName name of the card, useful to set it's image
     */
    void setCardImage(String cardName) {
        if (cardName == null)
            card.setImage(null);
        else{
            card.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + cardName +".png"))));
        }}

    /**
     * setter
     * @param familiarName familiar name
     */
    void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    /**
     * setter
     * @param s name of the familiar, useful to set it's image
     */
    void setFamiliarImage(String s) {
        if (familiarName == null)
            familiar.setImage(null);
        else
            familiar.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + s +".png"))));

    }

    /**
     * getter
     * @return familiar image
     */
    ImageView getFamiliarImage() {
        return familiar;
    }

    /**
     * settr
     * @param trueFamiliarImage set image of familiar
     */
    void setFamiliarImage(Image trueFamiliarImage) {
        familiar.setImage(trueFamiliarImage);
    }

    /**
     * getter
     * @return image view of the card
     */
    public ImageView getCard() {
        return card;
    }
}
