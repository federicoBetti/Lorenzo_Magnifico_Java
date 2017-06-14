package project.client.ui.gui.maingame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by federico on 11/06/17.
 */
public class MarketController extends AbstractController {

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
    public ImageView imageMarket0;
    public ImageView imageMarket1;
    public ImageView imageMarket2;
    public ImageView imageMarket3;

    public MarketController(){
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

    public void goToMarket(ActionEvent actionEvent) {
    }


    
    public void placeFamiliarOnMarket0(MouseEvent mouseEvent) {
        imageMarket0.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket1(MouseEvent mouseEvent) {
        imageMarket1.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket2(MouseEvent mouseEvent) {
        imageMarket2.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnMarket3(MouseEvent mouseEvent) {
        imageMarket3.setImage(getTrueFamiliarImage());
    }



    public void showPersonalBoard(ActionEvent actionEvent){
        super.showPersonalBoard(SceneType.MARKET);
    }
}
