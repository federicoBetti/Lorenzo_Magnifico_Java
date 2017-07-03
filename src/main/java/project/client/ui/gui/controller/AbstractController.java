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
import project.TowerIterator;
import project.controller.Constants;
import project.controller.cardsfactory.TerritoryCard;
import project.model.DevelopmentCard;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by federico on 13/06/17.
 */
public abstract class AbstractController {

    LoginBuilder loginBuilder;
    MainController mainController;
    /**
     * textfield to write chat messages
     */

    String familiarChosen;


    private List<ImageView> imageFamiltMember;
    private List<RadioButton> radioButtonFamiliar;

    void fillFamilymember(ImageView imageFamiliarNull, ImageView imageFamiliarBlack, ImageView imageFamiliarWhite, ImageView imageFamiliarOrange) {
        familiarChosen = new String();
        imageFamiltMember.add(imageFamiliarNull);
        imageFamiltMember.add(imageFamiliarBlack);
        imageFamiltMember.add(imageFamiliarWhite);
        imageFamiltMember.add(imageFamiliarOrange);
        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/empty" + mainController.getColour() + "neutral.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "black.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "white.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "orange.png"))));
    }


    void fillRadioButton(RadioButton familiarNull, RadioButton familiarBlack, RadioButton familiarWhite, RadioButton familiarOrange) {
        radioButtonFamiliar.add(familiarNull);
        radioButtonFamiliar.add(familiarBlack);
        radioButtonFamiliar.add(familiarWhite);
        radioButtonFamiliar.add(familiarOrange);
    }

    AbstractController() {
        familiarChosen = null;
    }

    void initialize() {
        imageFamiltMember = new ArrayList<>(4);

        radioButtonFamiliar = new ArrayList<>(4);
        //todo controllare in che ordine sono messi i family member sul player


    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public abstract void setMainController(MainController mainController);

    public void refresh(){
        familiarChosen = "";
    }

    @FXML
    void goToMainGame(ActionEvent actionEvent) {
        loginBuilder.setScene(SceneType.MAIN, SceneType.HARVESTER);
    }

    void sendChat(TextField chatText) {
        String text = chatText.getText() + "\n";
        writeOnChat(text);
    }

    public void writeOnChat(String s) {
        loginBuilder.stringBufferAppend(s);
        mainController.updateChat();

    }

    public void uploadImages() {
    }

    Image getTrueFamiliarImage() {
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


    void showPersonalBoard(SceneType oldScene) {
        loginBuilder.setScene(SceneType.PERSONAL_BOARD, oldScene);
    }

    void updateOneResource(int coins, Label numberOfCoins) {
        numberOfCoins.setText(String.valueOf(coins));
    }


    public void updateFamilyMember(FamilyMember[] uiFamilyMembers) {

        for (int i = 0; i < imageFamiltMember.size(); i++) {
            ImageView imageView = imageFamiltMember.get(i);
            RadioButton radioButton = radioButtonFamiliar.get(i);
            if (uiFamilyMembers[i].isPlayed()) {
                imageView.setOpacity(0.5);
                radioButton.setDisable(true);
            } else {
                imageView.setOpacity(1);
                radioButton.setDisable(false);
            }
        }
        familiarChosen = null;
    }

    void updateCards(List<? extends DevelopmentCard> territoryCards, List<String> nameOfTerritoryCard, List<ImageView> imageTerritoryCard){
        for (int i = 0; i< territoryCards.size(); i++) {
            try {
                nameOfTerritoryCard.get(i);
            } catch (IndexOutOfBoundsException e) {
                String nameOfNewCard = territoryCards.get(i).getName();
                nameOfTerritoryCard.add(nameOfNewCard);
                ImageView imageView = imageTerritoryCard.get(i);
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + nameOfNewCard + ".png"))));
            }
        }
    }

    void placeFamiliar(List<FamiliarPosition> allPosition, HBox familiarBox) {
        System.out.println("provo a piazzare familiare");
        for (FamiliarPosition f : allPosition) {
            if (f.getFamiliarName().equals("")) {
                System.out.println("ho trovato un posto vuoto");
                f.setImage(getTrueFamiliarImage());
                return;
            }
        }
        System.out.println("creo un nuovo posto per un mio familiare");
        FamiliarPosition newPosition = new FamiliarPosition("hbox");
        ImageView imageView = newPosition.getImage();
        imageView.setImage(getTrueFamiliarImage());

        //ArrayList<ImageView> toAddHbox = imageFromFamiliars(allPosition);
        familiarBox.getChildren().addAll(imageView);

        allPosition.add(newPosition);
    }


    int familiarPlaced(List<FamiliarPosition> allPosition) {
        int i = 0;
        for (FamiliarPosition f: allPosition)
            if (!f.getFamiliarName().equals(""))
                i++;
        return i;
    }


    void updatePosition(List<? extends Position> positions, List<FamiliarPosition> allPosition) {
        Iterator<? extends Position> itPR = positions.iterator();
        Iterator<FamiliarPosition> itFP = allPosition.iterator();

        if (positions.isEmpty()){
            //new round
            if (allPosition.isEmpty())
                return;
            FamiliarPosition familiarPositionn = allPosition.get(0);
            familiarPositionn.setImage(null);
            familiarPositionn.setFamiliarName("");
            allPosition.removeIf(familiarPosition -> {
                if (familiarPosition != familiarPositionn)
                    return true;
                return true;
            });
        }


        while (itPR.hasNext()) {
            FamiliarPosition familiarPosition;
            if (itFP.hasNext()) {
                familiarPosition = itFP.next();
            }
            else {
                familiarPosition = new FamiliarPosition("");
                allPosition.add(familiarPosition);
            }
            Position position = itPR.next();
           if (position.getFamiliarOnThisPosition() == null) {
                if (familiarPosition.getFamiliarName().equals("")) continue;
                else {
                    familiarPosition.setImage(null);
                    familiarPosition.setFamiliarName("");
                }
            } else if (!(familiarPosition.getFamiliarName().equals(position.getFamiliarOnThisPosition().toString()))){
                    familiarPosition.setFamiliarName(position.getFamiliarOnThisPosition().toString());
                    familiarPosition.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familiarPosition.getFamiliarName() + ".png"))));
                }

        }
    }

    void updatePosition(Tower[][] towers, TowerZone[][] myTower) {
        int floorNumber;
        int towerNumber;
        for (towerNumber = 0; towerNumber < 4; towerNumber++) {
            for (floorNumber = 0; floorNumber < 4; floorNumber++) {
                Tower serverTower = towers[towerNumber][floorNumber];
                TowerZone guiTower = myTower[towerNumber][floorNumber];


               if (serverTower.getCardOnThisFloor() == null) {
                    guiTower.setCardName(null);
                    guiTower.setCardImage(null);
                }
                else if (!serverTower.getCardOnThisFloor().getName().equals(guiTower.getCardName())) {
                        modifyCard(guiTower, serverTower.getCardOnThisFloor().getName());
                    }


                if (!(serverTower.isOccupied())) {
                    guiTower.setFamiliarName(null);
                    guiTower.setFamiliarImage((Image) null);
                } else if (!serverTower.getFamiliarOnThisPosition().toString().equals(guiTower.getFamiliarName())) {
                        modifyFamiliar(guiTower, serverTower.getFamiliarOnThisPosition().toString());
                    }

            }
        }

    }


    private void modifyFamiliar(TowerZone guiTower, String s) {
        guiTower.setFamiliarName(s);
        guiTower.setFamiliarImage(s);
    }

    private void modifyCard(TowerZone guiTower, String cardName) {
        guiTower.setCardName(cardName);
        guiTower.setCardImage(cardName);
    }


    void blockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(true);
        personalBoard.setDisable(true);
        buttonPlaceFamiliar.setDisable(true);
    }


    void unlockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(false);
        personalBoard.setDisable(false);
        buttonPlaceFamiliar.setDisable(false);
    }

    public abstract void updateResources(int coins, int wood, int stone, int servants);

    public void updateHBox(List<FamiliarPosition> familiarInTheCouncil, HBox familiarBox) {
        for (int i = 0; i < familiarInTheCouncil.size(); i++){
            System.out.println(i);
            if (!familiarBox.getChildren().contains(familiarInTheCouncil.get(i).getImage())) {
                familiarBox.getChildren().add(familiarInTheCouncil.get(i).getImage());
                System.out.println("ho aggiunto un familiare");
            }
        }
    }


    private ArrayList<ImageView> imageFromFamiliars(List<FamiliarPosition> allPosition) {
        ArrayList<ImageView> toAdd = new ArrayList<>();
        for (FamiliarPosition f: allPosition) {
            ImageView im = f.getImage();
            toAdd.add(im);
        }
        return toAdd;
    }
}
