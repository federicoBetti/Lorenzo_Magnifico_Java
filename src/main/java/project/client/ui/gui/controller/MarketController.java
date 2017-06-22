package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.model.Market;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 11/06/17.
 */
public class MarketController extends AbstractController {

    public ImageView market;
    @FXML
    protected Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button personalBoard;
    @FXML
    private Button buttonPlaceFamiliar;
    /**
     * radio button in which you can chose the familiar to use
     */
    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;

    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */

    public ImageView imageFamiliarNull;

    public ImageView imageFamiliarBlack;

    public ImageView imageFamiliarWhite;

    public ImageView imageFamiliarOrange;


    public Label numberOfCoins;

    public Label numberOfWood;

    public Label numberOfStone;

    public Label numberOfServants;


    public ImageView LorenzoMagnifico;


    public TextField chatText;
    /**
     * the imageViews where the familiar will be placed
     */
    public ImageView imageMarket0;
    public ImageView imageMarket1;
    public ImageView imageMarket2;
    public ImageView imageMarket3;

    private ImageView lastFamiliarPlaced;
    int positionSelected;

    private List<FamiliarPosition> familiarPositions;

    public MarketController(){
        super();
        System.out.print("sono nel controller");
        lastFamiliarPlaced = new ImageView();
        positionSelected = -1;
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize(){
        super.initialize();
        familiarPositions = new ArrayList<>(2);
    }


    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setMarketController(this);
    }

    @Override
    public void refresh() {

    }

    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));

        int playerNumber;
        if (mainController.getNumberOfPlayer() < 4)
            playerNumber = 2;
        else
            playerNumber = 4;
        market.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/mercato" + playerNumber + "Giocatori.png"))));

        fillFamilymember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
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

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    public void goToMarket() {
        mainController.goToMarket(positionSelected,familiarChosen);
    }


    private void placeFamiliar(int position){
        FamiliarPosition familiar = familiarPositions.get(position);
        if (familiar.getFamiliarName() != null)
            return; //posizione occupata
        lastFamiliarPlaced.setImage(null);
        positionSelected = position;
        familiar.setImage(getTrueFamiliarImage());
        lastFamiliarPlaced = familiar.getImage();
    }
    
    public void placeFamiliarOnMarket0() {
        placeFamiliar(0);
    }
    public void placeFamiliarOnMarket1() {
        placeFamiliar(1);
    }
    public void placeFamiliarOnMarket2() {
        placeFamiliar(2);
    }
    public void placeFamiliarOnMarket3() {
        placeFamiliar(3);
    }



    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.MARKET);
    }

    public void updatePosition(Market[] markets){
        List<Market> markets1 = new ArrayList<>(markets.length);
        for (Market m: markets){
            markets1.add(m);
        }
        super.updatePosition(markets1,familiarPositions);

    }

    public void sendChat(ActionEvent actionEvent) {
        sendChat(chatText);
    }
}
