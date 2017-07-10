package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import project.controller.Constants;
import project.model.DevelopmentCard;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * abstract controller of gui scenes
 */
public abstract class AbstractController {

    LoginBuilder loginBuilder;
    MainController mainController;

    String familiarChosen;


    private List<ImageView> imageFamilyMember;
    private List<RadioButton> radioButtonFamiliar;

    /**
     * methods that fill images of family members for every player and every scene
     * @param imageFamiliarNull imageview of neutral familiar
     * @param imageFamiliarBlack imageview of black familiar
     * @param imageFamiliarWhite imageview of white familiar
     * @param imageFamiliarOrange imageview of orange familiar
     */
    void fillFamilyMember(ImageView imageFamiliarNull, ImageView imageFamiliarBlack, ImageView imageFamiliarWhite, ImageView imageFamiliarOrange) {
        familiarChosen = "";
        imageFamilyMember.add(imageFamiliarNull);
        imageFamilyMember.add(imageFamiliarBlack);
        imageFamilyMember.add(imageFamiliarWhite);
        imageFamilyMember.add(imageFamiliarOrange);
        String s = "/images/familiar/";
        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource(s + "empty" + mainController.getColour() + "neutral.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource(s + mainController.getColour() + "black.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource(s + mainController.getColour() + "white.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource(s + mainController.getColour() + "orange.png"))));
    }

    /**
     * fill a list of radio button
     * @param familiarNull radio button of neutral familiar
     * @param familiarBlack radio button of black familiar
     * @param familiarWhite radio button of white familiar
     * @param familiarOrange radio button of orange familiar
     */
    void fillRadioButton(RadioButton familiarNull, RadioButton familiarBlack, RadioButton familiarWhite, RadioButton familiarOrange) {
        radioButtonFamiliar.add(familiarNull);
        radioButtonFamiliar.add(familiarBlack);
        radioButtonFamiliar.add(familiarWhite);
        radioButtonFamiliar.add(familiarOrange);
    }

    /**
     * constructor
     */
    AbstractController() {
        familiarChosen = null;
    }

    /**
     * method called by all controllers when they are initialized
     */
    void initialize() {
        imageFamilyMember = new ArrayList<>(4);
        radioButtonFamiliar = new ArrayList<>(4);
    }

    /**
     * setter
     * @param loginBuilder main application, used to switch scene
     */
    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    /**
     *  setter
     * @param mainController main controller used to communicate with clientSetter
     */
    public abstract void setMainController(MainController mainController);

    /**
     * super method of refresh, called by every scene when is loaded
     */
    public void refresh(){
        familiarChosen = "";
    }

    /**
     * method called by gui to go back to main game
     */
    @FXML
    void goToMainGame() {
        loginBuilder.setScene(SceneType.MAIN, SceneType.HARVESTER);
    }

    /**
     * method used to create a special popUp
     * @param chatText text to write in the popUp
     */
    void sendChat(TextField chatText) {
        String text = chatText.getText() + "\n";
        loginBuilder.popUp(text);
    }

    /**
     * method used to write in all the chat of gui scenes
     * @param s message to write
     */
    void writeOnChat(String s) {
        loginBuilder.stringBufferAppend(s);
        mainController.updateChat();

    }

    /**
     * super method implemented
     */
    public void uploadImages() {
    }

    /**
     * method that returns the image of the familiar chosen
     * @return image of the familiar chosen
     */
    Image getTrueFamiliarImage() {
        switch (familiarChosen) {
            case Constants.FAMILY_MEMBER_COLOUR_NEUTRAL:
                return imageFamilyMember.get(0).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_BLACK:
                return imageFamilyMember.get(1).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_WHITE:
                return imageFamilyMember.get(2).getImage();
            case Constants.FAMILY_MEMBER_COLOUR_ORANGE:
                return imageFamilyMember.get(3).getImage();
            default:
                return null;

        }
    }

    /**
     * method called by gui when the familiar orange has been chosen
     */
    public void familiarOrangeChosen() {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_ORANGE;
    }

    /**
     * method called by gui when the familiar white has been chosen
     */
    public void familiarWhiteChosen() {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_WHITE;
    }

    /**
     * method called by gui when the familiar black has been chosen
     */
    public void familiarBlackChosen() {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_BLACK;
    }

    /**
     * method called by gui when the familiar neutral has been chosen
     */
    public void familiarNullChosen() {
        familiarChosen = Constants.FAMILY_MEMBER_COLOUR_NEUTRAL;
    }

    /**
     * method called by gui scenes when they want to swich to persoal board scene
     * @param oldScene old scene used to go back from personal board
     */
    void showPersonalBoard(SceneType oldScene) {
        loginBuilder.setScene(SceneType.PERSONAL_BOARD, oldScene);
    }

    /**
     * method used to update a single resurce
     * @param coins value of the resource to update
     * @param numberOfCoins label in which write the new value of resource
     */
    void updateOneResource(int coins, Label numberOfCoins) {
        numberOfCoins.setText(String.valueOf(coins));
    }

    /**
     * method used to update family members
     * @param uiFamilyMembers server array of familiar that has to be emulated in the gui
     */
    void updateFamilyMember(FamilyMember[] uiFamilyMembers) {

        for (int i = 0; i < imageFamilyMember.size(); i++) {
            ImageView imageView = imageFamilyMember.get(i);
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

    /**
     * method used to update card in personal board, production or harvester scene
     * @param territoryCards list of cards from server
     * @param nameOfTerritoryCard list of card that are in gui at the moment
     * @param imageTerritoryCard images of card that in the gui at the moment
     */
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

    /**
     * method used to place a family memeber in a infinite action space like council, harvester or production (2+ players)
     * @param allPosition list of familiars already placed in the action space
     * @param familiarBox hbox where to put the next imageView of familiar
     */
    void placeFamiliar(List<FamiliarPosition> allPosition, HBox familiarBox) {
        for (FamiliarPosition f : allPosition) {
            if (f.getFamiliarName().equals("")) {
                f.setImage(getTrueFamiliarImage());
                return;
            }
        }
        FamiliarPosition newPosition = new FamiliarPosition();
        ImageView imageView = newPosition.getImage();
        imageView.setImage(getTrueFamiliarImage());

        familiarBox.getChildren().addAll(imageView);

        allPosition.add(newPosition);
    }

    /**
     * method that calculate the number of familiar already placed
     * @param allPosition list of familiars
     * @return number of familiars placed
     */
    private int familiarPlaced(List<FamiliarPosition> allPosition) {
        int i = 0;
        for (FamiliarPosition f: allPosition)
            if (!f.getFamiliarName().equals(""))
                i++;
        return i;
    }

    /**
     * method used to update the gui following an update from the server
     * @param positions new list of positions from the server
     * @param allPosition list of familiar already placed in the gui that should be update
     */
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
            allPosition = new ArrayList<>();
            allPosition.add(familiarPositionn);
        }


        while (itPR.hasNext()) {
            FamiliarPosition familiarPosition;
            if (itFP.hasNext()) {
                familiarPosition = itFP.next();
            }
            else {
                familiarPosition = new FamiliarPosition();
                allPosition.add(familiarPosition);
            }
            Position position = itPR.next();
           if (position.getFamiliarOnThisPosition() == null) {
                if (!familiarPosition.getFamiliarName().equals("")) {
                    familiarPosition.setImage(null);
                    familiarPosition.setFamiliarName("");
                }
            } else if (!(familiarPosition.getFamiliarName().equals(position.getFamiliarOnThisPosition().toString()))){
                    familiarPosition.setFamiliarName(position.getFamiliarOnThisPosition().toString());
                    familiarPosition.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familiarPosition.getFamiliarName() + ".png"))));
                }

        }
    }

    /**
     * method used to update card and familiars in the towers
     * @param towers towers from the server
     * @param myTower towers in the gui to update
     */
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

    /**
     * support method used to modify the familiar in the gui
     * @param guiTower gui tower space
     * @param s name of the familiar to place in that position
     */
    private void modifyFamiliar(TowerZone guiTower, String s) {
        guiTower.setFamiliarName(s);
        guiTower.setFamiliarImage(s);
    }

    /**
     * support method to modify the card in the tower zone
     * @param guiTower gui tower zone
     * @param cardName name of the card to place
     */
    private void modifyCard(TowerZone guiTower, String cardName) {
        guiTower.setCardName(cardName);
        guiTower.setCardImage(cardName);
    }

    /**
     * method used to block some buttons for bonus actions
     * @param mainGameButton button to go back to main game
     * @param personalBoard button to show the persona board
     * @param buttonPlaceFamiliar method used to place the familiar
     */
    void blockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(true);
        personalBoard.setDisable(true);
        buttonPlaceFamiliar.setDisable(true);
    }


/**
 * method used to unlock some buttons for bonus actions
 * @param mainGameButton button to go back to main game
 * @param personalBoard button to show the persona board
 * @param buttonPlaceFamiliar method used to place the familiar
 */

    void unlockButton(Button mainGameButton, Button personalBoard, Button buttonPlaceFamiliar) {
        mainGameButton.setDisable(false);
        personalBoard.setDisable(false);
        buttonPlaceFamiliar.setDisable(false);
    }

    /**
     * abstract method to update resources in all controllers
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    public abstract void updateResources(int coins, int wood, int stone, int servants);

    /**
     * method used to update the images of familiars placed in a hbox
     * @param familiarInTheCouncil list of familiar to place
     * @param familiarBox hbox where the mages of familiars will be placed
     */
    void updateHBox(List<FamiliarPosition> familiarInTheCouncil, HBox familiarBox) {
        int i;
        for (i = 0; i < familiarInTheCouncil.size(); i++){
            if (!familiarBox.getChildren().contains(familiarInTheCouncil.get(i).getImage())) {
                familiarBox.getChildren().add(familiarInTheCouncil.get(i).getImage());
            }
        }

        for (; i < familiarBox.getChildren().size(); i++){
            ((ImageView) familiarBox.getChildren().get(i)).setImage(null);
        }
    }

    /**
     * method called by harvester and production scene to update familiars images
     * @param positionSelected boolean that indicates if a familiars has been already placed by the user in this turn in that zone
     * @param allPosition list of familiars already placed
     * @param familiarBox hbox where images will be added
     * @return true if a familiar has been placed for the first time
     */
    boolean placeFamiliarHarvProd(boolean positionSelected, List<FamiliarPosition> allPosition, HBox familiarBox) {
        if (!mainController.isMyTurn()){
            writeOnChat("it isn't your turn!");
            return false;
        }
        if (familiarChosen.equals("")) {
            writeOnChat("you haven't selected the familiar\n");
            return false;
        }
        if (mainController.getNumberOfPlayer() < 3 && familiarPlaced(allPosition) > 0){
            writeOnChat("you can't place another familiar there!\n");
            return false;
        }
        if (positionSelected){
            if (allPosition.size()>1) {
                allPosition.remove(allPosition.size() - 1);
                familiarBox.getChildren().remove(familiarBox.getChildren().size() - 1);
            }
            else {
                allPosition.get(0).setFamiliarName("");
            }placeFamiliar(allPosition,familiarBox);
            return false;
        }
        else {
            placeFamiliar(allPosition, familiarBox);
            return true;
        }
    }

    /**
     * method used by conntrollers to unselect all radio buttons
     * @param familiar toggle group where are stored all familiars radio button
     */
    void unselectedRadioButton(ToggleGroup familiar) {
        RadioButton rb = (RadioButton) familiar.getSelectedToggle();
        if (rb != null)
            rb.setSelected(false);
    }
}
