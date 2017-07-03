package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import project.controller.cardsfactory.LeaderCard;
import project.model.FamilyMember;

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


    @FXML
    private  TextField chatText;
    @FXML
    private  ImageView imageLeaderCard0;
    @FXML
    private  ImageView imageLeaderCard1;
    @FXML
    private  ImageView imageLeaderCard2;
    @FXML
    private  ImageView imageLeaderCard3;
    @FXML
    private  ImageView imageLeaderCard4;
    @FXML
    private  Button goBackButton;
    private ArrayList<FamiliarPosition> arrayOfLeaderCard;
    private boolean[] leaderCardSelected;


    private DropShadow borderGlow= new DropShadow();
    private DropShadow borderNull= new DropShadow();

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
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard0));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard1));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard2));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard3));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard4));
        leaderCardSelected = new boolean[5];

    }

    public void uploadImages(){
        for (FamiliarPosition f: arrayOfLeaderCard){
        ImageView imageView = f.getImage();
        imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/leaders.jpg"))));
    }
    }

    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    private void unselectAllCards() {
        for (int i=0; i<leaderCardSelected.length;i++){
            if (leaderCardSelected[i]){
                arrayOfLeaderCard.get(i).getImage().setEffect(borderNull);
                leaderCardSelected[i]=false;
            }
        }
    }


    private void leaderCardChosen(String name) {
        int index = findIndex(name);
        if (!leaderCardSelected[index]){
            System.out.println("cambio selezione");
            unselectAllCards();
            leaderCardSelected[index] = true;
            ImageView imageView = arrayOfLeaderCard.get(index).getImage();
            imageView.setEffect(borderGlow);
        }
    }

    private int findIndex(String name) {
        for (FamiliarPosition f: arrayOfLeaderCard){
            if (f.getFamiliarName().equals(name))
                return arrayOfLeaderCard.indexOf(f);
        }
        return -1;
    }


    @FXML
    private void goBack() {
        loginBuilder.setScene(SceneType.MAIN,SceneType.LEADER);
    }

    private int getLeaderSelected(){
        for (int i = 0; i<leaderCardSelected.length; i++){
            if (leaderCardSelected[i])
                return i;
        }
        return -1;
    }
    public void playCard() {
        int cardSelected = getLeaderSelected();
        if (cardSelected!=-1) {
            String leaderName = arrayOfLeaderCard.get(cardSelected).getFamiliarName();
            unselectAllCards();
            mainController.playLeaderCard(leaderName);
        }
    }

    public void discardCard() {
        int cardSelected = getLeaderSelected();
        if (cardSelected!=-1) {
            String leaderName = arrayOfLeaderCard.get(cardSelected).getFamiliarName();
            unselectAllCards();
            mainController.discardLeaderCard(leaderName);
        }
    }

    public void updateCards(List<LeaderCard> leaderCards){
        int i;
        for (i = 0; i<leaderCards.size(); i++) {
            LeaderCard l = leaderCards.get(i);
            if (!(arrayOfLeaderCard.get(i).getFamiliarName().equals(l.getName()))) {
                ImageView imageView = arrayOfLeaderCard.get(i).getImage();
                arrayOfLeaderCard.get(i).setFamiliarName(l.getName());
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/leaderCards/" + l.getName() + ".png"))));
                imageView.setOnMouseClicked(event -> leaderCardChosen(l.getName()));
            }
        }
        for (;i< arrayOfLeaderCard.size(); i++){
                ImageView imageView = arrayOfLeaderCard.get(i).getImage();
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/leaders.jpg"))));
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
        loginBuilder.setScene(SceneType.MAIN, SceneType.LEADER);
    }
}
