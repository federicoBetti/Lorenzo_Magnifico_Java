package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controller.Constants;
import project.model.Tower;

/**
 * Created by federico on 10/06/17.
 */
public class TowersController extends AbstractController {


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
    private RadioButton familiarOrange;
    @FXML
    private RadioButton familiarWhite;
    @FXML
    private RadioButton familiarBlack;
    @FXML
    private RadioButton familiarNull;

    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */

    @FXML
    private ImageView imageFamiliarNull;

    @FXML
    private ImageView imageFamiliarBlack;

    @FXML
    private ImageView imageFamiliarWhite;

    @FXML
    private ImageView imageFamiliarOrange;


    @FXML
    private TextField chatText;
    /**
     * queste sono le imageView dove dentro ci staranno le immagini delle carte
     */
    @FXML
    private ImageView green3;
    @FXML
    private ImageView green2;
    @FXML
    private ImageView green1;
    @FXML
    private ImageView green0;
    @FXML
    private ImageView blue3;
    @FXML
    private ImageView blue2;
    @FXML
    private ImageView blue1;
    @FXML
    private ImageView blue0;
    @FXML
    private ImageView yellow3;
    @FXML
    private ImageView yellow2;
    @FXML
    private ImageView yellow1;
    @FXML
    private ImageView yellow0;
    @FXML
    private ImageView purple3;
    @FXML
    private ImageView purple2;
    @FXML
    private ImageView purple1;
    @FXML
    private ImageView purple0;



    /**
     * queste sono le image view che si trovano nei posti slezione di ogni carta. quando verrà selezioanta una carta
     * il familiare selezionato verrano posizionati li
     */
    @FXML
    private ImageView familiarGreen3;
    @FXML
    private ImageView familiarGreen2;
    @FXML
    private ImageView familiarGreen1;
    @FXML
    private ImageView familiarGreen0;
    @FXML
    private ImageView familiarBlue3;
    @FXML
    private ImageView familiarBlue2;
    @FXML
    private ImageView familiarBlue1;
    @FXML
    private ImageView familiarBlue0;
    @FXML
    private ImageView familiarYellow3;
    @FXML
    private ImageView familiarYellow2;
    @FXML
    private ImageView familiarYellow1;
    @FXML
    private ImageView familiarYellow0;
    @FXML
    private ImageView familiarPurple3;
    @FXML
    private ImageView familiarPurple2;
    @FXML
    private ImageView familiarPurple1;
    @FXML
    private ImageView familiarPurple0;


    @FXML
    private ToggleGroup familiar;



    private ImageView lastFamiiarPlaced;
    private int towerColour;
    private int floor;
    private String familiarColour;


    private TowerZone[][] myTower;
    private String bonusCardType;
    @FXML
    private Label chatArea;
    private boolean bonusAction;


    public TowersController() {
        super();
        System.out.print("sono nel controller");
        myTower = new TowerZone[4][4];
        towerColour = -1;
        floor = -1;
        bonusAction = false;
        familiarColour = null;
        buttonPlaceFamiliar = new Button();
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setTowerController(this);
    }

    @Override
    public void refresh() {
        super.refresh();
        //2
        // unlockButton();
        chatArea.setText(loginBuilder.getChat().toString());
        lastFamiiarPlaced.setImage(null);
        if (floor != -1){
            myTower[towerColour][floor].setFamiliarName("");
        }
        floor = -1;
        towerColour = -1;
        familiarChosen = "";

    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize() {
        super.initialize();

        lastFamiiarPlaced = new ImageView();

        myTower[0][0] = new TowerZone(green0,familiarGreen0);
        myTower[0][1] = new TowerZone(green1,familiarGreen1);
        myTower[0][2] = new TowerZone(green2,familiarGreen2);
        myTower[0][3] = new TowerZone(green3,familiarGreen3);
        myTower[1][0] = new TowerZone(blue0,familiarBlue0);
        myTower[1][1] = new TowerZone(blue1,familiarBlue1);
        myTower[1][2] = new TowerZone(blue2,familiarBlue2);
        myTower[1][3] = new TowerZone(blue3,familiarBlue3);
        myTower[2][0] = new TowerZone(yellow0,familiarYellow0);
        myTower[2][1] = new TowerZone(yellow1,familiarYellow1);
        myTower[2][2] = new TowerZone(yellow2,familiarYellow2);
        myTower[2][3] = new TowerZone(yellow3,familiarYellow3);
        myTower[3][0] = new TowerZone(purple0,familiarPurple0);
        myTower[3][1] = new TowerZone(purple1,familiarPurple1);
        myTower[3][2] = new TowerZone(purple2,familiarPurple2);
        myTower[3][3] = new TowerZone(purple3,familiarPurple3);


        System.out.print("sono nel initaize");
    }

    public void uploadImages(){
        super.uploadImages();
        fillFamilymember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);

    }

    public void showCardGreen3() {
        loginBuilder.showCardZoomed(green3.getImage());
    }

    public void showCardGreen2() {
        loginBuilder.showCardZoomed(green2.getImage());
    }

    public void showCardGreen1() {
        loginBuilder.showCardZoomed(green1.getImage());
    }

    public void showCardGreen0() {
        loginBuilder.showCardZoomed(green0.getImage());
    }

    public void showCardBlue3() {
        loginBuilder.showCardZoomed(blue3.getImage());
    }

    public void showCardBlue2() {
        loginBuilder.showCardZoomed(blue2.getImage());
    }

    public void showCardBlue1() {
        loginBuilder.showCardZoomed(blue1.getImage());
    }

    public void showCardBlue0() {
        loginBuilder.showCardZoomed(blue0.getImage());
    }

    public void showCardYellow0() {
        loginBuilder.showCardZoomed(yellow0.getImage());
    }

    public void showCardYellow1() {
        loginBuilder.showCardZoomed(yellow1.getImage());
    }

    public void showCardYellow2() {
        loginBuilder.showCardZoomed(yellow2.getImage());
    }

    public void showCardYellow3() {
        loginBuilder.showCardZoomed(yellow3.getImage());
    }

    public void showCardPurple0() {
        loginBuilder.showCardZoomed(purple0.getImage());
    }

    public void showCardPurple1() {
        loginBuilder.showCardZoomed(purple1.getImage());
    }

    public void showCardPurple2() {
        loginBuilder.showCardZoomed(purple2.getImage());
    }

    public void showCardPurple3() {
        loginBuilder.showCardZoomed(purple3.getImage());
    }


    public void takeCard(int tower, int floor){
        if (!mainController.isMyTurn())
            return;
        if (!bonusAction) {
            if (myTower[tower][floor].getFamiliarImage().getImage() == null) {
                lastFamiiarPlaced.setImage(null);
                lastFamiiarPlaced = myTower[tower][floor].getFamiliarImage();
                myTower[tower][floor].setFamiliarImage(getTrueFamiliarImage());
                selectCard(tower, floor);
            }
        }
        else {
            System.err.println("ho cliccato per mettere un familiare fake in posizione "+tower + "   " + floor);
            lastFamiiarPlaced.setImage(null);
            lastFamiiarPlaced = myTower[tower][floor].getFamiliarImage();
            myTower[tower][floor].setFamiliarImage(new Image(String.valueOf(getClass().getResource("/images/familiar/empty" + mainController.getColour() + "neutral.png"))));
            selectCard(tower, floor);

        }

    }
    public void takeCardGreen3() {
        takeCard(0,3);
    }

    public void takeCardGreen2() {
        takeCard(0,2);
    }

    public void takeCardGreen1() {
        takeCard(0, 1);
    }

    public void takeCardGreen0() {
        takeCard(0,0);
    }


    public void takeCardBlue3() {
        takeCard(1,3);
    }

    public void takeCardBlue2() {
        takeCard(1,2);
    }

    public void takeCardBlue1() {
        takeCard(1,1);
    }

    public void takeCardBlue0() {
        takeCard(1,0);
    }

    public void takeCardYellow0() {
        takeCard(2,0);
    }

    public void takeCardYellow1() {
        takeCard(2,1);
    }

    public void takeCardYellow2() {
        takeCard(2,2);
    }

    public void takeCardYellow3() {
        takeCard(2,3);
    }

    public void takeCardPurple0() {
        takeCard(3,0);
    }

    public void takeCardPurple1() {
        takeCard(3,1);
    }

    public void takeCardPurple2() {
        takeCard(3,2);
    }

    public void takeCardPurple3(){
        takeCard(3,3);
    }

    private void selectCard(int towerColour, int floor) {
        this.familiarColour = familiarChosen;
        this.floor = floor;
        this.towerColour = towerColour;
    }


    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.TOWERS);
    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    @FXML
    private void takeCard() {
        if (floor != -1){
            String towerColourString = getTowerColour(towerColour);
            lastFamiiarPlaced = new ImageView();
            mainController.takeDevCard(towerColourString,floor, familiarChosen);
            floor = -1;
            towerColour = -1;
        }
    }

    private String getTowerColour(int towerColour) {
        switch (towerColour) {
            case 0:
                return Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD;
            case 1:
                return Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD;
            case 2:
                return Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD;
            case 3:
                return Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD;
            default:
                return "";
        }

    }

    public void updatePosition(Tower[][] towers) {
        super.updatePosition(towers, myTower);
    }


    public void takeBonusCard(String kindOfCard, String printBonusAction) {
        bonusAction = true;

        writeOnChat(printBonusAction);
        writeOnChat("click on the position next to the card you want"); //attenione a quando vinee l'update dei familiari che potrebbe essere che non ci sono familairi disponibili
        blockButton();
        System.out.println("HO CHIUSO I BOTTONIFF");
        this.bonusCardType = kindOfCard;
    }

    private void blockButton(){
        System.out.println("STO CHIUDENDO I BOTTONI");
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takeBonusCard());
    }

    void unlockButton(){
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takeCard());
        bonusAction = false;
    }

    private void takeBonusCard() {
        if (towerColour != -1 && floor != -1) {
            String towerColourString = getTowerColour(towerColour);
            if (towerColourString.equals(bonusCardType) || bonusCardType.equals(Constants.ALL_COLOURS)) {
                lastFamiiarPlaced = new ImageView();
                System.out.println("sono nel tasto prendere la carta di azione bonus");
                mainController.takeBonusCardAction(floor, towerColourString);
                unlockButton();
                floor = -1;
                towerColour = -1;
            }
            else writeOnChat("you have chosen the wrong tower!\n");
        }
        else writeOnChat("you haven't selected any card!\n");
    }
}
