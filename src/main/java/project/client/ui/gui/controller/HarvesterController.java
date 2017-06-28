package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import project.controller.cardsfactory.TerritoryCard;
import project.model.Harvester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by federico on 11/06/17.
 */
public class HarvesterController extends AbstractController {

    @FXML
    private  ImageView harvesterZoneImage;
    @FXML
    private  HBox familiarBox;

    //todo si potrebbe fare che in base al numero di servants messo si illuminano le carte attivate

    @FXML
    private Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button personalBoard;
    @FXML
    private Button buttonPlaceFamiliar;
    /**
     * radio button in which you can chose the familiar to use
     */
    @FXML
    private  RadioButton familiarOrange;
    @FXML
    private  RadioButton familiarWhite;
    @FXML
    private  RadioButton familiarBlack;
    @FXML
    private  RadioButton familiarNull;

    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */
    @FXML
    private  ImageView imageFamiliarNull;

    @FXML
    private  ImageView imageFamiliarBlack;

    @FXML
    private  ImageView imageFamiliarWhite;

    @FXML
    private  ImageView imageFamiliarOrange;



    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;


    @FXML
    private ImageView LorenzoMagnifico;


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
    private Label chatArea;

    //todo si potrebbe fare la stessa cosa con familiar position con le carte
    private List<ImageView> imageTerritoryCard;
    private List<String> nameOfTerritoryCard;
    private boolean positionSelected;
    private int diceValueBonus;


    public HarvesterController() {
        super();
        positionSelected = false;
        System.out.print("sono nel controller");
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setHarvesterController(this);
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize() {
        super.initialize();
    }

    public void uploadImages() {
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        int playerNumber;
        if (mainController.getNumberOfPlayer() > 2)
            playerNumber = 3;
        else
            playerNumber = 2;
        harvesterZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/raccolto" + playerNumber + "Giocatori.png"))));

        fillFamilymember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);
        nameOfTerritoryCard = new ArrayList<String>();
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


    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    public void refresh(){
        super.refresh();
        chatArea.setText(loginBuilder.getChat().toString());

        if (positionSelected){
            FamiliarPosition f = allPosition.get(allPosition.size() -1 );
            f.setImage(null);
            allPosition.remove(allPosition.size()-1);

        }
        positionSelected = false;
    }



    @FXML
    private void doHarvester() {
        int servants = -1;
        if (familiarChosen.equals(""))
            return;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }
        positionSelected = false;
        mainController.doHarvester(servants,familiarChosen);
    }


    public void zoomTerritoryCard0() {
        loginBuilder.showCardZoomed(territoryCard0.getImage());
    }

    public void zoomTerritoryCard1() {
        loginBuilder.showCardZoomed(territoryCard1.getImage());
    }

    public void zoomTerritoryCard2() {
        loginBuilder.showCardZoomed(territoryCard2.getImage());
    }

    public void zoomTerritoryCard3() {
        loginBuilder.showCardZoomed(territoryCard3.getImage());
    }

    public void zoomTerritoryCard4() {
        loginBuilder.showCardZoomed(territoryCard4.getImage());
    }

    public void zoomTerritoryCard5() {
        loginBuilder.showCardZoomed(territoryCard5.getImage());
    }


    public void placeFamiliar(){
        if (familiarChosen.equals(""))
            return;
        if (positionSelected){
            if (allPosition.size()>1)
            allPosition.remove(allPosition.size() - 1);
            else
                allPosition.get(0).setFamiliarName("");
            super.placeFamiliar(allPosition,familiarBox);
        }
        if (mainController.getNumberOfPlayer() < 3 && familiarPlaced(allPosition) > 0)
            return;
        super.placeFamiliar(allPosition, familiarBox);
        positionSelected = true;
    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.HARVESTER);
    }


    public void updateCards(List<TerritoryCard> territoryCards){
        super.updateCards(territoryCards,nameOfTerritoryCard,imageTerritoryCard);
    }

    public void updatePosition(List<Harvester> harvesterZone) {
        super.updatePosition(harvesterZone,allPosition);
    }

    public void bonusHarvester(int diceValue) {
        loginBuilder.setScene(SceneType.HARVESTER, SceneType.PERSONAL_BOARD);
        blockButton();
        this.diceValueBonus = diceValue;
        numberOfServantsTextField.setText("");
        writeOnChat("BONUS ACTION: you can perform an harvester action of value" + diceValueBonus);
        writeOnChat("select the number of servants that you want to use");
    }


    private void bonusAction() {
        int servants = -1;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }

        mainController.doBonusHarvester(servants);
        unlockButton();

    }

    private void blockButton() {
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> bonusAction());
    }

    private void unlockButton() {
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> doHarvester());
    }
}
