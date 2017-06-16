package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controller.Constants;

/**
 * Created by federico on 13/06/17.
 */
public abstract class AbstractController {

    protected LoginBuilder loginBuilder;
    protected MainController mainController;
    /**
     * textfield to write chat messages
     */
    
    public TextField chatText;
    String familiarChosen;


    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */
    
    public ImageView imageFamiliarNull;
    
    public ImageView imageFamiliarBlack;
    
    public ImageView imageFamiliarWhite;
    
    public ImageView imageFamiliarOrange;


    
    public Label numberOfCoins;
    
    public Label numberOfWood;
    
    public Label numberOfStone;
    
    public Label numberOfServants;

    
    public ImageView LorenzoMagnifico;

    protected AbstractController(){
        familiarChosen = "Null";
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void goToMainGame(ActionEvent actionEvent) {
        loginBuilder.setScene(SceneType.MAIN,SceneType.HARVESTER);
    }

    public void sendChat(ActionEvent actionEvent) {
        String text = chatText.getText() + "\n";
        loginBuilder.sendChat(text);
    }

    public void uploadImages(){
    }

    protected Image getTrueFamiliarImage(){
        Image familiar;
        if (familiarChosen == "Null")
            familiar = imageFamiliarNull.getImage();
        else if (familiarChosen == "Black")
            familiar = imageFamiliarBlack.getImage();
        else if (familiarChosen == "White")
            familiar = imageFamiliarWhite.getImage();
        else
            familiar = imageFamiliarOrange.getImage();
        return familiar;
    }


    public void familiarOrangeChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_ORANGE;
    }

    public void familiarWhiteChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_WHITE;
    }

    public void familiarBlackChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_BLACK;
    }

    public void familiarNullChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_NEUTRAL;
    }


    public void showPersonalBoard(SceneType oldScene) {
        loginBuilder.setScene(SceneType.PERSONAL_BOARD,oldScene);
    }
}
