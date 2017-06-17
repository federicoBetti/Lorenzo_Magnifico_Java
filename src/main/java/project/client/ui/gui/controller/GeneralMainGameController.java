package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by federico on 10/06/17.
 */
public class GeneralMainGameController extends AbstractController{

    public Button send;
    public Label chatArea;
    public Button goToTowersButton;
    public Button goToHarvesterAndProductionButton;
    public Button goToMarketButton;
    public Button goToCouncilPalaceButton;
    public Button personalBoardButton;
    public Button pointsButton;
    public Button leaderCardButton;
    public Button skipTurnButton;


    public TextField chatTesto;



    public ImageView towerGreen_0;
    public ImageView towerGreen_1;
    public ImageView towerGreen_2;
    public ImageView towerGreen_3;
    public ImageView towerBlue_0;
    public ImageView towerBlue_1;
    public ImageView towerBlue_2;
    public ImageView towerBlue_3;
    public ImageView towerYellow_0;
    public ImageView towerYellow_1;
    public ImageView towerYellow_2;
    public ImageView towerYellow_3;
    public ImageView towerPurple_0;
    public ImageView towerPurple_1;
    public ImageView towerPurple_2;
    public ImageView towerPurple_3;

    public ImageView[][] torri = new ImageView[4][4];
    public ImageView faithPoint0;
    public ImageView faithPoint1;
    public ImageView faithPoint2;
    public ImageView faithPoint3;
    public ImageView faithPoint4;
    public ImageView faithPoint5;
    public ImageView faithPoint6;
    public ImageView faithPoint7;
    public ImageView faithPoint8;
    public ImageView faithPoint9;
    public ImageView faithPoint10;
    public ImageView faithPoint11;
    public ImageView faithPoint12;
    public ImageView faithPoint13;
    public ImageView faithPoint14;
    public ImageView faithPoint15;
    private ArrayList<ImageView> faithPointsArray;


    public ImageView turnOrder1;
    public ImageView turnOrder2;
    public ImageView turnOrder3;
    public ImageView turnOrder4;
    private ArrayList<ImageView> turnOrderArray;



    public GeneralMainGameController(){
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

    public void uploadImages(){
        super.uploadImages();
        int faithPoints = loginBuilder.getFaithPoints();
        int turnOrder = loginBuilder.getTurnOrder();
        faithPointsArray.get(faithPoints).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + loginBuilder.getColour() + "Pedone.png"))));
        turnOrderArray.get(turnOrder).setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + loginBuilder.getColour() + "Pedone.png"))));
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
}
