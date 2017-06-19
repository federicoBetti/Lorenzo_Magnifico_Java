package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public ImageView harvesterZoneImage;
    public HBox familiarBox;
    public Button submit;
    public Button mainGameButton;
    public Button personalBoard;
    public Button buttonPlaceFamiliar;

    //todo si potrebbe fare che in base al numero di servants messo si illuminano le carte attivate


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
        nameOfTerritoryCard = new ArrayList<String>(6);
        imageTerritoryCard = new ArrayList<>(6);
        imageTerritoryCard.add(territoryCard0);
        imageTerritoryCard.add(territoryCard1);
        imageTerritoryCard.add(territoryCard2);
        imageTerritoryCard.add(territoryCard3);
        imageTerritoryCard.add(territoryCard4);
        imageTerritoryCard.add(territoryCard5);

        allPosition = new ArrayList<>();
        allPosition.add(new FamiliarPosition(imageHarvester0));
        if (mainController.getNumberOfPlayer() >= 3){
        }
        else {
            allPosition = Collections.unmodifiableList(allPosition);
        }

    }

    public void uploadImages() {
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        //attenzione che bisogna mettere che sia se i giocatori sono 3 o 4 è la stessa cosa
        harvesterZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/raccolto" + mainController.getNumberOfPlayer() + "Giocatori.png"))));
    }

    public void refresh(){

    }

    public void inizializeWithMain() {
        chatText = loginBuilder.getChat();
    }

    public void aggiornaChat() {
        chatText.setText(loginBuilder.getChat().getText());
    }


    public void doHarvester() {
        int servants = -1;
        if (familiarChosen==null)
            return;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }

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
        super.placeFamiliar(allPosition, familiarBox);
        positionSelected = true;
    }

    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.HARVESTER);
    }


    public void updateCards(List<TerritoryCard> territoryCards){
        for (int i = 0; i< territoryCards.size(); i++){
            String oldCard = nameOfTerritoryCard.get(i);
            if (oldCard == null){
                String nameOfNewCard = territoryCards.get(i).getName();
                nameOfTerritoryCard.set(i,nameOfNewCard);
                ImageView imageView = imageTerritoryCard.get(i);
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + nameOfNewCard + ".png"))));
            }
        }
    }

    public void updatePosition(Harvester[] harvesterZone) {
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

    protected void blockButton() {
        super.blockButton();
        submit.setOnAction(#bonusAction());
    }

    protected void unlockButton() {
        super.unlockButton();
        submit.setOnAction(#doHarvester());
    }
}
