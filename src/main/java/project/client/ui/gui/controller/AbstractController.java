package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
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
import java.util.Iterator;
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

    String familiarChosen;


    protected List<ImageView> imageFamiltMember;
    protected List<RadioButton> radioButtonFamiliar;

   protected void fillFamilymember(ImageView imageFamiliarNull, ImageView imageFamiliarBlack, ImageView imageFamiliarWhite, ImageView imageFamiliarOrange){
       imageFamiltMember.add(imageFamiliarNull);
       imageFamiltMember.add(imageFamiliarBlack);
       imageFamiltMember.add(imageFamiliarWhite);
       imageFamiltMember.add(imageFamiliarOrange);
       imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  + "blu" + "Zero.png"))));
       imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  +"blu" + "Nero.png"))));
       imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  +"blu" + "Bianco.png"))));
       imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/"  +"blu" + "Arancio.png"))));
   }

    protected AbstractController(){
        familiarChosen = null;
    }

    public void initialize(){
        imageFamiltMember = new ArrayList<>(4);

        radioButtonFamiliar = new ArrayList<>(4);
        //todo controllare in che ordine sono messi i family member sul player




    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public abstract void setMainController(MainController mainController);

    public abstract void refresh();

    public void goToMainGame(ActionEvent actionEvent) {
        loginBuilder.setScene(SceneType.MAIN,SceneType.HARVESTER);
    }

    public void sendChat(TextField chatText) {
        String text = chatText.getText() + "\n";
        writeOnChat(text);
    }

    public StringBuffer writeOnChat(String s){
        loginBuilder.stringBufferAppend(s);
        return loginBuilder.getStringBuffer();

    }

    public void uploadImages(){
    }

    protected Image getTrueFamiliarImage(){
        switch (familiarChosen) {
            case Constants.FAMILY_MEMBER_COLOUR_NEUTRAL:
                return imageFamiltMember.get(0).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_BLACK:
                return imageFamiltMember.get(1).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_WHITE:
                return imageFamiltMember.get(2).getImage();
            default:
                return imageFamiltMember.get(3).getImage();
        }
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

    public void updateOneResource(int coins, Label numberOfCoins) {
        numberOfCoins.setText(String.valueOf(coins));
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


    public void updatePosition(List<? extends Position> positions, List<FamiliarPosition> allPosition){
        Iterator<? extends Position> itPR = positions.iterator();
        Iterator<FamiliarPosition> itFP = allPosition.iterator();

        while (itFP.hasNext() && itPR.hasNext()){
            FamiliarPosition familiarPosition = itFP.next();
            Position position = itPR.next();
            if (position.getFamiliarOnThisPosition() == null){
                if (familiarPosition.getFamiliarName() == null)
                    continue;
                else {
                    familiarPosition.setImage(null);
                    familiarPosition.setFamiliarName(null);
                }
            }
            if (familiarPosition.getFamiliarName().equals(position.getFamiliarOnThisPosition().toString()))
                continue;
            else {
                familiarPosition.setFamiliarName(position.getFamiliarOnThisPosition().toString());
                familiarPosition.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familiarPosition.getFamiliarName() + ".png"))));
            }
        }
    }


    protected void blockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(true);
        personalBoard.setDisable(true);
        buttonPlaceFamiliar.setDisable(true);
    }


    protected void unlockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(false);
        personalBoard.setDisable(false);
        buttonPlaceFamiliar.setDisable(false);
    }

    public abstract void updateResources(int coins, int wood, int stone, int servants);
}
