package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controller.Constants;

import java.util.ArrayList;
import java.util.List;

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
     * radio button in which you can chose the familiar to use
     */
    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;

    protected List<RadioButton> radioButtonFamiliar;
    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */
    
    public ImageView imageFamiliarNull;
    
    public ImageView imageFamiliarBlack;
    
    public ImageView imageFamiliarWhite;
    
    public ImageView imageFamiliarOrange;

    protected List<ImageView> imageFamiltMember;

    
    public Label numberOfCoins;
    
    public Label numberOfWood;
    
    public Label numberOfStone;
    
    public Label numberOfServants;

    
    public ImageView LorenzoMagnifico;

    protected AbstractController(){
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_NEUTRAL;
    }

    public void initialize(){
        imageFamiltMember = new ArrayList<>(4);
        imageFamiltMember.add(imageFamiliarNull);
        imageFamiltMember.add(imageFamiliarBlack);
        imageFamiltMember.add(imageFamiliarWhite);
        imageFamiltMember.add(imageFamiliarOrange);

        radioButtonFamiliar = new ArrayList<>(4);
        radioButtonFamiliar.add(familiarNull);
        radioButtonFamiliar.add(familiarBlack);
        radioButtonFamiliar.add(familiarWhite);
        radioButtonFamiliar.add(familiarOrange);
        //todo controllare in che ordine sono messi i family member sul player

    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public abstract void setMainController(MainController mainController);

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
        if (familiarChosen.equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL))
            familiar = imageFamiliarNull.getImage();
        else if (familiarChosen.equals(Constants.FAMILY_MEMBER_COLOUR_BLACK))
            familiar = imageFamiliarBlack.getImage();
        else if (familiarChosen.equals(Constants.FAMILY_MEMBER_COLOUR_WHITE))
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

    public void updateResources(int coins, int wood, int stone, int servants) {
        numberOfCoins.setText(String.valueOf(coins));
        numberOfWood.setText(String.valueOf(wood));
        numberOfStone.setText(String.valueOf(stone));
        numberOfServants.setText(String.valueOf(servants));
    }
}
