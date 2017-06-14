package project.client.ui.gui.maingame;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class PersonalBoardController extends AbstractController {
    public Button goBackButton;


    public ImageView territory0;
    public ImageView territory1;
    public ImageView territory2;
    public ImageView territory3;
    public ImageView territory4;
    public ImageView territory5;
    public ImageView character0;
    public ImageView character1;
    public ImageView character2;
    public ImageView character3;
    public ImageView character4;
    public ImageView character5;
    public ImageView building0;
    public ImageView building1;
    public ImageView building2;
    public ImageView building3;
    public ImageView building4;
    public ImageView building5;
    public ImageView ventures0;
    public ImageView ventures1;
    public ImageView ventures2;
    public ImageView ventures3;
    public ImageView ventures4;
    public ImageView ventures5;

    public PersonalBoardController(){
        imageFamiliarBlack = new ImageView();
        imageFamiliarNull = new ImageView();
        imageFamiliarWhite = new ImageView();
        imageFamiliarOrange = new ImageView();
    }

    public void goBack() {
        SceneType lastScene = mainController.getLastScene();
        mainController.setScene(lastScene, SceneType.PERSONAL_BOARD);
    }


    public void zoomOnTerritory0() {
        mainController.showCardZoomed(territory0.getImage());
    }
    public void zoomOnTerritory1() {
        mainController.showCardZoomed(territory1.getImage());
    }
    public void zoomOnTerritory2() {
        mainController.showCardZoomed(territory2.getImage());
    }
    public void zoomOnTerritory3() {
        mainController.showCardZoomed(territory3.getImage());
        /**
         *todo dire che carta scelta è quella di territory 3. magari prendendo il riferiemtno alla board sul cliente prendendo il nome da mandare poi
         * al client setter. poi ogni volta che si vuole psizionare un prosnaggio bisogna controllare la board che quel posto sia vuoto altriemnti non lo fa fare
         * poi bisogna far si che il mainController.showCardZoomed(e posizioanto è uno solo, e non posso metterne tanti
         */

    }
    public void zoomOnTerritory4() {
        mainController.showCardZoomed(territory4.getImage());
    }
    public void zoomOnTerritory5() {
        mainController.showCardZoomed(territory5.getImage());
    }


    public void zoomOnCharacter0() {
        mainController.showCardZoomed(character0.getImage());
    }
    public void zoomOnCharacter1() {
        mainController.showCardZoomed(character1.getImage());
    }
    public void zoomOnCharacter2() {
        mainController.showCardZoomed(character2.getImage());
    }
    public void zoomOnCharacter3() {
        mainController.showCardZoomed(character3.getImage());
    }
    public void zoomOnCharacter4() {
        mainController.showCardZoomed(character4.getImage());
    }
    public void zoomOnCharacter5() {
        mainController.showCardZoomed(character5.getImage());
    }


    public void zoomOnBuilding0() {
        mainController.showCardZoomed(building0.getImage());
    }
    public void zoomOnBuilding1() {
        mainController.showCardZoomed(building1.getImage());
    }
    public void zoomOnBuilding2() {
        mainController.showCardZoomed(building2.getImage());
    }
    public void zoomOnBuilding3() {
        mainController.showCardZoomed(building3.getImage());
    }
    public void zoomOnBuilding4() {
        mainController.showCardZoomed(building4.getImage());
    }
    public void zoomOnBuilding5() {
        mainController.showCardZoomed(building5.getImage());
    }


    public void zoomOnVentures0() {
        mainController.showCardZoomed(ventures0.getImage());
    }
    public void zoomOnVentures1() {
        mainController.showCardZoomed(ventures1.getImage());
    }
    public void zoomOnVentures2() {
        mainController.showCardZoomed(ventures2.getImage());
    }
    public void zoomOnVentures3() {
        mainController.showCardZoomed(ventures3.getImage());
    }
    public void zoomOnVentures4() {
        mainController.showCardZoomed(ventures4.getImage());
    }
    public void zoomOnVentures5() {
        mainController.showCardZoomed(ventures5.getImage());
    }

}
