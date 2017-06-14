package project.client.ui.gui.maingame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by federico on 11/06/17.
 */
public class HarvesterController extends AbstractController {

    //todo si potrebbe fare che in base al numero di servants messo si illuminano le carte attivate
    /**
     * radio button in which you can chose the familiar to use
     */
    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;

    /**
     * the imageViews where the familiar will be placed
     */
    public ImageView imageHarvester0;
    public ImageView imageHarvester1;
    public ImageView imageHarvester2;
    public ImageView imageHarvester3;


    /**
     * the imageViews where there are the territory cards
     */
    public ImageView territoryCard0;
    public ImageView territoryCard1;
    public ImageView territoryCard2;
    public ImageView territoryCard3;
    public ImageView territoryCard4;
    public ImageView territoryCard5;


    public TextField numberOfServantsTextField;

    public HarvesterController(){
        System.out.print("sono nel controller");
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    private void initialize(){
    }


    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + mainController.getColour() + ".png"))));
    }



    public void inizializeWithMain() {
        chatText = mainController.getChat();
    }

    public void aggiornaChat(){
        chatText.setText(mainController.getChat().getText());
    }




    public void doHarvester(ActionEvent actionEvent) {
    }


    public void zoomTerritoryCard0(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard0.getImage());
    }

    public void zoomTerritoryCard1(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard1.getImage());
    }

    public void zoomTerritoryCard2(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard2.getImage());
    }

    public void zoomTerritoryCard3(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard3.getImage());
    }

    public void zoomTerritoryCard4(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard4.getImage());
    }

    public void zoomTerritoryCard5(MouseEvent mouseEvent) {
        mainController.showCardZoomed(territoryCard5.getImage());
    }


    public void placeFamiliarOnHarvester0(MouseEvent mouseEvent) {
        imageHarvester0.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnHarvester1(MouseEvent mouseEvent) {
        imageHarvester1.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnHarvester2(MouseEvent mouseEvent) {
        imageHarvester2.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnHarvester3(MouseEvent mouseEvent) {
        imageHarvester3.setImage(getTrueFamiliarImage());
    }


    public void showPersonalBoard(ActionEvent actionEvent){
        super.showPersonalBoard(SceneType.HARVESTER);
    }
}
