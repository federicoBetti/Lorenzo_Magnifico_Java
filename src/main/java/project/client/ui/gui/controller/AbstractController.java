package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import project.controller.Constants;
import project.model.FamilyMember;
import project.model.Position;

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


    @FXML
    protected Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button personalBoard;
    @FXML
    private Button buttonPlaceFamiliar;
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
        familiarChosen = null;
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


        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  + mainController.getColour() + "Zero.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  + mainController.getColour() + "Nero.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  + mainController.getColour() + "Bianco.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  + mainController.getColour() + "Arancio.png"))));


    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public abstract void setMainController(MainController mainController);

    public abstract void refresh();

    public void goToMainGame(ActionEvent actionEvent) {
        loginBuilder.setScene(SceneType.MAIN,SceneType.HARVESTER);
    }

    public void sendChat(ActionEvent actionEvent) {
        String text = chatText.getText() + "\n";
        loginBuilder.sendChat(text);
    }

    public void writeOnChat(String s){
        chatText.setText(chatText.getText() + s + "\n");
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



    public void updateFamilyMember(FamilyMember[] uiFamilyMembers) {

        for (int i = 0;i<imageFamiltMember.size(); i++){
            ImageView imageView = imageFamiltMember.get(i);
            RadioButton radioButton = radioButtonFamiliar.get(i);
            if (uiFamilyMembers[i].isPlayed()){
                imageView.setOpacity(0.7);
                radioButton.setDisable(true);
            }
            else {
                imageView.setOpacity(1);
                radioButton.setDisable(false);
            }
        }
        familiarChosen = null;
    }

    public void placeFamiliar(List<FamiliarPosition> allPosition, HBox familiarBox){
        for (FamiliarPosition f: allPosition){
            if (f.getFamiliarName() == null){
                f.setImage(getTrueFamiliarImage());
                return;
            }
        }
        FamiliarPosition newPosition = new FamiliarPosition(allPosition.get(allPosition.size() - 1));
        if (!allPosition.add(newPosition))//se è immtabile ritorna false
            return;
        ImageView imageView = newPosition.getImage();
        imageView.setImage(getTrueFamiliarImage());
        familiarBox.getChildren().addAll(imageView);
        allPosition.add(newPosition);
    }


    public void updatePosition(Position[] positions, List<FamiliarPosition> allPosition){
        for (int i = 0; i<positions.length; i++){
            FamiliarPosition familiarPosition = allPosition.get(i);
            if (positions[i].getFamiliarOnThisPosition() == null){
                if (familiarPosition.getFamiliarName() == null)
                    continue;
                else {
                    familiarPosition.setImage(null);
                    familiarPosition.setFamiliarName(null);
                }
            }
            if (familiarPosition.getFamiliarName().equals(positions[i].getFamiliarOnThisPosition().toString()))
                continue;
            else {
                familiarPosition.setFamiliarName(positions[i].getFamiliarOnThisPosition().toString());
                familiarPosition.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familiarPosition.getFamiliarName() + ".png"))));
            }
        }
    }


    protected void blockButton() {
        mainGameButton.setDisable(true);
        personalBoard.setDisable(true);
        buttonPlaceFamiliar.setDisable(true);
    }


    protected void unlockButton() {
        mainGameButton.setDisable(false);
        personalBoard.setDisable(false);
        buttonPlaceFamiliar.setDisable(false);
    }
}
