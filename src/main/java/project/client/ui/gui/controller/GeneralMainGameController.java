package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import project.model.ExcommunicationZone;
import project.model.Score;
import project.model.Tower;
import project.model.Turn;

import java.util.ArrayList;

/**
 * controller of the main scene of gui. from this scene you can move around every scene of the game
 */
public class GeneralMainGameController extends AbstractController{


    @FXML
    private Label diceValueWhite;
    @FXML
    private Label diceValueBlack;
    @FXML
    private Label diceValueOrange;
    @FXML
    private ImageView excommunication1;
    @FXML
    private ImageView excommunication3;
    @FXML
    private ImageView excommunication2;
    @FXML
    private Label name;
    @FXML
    private ImageView gameboard;
    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;

    @FXML
    private ScrollPane chatArea;



    @FXML
    private ImageView towerGreen0;
    @FXML
    private ImageView towerGreen1;
    @FXML
    private ImageView towerGreen2;
    @FXML
    private ImageView towerGreen3;
    @FXML
    private ImageView towerBlue0;
    @FXML
    private ImageView towerBlue1;
    @FXML
    private ImageView towerBlue2;
    @FXML
    private ImageView towerBlue3;
    @FXML
    private ImageView towerYellow0;
    @FXML
    private ImageView towerYellow1;
    @FXML
    private ImageView towerYellow2;
    @FXML
    private ImageView towerYellow3;
    @FXML
    private ImageView towerPurple0;
    @FXML
    private ImageView towerPurple1;
    @FXML
    private ImageView towerPurple2;
    @FXML
    private ImageView towerPurple3;


    @FXML
    private  ImageView familiarGreen0;
    @FXML
    private  ImageView familiarGreen1;
    @FXML
    private  ImageView familiarGreen2;
    @FXML
    private  ImageView familiarGreen3;
    @FXML
    private  ImageView familiarBlue0;
    @FXML
    private  ImageView familiarBlue1;
    @FXML
    private  ImageView familiarBlue2;
    @FXML
    private  ImageView familiarBlue3;
    @FXML
    private  ImageView familiarYellow0;
    @FXML
    private  ImageView familiarYellow1;
    @FXML
    private  ImageView familiarYellow2;
    @FXML
    private  ImageView familiarYellow3;
    @FXML
    private  ImageView familiarPurple0;
    @FXML
    private  ImageView familiarPurple1;
    @FXML
    private  ImageView familiarPurple2;
    @FXML
    private  ImageView familiarPurple3;

    @FXML
    private ImageView faithPoint0;
    @FXML
    private ImageView faithPoint1;
    @FXML
    private ImageView faithPoint2;
    @FXML
    private ImageView faithPoint3;
    @FXML
    private ImageView faithPoint4;
    @FXML
    private ImageView faithPoint5;
    @FXML
    private ImageView faithPoint6;
    @FXML
    private ImageView faithPoint7;
    @FXML
    private ImageView faithPoint8;
    @FXML
    private ImageView faithPoint9;
    @FXML
    private ImageView faithPoint10;
    @FXML
    private ImageView faithPoint11;
    @FXML
    private ImageView faithPoint12;
    @FXML
    private ImageView faithPoint13;
    @FXML
    private ImageView faithPoint14;
    @FXML
    private ImageView faithPoint15;
    private ArrayList<ImageView> faithPointsArray;


    @FXML
    private ImageView turnOrder1;
    @FXML
    private ImageView turnOrder2;
    @FXML
    private ImageView turnOrder3;
    @FXML
    private ImageView turnOrder4;
    private ArrayList<ImageView> turnOrderArray;

    @FXML
    private TextField chatText;


    private TowerZone[][] myTower;
    private ImageView lastImageFaith;

    /**
     * constructor
     */
    public GeneralMainGameController(){
        super();
        lastImageFaith = new ImageView();
        faithPointsArray = new ArrayList<>(15);
        turnOrderArray = new ArrayList<>(4);
        myTower = new TowerZone[4][4];
    }

    /**
     * method called at the beginning to initialize the scene
     */
    @Override
    @FXML
    public void initialize(){
        super.initialize();

        faithPointsArray.add(faithPoint0);
        faithPointsArray.add(faithPoint1);
        faithPointsArray.add(faithPoint2);
        faithPointsArray.add(faithPoint3);
        faithPointsArray.add(faithPoint4);
        faithPointsArray.add(faithPoint5);
        faithPointsArray.add(faithPoint6);
        faithPointsArray.add(faithPoint7);
        faithPointsArray.add(faithPoint8);
        faithPointsArray.add(faithPoint9);
        faithPointsArray.add(faithPoint10);
        faithPointsArray.add(faithPoint11);
        faithPointsArray.add(faithPoint12);
        faithPointsArray.add(faithPoint13);
        faithPointsArray.add(faithPoint14);
        faithPointsArray.add(faithPoint15);

        turnOrderArray.add(turnOrder1);
        turnOrderArray.add(turnOrder2);
        turnOrderArray.add(turnOrder3);
        turnOrderArray.add(turnOrder4);

        myTower[0][0] = new TowerZone(towerGreen0,familiarGreen0);
        myTower[0][1] = new TowerZone(towerGreen1,familiarGreen1);
        myTower[0][2] = new TowerZone(towerGreen2,familiarGreen2);
        myTower[0][3] = new TowerZone(towerGreen3,familiarGreen3);
        myTower[1][0] = new TowerZone(towerBlue0,familiarBlue0);
        myTower[1][1] = new TowerZone(towerBlue1,familiarBlue1);
        myTower[1][2] = new TowerZone(towerBlue2,familiarBlue2);
        myTower[1][3] = new TowerZone(towerBlue3,familiarBlue3);
        myTower[2][0] = new TowerZone(towerYellow0,familiarYellow0);
        myTower[2][1] = new TowerZone(towerYellow1,familiarYellow1);
        myTower[2][2] = new TowerZone(towerYellow2,familiarYellow2);
        myTower[2][3] = new TowerZone(towerYellow3,familiarYellow3);
        myTower[3][0] = new TowerZone(towerPurple0,familiarPurple0);
        myTower[3][1] = new TowerZone(towerPurple1,familiarPurple1);
        myTower[3][2] = new TowerZone(towerPurple2,familiarPurple2);
        myTower[3][3] = new TowerZone(towerPurple3,familiarPurple3);

    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setGeneralGameController(this);
    }

    /**
     * method used to refresh the scene
     */
    @Override
    public void refresh() {
        chatArea.setContent(new Text(loginBuilder.getChat().toString()));
    }

    /**
     * method used to upload images during the initialization
     */
    @Override
    public void uploadImages(){
        super.uploadImages();
        gameboard.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/gameboard"  + mainController.getNumberOfPlayer() + "Giocatori.png"))));
    }

    /**
     * method used to update resources in the scene
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    /**
     * method called to switch to tower scene
     */
    public void goToTowers() {
        loginBuilder.setScene(SceneType.TOWERS,SceneType.MAIN);
    }

    /**
     * method called to switch to production scene
     */
    public void goToProduction() {
        loginBuilder.setScene(SceneType.PRODUCTION,SceneType.MAIN);
    }

    /**
     * method called to switch to market scene
     */
    public void goToMarket() {
        loginBuilder.setScene(SceneType.MARKET,SceneType.MAIN);
    }

    /**
     * method called to show points scene
     */
    public void showPoints() {
        loginBuilder.showPoints();
    }

    /**
     * method called to switch to leader scene
     */
    public void showLeaderCard() {
        loginBuilder.setScene(SceneType.LEADER, SceneType.MAIN);

    }

    /**
     * method used to send messages in chat
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     * method used to skip turn
     */
    public void skipTurn() {
        if (!mainController.isMyTurn())
            loginBuilder.writeOnMyChat("it isn't your turn!\n");
        else
            mainController.skipTurn();
    }

    /**
     * method called to switch to harvester scene
     */
    public void goToHarvester() {
        loginBuilder.setScene(SceneType.HARVESTER,SceneType.MAIN);
    }

    /**
     * method called to switch to council scene
     */
    public void goToCouncil() {
        loginBuilder.setScene(SceneType.COUNCIL,SceneType.MAIN);
    }

    /**
     * method called to switch to personal board scene
     */
    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.MAIN);
    }

    /**
     * method used to update turn images
     * @param playerTurn players turn
     */
    void updateTurn(Turn playerTurn) {
        int current = 0;
        if (playerTurn == null)
            return;
        for (String s: playerTurn.getPlayersColor()){
            turnOrderArray.get(current).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + s + "Pedone.png"))));
            current++;
        }
    }

    /**
     * method used to update towers position
     * @param towers towers position arrived from the update
     */
    void updatePosition(Tower[][] towers) {
        super.updatePosition(towers, myTower);
    }

    /**
     * method used to show a choice popUp
     * @param bothPaymentAvailable title of choice popUp
     * @param s first possible choice
     * @param s1 second possible choice
     */
    void setChoice(String bothPaymentAvailable, String s, String s1) {
        loginBuilder.showChoice(bothPaymentAvailable,s,s1);
        mainController.addIntegerQueue(loginBuilder.getChoiceDone());
    }

    /**
     * method used to update faith track
     * @param uiScore score arrived from update
     */
    public void setScore(Score uiScore) {
        lastImageFaith.setImage(null);
        ImageView faith = faithPointsArray.get(uiScore.getFaithPoints());
        faith.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Pedone.png"))));
        lastImageFaith = faith;
    }

    /**
     * method called during the update of dices
     * @param diceValue new dice value top update
     */
    public void setDice(int[] diceValue){
        diceValueBlack.setText(String.valueOf(diceValue[0]));
        diceValueWhite.setText(String.valueOf(diceValue[1]));
        diceValueOrange.setText(String.valueOf(diceValue[2]));
    }

    /**
     * method used to update the excommunication tiles
     * @param excommunicationZone excommunication tiles arrived from the update
     */
    void excommunicationUpdate(ExcommunicationZone[] excommunicationZone) {
        excommunication1.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_1_" + excommunicationZone[0].getCardForThisPeriod().getIdCard() + ".png"))));
        excommunication2.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_2_" + excommunicationZone[1].getCardForThisPeriod().getIdCard() + ".png"))));
        excommunication3.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_3_" + excommunicationZone[2].getCardForThisPeriod().getIdCard() + ".png"))));

        excommunication1.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication1.getImage()));
        excommunication2.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication2.getImage()));
        excommunication3.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication3.getImage()));
    }

    /**
     * method used to set the username in the main scene
     * @param usernameChosen player's username
     */
    public void setName(String usernameChosen) {
        name.setText(usernameChosen);
    }

    /**
     * getter of excommunication tile image used during the askForPraying method
     * @param currentPeriod current period
     * @return excommunication tile image
     */
    Image getExcommunicationImage(int currentPeriod) {
        if (currentPeriod == 0) {
            return excommunication1.getImage();
        } else if (currentPeriod == 1) {
            return excommunication2.getImage();
        } else if (currentPeriod == 2) {
            return excommunication3.getImage();
        }
        return null;
    }
}
