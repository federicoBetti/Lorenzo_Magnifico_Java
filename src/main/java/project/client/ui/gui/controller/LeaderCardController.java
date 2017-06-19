package project.client.ui.gui.controller;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by federico on 14/06/17.
 */
public class LeaderCardController extends AbstractController {

    public ImageView imageLeaderCard0;
    public ImageView imageLeaderCard1;
    public ImageView imageLeaderCard2;
    public ImageView imageLeaderCard3;
    public ImageView imageLeaderCard4;
    public Button goBackButton;
    private ArrayList<ImageView> arrayOfLeaderCard;
    private boolean[] leaderCardSelected;


    DropShadow borderGlow= new DropShadow();
    DropShadow borderNull= new DropShadow();

    public LeaderCardController(){
        /**
         * initializing of border to show wich card are selected
         */

        int depth = 70;//Setting the uniform variable for the glow width and height
        borderGlow.setColor(Color.BROWN);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);


    }


    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setLeaderCardController(this);
    }

    @Override
    public void refresh() {

    }

    public void initialize(){
        imageFamiliarBlack = new ImageView();
        imageFamiliarOrange = new ImageView();
        imageFamiliarNull = new ImageView();
        imageFamiliarWhite = new ImageView();
        imageLeaderCard0.setImage(new Image(String.valueOf(getClass().getResource("/images/leaders.jpg"))));
        imageLeaderCard1.setImage(new Image(String.valueOf(getClass().getResource("/images/leaders.jpg"))));
        arrayOfLeaderCard = new ArrayList<>();
        arrayOfLeaderCard.add(imageLeaderCard0);
        arrayOfLeaderCard.add(imageLeaderCard1);
        arrayOfLeaderCard.add(imageLeaderCard2);
        arrayOfLeaderCard.add(imageLeaderCard3);
        arrayOfLeaderCard.add(imageLeaderCard4);
        leaderCardSelected = new boolean[5];

    }


    private void unselectAllCards() {
        for (int i=0; i<leaderCardSelected.length;i++){
            if (leaderCardSelected[i]){
                arrayOfLeaderCard.get(i).setEffect(borderNull);
                leaderCardSelected[i]=false;
            }
        }
    }
    private void selectLeaderCard(int index){
        if (leaderCardSelected[index])
            return;
        else {
            System.out.println("cambio selezione");
            unselectAllCards();
            leaderCardSelected[index] = true;
            ImageView imageView = arrayOfLeaderCard.get(index);
            imageView.setEffect(borderGlow);
        }
    }

    public void leaderCardChosen0() {
        int indexOfLeaderCard = 0;
        selectLeaderCard(indexOfLeaderCard);
    }


    public void leaderCardChosen1() {
        int indexOfLeaderCard = 1;
        selectLeaderCard(indexOfLeaderCard);
    }

    public void leaderCardChosen2() {
        int indexOfLeaderCard = 2;
        selectLeaderCard(indexOfLeaderCard);
    }

    public void leaderCardChosen3() {
        int indexOfLeaderCard = 3;
        selectLeaderCard(indexOfLeaderCard);
    }

    public void leaderCardChosen4() {
        int indexOfLeaderCard = 4;
        selectLeaderCard(indexOfLeaderCard);
    }

    public void goBack() {
        loginBuilder.setScene(SceneType.MAIN,SceneType.LEADER);
    }

    private int getLeaderSelected(){
        for (int i = 0; i<leaderCardSelected.length; i++){
            if (leaderCardSelected[i])
                return i;
        }
    }
    public void playCard() {
        int cardSelected = getLeaderSelected();
        mainController.playLeaderCard(cardSelected);
    }

    public void discardCard() {
        int cardSelected = getLeaderSelected();
        mainController.discardLeaderCard(cardSelected);
    }

    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.LEADER);
    }

    public void endTurnContext() {
        goBackButton.setText("end turn");
        goBackButton.setOnAction(#skipTurn());
        blockButton();
    }

    private void skipTurn() {
        mainController.skipTurn();
        goBackButton.setText("go back");
        goBackButton.setOnAction(#goBack());
        unlockButton();
    }
}
