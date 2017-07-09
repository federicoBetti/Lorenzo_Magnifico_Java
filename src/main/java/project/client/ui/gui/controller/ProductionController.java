package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import project.controller.cardsfactory.BuildingCard;
import project.model.Production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductionController extends AbstractController {


    @FXML
    private Label numberOfServants;
    @FXML
    private Label numberOfCoins;
    @FXML
    private Label numberOfWood;
    @FXML
    private Label numberOfStone;

    @FXML
    private Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button buttonPlaceFamiliar;
    @FXML
    private Button personalBoard;


    @FXML
    private RadioButton familiarWhite;
    @FXML
    private RadioButton familiarOrange;
    @FXML
    private RadioButton familiarNull;
    @FXML
    private RadioButton familiarBlack;


    @FXML
    private ImageView imageFamiliarNull;
    @FXML
    private ImageView imageFamiliarOrange;
    @FXML
    private ImageView imageFamiliarBlack;
    @FXML
    private ImageView imageFamiliarWhite;





    @FXML
    private ImageView lorenzoMagnifico;


    @FXML
    private TextField chatText;
    private static final int NUMBER_OF_CARD = 6;

    @FXML
    private ImageView productionZoneImage;
    @FXML
    private ImageView buildingCard0;
    @FXML
    private ImageView buildingCard1;
    @FXML
    private ImageView buildingCard2;
    @FXML
    private ImageView buildingCard3;
    @FXML
    private ImageView buildingCard4;
    @FXML
    private ImageView buildingCard5;
    @FXML
    private HBox familiarBox;

    private ArrayList<ImageView> allBuildingCard;
    private ArrayList<String> nameOfBuilding;

    @FXML
    private ImageView imageProduction0;
    private List<FamiliarPosition> allPosition;


    private boolean[] cardSelected;
    private boolean positionSelected;

    private DropShadow borderGlow = new DropShadow();
    private DropShadow borderNull = new DropShadow();
    @FXML
    private ScrollPane chatArea;
    @FXML
    private ToggleGroup familiar;

    /**
     * constructor
     */
    public ProductionController() {
        super();
        cardSelected = new boolean[NUMBER_OF_CARD];
        positionSelected = false;

        int depth = 70;
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);
    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setProductionController(this);
    }

    /**
     * initialization
     */
    @Override
    public void initialize() {
        super.initialize();

        nameOfBuilding = new ArrayList<>(6);
        allBuildingCard = new ArrayList<>(6);

    }

    /**
     * method called during the initialization to update images
     */
    @Override
    public void uploadImages() {
        super.uploadImages();
        lorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        int playerNumber;
        if (mainController.getNumberOfPlayer() > 2) playerNumber = 3;
        else playerNumber = 2;
        productionZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/produzione" + playerNumber + "Giocatori.png"))));

        fillFamilyMember(imageFamiliarNull, imageFamiliarBlack, imageFamiliarWhite, imageFamiliarOrange);
        allBuildingCard.add(buildingCard0);
        fillRadioButton(familiarNull, familiarBlack, familiarWhite, familiarOrange);
        allBuildingCard.add(buildingCard1);
        allBuildingCard.add(buildingCard2);
        allBuildingCard.add(buildingCard3);
        allBuildingCard.add(buildingCard4);
        allBuildingCard.add(buildingCard5);

        allPosition = new ArrayList<>();
        allPosition.add(new FamiliarPosition(imageProduction0));
    }

    /**
     * method that updates resources
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins, numberOfCoins);
        updateOneResource(wood, numberOfWood);
        updateOneResource(stone, numberOfStone);
        updateOneResource(servants, numberOfServants);
    }

    /**
     * method that refresh the scene
     */
    @Override
    public void refresh() {
        super.refresh();
        super.unselectedRadioButton(familiar);
        chatArea.setAccessibleText(loginBuilder.getChat().toString());

        if (positionSelected) {
            FamiliarPosition f = allPosition.get(allPosition.size() - 1);
            f.setFamiliarName("");
            f.setImage(null);
            if (allPosition.size() > 1) allPosition.remove(allPosition.size() - 1);
        }
        positionSelected = false;

        for (int i = 0; i < cardSelected.length; i++) {
            cardSelected[i] = false;
            allBuildingCard.get(i).setEffect(borderNull);
        }
    }

    /**
     * method that submit the action to do the production to the server
     */
    @FXML
    private void doProduction() {
        if (familiarChosen.equals("")) return;
        List<String> buildingCardSelected = new LinkedList<>();
        for (int i = 0; i < cardSelected.length; i++) {
            if (cardSelected[i]) {
                String cardName = nameOfBuilding.get(i);
                buildingCardSelected.add(cardName);
            }
        }

        positionSelected = false;
        writeOnChat("production done!\n");
        mainController.doProduction(familiarChosen, buildingCardSelected);
    }

    /**
     * method that select a card
     * @param index card to select
     */
    private void selectCard(int index) {
        if (!cardSelected[index]) {
            allBuildingCard.get(index).setEffect(borderGlow);
            cardSelected[index] = true;
        } else {
            cardSelected[index] = false;
            allBuildingCard.get(index).setEffect(borderNull);
        }
    }

    /**
     * method calls when the card 0 has been selected
     */
    public void selectBuildingCard0( ) {
        selectCard(0);
    }

    /**
     * method calls when the card 1 has been selected
     */
    public void selectBuildingCard1( ) {
        selectCard(1);
    }

    /**
     * method calls when the card 2 has been selected
     */
    public void selectBuildingCard2( ) {
        selectCard(2);
    }

    /**
     * method calls when the card 3 has been selected
     */
    public void selectBuildingCard3( ) {
        selectCard(3);
    }

    /**
     * method calls when the card 4 has been selected
     */
    public void selectBuildingCard4( ) {
        selectCard(4);
    }

    /**
     * method calls when the card 5 has been selected
     */
    public void selectBuildingCard5( ) {
        selectCard(5);
    }

    /**
     * method that send message to chat
     */
    public void sendChat() {
        sendChat(chatText);
    }

    /**
     * method called to place a familiar in the production positions
     */
    public void placeFamiliar() {
        if (super.placeFamiliarHarvProd(positionSelected,allPosition,familiarBox))
            positionSelected = true;
    }

    /**
     * method called to show the personal board
     */
    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.PRODUCTION);
    }

    /**
     * method called to update card in the production
     * @param buildings server building cards
     */
    void updateCards(List<BuildingCard> buildings) {
        super.updateCards(buildings, nameOfBuilding, allBuildingCard);
    }

    /**
     * method used to update positions in production
     * @param productions server familiars on production positions
     */
    void updatePosition(List<Production> productions) {
        super.updatePosition(productions, allPosition);
        if (allPosition.size() > 1) {
            super.updateHBox(allPosition.subList(1, allPosition.size()), familiarBox);
        }
    }

    /**
     * method called when the player has to make a bonus production action
     * @param diceValue dice value of the bonus action
     */
    void bonusProduction(int diceValue) {
        loginBuilder.setScene(SceneType.PRODUCTION, SceneType.PERSONAL_BOARD);
        blockButton();
        for (int i = 0; i < NUMBER_OF_CARD; i++) {
            cardSelected[i] = false;
            allBuildingCard.get(i).setEffect(borderNull);
        }
        writeOnChat("BONUS ACTION: you can perform a prodution action of value" + diceValue);
        writeOnChat("select the cards that you want to use");
    }

    /**
     * method called to block button for bonus action
     */
    private void blockButton() {
        super.blockButton(mainGameButton, personalBoard, buttonPlaceFamiliar);
        submit.setOnAction(event -> bonusAction());
    }

    /**
     * method called to perform the bonus action
     */
    private void bonusAction() {

        List<String> buildingCardSelected = new LinkedList<>();
        for (int i = 0; i < cardSelected.length; i++) {
            if (cardSelected[i]) {
                String cardName = nameOfBuilding.get(i);
                buildingCardSelected.add(cardName);
            }
        }

        mainController.doBonusProduction(buildingCardSelected);

    }

    /**
     * method called to unlock buttons after a bonus action
     */
    void unlockButton() {
        super.unlockButton(mainGameButton, personalBoard, buttonPlaceFamiliar);
        submit.setOnAction(event -> doProduction());
    }
}

