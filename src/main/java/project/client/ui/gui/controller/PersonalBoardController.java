package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.util.ArrayList;
import java.util.List;


public class PersonalBoardController extends AbstractController {
    @FXML
    private Button goBackButton;
    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;


    @FXML
    private ImageView territory0;
    @FXML
    private ImageView territory1;
    @FXML
    private ImageView territory2;
    @FXML
    private ImageView territory3;
    @FXML
    private ImageView territory4;
    @FXML
    private ImageView territory5;
    @FXML
    private ImageView character0;
    @FXML
    private ImageView character1;
    @FXML
    private ImageView character2;
    @FXML
    private ImageView character3;
    @FXML
    private ImageView character4;
    @FXML
    private ImageView character5;
    @FXML
    private ImageView building0;
    @FXML
    private ImageView building1;
    @FXML
    private ImageView building2;
    @FXML
    private ImageView building3;
    @FXML
    private ImageView building4;
    @FXML
    private ImageView building5;
    @FXML
    private ImageView ventures0;
    @FXML
    private ImageView ventures1;
    @FXML
    private ImageView ventures2;
    @FXML
    private ImageView ventures3;
    @FXML
    private ImageView ventures4;
    @FXML
    private ImageView ventures5;

    private List<ImageView> territoryCards;
    private List<ImageView> characterCards;
    private List<ImageView> buildingCards;
    private List<ImageView> venturesCards;
    @FXML
    private TextField chatText;

    public PersonalBoardController() {
        super();
        territoryCards = new ArrayList<>();
        characterCards = new ArrayList<>();
        buildingCards = new ArrayList<>();
        venturesCards = new ArrayList<>();
    }

    public void initialize() {
        territoryCards.add(territory0);
        territoryCards.add(territory1);
        territoryCards.add(territory2);
        territoryCards.add(territory3);
        territoryCards.add(territory4);
        territoryCards.add(territory5);

        characterCards.add(character0);
        characterCards.add(character1);
        characterCards.add(character2);
        characterCards.add(character3);
        characterCards.add(character4);
        characterCards.add(character5);

        buildingCards.add(building0);
        buildingCards.add(building1);
        buildingCards.add(building2);
        buildingCards.add(building3);
        buildingCards.add(building4);
        buildingCards.add(building5);

        venturesCards.add(ventures0);
        venturesCards.add(ventures1);
        venturesCards.add(ventures2);
        venturesCards.add(ventures3);
        venturesCards.add(ventures4);
        venturesCards.add(ventures5);
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setPersonalBoardController(this);
    }

    @Override
    public void refresh() {

    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    public void goBack() {
        SceneType lastScene = loginBuilder.getLastScene();
        loginBuilder.setScene(lastScene, SceneType.PERSONAL_BOARD);
    }


    public void zoomOnTerritory0() {
        loginBuilder.showCardZoomed(territory0.getImage());
    }

    public void zoomOnTerritory1() {
        loginBuilder.showCardZoomed(territory1.getImage());
    }

    public void zoomOnTerritory2() {
        loginBuilder.showCardZoomed(territory2.getImage());
    }

    public void zoomOnTerritory3() {
        loginBuilder.showCardZoomed(territory3.getImage());
    }

    public void zoomOnTerritory4() {
        loginBuilder.showCardZoomed(territory4.getImage());
    }

    public void zoomOnTerritory5() {
        loginBuilder.showCardZoomed(territory5.getImage());
    }


    public void zoomOnCharacter0() {
        loginBuilder.showCardZoomed(character0.getImage());
    }

    public void zoomOnCharacter1() {
        loginBuilder.showCardZoomed(character1.getImage());
    }

    public void zoomOnCharacter2() {
        loginBuilder.showCardZoomed(character2.getImage());
    }

    public void zoomOnCharacter3() {
        loginBuilder.showCardZoomed(character3.getImage());
    }

    public void zoomOnCharacter4() {
        loginBuilder.showCardZoomed(character4.getImage());
    }

    public void zoomOnCharacter5() {
        loginBuilder.showCardZoomed(character5.getImage());
    }


    public void zoomOnBuilding0() {
        loginBuilder.showCardZoomed(building0.getImage());
    }

    public void zoomOnBuilding1() {
        loginBuilder.showCardZoomed(building1.getImage());
    }

    public void zoomOnBuilding2() {
        loginBuilder.showCardZoomed(building2.getImage());
    }

    public void zoomOnBuilding3() {
        loginBuilder.showCardZoomed(building3.getImage());
    }

    public void zoomOnBuilding4() {
        loginBuilder.showCardZoomed(building4.getImage());
    }

    public void zoomOnBuilding5() {
        loginBuilder.showCardZoomed(building5.getImage());
    }


    public void zoomOnVentures0() {
        loginBuilder.showCardZoomed(ventures0.getImage());
    }

    public void zoomOnVentures1() {
        loginBuilder.showCardZoomed(ventures1.getImage());
    }

    public void zoomOnVentures2() {
        loginBuilder.showCardZoomed(ventures2.getImage());
    }

    public void zoomOnVentures3() {
        loginBuilder.showCardZoomed(ventures3.getImage());
    }

    public void zoomOnVentures4() {
        loginBuilder.showCardZoomed(ventures4.getImage());
    }

    public void zoomOnVentures5() {
        loginBuilder.showCardZoomed(ventures5.getImage());
    }


    public void update(PersonalBoard personalBoard) {
        fillList(territoryCards, personalBoard.getTerritories());
        fillList(characterCards, personalBoard.getCharacters());
        fillList(buildingCards, personalBoard.getBuildings());
        fillList(venturesCards, personalBoard.getVentures());
    }


    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    private void fillList(List<ImageView> cards, List<? extends DevelopmentCard> territories) {
        int counter = 0;
        for (DevelopmentCard c : territories) {
            if (c == null) return;
            else {
                if (cards.get(counter).getImage() == null)
                    cards.get(counter).setImage(new Image(String.valueOf(getClass().getResource("/images/cards/" + c.getName() + ".png"))));
                counter++;
            }
        }
    }
}
