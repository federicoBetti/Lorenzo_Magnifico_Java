package project.client.ui.gui.controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by federico on 13/06/17.
 */
public class councilPalaceController extends AbstractController {


    public Button buttonStoneWood;
    public Button buttonServants;
    public Button buttonTwoCoins;
    public Button buttonMilitaryPoints;
    public Button buttonFaithPoints;
    public Button buttonPlaceFamiliar;
    private boolean[] privilegeChoosen;
    private int maxPrivilegeChosen;
    private final int numberOfDifferentPrivileges = 5;



    public ImageView imageInTheCouncil0;
    public ImageView imageInTheCouncil1;
    public ImageView imageInTheCouncil2;
    public ImageView imageInTheCouncil3;
    public ImageView imageInTheCouncil4;
    public ImageView imageInTheCouncil5;
    private ArrayList<ImageView> familiarInTheCouncil;



    public councilPalaceController(){
        super();
        privilegeChoosen = new boolean[numberOfDifferentPrivileges];
        familiarInTheCouncil = new ArrayList<>(6);
        this.maxPrivilegeChosen = 1;

    }

    public void initialize(){
        familiarInTheCouncil.add(imageInTheCouncil0);
        familiarInTheCouncil.add(imageInTheCouncil1);
        familiarInTheCouncil.add(imageInTheCouncil2);
        familiarInTheCouncil.add(imageInTheCouncil3);
        familiarInTheCouncil.add(imageInTheCouncil4);
        familiarInTheCouncil.add(imageInTheCouncil5);

    }


    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setCouncilPalaceController(this);
    }

    public void setParameters(int maxPrivilegeChosen){
        this.maxPrivilegeChosen = maxPrivilegeChosen;
    }

    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + loginBuilder.getColour() + ".png"))));
    }

    private int numberOfPrivilegeSelected(){
        int number = 0;
        for (boolean b: privilegeChoosen){
            if (b)
                number++;
        }
        return number;
    }

    public void takeStoneWood() {
        if (!(numberOfPrivilegeSelected()<maxPrivilegeChosen) && !privilegeChoosen[0])
            return;
        if (!privilegeChoosen[0]){
            buttonStoneWood.setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[0] = true;
            System.out.println("cambio effetto");
        }
        else{
            privilegeChoosen[0] = false;
            buttonStoneWood.setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void takeTwoServants() {
        if (!(numberOfPrivilegeSelected()<maxPrivilegeChosen) && !privilegeChoosen[1])
            return;
        if (!privilegeChoosen[1]){
            buttonServants.setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[1] = true;
            System.out.println("cambio effetto");
        }
        else{
            privilegeChoosen[1] = false;
            buttonServants.setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void takeTwoCoins() {
        if (!(numberOfPrivilegeSelected()<maxPrivilegeChosen) && !privilegeChoosen[2])
            return;
        if (!privilegeChoosen[2]){
            buttonTwoCoins.setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[2] = true;
            System.out.println("cambio effetto");
        }
        else{
            privilegeChoosen[2] = false;
            buttonStoneWood.setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void takeTwoMiliaryPoints() {
        if (!(numberOfPrivilegeSelected()<maxPrivilegeChosen) && !privilegeChoosen[3])
            return;
        if (!privilegeChoosen[3]){
            buttonMilitaryPoints.setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[3] = true;
            System.out.println("cambio effetto");
        }
        else{
            privilegeChoosen[3] = false;
            buttonStoneWood.setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void takeOneFaithPoint() {
        if (!(numberOfPrivilegeSelected()<maxPrivilegeChosen) && !privilegeChoosen[4])
            return;
        if (!privilegeChoosen[4]){
            buttonFaithPoints.setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[4] = true;
            System.out.println("cambio effetto");
        }
        else{
            privilegeChoosen[4] = false;
            buttonStoneWood.setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void placeFamiliarInCouncil() {
        ImageView spotToMoveFamiliar = findFirstFreePlace();
        spotToMoveFamiliar.setImage(getTrueFamiliarImage());
    }

    private ImageView findFirstFreePlace() {
        for (ImageView imageView: familiarInTheCouncil){
            if (imageView.getImage() == null)
                return imageView;
        }
        return imageInTheCouncil5; //attenzione
    }

    public void goToCouncil() {

    }

    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.COUNCIL);
    }
}
