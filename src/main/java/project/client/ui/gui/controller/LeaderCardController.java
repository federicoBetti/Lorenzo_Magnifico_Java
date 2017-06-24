package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import project.controller.cardsfactory.LeaderCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 14/06/17.
 */
public class LeaderCardController extends AbstractController {

    @FXML
    private Button personalBoard;

    @FXML
    private Label chatArea;





    public ImageView LorenzoMagnifico;


    public TextField chatText;
    public ImageView imageLeaderCard0;
    public ImageView imageLeaderCard1;
    public ImageView imageLeaderCard2;
    public ImageView imageLeaderCard3;
    public ImageView imageLeaderCard4;
    public Button goBackButton;
    private ArrayList<ImageView> arrayOfLeaderCard;
    private boolean[] leaderCardSelected;
    private boolean[] leaderCardPresent;


    DropShadow borderGlow= new DropShadow();
    DropShadow borderNull= new DropShadow();

    public LeaderCardController(){
        super();
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
        chatArea.setText(loginBuilder.getChat().toString());
    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

    }

    public void initialize(){
        super.initialize();
        arrayOfLeaderCard = new ArrayList<>();
        arrayOfLeaderCard.add(imageLeaderCard0);
        arrayOfLeaderCard.add(imageLeaderCard1);
        arrayOfLeaderCard.add(imageLeaderCard2);
        arrayOfLeaderCard.add(imageLeaderCard3);
        arrayOfLeaderCard.add(imageLeaderCard4);
        leaderCardSelected = new boolean[5];
        leaderCardPresent = new boolean[5];

    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
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
        if (!leaderCardSelected[index]){
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
        return 0;
    }
    public void playCard() {
        int cardSelected = getLeaderSelected();
        mainController.playLeaderCard(cardSelected);
    }

    public void discardCard() {
        int cardSelected = getLeaderSelected();
        mainController.discardLeaderCard(cardSelected);
    }

    public void updateCards(List<LeaderCard> leaderCards){
        for (int i = 0; i<leaderCards.size(); i++){
            LeaderCard l = leaderCards.get(i);
            if (l == null){
                if (leaderCardPresent[i]){
                    ImageView imageView = arrayOfLeaderCard.get(i);
                    imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCard/back.png"))));
                    leaderCardPresent[i] = false;
                }
            }
            else {
                if (!leaderCardPresent[i]){
                    ImageView imageView = arrayOfLeaderCard.get(i);
                     imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCards/" + l.getName() + ".png"))));

                     leaderCardPresent[i] = true;
                }
            }
        }
    }

    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.LEADER);
    }

    public void endTurnContext() {
        goBackButton.setText("end turn");
        goBackButton.setOnAction(event -> skipTurn());
        personalBoard.setDisable(true);
    }

    private void skipTurn() {
        mainController.skipTurn();
        goBackButton.setText("go back");
        goBackButton.setOnAction(event -> goBack());
        personalBoard.setDisable(false);
    }
}
