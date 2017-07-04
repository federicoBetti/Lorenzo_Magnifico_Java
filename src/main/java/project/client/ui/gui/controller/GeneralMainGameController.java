package project.client.ui.gui.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import project.model.ExcommunicationZone;
import project.model.Score;
import project.model.Tower;
import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 10/06/17.
 */
public class GeneralMainGameController extends AbstractController{


    public Label diceValueWhite;
    public Label diceValueBlack;
    public Label diceValueOrange;
    public ImageView excommunication1;
    public ImageView excommunication3;
    public ImageView excommunication2;
    @FXML
    private Pane gameboard;
    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;

    @FXML
    private Button send;
    @FXML
    private ScrollPane chatArea;
    @FXML
    private Button goToTowersButton;
    @FXML
    private Button goToMarketButton;
    @FXML
    private Button goToCouncilPalaceButton;
    @FXML
    private Button personalBoardButton;
    @FXML
    private Button pointsButton;
    @FXML
    private Button leaderCardButton;
    @FXML
    private Button skipTurnButton;



    @FXML
    private ImageView towerGreen_0;
    @FXML
    private ImageView towerGreen_1;
    @FXML
    private ImageView towerGreen_2;
    @FXML
    private ImageView towerGreen_3;
    @FXML
    private ImageView towerBlue_0;
    @FXML
    private ImageView towerBlue_1;
    @FXML
    private ImageView towerBlue_2;
    @FXML
    private ImageView towerBlue_3;
    @FXML
    private ImageView towerYellow_0;
    @FXML
    private ImageView towerYellow_1;
    @FXML
    private ImageView towerYellow_2;
    @FXML
    private ImageView towerYellow_3;
    @FXML
    private ImageView towerPurple_0;
    @FXML
    private ImageView towerPurple_1;
    @FXML
    private ImageView towerPurple_2;
    @FXML
    private ImageView towerPurple_3;


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

    public ImageView[][] torri = new ImageView[4][4];


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


    public GeneralMainGameController(){
        super();
        lastImageFaith = new ImageView();
        System.out.print("sono nel controller");
        faithPointsArray = new ArrayList<>(15);
        turnOrderArray = new ArrayList<>(4);
        myTower = new TowerZone[4][4];
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize(){
        super.initialize();
        // da eliminare


        
          ImageView familiarGreen0 = new ImageView();
        
          ImageView familiarGreen1 = new ImageView();
        
          ImageView familiarGreen2 = new ImageView();
        
          ImageView familiarGreen3 = new ImageView();
        
          ImageView familiarBlue0 = new ImageView();
        
          ImageView familiarBlue1 = new ImageView();
        
          ImageView familiarBlue2 = new ImageView();
        
          ImageView familiarBlue3 = new ImageView();
        
          ImageView familiarYellow0 = new ImageView();
        
          ImageView familiarYellow1 = new ImageView();
        
          ImageView familiarYellow2 = new ImageView();
        
          ImageView familiarYellow3 = new ImageView();
        
          ImageView familiarPurple0 = new ImageView();
        
          ImageView familiarPurple1 = new ImageView();
        
          ImageView familiarPurple2 = new ImageView();
        
          ImageView familiarPurple3 = new ImageView();
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


        myTower[0][0] = new TowerZone(towerGreen_0,familiarGreen0);
        myTower[0][1] = new TowerZone(towerGreen_1,familiarGreen1);
        myTower[0][2] = new TowerZone(towerGreen_2,familiarGreen2);
        myTower[0][3] = new TowerZone(towerGreen_3,familiarGreen3);
        myTower[1][0] = new TowerZone(towerBlue_0,familiarBlue0);
        myTower[1][1] = new TowerZone(towerBlue_1,familiarBlue1);
        myTower[1][2] = new TowerZone(towerBlue_2,familiarBlue2);
        myTower[1][3] = new TowerZone(towerBlue_3,familiarBlue3);
        myTower[2][0] = new TowerZone(towerYellow_0,familiarYellow0);
        myTower[2][1] = new TowerZone(towerYellow_1,familiarYellow1);
        myTower[2][2] = new TowerZone(towerYellow_2,familiarYellow2);
        myTower[2][3] = new TowerZone(towerYellow_3,familiarYellow3);
        myTower[3][0] = new TowerZone(towerPurple_0,familiarPurple0);
        myTower[3][1] = new TowerZone(towerPurple_1,familiarPurple1);
        myTower[3][2] = new TowerZone(towerPurple_2,familiarPurple2);
        myTower[3][3] = new TowerZone(towerPurple_3,familiarPurple3);

    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

        mainController.setGeneralGameController(this);
    }

    @Override
    public void refresh() {
        chatArea.setContent(new Text(loginBuilder.getChat().toString()));
    }

    public void uploadImages(){
        super.uploadImages();
        int faithPoints = loginBuilder.getFaithPoints();
        int turnOrder = loginBuilder.getTurnOrder();
        String urlImage = "/images/immaginiSetUp/gameboard" + mainController.getNumberOfPlayer() + "Giocatori.png";
        gameboard.setStyle("-fx-background-image: url(" + urlImage + ")");
    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }


    public void goToTowers() {
        loginBuilder.setScene(SceneType.TOWERS,SceneType.MAIN);
    }

    public void goToProduction() {
        loginBuilder.setScene(SceneType.PRODUCTION,SceneType.MAIN);
    }

    public void goToMarket() {
        loginBuilder.setScene(SceneType.MARKET,SceneType.MAIN);
    }

    public void goToCouncliPalace() {

    }


    public void showPoints() {
        loginBuilder.showPoints();
    }

    public void showLeaderCard() {
        loginBuilder.setScene(SceneType.LEADER, SceneType.MAIN);

    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    public void skipTurn() {
        if (!mainController.isMyTurn())
            loginBuilder.writeOnMyChat("it isn't your turn!\n");
        else
            mainController.skipTurn();
    }


    public void goToHarvester() {
        loginBuilder.setScene(SceneType.HARVESTER,SceneType.MAIN);
    }

    public void goToCouncil() {
        loginBuilder.setScene(SceneType.COUNCIL,SceneType.MAIN);
    }


    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.MAIN);
    }

    public void updateTurn(List<PlayerHandler> playerTurn) {
        int current = 0;
        for (PlayerHandler p: playerTurn){
            String playerColour = p.getFamilyColour();
            System.out.println("il colore che devo mettere al posto " + current + " del turno è " + playerColour);
            turnOrderArray.get(current).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + playerColour + "Pedone.png"))));
            current++;
        }
    }


    public void updatePosition(Tower[][] towers) {
        super.updatePosition(towers, myTower);
    }

    public void setScelta(String bothPaymentAvaiable, String s, String s1) {
        loginBuilder.showChoice(bothPaymentAvaiable,s,s1);
        mainController.wakeUp(loginBuilder.getChoiceDone());
    }

    public void setScore(Score uiScore) {
        lastImageFaith.setImage(null);
        ImageView faith = faithPointsArray.get(uiScore.getFaithPoints());
        faith.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + mainController.getColour() + "Pedone.png"))));
        lastImageFaith = faith;
    }

    public void setDice(int[] diceValue){
        diceValueBlack.setText(String.valueOf(diceValue[0]));
        diceValueWhite.setText(String.valueOf(diceValue[1]));
        diceValueOrange.setText(String.valueOf(diceValue[2]));
    }

    public void excommunicationUpdate(ExcommunicationZone[] excommunicationZone) {
        excommunication1.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_1_" + excommunicationZone[0].getCardForThisPeriod().getIdCard() + ".png"))));
        excommunication2.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_2_" + excommunicationZone[1].getCardForThisPeriod().getIdCard() + ".png"))));
        excommunication3.setImage(new Image(String.valueOf(getClass().getResource("/images/excommunication/excomm_3_" + excommunicationZone[2].getCardForThisPeriod().getIdCard() + ".png"))));

        excommunication1.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication1.getImage()));
        excommunication2.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication2.getImage()));
        excommunication3.setOnMouseClicked(event -> loginBuilder.showCardZoomed(excommunication3.getImage()));
    }
}
