package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.model.Market;

/**
 * Created by federico on 11/06/17.
 */
public class MarketController extends AbstractController {

    /**
     * the imageViews where the familiar will be placed
     */
    public ImageView imageMarket0;
    public ImageView imageMarket1;
    public ImageView imageMarket2;
    public ImageView imageMarket3;

    private FamiliarPosition[] familiarPositions;

    public MarketController(){
        System.out.print("sono nel controller");
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    public void initialize(){
        if (mainController.getNumberOfPlayer()==4){
            familiarPositions = new FamiliarPosition[4];
            familiarPositions[0] = new FamiliarPosition(imageMarket0);
            familiarPositions[1] = new FamiliarPosition((imageMarket1));
            familiarPositions[2] = new FamiliarPosition(imageMarket2);
            familiarPositions[3] = new FamiliarPosition(imageMarket3);
        }
        else{
            familiarPositions = new FamiliarPosition[2];
            familiarPositions[0] = new FamiliarPosition(imageMarket0);
            familiarPositions[1] = new FamiliarPosition((imageMarket1));
        }
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
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + loginBuilder.getColour() + ".png"))));
    }

    public void goToMarket() {
    }


    
    public void placeFamiliarOnMarket0() {
        imageMarket0.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket1() {
        imageMarket1.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket2() {
        imageMarket2.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket3() {
        imageMarket3.setImage(getTrueFamiliarImage());
    }



    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.MARKET);
    }

    public void updatePosition(Market[] markets){

    }
}
