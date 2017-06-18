package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controller.cardsfactory.TerritoryCard;
import project.model.FamilyMember;
import project.model.Harvester;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 11/06/17.
 */
public class HarvesterController extends AbstractController {
    public ImageView harvesterZoneImage;

    //todo si potrebbe fare che in base al numero di servants messo si illuminano le carte attivate


    /**
     * the imageViews where the familiar will be placed
     */
    @FXML
    private ImageView imageHarvester0;
    @FXML
    private ImageView imageHarvester1;
    @FXML
    private ImageView imageHarvester2;
    @FXML
    private ImageView imageHarvester3;

    private ArrayList<ImageView> allPosition;
    private String[] familyMemberOnPositions;

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

    private List<ImageView> imageTerritoryCard;
    private List<String> nameOfTerritoryCard;
    private int positionSelected;

    public HarvesterController() {
        super();
        positionSelected = -1;
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
        allPosition.add(imageHarvester0);
        if (mainController.getNumberOfPlayer() >= 3){
            allPosition.add(imageHarvester1);
            allPosition.add(imageHarvester2);
            allPosition.add(imageHarvester3);
            familyMemberOnPositions = new String[4];
        }
        else {
            familyMemberOnPositions = new String[1];
        }

    }

    public void uploadImages() {
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        //attenzione che bisogna mettere che sia se i giocatori sono 3 o 4 è la stessa cosa
        harvesterZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/raccolto" + mainController.getNumberOfPlayer() + "Giocatori.png"))));
    }

    public void refresh(){
        positionSelected = -1;
    }

    public void inizializeWithMain() {
        chatText = loginBuilder.getChat();
    }

    public void aggiornaChat() {
        chatText.setText(loginBuilder.getChat().getText());
    }


    public void doHarvester() {
        int servants = 0;
        if (positionSelected == -1)
            return;
        try {
            servants = Integer.parseInt(numberOfServantsTextField.getText());
        }
        catch (NumberFormatException e){
            return;
        }

        mainController.doHarvester(positionSelected,servants,familiarChosen);
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


    public void placeFamiliarOnHarvester0() {
        if (familyMemberOnPositions[0] != null)
            return;
        imageHarvester0.setImage(getTrueFamiliarImage());
        positionSelected = 0;
    }

    public void placeFamiliarOnHarvester1() {
        if (familyMemberOnPositions[1] != null)
            return;
        imageHarvester1.setImage(getTrueFamiliarImage());
        positionSelected = 1;
    }

    public void placeFamiliarOnHarvester2() {
        if (familyMemberOnPositions[2] != null)
            return;
        imageHarvester2.setImage(getTrueFamiliarImage());
        positionSelected = 2;
    }

    public void placeFamiliarOnHarvester3() {
        if (familyMemberOnPositions[3] != null)
            return;
        imageHarvester3.setImage(getTrueFamiliarImage());
        positionSelected = 3;
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
        for (int i = 0; i<harvesterZone.length; i++){
            if (harvesterZone[i].getFamiliarOnThisPosition() == null){
                familyMemberOnPositions[i] = "";
                allPosition.get(i).setImage(null);
            }
            if (familyMemberOnPositions[i].equals(harvesterZone[i].getFamiliarOnThisPosition().toString()))
                continue;
            else {
                familyMemberOnPositions[i] = harvesterZone[i].getFamiliarOnThisPosition().toString();
                ImageView imageView = allPosition.get(i);
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familyMemberOnPositions[i] + ".png"))));
            }
        }
    }
}
