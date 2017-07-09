package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * controller  of personal board scene
 */
public class PersonalBoardController extends AbstractController {
    @FXML
    private  ImageView bonusTile;
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
    @FXML
    private Label chatArea;

    /**
     * constructor
     */
    public PersonalBoardController() {
        super();
        territoryCards = new ArrayList<>();
        characterCards = new ArrayList<>();
        buildingCards = new ArrayList<>();
        venturesCards = new ArrayList<>();
    }

    /**
     * initialization
     */
    @Override
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

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setPersonalBoardController(this);
    }

    /**
     * method used to refresh the scene
     */
    @Override
    public void refresh() {
        chatArea.setText(loginBuilder.getChat().toString());
    }

    /**
     * method used to update resources
     * @param coins new coins value
     * @param wood new wood value
     * @param stone new stone value
     * @param servants new servants value
     */
    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    /**
     * method used to go back to the last scene
     */
    public void goBack() {
        SceneType lastScene = loginBuilder.getLastScene();
        loginBuilder.setScene(lastScene, SceneType.PERSONAL_BOARD);
    }

    /**
     * method used to zoom on the territory card 0
     */
    public void zoomOnTerritory0() {
        loginBuilder.showCardZoomed(territory0.getImage());
    }

    /**
     * method used to zoom on the territory card 1
     */
    public void zoomOnTerritory1() {
        loginBuilder.showCardZoomed(territory1.getImage());
    }

    /**
     * method used to zoom on the territory card 2
     */
    public void zoomOnTerritory2() {
        loginBuilder.showCardZoomed(territory2.getImage());
    }

    /**
     * method used to zoom on the territory card 3
     */
    public void zoomOnTerritory3() {
        loginBuilder.showCardZoomed(territory3.getImage());
    }

    /**
     * method used to zoom on the territory card 4
     */
    public void zoomOnTerritory4() {
        loginBuilder.showCardZoomed(territory4.getImage());
    }

    /**
     * method used to zoom on the territory card 5
     */
    public void zoomOnTerritory5() {
        loginBuilder.showCardZoomed(territory5.getImage());
    }

    /**
     * method used to zoom on the character card 0
     */
    public void zoomOnCharacter0() {
        loginBuilder.showCardZoomed(character0.getImage());
    }

    /**
     * method used to zoom on the character card 1
     */
    public void zoomOnCharacter1() {
        loginBuilder.showCardZoomed(character1.getImage());
    }

    /**
     * method used to zoom on the character card 2
     */
    public void zoomOnCharacter2() {
        loginBuilder.showCardZoomed(character2.getImage());
    }

    /**
     * method used to zoom on the character card 3
     */
    public void zoomOnCharacter3() {
        loginBuilder.showCardZoomed(character3.getImage());
    }

    /**
     * method used to zoom on the character card 4
     */
    public void zoomOnCharacter4() {
        loginBuilder.showCardZoomed(character4.getImage());
    }

    /**
     * method used to zoom on the character card 5
     */
    public void zoomOnCharacter5() {
        loginBuilder.showCardZoomed(character5.getImage());
    }

    /**
     * method used to zoom on the building card 0
     */
    public void zoomOnBuilding0() {
        loginBuilder.showCardZoomed(building0.getImage());
    }

    /**
     * method used to zoom on the building card 1
     */
    public void zoomOnBuilding1() {
        loginBuilder.showCardZoomed(building1.getImage());
    }

    /**
     * method used to zoom on the building card 2
     */
    public void zoomOnBuilding2() {
        loginBuilder.showCardZoomed(building2.getImage());
    }

    /**
     * method used to zoom on the building card 3
     */
    public void zoomOnBuilding3() {
        loginBuilder.showCardZoomed(building3.getImage());
    }

    /**
     * method used to zoom on the building card 4
     */
    public void zoomOnBuilding4() {
        loginBuilder.showCardZoomed(building4.getImage());
    }

    /**
     * method used to zoom on the building card 5
     */
    public void zoomOnBuilding5() {
        loginBuilder.showCardZoomed(building5.getImage());
    }

    /**
     * method used to zoom on the ventures card 0
     */
    public void zoomOnVentures0() {
        loginBuilder.showCardZoomed(ventures0.getImage());
    }

    /**
     * method used to zoom on the ventures card 1
     */
    public void zoomOnVentures1() {
        loginBuilder.showCardZoomed(ventures1.getImage());
    }

    /**
     * method used to zoom on the ventures card 2
     */
    public void zoomOnVentures2() {
        loginBuilder.showCardZoomed(ventures2.getImage());
    }

    /**
     * method used to zoom on the ventures card 3
     */
    public void zoomOnVentures3() {
        loginBuilder.showCardZoomed(ventures3.getImage());
    }

    /**
     * method used to zoom on the ventures card 4
     */
    public void zoomOnVentures4() {
        loginBuilder.showCardZoomed(ventures4.getImage());
    }

    /**
     * method used to zoom on the ventures card 5
     */
    public void zoomOnVentures5() {
        loginBuilder.showCardZoomed(ventures5.getImage());
    }

    /**
     * method called to update card
     * @param personalBoard personal board updated
     */
    public void update(PersonalBoard personalBoard) {
        fillList(territoryCards, personalBoard.getTerritories());
        fillList(characterCards, personalBoard.getCharacters());
        fillList(buildingCards, personalBoard.getBuildings());
        fillList(venturesCards, personalBoard.getVentures());
    }

    /**
     * method used to send a message in the chat
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     *  method used to update a list of card
     * @param cards imageviews of card in the gui
     * @param territories list of card arrived from update
     */
    private void fillList(List<ImageView> cards, List<? extends DevelopmentCard> territories) {
        int counter = 0;
        for (DevelopmentCard c : territories) {
            if (c == null) return;
            else {
                if (cards.get(counter).getImage() == null)
                    cards.get(counter).setImage(new Image(valueOf(getClass().getResource("/images/cards/" + c.getName() + ".png"))));
                counter++;
            }
        }
    }

    /**
     * method used to set the bonus tile chosen in the draft
     * @param i number of tile chosen
     */
    void setBonusTile(int i) {
        if (bonusTile.getImage()!=null)
            return;
        bonusTile.setImage(new Image(valueOf(getClass().getResource("/images/risorse/bonusTile" + i + ".png"))));
    }
}
