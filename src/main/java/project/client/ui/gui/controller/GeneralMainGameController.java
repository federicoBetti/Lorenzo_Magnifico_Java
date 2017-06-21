package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 10/06/17.
 */
public class GeneralMainGameController extends AbstractController{


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
    private Label chatArea;
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


    public GeneralMainGameController(){
        super();
        System.out.print("sono nel controller");
        faithPointsArray = new ArrayList<>(15);
        turnOrderArray = new ArrayList<>(4);
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize(){
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
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setGeneralGameController(this);
    }

    @Override
    public void refresh() {

    }

    public void uploadImages(){
        super.uploadImages();
        int faithPoints = loginBuilder.getFaithPoints();
        int turnOrder = loginBuilder.getTurnOrder();
        faithPointsArray.get(faithPoints).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + loginBuilder.getColour() + "Pedone.png"))));
        turnOrderArray.get(turnOrder).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + loginBuilder.getColour() + "Pedone.png"))));
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

    }

    public void showLeaderCard() {
        loginBuilder.setScene(SceneType.LEADER, SceneType.MAIN);

    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    public void skipTurn() {
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
            turnOrderArray.get(current).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + playerColour + "Pedone.png"))));
            current++;
        }
    }
}
