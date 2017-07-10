package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import project.controller.cardsfactory.TerritoryCard;
import project.model.Harvester;

import java.util.ArrayList;
import java.util.List;

/**
 * controller of harvester scene in gui
 */
public class HarvesterController extends AbstractController {

    @FXML
    private  ImageView harvesterZoneImage;
    @FXML
    private  HBox familiarBox;

    @FXML
    private  RadioButton familiarOrange;
    @FXML
    private  RadioButton familiarWhite;
    @FXML
    private  RadioButton familiarBlack;
    @FXML
    private  RadioButton familiarNull;

    @FXML
    private ToggleGroup familiar;

    @FXML
    private Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button personalBoard;
    @FXML
    private Button buttonPlaceFamiliar;


    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;

    @FXML
    private  ImageView imageFamiliarNull;

    @FXML
    private  ImageView imageFamiliarBlack;

    @FXML
    private  ImageView imageFamiliarWhite;

    @FXML
    private  ImageView imageFamiliarOrange;


    @FXML
    private ImageView lorenzoMagnifico;


    @FXML
    private TextField chatText;
    /**
     * the imageViews where the familiar will be placed
     */
    @FXML
    private ImageView imageHarvester0;

    private List<FamiliarPosition> allPosition;

    /**
     * the imageViews where there are the territory cards
     */
    @FXML
    private ImageView territoryCard0;
    @FXML
    private ImageView territoryCard1;
    @FXML
    private ImageView territoryCard2;
    @FXML
    private ImageView territoryCard3;
    @FXML
    private ImageView territoryCard4;
    @FXML
    private ImageView territoryCard5;


    @FXML
    private TextField numberOfServantsTextField;
    @FXML
    private ScrollPane chatArea;

    private List<ImageView> imageTerritoryCard;
    private List<String> nameOfTerritoryCard;
    private boolean positionSelected;

    /**
     * constructor
     */
    public HarvesterController() {
        super();
        positionSelected = false;
    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setHarvesterController(this);
    }

    /**
     * method called during initialization of gui scenes
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();
    }

    /**
     * method called for uploading images during the initialization
     */
    @Override
    public void uploadImages() {
        super.uploadImages();
        lorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        int playerNumber;
        if (mainController.getNumberOfPlayer() > 2)
            playerNumber = 3;
        else
            playerNumber = 2;
        harvesterZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/raccolto" + playerNumber + "Giocatori.png"))));

        fillFamilyMember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);
        nameOfTerritoryCard = new ArrayList<>();
        imageTerritoryCard = new ArrayList<>(6);
        imageTerritoryCard.add(territoryCard0);
        imageTerritoryCard.add(territoryCard1);
        imageTerritoryCard.add(territoryCard2);
        imageTerritoryCard.add(territoryCard3);
        imageTerritoryCard.add(territoryCard4);
        imageTerritoryCard.add(territoryCard5);

        allPosition = new ArrayList<>();
        allPosition.add(new FamiliarPosition(imageHarvester0));

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

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    /**
     * method called to refresh the scene
     */
    @Override
    public void refresh(){
        super.refresh();
        super.unselectedRadioButton(familiar);
        chatArea.setContent(new Text(loginBuilder.getChat().toString()));

        if (positionSelected){

            FamiliarPosition f = allPosition.get(allPosition.size() -1 );
            f.setImage(null);
            f.setFamiliarName("");
            if (allPosition.size() > 1)
                allPosition.remove(allPosition.size()-1);

        }
        positionSelected = false;
    }

    /**
     * method called by submit button to make an harvester action
     */
    @FXML
    private void doHarvester() {
        int servants;
        if (familiarChosen.equals(""))
            return;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }
        if (servants < 0)
            return;
        positionSelected = false;
        mainController.doHarvester(servants,familiarChosen);
    }

    /**
     * method called to zoom on territory card 0
     */
    public void zoomTerritoryCard0() {
        loginBuilder.showCardZoomed(territoryCard0.getImage());
    }

    /**
     * method called to zoom on territory card 1
     */
    public void zoomTerritoryCard1() {
        loginBuilder.showCardZoomed(territoryCard1.getImage());
    }

    /**
     * method called to zoom on territory card 2
     */
    public void zoomTerritoryCard2() {
        loginBuilder.showCardZoomed(territoryCard2.getImage());
    }

    /**
     * method called to zoom on territory card 3
     */
    public void zoomTerritoryCard3() {
        loginBuilder.showCardZoomed(territoryCard3.getImage());
    }

    /**
     * method called to zoom on territory card 4
     */
    public void zoomTerritoryCard4() {
        loginBuilder.showCardZoomed(territoryCard4.getImage());
    }

    /**
     * method called to zoom on territory card 5
     */
    public void zoomTerritoryCard5() {
        loginBuilder.showCardZoomed(territoryCard5.getImage());
    }

    /**
     * method called to place a familiar in the harvester action space
     */
    public void placeFamiliar(){
        if (super.placeFamiliarHarvProd(positionSelected,allPosition,familiarBox))
            positionSelected = true;
    }

    /**
     * method called to send a message in the chat
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     * method called to show personal board scene
     */
    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.HARVESTER);
    }

    /**
     * method used to update card in the harvester scene
     * @param territoryCards territory card of the player to set in harvester scene
     */
    void updateCards(List<TerritoryCard> territoryCards){
        super.updateCards(territoryCards,nameOfTerritoryCard,imageTerritoryCard);
    }

    /**
     * method used t update familiars following an update from the serve
     * @param harvesterZone harvester zone arrived from update
     */
    void updatePosition(List<Harvester> harvesterZone) {
        super.updatePosition(harvesterZone,allPosition);

        if (allPosition.size()>1){
            super.updateHBox(allPosition.subList(1,allPosition.size()),familiarBox);
        }
    }

    /**
     * method called to perform a bonus harvester action
     * @param diceValue dice value of harvester bonus action
     */
    void bonusHarvester(int diceValue) {
        loginBuilder.setScene(SceneType.HARVESTER, SceneType.PERSONAL_BOARD);
        blockButton();
        numberOfServantsTextField.setText("");
        writeOnChat("BONUS ACTION: you can perform an harvester action of value" + diceValue);
        writeOnChat("select the number of servants that you want to use");
    }

    /**
     * method called by submit button to make a bonus action
     */
    private void bonusAction() {
        int servants;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }

        mainController.doBonusHarvester(servants);

    }

    /**
     * method used to block button before bonus actions
     */
    private void blockButton() {
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> bonusAction());
    }

    /**
     * method used to ulock buttons after bonus actions
     */
    void unlockButton() {
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> doHarvester());
    }
}
