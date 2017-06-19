package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.TowerIterator;
import project.controller.Constants;
import project.model.Tower;

/**
 * Created by federico on 10/06/17.
 */
public class TowersController extends AbstractController {


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


    @FXML
    private Button mainGameButton;

    private ImageView lastFamiiarPlaced;
    private int towerColour;
    private int floor;
    private String familiarColour;


    private TowerZone[][] myTower;
    private String bonusCardType;


    public TowersController() {
        super();
        System.out.print("sono nel controller");
        myTower = new TowerZone[4][4];
        towerColour = -1;
        floor = -1;
        familiarColour = null;
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setTowerController(this);
    }

    @Override
    public void refresh() {

    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize() {

        lastFamiiarPlaced = new ImageView();

       green0.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/commercialHub.png"))));
        green1.setImage(new Image(String.valueOf(getClass().getResource("/images/woods.png"))));
        myTower[0][0] = new TowerZone(green0,familiarGreen0);
        myTower[1][0] = new TowerZone(green1,familiarGreen1);
        myTower[2][0] = new TowerZone(green2,familiarGreen2);
        myTower[3][0] = new TowerZone(green3,familiarGreen3);
        myTower[0][1] = new TowerZone(blue0,familiarBlue0);
        myTower[1][1] = new TowerZone(blue1,familiarBlue1);
        myTower[2][1] = new TowerZone(blue2,familiarBlue2);
        myTower[3][1] = new TowerZone(blue3,familiarBlue3);
        myTower[0][2] = new TowerZone(yellow0,familiarYellow0);
        myTower[1][2] = new TowerZone(yellow1,familiarYellow1);
        myTower[2][2] = new TowerZone(yellow2,familiarYellow2);
        myTower[3][2] = new TowerZone(yellow3,familiarYellow3);
        myTower[0][3] = new TowerZone(purple0,familiarPurple0);
        myTower[1][3] = new TowerZone(purple1,familiarPurple1);
        myTower[2][3] = new TowerZone(purple2,familiarPurple2);
        myTower[3][3] = new TowerZone(purple3,familiarPurple3);


        System.out.print("sono nel initaize");
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


    private void takeCard(int floor, int tower){
        if (myTower[floor][tower].getFamiliarName() == null) {
            lastFamiiarPlaced.setImage(null);
            lastFamiiarPlaced = myTower[floor][tower].getFamiliarImage();
            myTower[floor][tower].setFamiliarImage(getTrueFamiliarImage());
            selectCard(tower, floor);
        }

    }
    public void takeCardGreen3() {
        takeCard(3,0);

    }

    public void takeCardGreen2() {
        takeCard(2,0);

    }

    public void takeCardGreen1() {
        takeCard(1,0);

    }

    public void takeCardGreen0() {
        takeCard(0,0);
    }


    public void takeCardBlue3() {
        takeCard(3,1);
    }

    public void takeCardBlue2() {
        takeCard(2,1);
    }

    public void takeCardBlue1() {
        takeCard(1,1);
    }

    public void takeCardBlue0() {
        takeCard(0,1);
    }

    public void takeCardYellow0() {
        takeCard(0,2);
    }

    public void takeCardYellow1() {
        takeCard(1,2);
    }

    public void takeCardYellow2() {
        takeCard(2,2);
    }

    public void takeCardYellow3() {
        takeCard(3,2);
    }

    public void takeCardPurple0() {
        takeCard(0,3);
    }

    public void takeCardPurple1() {
        takeCard(1,3);
    }

    public void takeCardPurple2() {
        takeCard(2,3);
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


    public void takeCard() {
        if (floor != -1){
            String towerColourString = getTowerColour(towerColour);
            mainController.takeDevCard(towerColourString, String.valueOf(floor), familiarChosen);
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

    public void updatePosition(Tower[][] towers){
        int floorNumber;
        int towerNumber;
        TowerIterator towerIterator = new TowerIterator();
        for (;towerIterator.hasNext();towerIterator.next()){
            floorNumber = towerIterator.getFloor();
            towerNumber = towerIterator.getTowerNumber();
            Tower serverTower = towers[floorNumber][towerNumber];
            TowerZone guiTower = myTower[floorNumber][towerNumber];

            if (serverTower.getCardOnThisFloor() == null && guiTower.getCardName() == null){
                //niente, ho dovuto fare questi if separati perche nel secondo accedo al nome della carta e se lo facessi con una carta
                //null mi andrebbe in null pointer
            }
            else if (serverTower.getCardOnThisFloor() == null){
                modifyCard(guiTower,null);
            }
            else {
                if (serverTower.getCardOnThisFloor().getName().equals(guiTower.getCardName())){
                    //niente
                }
                else{
                    modifyCard(guiTower,serverTower.getCardOnThisFloor().getName());
                }
            }

            if (serverTower.getFamiliarOnThisPosition() == null && guiTower.getFamiliarName() == null){
                // niente
            }
            else if (serverTower.getFamiliarOnThisPosition() == null)
                modifyFamiliar(guiTower,null);
            else {
                if (serverTower.getFamiliarOnThisPosition().toString().equals(guiTower.getFamiliarName())){
                    //niente
                }
                else{
                    modifyFamiliar(guiTower,serverTower.getFamiliarOnThisPosition().toString());
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

    public void takeBonusCard(String kindOfCard, String printBonusAction) {
        loginBuilder.setScene(SceneType.TOWERS,SceneType.PERSONAL_BOARD);
        writeOnChat(printBonusAction);
        writeOnChat("you can use a random familiar to select the card"); //attenione a quando vinee l'update dei familiari che potrebbe essere che non ci sono familairi disponibili
        blockButton();
        this.bonusCardType = kindOfCard;
    }

    protected void blockButton(){
        super.blockButton();
        submit.setOnAction(#takeBonusCard());
    }

    protected void unlockButton(){
        super.unlockButton();
        submit.setOnAction(#takeCard(););
    }

    private void takeBonusCard() {
        String towerColourString = getTowerColour(towerColour);
        if (towerColourString.equals(bonusCardType || bonusCardType.equals(Constants.))){
            mainController.takeBonusCardAction(floor,towerColourString);
            unlockButton();
        }
    }
}
