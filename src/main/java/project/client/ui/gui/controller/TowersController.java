package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import project.controller.Constants;
import project.model.Tower;

/**
 * controller of the tower scene
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


    @FXML
    private RadioButton familiarOrange;
    @FXML
    private RadioButton familiarWhite;
    @FXML
    private RadioButton familiarBlack;
    @FXML
    private RadioButton familiarNull;




    @FXML
    private TextField chatText;


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



    @FXML
    private ImageView imageFamiliarNull;

    @FXML
    private ImageView imageFamiliarBlack;

    @FXML
    private ImageView imageFamiliarWhite;

    @FXML
    private ImageView imageFamiliarOrange;


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


    private TowerZone[][] myTower;
    private String bonusCardType;
    @FXML
    private ScrollPane chatArea;
    private boolean bonusAction;

    /**
     * constructor
     */
    public TowersController() {
        super();
        myTower = new TowerZone[4][4];
        towerColour = -1;
        floor = -1;
        bonusAction = false;
        buttonPlaceFamiliar = new Button();
    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setTowerController(this);
    }

    /**
     * method used to refresh the scene
     */
    @Override
    public void refresh() {
        super.refresh();
        super.unselectedRadioButton(familiar);

        chatArea.setContent(new Text(loginBuilder.getChat().toString()));
        lastFamiiarPlaced.setImage(null);
        if (floor != -1){
            myTower[towerColour][floor].setFamiliarName("");
        }
        System.err.println("tower refresh");
        floor = -1;
        towerColour = -1;
        familiarChosen = "";

    }

    /**
     * method used to update the resource, in this case there aren't resources displayed in the scene. this method has to be implemented because
     * it has been declared abstract in the abstract controller
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {
        //correct
    }

    /**
     * initialization
     */
    @Override
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

    }

    /**
     * method used to upload images during initialization
     */
    @Override
    public void uploadImages(){
        super.uploadImages();
        fillFamilyMember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);

    }

    /**
     * method called to zoom in the territory card 3
     */
    public void showCardGreen3() {
        loginBuilder.showCardZoomed(green3.getImage());
    }

    /**
     * method called to zoom in the territory card 2
     */
    public void showCardGreen2() {
        loginBuilder.showCardZoomed(green2.getImage());
    }

    /**
     * method called to zoom in the territory card 1
     */
    public void showCardGreen1() {
        loginBuilder.showCardZoomed(green1.getImage());
    }

    /**
     * method called to zoom in the territory card 0
     */
    public void showCardGreen0() {
        loginBuilder.showCardZoomed(green0.getImage());
    }

    /**
     * method called to zoom in the character card 3
     */
    public void showCardBlue3() {
        loginBuilder.showCardZoomed(blue3.getImage());
    }

    /**
     * method called to zoom in the character card 2
     */
    public void showCardBlue2() {
        loginBuilder.showCardZoomed(blue2.getImage());
    }

    /**
     * method called to zoom in the character card 1
     */
    public void showCardBlue1() {
        loginBuilder.showCardZoomed(blue1.getImage());
    }

    /**
     * method called to zoom in the character card 0
     */
    public void showCardBlue0() {
        loginBuilder.showCardZoomed(blue0.getImage());
    }

    /**
     * method called to zoom in the building card 0
     */
    public void showCardYellow0() {
        loginBuilder.showCardZoomed(yellow0.getImage());
    }

    /**
     * method called to zoom in the building card 1
     */
    public void showCardYellow1() {
        loginBuilder.showCardZoomed(yellow1.getImage());
    }
/**
method called to zoom in the building card 2
 */
    public void showCardYellow2() {
        loginBuilder.showCardZoomed(yellow2.getImage());
    }

    /**
     * method called to zoom in the building card 3
     */
    public void showCardYellow3() {
        loginBuilder.showCardZoomed(yellow3.getImage());
    }

    /**
     * method called to zoom in the venture card 0
     */
    public void showCardPurple0() {
        loginBuilder.showCardZoomed(purple0.getImage());
    }

    /**
     * method called to zoom in the venture card 1
     */
    public void showCardPurple1() {
        loginBuilder.showCardZoomed(purple1.getImage());
    }

    /**
     * method called to zoom in the venture card 2
     */
    public void showCardPurple2() {
        loginBuilder.showCardZoomed(purple2.getImage());
    }

    /**
     * method called to zoom in the venture card 3
     */
    public void showCardPurple3() {
        loginBuilder.showCardZoomed(purple3.getImage());
    }

    /**
     * method called to select a developement card and place the right familiar on the position
     * @param tower tower selected
     * @param floor floor selected
     */
    private void takeCard(int tower, int floor){
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
            lastFamiiarPlaced.setImage(null);
            lastFamiiarPlaced = myTower[tower][floor].getFamiliarImage();
            myTower[tower][floor].setFamiliarImage(new Image(String.valueOf(getClass().getResource("/images/familiar/empty" + mainController.getColour() + "neutral.png"))));
            selectCard(tower, floor);
        }
    }

    /**
     * method called to select the territory card 3
     */
    public void takeCardGreen3() {
        takeCard(0,3);
    }

    /**
     * method called to select the territory card 2
     */
    public void takeCardGreen2() {
        takeCard(0,2);
    }

    /**
     * method called to select the territory card 1
     */
    public void takeCardGreen1() {
        takeCard(0, 1);
    }

    /**
     * method called to select the territory card 0
     */
    public void takeCardGreen0() {
        takeCard(0,0);
    }

    /**
     * method called to select the character card 3
     */
    public void takeCardBlue3() {
        takeCard(1,3);
    }

    /**
     * method called to select the character card 2
     */
    public void takeCardBlue2() {
        takeCard(1,2);
    }

    /**
     * method called to select the character card 1
     */
    public void takeCardBlue1() {
        takeCard(1,1);
    }

    /**
     * method called to select the character card 0
     */
    public void takeCardBlue0() {
        takeCard(1,0);
    }

    /**
     * method called to select the building card 0
     */
    public void takeCardYellow0() {
        takeCard(2,0);
    }

    /**
     * method called to select the building card 1
     */
    public void takeCardYellow1() {
        takeCard(2,1);
    }

    /**
     * method called to select the building card 2
     */
    public void takeCardYellow2() {
        takeCard(2,2);
    }

    /**
     * method called to select the building card 3
     */
    public void takeCardYellow3() {
        takeCard(2,3);
    }

    /**
     * method called to select the venture card 0
     */
    public void takeCardPurple0() {
        takeCard(3,0);
    }

    /**
     * method called to select the venture card 1
     */
    public void takeCardPurple1() {
        takeCard(3,1);
    }

    /**
     * method called to select the venture card 2
     */
    public void takeCardPurple2() {
        takeCard(3,2);
    }

    /**
     * method called to select the venture card 3
     */
    public void takeCardPurple3(){
        takeCard(3,3);
    }

    /**
     * method used to select the card during a bonus action
     * @param towerColour tower selected
     * @param floor floor selected
     */
    private void selectCard(int towerColour, int floor) {
        this.floor = floor;
        this.towerColour = towerColour;
    }

    /**
     * method called to show personal board scene
     */
    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.TOWERS);
    }

    /***
     * methos used to how a message in chat
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     * method used to submit a take development card action to the server
     */
    @FXML
    private void takeCard() {
        System.err.println("sto per fare una tower take normale");
        if (floor != -1){
            String towerColourString = getTowerColour(towerColour);
            lastFamiiarPlaced.setImage(null);
            lastFamiiarPlaced = new ImageView();
            mainController.takeDevCard(towerColourString,floor, familiarChosen);
            floor = -1;
            towerColour = -1;
            refresh();
        }
    }

    /**
     * method that give the color of a tower
     * @param towerColour tower passed
     * @return string of the tower passed
     */
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

    /**
     * method called to make an update on the towers
     * @param towers tower arrived from update
     */
    void updatePosition(Tower[][] towers) {
        super.updatePosition(towers, myTower);
    }

    /**
     * method that arrives from the server, used to performa a bonus action of take a development card
     * @param kindOfCard kind of bonus card to take
     * @param printBonusAction string to print in the chat
     */
    void takeBonusCard(String kindOfCard, String printBonusAction) {
        bonusAction = true;

        this.bonusCardType = kindOfCard;
        System.err.println("kind of card " + kindOfCard);
        writeOnChat(printBonusAction);
        writeOnChat("click on the position next to the card you want"); //attenione a quando vinee l'update dei familiari che potrebbe essere che non ci sono familairi disponibili
        blockButton();
    }

    /**
     * method that block button for bonus actions
     */
    private void blockButton(){
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takeBonusCard());
    }

    /**
     * method that unlock buttons for bonus actions
     */
    void unlockButton(){
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takeCard());
        bonusAction = false;
    }

    /**
     * method called to perform a bonus take development card action
     */
    private void takeBonusCard() {
        System.out.println("tower color e floor " + towerColour + floor);
        if (floor != -1) {
            System.out.println("sto per mandare l'azione del bonus tower acion");
            String towerColourString = getTowerColour(towerColour);
            System.err.println("toer string, bonus card type " + towerColourString + bonusCardType);
            if (towerColourString.equals(bonusCardType) || bonusCardType.equals(Constants.ALL_COLOURS)) {
                System.out.println("sto per mandare l'azione del bonus tower acion");
                lastFamiiarPlaced.setImage(null);
                lastFamiiarPlaced = new ImageView();
                System.out.println("sto per mandare l'azione del bonus tower acion");
                mainController.takeBonusCardAction(floor, towerColourString);
                floor = -1;
                towerColour = -1;
            }
            else writeOnChat("you have chosen the wrong tower!\n");
        }
        else writeOnChat("you haven't selected any card!\n");
    }
}
