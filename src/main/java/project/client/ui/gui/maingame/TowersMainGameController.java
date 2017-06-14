package project.client.ui.gui.maingame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Created by federico on 10/06/17.
 */
public class TowersMainGameController extends AbstractController{

    /**
     * todo vedere come importnare la board, a ogni update deve essere refresshata la board e aggiornate le carte etc
     */
    /**
     * queste sono le imageView dove dentro ci staranno le immagini delle carte
     */
    public ImageView green3;
    public ImageView green2;
    public ImageView green1;
    public ImageView green0;
    public ImageView blue3;
    public ImageView blue2;
    public ImageView blue1;
    public ImageView blue0;
    public ImageView yellow3;
    public ImageView yellow2;
    public ImageView yellow1;
    public ImageView yellow0;
    public ImageView purple3;
    public ImageView purple2;
    public ImageView purple1;
    public ImageView purple0;



    public Button personalBoard;
    public Button submit;


    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;


    /**
     * queste sono le image view che si trovano nei posti slezione di ogni carta. quando verrà selezioanta una carta
     * il familiare selezionato verrano posizionati li
     */
    public ImageView familiarGreen3;
    public ImageView familiarGreen2;
    public ImageView familiarGreen1;
    public ImageView familiarGreen0;
    public ImageView familiarBlue3;
    public ImageView familiarBlue2;
    public ImageView familiarBlue1;
    public ImageView familiarBlue0;
    public ImageView familiarYellow3;
    public ImageView familiarYellow2;
    public ImageView familiarYellow1;
    public ImageView familiarYellow0;
    public ImageView familiarPurple3;
    public ImageView familiarPurple2;
    public ImageView familiarPurple1;
    public ImageView familiarPurple0;


    public ToggleGroup familiar;


    public Button mainGameButton;



    public TowersMainGameController(){
        super();
        System.out.print("sono nel controller");
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni
    @FXML
    private void initialize(){

        imageFamiliarNull.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/rossoZero.png"))));
        imageFamiliarBlack.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/rossoNero.png"))));
        imageFamiliarWhite.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/rossoBianco.png"))));
        imageFamiliarOrange.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/rossoArancio.png"))));
        green0.setImage(new Image(String.valueOf(getClass().getResource("/images/commercialHub.png"))));
        green1.setImage(new Image(String.valueOf(getClass().getResource("/images/woods.png"))));


        System.out.print("sono nel initaize");
    }


    public void showCardGreen3() {
        mainController.showCardZoomed(green3.getImage());
        /**
         *todo dire che carta scelta è quella di green 3. magari prendendo il riferiemtno alla board sul cliente prendendo il nome da mandare poi
         * al client setter. poi ogni volta che si vuole psizionare un prosnaggio bisogna controllare la board che quel posto sia vuoto altriemnti non lo fa fare
         * poi bisogna far si che il mainController.showCardZoomed(e posizioanto è uno solo, e non posso metterne tanti
         */

    }

    public void showCardGreen2() {
        mainController.showCardZoomed(green2.getImage());
    }

    public void showCardGreen1() {
        mainController.showCardZoomed(green1.getImage());
    }

    public void showCardGreen0() {
        mainController.showCardZoomed(green0.getImage());
    }

    public void showCardBlue3() {
        mainController.showCardZoomed(blue3.getImage());
    }

    public void showCardBlue2() {
        mainController.showCardZoomed(blue2.getImage());
    }

    public void showCardBlue1() {
        mainController.showCardZoomed(blue1.getImage());
    }

    public void showCardBlue0() {
        mainController.showCardZoomed(blue0.getImage());
    }

    public void showCardYellow0() {
        mainController.showCardZoomed(yellow0.getImage());
    }

    public void showCardYellow1() {
        mainController.showCardZoomed(yellow1.getImage());
    }

    public void showCardYellow2() {
        mainController.showCardZoomed(yellow2.getImage());
    }

    public void showCardYellow3() {
        mainController.showCardZoomed(yellow3.getImage());
    }

    public void showCardPurple0() {
        mainController.showCardZoomed(purple0.getImage());
    }

    public void showCardPurple1() {
        mainController.showCardZoomed(purple1.getImage());
    }

    public void showCardPurple2() {
        mainController.showCardZoomed(purple2.getImage());
    }

    public void showCardPurple3() {
        mainController.showCardZoomed(purple3.getImage());
    }


    public void takeCardGreen3() {
        familiarGreen3.setImage(getTrueFamiliarImage());
    }

    public void takeCardGreen2() {
        familiarGreen2.setImage(getTrueFamiliarImage());
    }

    public void takeCardGreen1() {
        familiarGreen1.setImage(getTrueFamiliarImage());
   }

    public void takeCardGreen0() {
        familiarGreen0.setImage(getTrueFamiliarImage());
    }


    public void takeCardBlue3() {
        familiarBlue3.setImage(getTrueFamiliarImage());
    }

    public void takeCardBlue2() {
        familiarBlue2.setImage(getTrueFamiliarImage());
    }

    public void takeCardBlue1() {
        familiarBlue1.setImage(getTrueFamiliarImage());
    }

    public void takeCardBlue0() {
        familiarBlue0.setImage(getTrueFamiliarImage());
    }

    public void takeCardYellow0() {
        familiarYellow0.setImage(getTrueFamiliarImage());
    }

    public void takeCardYellow1() {
        familiarYellow1.setImage(getTrueFamiliarImage());
    }

    public void takeCardYellow2() {
        familiarYellow2.setImage(getTrueFamiliarImage());
    }

    public void takeCardYellow3() {
        familiarYellow3.setImage(getTrueFamiliarImage());
    }

    public void takeCardPurple0() {
        familiarPurple0.setImage(getTrueFamiliarImage());
    }

    public void takeCardPurple1() {
        familiarPurple1.setImage(getTrueFamiliarImage());
    }

    public void takeCardPurple2() {
        familiarPurple2.setImage(getTrueFamiliarImage());
    }

    public void takeCardPurple3() {
        familiarPurple3.setImage(getTrueFamiliarImage());
    }





    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.TOWERS);
    }


    public void takeCard() {


    }

    public void imageGreen1Clicked() {
        familiarGreen1.setImage(getTrueFamiliarImage());
    }
}
