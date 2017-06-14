package project.client.ui.gui.maingame;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by federico on 13/06/17.
 */
public abstract class AbstractController {

    protected MainGameBuilder mainController;
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

    public void setMainController(MainGameBuilder mainController) {
        this.mainController = mainController;
    }

    public void goToMainGame(ActionEvent actionEvent) {
        mainController.setScene(SceneType.MAIN,SceneType.HARVESTER);
    }

    public void sendChat(ActionEvent actionEvent) {
        String text = chatText.getText() + "\n";
        mainController.sendChat(text);
    }

    public void uploadImages(){
        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Zero.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Nero.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Bianco.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Arancio.png"))));

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
        familiarChosen = "Orange";
    }

    public void familiarWhiteChosen(ActionEvent actionEvent) {
        familiarChosen = "White";
    }

    public void familiarBlackChosen(ActionEvent actionEvent) {
        familiarChosen = "Black";
    }

    public void familiarNullChosen(ActionEvent actionEvent) {
        familiarChosen = "Null";
    }


    public void showPersonalBoard(SceneType oldScene) {
        mainController.setScene(SceneType.PERSONAL_BOARD,oldScene);
    }
}
