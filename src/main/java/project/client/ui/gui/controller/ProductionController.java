package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import project.controller.cardsfactory.BuildingCard;
import project.model.Production;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProductionController extends AbstractController{


    @FXML
    private Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button personalBoard;
    @FXML
    private Button buttonPlaceFamiliar;
    /**
     * radio button in which you can chose the familiar to use
     */
    @FXML
    private  RadioButton familiarOrange;
    @FXML
    private  RadioButton familiarWhite;
    @FXML
    private  RadioButton familiarBlack;
    @FXML
    private  RadioButton familiarNull;

    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */

    @FXML
    private  ImageView imageFamiliarNull;

    @FXML
    private  ImageView imageFamiliarBlack;

    @FXML
    private  ImageView imageFamiliarWhite;

    @FXML
    private  ImageView imageFamiliarOrange;


    @FXML
    private  Label numberOfCoins;

    @FXML
    private  Label numberOfWood;

    @FXML
    private  Label numberOfStone;

    @FXML
    private  Label numberOfServants;


    @FXML
    private  ImageView LorenzoMagnifico;


    @FXML
    private  TextField chatText;
    private final int numberOfCard = 6;

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
    private  ImageView imageProduction0;
    private List<FamiliarPosition> allPosition;


    private boolean[] cardSelected;
    private boolean positionSelected;

    private DropShadow borderGlow= new DropShadow();
    private DropShadow borderNull= new DropShadow();
    private int diceValueBonus;
    @FXML
    private Label chatArea;


    public ProductionController(){
         super();
        cardSelected = new boolean[numberOfCard];
        positionSelected = false;
        //todo guardare bene come fare sta parte in caso di piu di 4 persone nella zona

         /**
          * initializing of border to show wich card are selected
          */
         int depth = 70;//Setting the uniform variable for the glow width and height
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setProductionController(this);
    }

    public void initialize(){
        super.initialize();

        //example
        buildingCard0.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/commercialHub.png"))));

        nameOfBuilding = new ArrayList<>(6);
        allBuildingCard = new ArrayList<>(6);
        allPosition = new ArrayList<>();

    }

    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        int playerNumber;
        if (mainController.getNumberOfPlayer() > 2)
            playerNumber = 3;
        else
            playerNumber = 2;
        productionZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/produzione" + playerNumber + "Giocatori.png"))));

        fillFamilymember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);allBuildingCard.add(buildingCard0);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);
        allBuildingCard.add(buildingCard1);
        allBuildingCard.add(buildingCard2);
        allBuildingCard.add(buildingCard3);
        allBuildingCard.add(buildingCard4);
        allBuildingCard.add(buildingCard5);

        allPosition.add(new FamiliarPosition(imageProduction0));
    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    @Override
    public void refresh(){
        super.refresh();
        chatArea.setText(loginBuilder.getChat().toString());

        if (positionSelected)
            allPosition.remove(allPosition.size()-1);
        positionSelected = false;

        for (int i = 0; i<cardSelected.length; i++){
            cardSelected[i] = false;
            allBuildingCard.get(i).setEffect(borderNull);
        }
    }

    @FXML
    private void doProduction() {
        if (familiarChosen.equals(""))
            return;
        List<String> buildingCardSelected = new LinkedList<>();
        for (int i = 0; i<cardSelected.length; i++){
            if (cardSelected[i]){
                String cardName = nameOfBuilding.get(i);
                buildingCardSelected.add(cardName);
            }
        }

        positionSelected = false;
        mainController.doProduction(familiarChosen,buildingCardSelected);
    }

    private void selectCard(int index){
        if (!cardSelected[index]){
            allBuildingCard.get(index).setEffect(borderGlow);
            cardSelected[index] = true;
        }
        else{
            cardSelected[index] = false;
            allBuildingCard.get(index).setEffect(borderNull);
        }
    }

    public void selectBuildingCard0(MouseEvent MouseEvent) {
        selectCard(0);
    }

    public void selectBuildingCard1(MouseEvent MouseEvent) {
        selectCard(1);
    }

    public void selectBuildingCard2(MouseEvent MouseEvent) {
        selectCard(2);
    }

    public void selectBuildingCard3(MouseEvent MouseEvent) {
        selectCard(3);
    }

    public void selectBuildingCard4(MouseEvent MouseEvent) {
        selectCard(4);
    }

    public void selectBuildingCard5(MouseEvent MouseEvent) {
        selectCard(5);
    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    public void placeFamiliar(){
        if (familiarChosen.equals(""))
            return;
        if (positionSelected){
            if (allPosition.size()>1)
                allPosition.remove(allPosition.size() - 1);
            else
                allPosition.get(0).setFamiliarName("");
            super.placeFamiliar(allPosition,familiarBox);
        }
        if (mainController.getNumberOfPlayer() < 3 && familiarPlaced(allPosition) > 0)
            return;
        super.placeFamiliar(allPosition, familiarBox);
        positionSelected = true;
    }

    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.PRODUCTION);
    }

    public void updateCards(List<BuildingCard> buildings) {
        super.updateCards(buildings,nameOfBuilding,allBuildingCard);
    }

    public void updatePosition(List<Production> productions){
        super.updatePosition(productions,allPosition);
    }

    public void bonusProduction(int diceValue) {
        loginBuilder.setScene(SceneType.HARVESTER, SceneType.PERSONAL_BOARD);
        blockButton();
        this.diceValueBonus = diceValue;
        for (int i = 0; i < numberOfCard; i++){
            cardSelected[i] = false;
            allBuildingCard.get(i).setEffect(borderNull);
        }
        writeOnChat("BONUS ACTION: you can perform a prodution action of value" + diceValueBonus);
        writeOnChat("select the cards that you want to use");
    }

    private void blockButton() {
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> bonusAction());
    }

    private void bonusAction() {

        List<String> buildingCardSelected = new LinkedList<>();
        for (int i = 0; i<cardSelected.length; i++){
            if (cardSelected[i]){
                String cardName = nameOfBuilding.get(i);
                buildingCardSelected.add(cardName);
            }
        }

        mainController.doBonusProduction(buildingCardSelected);
        unlockButton();

    }

    private void unlockButton() {
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> doProduction());
    }
}

