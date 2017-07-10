package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import project.model.Market;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * controller class of the market scene
 */
public class MarketController extends AbstractController {

    @FXML
    private ImageView market;
    @FXML
    protected Button submit;

    @FXML
    private  Label numberOfCoins;
    @FXML
    private  Label numberOfWood;
    @FXML
    private  Label numberOfStone;
    @FXML
    private  Label numberOfServants;


    @FXML
    private  ImageView imageFamiliarNull;

    @FXML
    private  ImageView imageFamiliarBlack;

    @FXML
    private  ImageView imageFamiliarWhite;

    @FXML
    private  ImageView imageFamiliarOrange;


    @FXML
    private  RadioButton familiarOrange;
    @FXML
    private  RadioButton familiarWhite;
    @FXML
    private  RadioButton familiarBlack;
    @FXML
    private  RadioButton familiarNull;



    @FXML
    private  ImageView lorenzoMagnifico;


    @FXML
    private  TextField chatText;


    @FXML
    private  ImageView imageMarket0;
    @FXML
    private  ImageView imageMarket1;
    @FXML
    private  ImageView imageMarket2;
    @FXML
    private  ImageView imageMarket3;

    private ImageView lastFamiliarPlaced;
    private int positionSelected;

    private List<FamiliarPosition> familiarPositions;

    @FXML
    private ScrollPane chatArea;
    @FXML
    private ToggleGroup familiar;

    /**
     * constructor
     */
    public MarketController(){
        super();
        lastFamiliarPlaced = new ImageView();
        positionSelected = -1;
    }

    /**
     * initialization method
     */
    @Override
    @FXML
    public void initialize(){
        super.initialize();
        familiarPositions = new ArrayList<>(2);
    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setMarketController(this);
    }

    /**
     * method used to refresh the scene
     */
    @Override
    public void refresh() {
        super.refresh();
        super.unselectedRadioButton(familiar);
        lastFamiliarPlaced.setImage(null);
        chatArea.setContent(new Text(loginBuilder.getChat().toString()));
        lastFamiliarPlaced.setImage(null);
    }

    /**
     * method used to upload images during initialization
     */
    @Override
    public void uploadImages(){
        super.uploadImages();
        lorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));

        int playerNumber;
        if (mainController.getNumberOfPlayer() < 4)
            playerNumber = 2;
        else
            playerNumber = 4;
        market.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/mercato" + playerNumber + "Giocatori.png"))));

        fillFamilyMember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);
        if (mainController.getNumberOfPlayer()==4){
            familiarPositions.add(new FamiliarPosition(imageMarket0));
            familiarPositions.add(new FamiliarPosition(imageMarket1));
            familiarPositions.add(new FamiliarPosition(imageMarket2));
            familiarPositions.add(new FamiliarPosition(imageMarket3));
        }
        else{
            familiarPositions.add(new FamiliarPosition(imageMarket0));
            familiarPositions.add(new FamiliarPosition(imageMarket1));
            imageMarket2.setDisable(true);
            imageMarket3.setDisable(true);
        }
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
     * method used to submit market action
     */
    public void goToMarket() {
        if (positionSelected!= -1){
            lastFamiliarPlaced = new ImageView((Image) null);
            mainController.goToMarket(positionSelected,familiarChosen);
        }
    }

    /**
     * method used to place familiar in a market position
     * @param position position in which place the familiar
     */
    private void placeFamiliar(int position){
        if (!mainController.isMyTurn())
            return;
        FamiliarPosition familiarChosen = familiarPositions.get(position);
        if (!familiarChosen.getFamiliarName().equals(""))
            return; //posizione occupata
        lastFamiliarPlaced.setImage(null);
        positionSelected = position;
        familiarChosen.setImage(getTrueFamiliarImage());
        lastFamiliarPlaced = familiarChosen.getImage();
    }

    /**
     * method used to place a familiar in the market position 0
     */
    public void placeFamiliarOnMarket0() {
        placeFamiliar(0);
    }

    /**
     * method used to place a familiar in the market position 1
     */
    public void placeFamiliarOnMarket1() {
        placeFamiliar(1);
    }

    /**
     * method used to place a familiar in the market position 2
     */
    public void placeFamiliarOnMarket2() {
        placeFamiliar(2);
    }

    /**
     * method used to place a familiar in the market position 3
     */
    public void placeFamiliarOnMarket3() {
        placeFamiliar(3);
    }


    /**
     * methos used to show personal board sceen
     */
    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.MARKET);
    }

    /**
     * method used to update positions following an update
     * @param markets market arrived from update
     */
    void updatePosition(Market[] markets){
        if (markets == null)
            return;
        List<Market> markets1 = new ArrayList<>();
        markets1.addAll(Arrays.asList(markets));
        super.updatePosition(markets1,familiarPositions);

    }

    /**
     * method used to send a message in the chat
     */
    public void sendChat() {
        sendChat(chatText);
    }
}
