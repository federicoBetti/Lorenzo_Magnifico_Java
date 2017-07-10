package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.controller.cardsfactory.LeaderCard;

import java.util.ArrayList;
import java.util.List;

/**
 * controller of leader card scene
 */
public class LeaderCardController extends AbstractController {

    @FXML
    private Button personalBoard;

    @FXML
    private ScrollPane chatArea;

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
    private  Button goBackButton;
    private ArrayList<FamiliarPosition> arrayOfLeaderCard;
    private boolean[] leaderCardSelected;


    private DropShadow borderGlow= new DropShadow();
    private DropShadow borderNull= new DropShadow();

    /**
     * constructor
     */
    public LeaderCardController(){
        super();
        int depth = 70;
        borderGlow.setColor(Color.BROWN);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);
    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setLeaderCardController(this);
    }

    /**
     * method used to refresh the scene
     */
    @Override
    public void refresh() {
        chatArea.setContent(new Text(loginBuilder.getChat().toString()));
    }

    /**
     * method used to upload resources
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {
        //there aren't the resources in this scene
    }

    /**
     * method called during the initialization
     */
    @Override
    public void initialize(){
        super.initialize();
        arrayOfLeaderCard = new ArrayList<>();
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard0));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard1));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard2));
        arrayOfLeaderCard.add(new FamiliarPosition(imageLeaderCard3));
        leaderCardSelected = new boolean[5];

    }

    /**
     * method used to upload images in the scene during the initialization
     */
    @Override
    public void uploadImages(){
        for (FamiliarPosition f: arrayOfLeaderCard){
        ImageView imageView = f.getImage();
        imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/leaders.jpg"))));
    }
    }

    /**
     * method used to send a message in chat
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     * method used to deselect all leader cards
     */
    private void deselectAllCards() {
        for (int i=0; i<leaderCardSelected.length;i++){
            if (leaderCardSelected[i]){
                arrayOfLeaderCard.get(i).getImage().setEffect(borderNull);
                leaderCardSelected[i]=false;
            }
        }
    }

    /**
     * method called if a eader card has been chosen
     * @param name leader name
     */
    private void leaderCardChosen(String name) {
        int index = findIndex(name);
        if (!leaderCardSelected[index]){
            deselectAllCards();
            leaderCardSelected[index] = true;
            ImageView imageView = arrayOfLeaderCard.get(index).getImage();
            imageView.setEffect(borderGlow);
        }
    }

    /**
     * find index of leader card
     * @param name leader card
     * @return index
     */
    private int findIndex(String name) {
        for (FamiliarPosition f: arrayOfLeaderCard){
            if (f.getFamiliarName().equals(name))
                return arrayOfLeaderCard.indexOf(f);
        }
        return -1;
    }

    /**
     * method used to go back to main scene
     */
    @FXML
    private void goBack() {
        loginBuilder.setScene(SceneType.MAIN,SceneType.LEADER);
    }

    /**
     * find index of leader card chosen
     * @return index of leader chosen
     */
    private int getLeaderSelected(){
        for (int i = 0; i<leaderCardSelected.length; i++){
            if (leaderCardSelected[i])
                return i;
        }
        return -1;
    }

    /**
     * method called to play a leader card
     */
    public void playCard() {
        if (!mainController.isMyTurn())
            return;
        int cardSelected = getLeaderSelected();
        if (cardSelected!=-1) {
            String leaderName = arrayOfLeaderCard.get(cardSelected).getFamiliarName();
            deselectAllCards();
            mainController.playLeaderCard(leaderName);
        }
    }

    /**
     * method called to discard leader card
     */
    public void discardCard() {
        if (!mainController.isMyTurn())
            return;
        int cardSelected = getLeaderSelected();
        if (cardSelected!=-1) {
            String leaderName = arrayOfLeaderCard.get(cardSelected).getFamiliarName();
            deselectAllCards();
            mainController.discardLeaderCard(leaderName);
        }
    }

    /**
     * method called to update leader cards
     * @param leaderCards leader cards arrived from update
     */
    void updateCards(List<LeaderCard> leaderCards){
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

    /**
     * method used to shw personal board scene
     */
    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.LEADER);
    }

    /**
     * method to switch scene in the end turn scene, blocking buttons
     */
    void endTurnContext() {
        goBackButton.setText("end turn");
        goBackButton.setOnAction(event -> skipTurn());
        personalBoard.setDisable(true);
    }

    /**
     * method called to skip turn
     */
    private void skipTurn() {
        mainController.skipTurn();
        goBackButton.setText("go back");
        goBackButton.setOnAction(event -> goBack());
        personalBoard.setDisable(false);
        loginBuilder.setScene(SceneType.MAIN, SceneType.LEADER);
    }
}
