package project.client.ui.gui.controller;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import project.controller.cardsfactory.BuildingCard;
import project.model.Production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductionController extends AbstractController{

    public Button goBackButton;
    public ImageView productionZoneImage;

    public ImageView buildingCard0;
    public ImageView buildingCard1;
    public ImageView buildingCard2;
    public ImageView buildingCard3;
    public ImageView buildingCard4;
    public ImageView buildingCard5;
    private ArrayList<ImageView> allBuildingCard;
    private ArrayList<String> nameOfBuilding;

    public ImageView imageProduction0;
    public ImageView imageProduction1;
    public ImageView imageProduction2;
    public ImageView imageProduction3;
    private ArrayList<ImageView> allPosition;
    private String[] familyMemberOnPositions;


    private boolean[] cardSelected;
    private int positionSelected;

    DropShadow borderGlow= new DropShadow();
    DropShadow borderNull= new DropShadow();




     public ProductionController(){
         super();
        cardSelected = new boolean[6];
        positionSelected = -1;
        //todo guardare bene come fare sta parte in caso di piu di 4 persone nella zona

         /**
          * initializing of border to show wich card are selected
          */
         int depth = 70;//Setting the uniform variable for the glow width and height
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        borderNull.setColor(Color.TRANSPARENT);
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setProductionController(this);
    }

    public void initialize(){

        //example
        buildingCard0.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/commercialHub.png"))));

        nameOfBuilding = new ArrayList<>(6);
        allBuildingCard = new ArrayList<>(6);
        allPosition = new ArrayList<>();
        allBuildingCard.add(buildingCard0);
        allBuildingCard.add(buildingCard1);
        allBuildingCard.add(buildingCard2);
        allBuildingCard.add(buildingCard3);
        allBuildingCard.add(buildingCard4);
        allBuildingCard.add(buildingCard5);

        allPosition.add(imageProduction0);
        if (mainController.getNumberOfPlayer() >= 3){
            allPosition.add(imageProduction1);
            allPosition.add(imageProduction2);
            allPosition.add(imageProduction3);
            familyMemberOnPositions = new String[4];
        }
        else {
            familyMemberOnPositions = new String[1];
        }
    }

    public void uploadImages(){
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        //attenzione che bisogna mettere che sia se i giocatori sono 3 o 4 è la stessa cosa
        productionZoneImage.setImage(new Image(String.valueOf(getClass().getResource("/images/produzione" + mainController.getNumberOfPlayer() + "Giocatori.png"))));
    }

    @Override
    public void refresh(){
        for (int i = 0; i<cardSelected.length; i++){
            cardSelected[i] = false;
            allBuildingCard.get(i).setEffect(borderNull);
        }
    }

    public void doProduction() {
        if (positionSelected == -1)
            return;
        List<String> buildingCardSelected = new LinkedList<>();
        for (int i = 0; i<cardSelected.length; i++){
            if (cardSelected[i]){
                String cardName = nameOfBuilding.get(i);
                buildingCardSelected.add(cardName);
            }
        }
        mainController.doProduction(positionSelected,familiarChosen,buildingCardSelected);
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
        if (familyMemberOnPositions[0] != null)
            return;
        imageProduction0.setImage(getTrueFamiliarImage());
        positionSelected = 0;
    }
    public void placeFamiliarOnProduction1() {
        if (familyMemberOnPositions[1] != null)
            return;
        imageProduction1.setImage(getTrueFamiliarImage());
        positionSelected = 1;
    }
    public void placeFamiliarOnProduction2() {
        if (familyMemberOnPositions[2] != null)
            return;
        imageProduction2.setImage(getTrueFamiliarImage());
        positionSelected = 2;
    }
    public void placeFamiliarOnProduction3() {
        if (familyMemberOnPositions[3] != null)
            return;
        imageProduction3.setImage(getTrueFamiliarImage());
        positionSelected = 3;
    }


    public void showPersonalBoard(){
        super.showPersonalBoard(SceneType.PRODUCTION);
    }

    public void updateCards(List<BuildingCard> buildings) {
        for (int i = 0; i< buildings.size(); i++){
            String oldCard = nameOfBuilding.get(i);
            if (oldCard == null){
                int ciao;
                String nameOfNewCard = buildings.get(i).getName();
                nameOfBuilding.set(i,nameOfNewCard);
                ImageView imageView = allBuildingCard.get(i);
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + nameOfNewCard + ".png"))));
            }
        }
    }

    public void updatePosition(Production[] productions){
        for (int i = 0; i<productions.length; i++){
            if (productions[i].getFamiliarOnThisPosition() == null){
                familyMemberOnPositions[i] = null;
                allPosition.get(i).setImage(null);
            }
            if (familyMemberOnPositions[i].equals(productions[i].getFamiliarOnThisPosition().toString()))
                continue;
            else {
                familyMemberOnPositions[i] = productions[i].getFamiliarOnThisPosition().toString();
                ImageView imageView = allPosition.get(i);
                imageView.setImage(new Image(String.valueOf(getClass().getResource("/images/familiar/" + familyMemberOnPositions[i] + ".png"))));
            }
        }
    }
}

