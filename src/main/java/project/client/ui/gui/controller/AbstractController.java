package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import project.TowerIterator;
import project.controller.Constants;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;

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

    protected void fillFamilymember(ImageView imageFamiliarNull, ImageView imageFamiliarBlack, ImageView imageFamiliarWhite, ImageView imageFamiliarOrange) {
        familiarChosen = new String();
        imageFamiltMember.add(imageFamiliarNull);
        imageFamiltMember.add(imageFamiliarBlack);
        imageFamiltMember.add(imageFamiliarWhite);
        imageFamiltMember.add(imageFamiliarOrange);
        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "neutral.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "black.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "white.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "orange.png"))));
    }


    protected void fillRadioButton(RadioButton familiarNull, RadioButton familiarBlack, RadioButton familiarWhite, RadioButton familiarOrange) {
        radioButtonFamiliar.add(familiarNull);
        radioButtonFamiliar.add(familiarBlack);
        radioButtonFamiliar.add(familiarWhite);
        radioButtonFamiliar.add(familiarOrange);
    }

    protected AbstractController() {
        familiarChosen = null;
    }

    public void initialize() {
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
        loginBuilder.setScene(SceneType.MAIN, SceneType.HARVESTER);
    }

    public void sendChat(TextField chatText) {
        String text = chatText.getText() + "\n";
        writeOnChat(text);
    }

    public StringBuffer writeOnChat(String s) {
        loginBuilder.stringBufferAppend(s);
        return loginBuilder.getStringBuffer();

    }

    public void uploadImages() {
    }

    protected Image getTrueFamiliarImage() {
        switch (familiarChosen) {
            case Constants.FAMILY_MEMBER_COLOUR_NEUTRAL:
                return imageFamiltMember.get(0).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_BLACK:
                return imageFamiltMember.get(1).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_WHITE:
                return imageFamiltMember.get(2).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_ORANGE:
                return imageFamiltMember.get(3).getImage();
            default:
                return null;

        }
    }


    public void familiarOrangeChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_ORANGE;
    }

    public void familiarWhiteChosen(ActionEvent actionEvent) {
        System.out.println("ho scelto il faiiare bianco");
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_WHITE;
    }

    public void familiarBlackChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_BLACK;
    }

    public void familiarNullChosen(ActionEvent actionEvent) {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_NEUTRAL;
    }


    public void showPersonalBoard(SceneType oldScene) {
        loginBuilder.setScene(SceneType.PERSONAL_BOARD, oldScene);
    }

    public void updateOneResource(int coins, Label numberOfCoins) {
        numberOfCoins.setText(String.valueOf(coins));
    }


    public void updateFamilyMember(FamilyMember[] uiFamilyMembers) {

        for (int i = 0; i < imageFamiltMember.size(); i++) {
            ImageView imageView = imageFamiltMember.get(i);
            RadioButton radioButton = radioButtonFamiliar.get(i);
            if (uiFamilyMembers[i].isPlayed()) {
                imageView.setOpacity(0.7);
                radioButton.setDisable(true);
            } else {
                imageView.setOpacity(1);
                radioButton.setDisable(false);
            }
        }
        familiarChosen = null;
    }

    public void placeFamiliar(List<FamiliarPosition> allPosition, HBox familiarBox) {
        System.out.println("provo a piazzare familiare");
        for (FamiliarPosition f : allPosition) {
            if (f.getFamiliarName() == "") {
                System.out.println("ho trovato un posto vuoto");
                f.setImage(getTrueFamiliarImage());
                return;
            }
        }
        System.out.println("creo un nuovo posto per un mio familiare");
        FamiliarPosition newPosition = new FamiliarPosition(allPosition.get(allPosition.size() - 1));
        ImageView imageView = newPosition.getImage();
        imageView.setImage(getTrueFamiliarImage());
        familiarBox.getChildren().addAll(imageView);
        allPosition.add(newPosition);
    }

    protected int familiarPlaced(List<FamiliarPosition> allPosition) {
        int i = 0;
        for (FamiliarPosition f: allPosition)
            if (f.getFamiliarName()!= "")
                i++;
        return i;
    }


    public void updatePosition(List<? extends Position> positions, List<FamiliarPosition> allPosition) {
        Iterator<? extends Position> itPR = positions.iterator();
        Iterator<FamiliarPosition> itFP = allPosition.iterator();

        while (itFP.hasNext() && itPR.hasNext()) {
            FamiliarPosition familiarPosition = itFP.next();
            Position position = itPR.next();
            System.out.println("devo aggiornare una posizione con familiare " + position.getFamiliarOnThisPosition());
            if (position.getFamiliarOnThisPosition() == null) {
                if (familiarPosition.getFamiliarName().equals("")) continue;
                else {
                    familiarPosition.setImage(null);
                    familiarPosition.setFamiliarName("");
                }
            } else {
                if (familiarPosition.getFamiliarName().equals(position.getFamiliarOnThisPosition().toString()))
                    continue;
                else {
                    System.out.println("devo cambiare il familiar sulla posizione");
                    familiarPosition.setFamiliarName(position.getFamiliarOnThisPosition().toString());
                    System.out.println(familiarPosition.getFamiliarName());
                    familiarPosition.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familiarPosition.getFamiliarName() + ".png"))));
                }
            }
        }
    }

    public void updatePosition(Tower[][] towers, TowerZone[][] myTower) {
        int floorNumber;
        int towerNumber;
        for (towerNumber = 0; towerNumber < 4; towerNumber++) {
            for (floorNumber = 0; floorNumber < 4; floorNumber++) {
                Tower serverTower = towers[towerNumber][floorNumber];
                TowerZone guiTower = myTower[towerNumber][floorNumber];


                System.out.println(serverTower.getCardOnThisFloor());
                if (serverTower.getCardOnThisFloor() == null) {
                    modifyCard(guiTower, null);
                } else {
                    if (!serverTower.getCardOnThisFloor().getName().equals(guiTower.getCardName())) {
                        modifyCard(guiTower, serverTower.getCardOnThisFloor().getName());
                    }
                }

                System.out.println(serverTower.getFamiliarOnThisPosition());

                if (!(serverTower.isOccupied())) {
                    guiTower.setFamiliarName(null);
                    guiTower.setFamiliarImage((Image) null);
                } else {

                    System.out.println(serverTower.getFamiliarOnThisPosition());
                    if (!serverTower.getFamiliarOnThisPosition().toString().equals(guiTower.getFamiliarName())) {
                        System.out.println(serverTower.getFamiliarOnThisPosition());
                        modifyFamiliar(guiTower, serverTower.getFamiliarOnThisPosition().toString());
                    }
                }
            }
        }

    }


    private void modifyFamiliar(TowerZone guiTower, String s) {
        guiTower.setFamiliarName(s);
        guiTower.setFamiliarImage(s);
    }

    private void modifyCard(TowerZone guiTower, String cardName) {
        if (guiTower.getCardName().equals(cardName))
            return;
        guiTower.setCardName(cardName);
        guiTower.setCardImage(cardName);
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
