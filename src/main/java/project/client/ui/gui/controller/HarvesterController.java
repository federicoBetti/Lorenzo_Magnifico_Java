package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.controller.cardsfactory.TerritoryCard;
import project.model.FamilyMember;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by federico on 11/06/17.
 */
public class HarvesterController extends AbstractController {

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

    public HarvesterController() {
        super();
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

    }


    public void uploadImages() {
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + loginBuilder.getColour() + ".png"))));
    }


    public void inizializeWithMain() {
        chatText = loginBuilder.getChat();
    }

    public void aggiornaChat() {
        chatText.setText(loginBuilder.getChat().getText());
    }


    public void doHarvester() {
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
        imageHarvester0.setImage(getTrueFamiliarImage());
    }

    public void placeFamiliarOnHarvester1() {
        imageHarvester1.setImage(getTrueFamiliarImage());
    }

    public void placeFamiliarOnHarvester2() {
        imageHarvester2.setImage(getTrueFamiliarImage());
    }

    public void placeFamiliarOnHarvester3() {
        imageHarvester3.setImage(getTrueFamiliarImage());
    }


    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.HARVESTER);
    }

    public void updateFamilyMember(FamilyMember[] uiFamilyMembers) {

        for (int i = 0;i<imageFamiltMember.size(); i++){
            ImageView imageView = imageFamiltMember.get(i);
            RadioButton radioButton = radioButtonFamiliar.get(i);
            if (uiFamilyMembers[i].isPlayed()){
                imageView.setOpacity(0.7);
                radioButton.setDisable(true);
            }
            else {
                imageView.setOpacity(1);
                radioButton.setDisable(false);
            }
        }
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
}
