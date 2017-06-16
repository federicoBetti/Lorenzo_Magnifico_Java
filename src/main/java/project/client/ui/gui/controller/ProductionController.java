package project.client.ui.gui.controller;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ProductionController extends AbstractController{
        public Button goBackButton;


    /**
     * button placed on the card. if you click them, you select the corrispective card
     */
    public Button buttonBuildingCard0;
    public Button buttonBuildingCard1;
    public Button buttonBuildingCard2;
    public Button buttonBuildingCard3;
    public Button buttonBuildingCard4;
    public Button buttonBuildingCard5;

    public ImageView buildingCard0;
    public ImageView buildingCard1;
    public ImageView buildingCard2;
    public ImageView buildingCard3;
    public ImageView buildingCard4;
    public ImageView buildingCard5;
    private ArrayList<ImageView> allBuildingCard;



    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;

    public ImageView imageProduction0;
    public ImageView imageProduction1;
    public ImageView imageProduction2;
    public ImageView imageProduction3;


    private boolean[] cardSelected;

    DropShadow borderGlow= new DropShadow();
    DropShadow borderNull= new DropShadow();




     public ProductionController(){
         super();
        cardSelected = new boolean[6];

         /**
          * initializing of border to show wich card are selected
          */
         int depth = 70;//Setting the uniform variable for the glow width and height
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);
    }
    public void initialize(){

        buildingCard0.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/commercialHub.png"))));
        allBuildingCard = new ArrayList<>(6);
        allBuildingCard.add(buildingCard0);
        allBuildingCard.add(buildingCard1);
        allBuildingCard.add(buildingCard2);
        allBuildingCard.add(buildingCard3);
        allBuildingCard.add(buildingCard4);
        allBuildingCard.add(buildingCard5);

    }

    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + loginBuilder.getColour() + ".png"))));
    }


    public void doProduction() {
    }

    private void selectCard(int index){
        if (!cardSelected[index]){
            allBuildingCard.get(index).setEffect(borderGlow);
            cardSelected[index] = true;
        }
        else{
            cardSelected[index] = false;
            allBuildingCard.get(index).setEffect(borderNull);
        }
    }

    public void selectBuildingCard0(MouseEvent MouseEvent) {
        selectCard(0);
    }

    public void selectBuildingCard1(MouseEvent MouseEvent) {
        selectCard(1);
    }

    public void selectBuildingCard2(MouseEvent MouseEvent) {
        selectCard(2);
    }

    public void selectBuildingCard3(MouseEvent MouseEvent) {
        selectCard(3);
    }

    public void selectBuildingCard4(MouseEvent MouseEvent) {
        selectCard(4);
    }

    public void selectBuildingCard5(MouseEvent MouseEvent) {
        selectCard(5);
    }



    public void placeFamiliarOnProduction0() {
        imageProduction0.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnProduction1() {
        imageProduction1.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnProduction2() {
        imageProduction2.setImage(getTrueFamiliarImage());
    }
    public void placeFamiliarOnProduction3() {
        imageProduction3.setImage(getTrueFamiliarImage());
    }


    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.PRODUCTION);
    }
}

