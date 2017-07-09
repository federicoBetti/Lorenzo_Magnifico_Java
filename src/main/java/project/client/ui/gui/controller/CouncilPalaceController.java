package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import project.model.Council;

import java.util.ArrayList;
import java.util.List;

/**
 * class used to control the scene of the council place
 */
public class CouncilPalaceController extends AbstractController {


    /**
     * radio button in which you can chose the familiar to use
     */
    @FXML
    private  RadioButton familiarOrange;
    @FXML
    private  RadioButton familiarWhite;
    @FXML
    private  RadioButton familiarBlack;
    @FXML
    private  RadioButton familiarNull;

    @FXML
    private  ImageView imageFamiliarNull;

    @FXML
    private  ImageView imageFamiliarBlack;

    @FXML
    private  ImageView imageFamiliarWhite;

    @FXML
    private  ImageView imageFamiliarOrange;


    @FXML
    private Label numberOfCoins;

    @FXML
    private Label numberOfWood;

    @FXML
    private Label numberOfStone;

    @FXML
    private Label numberOfServants;

    @FXML
    private Button personalBoard;
    @FXML
    private Button submit;
    @FXML
    private Button mainGameButton;
    @FXML
    private Button buttonStoneWood;
    @FXML
    private Button buttonServants;
    @FXML
    private Button buttonTwoCoins;
    @FXML
    private Button buttonMilitaryPoints;
    @FXML
    private Button buttonFaithPoints;

    @FXML
    private Button buttonPlaceFamiliar;
    @FXML
    private HBox familiarBox;


    @FXML
    private ImageView lorenzoMagnifico;

    private Button[] privilegeButtons;
    private boolean[] privilegeChosen;
    private int maxPrivilegeChosen;


    @FXML
    private ImageView imageInTheCouncil0;
    private List<FamiliarPosition> familiarInTheCouncil;
    @FXML
    private TextField chatText;
    @FXML
    private ScrollPane chatArea;
    private boolean familiarPlaced;
    @FXML
    private ToggleGroup familiar;

    String transparentStyle = "-fx-background-color: transparent;-fx-border-color: transparent; ";

    /**
     * constructor
     */
    public CouncilPalaceController() {
        super();
        int numberOfDifferentPrivileges = 5;
        privilegeButtons = new Button[numberOfDifferentPrivileges];
        privilegeChosen = new boolean[numberOfDifferentPrivileges];
        familiarInTheCouncil = new ArrayList<>();
        familiarPlaced = false;
        this.maxPrivilegeChosen = 1;

    }

    /**
     * method called by application during initialization of the scene
     */
    @Override
    public void initialize() {
        super.initialize();
        familiarInTheCouncil.add(new FamiliarPosition(imageInTheCouncil0));

        privilegeButtons[0] = buttonStoneWood;
        privilegeButtons[1] = buttonServants;
        privilegeButtons[2] = buttonTwoCoins;
        privilegeButtons[3] = buttonMilitaryPoints;
        privilegeButtons[4] = buttonFaithPoints;

    }

    /**
     * setter
     * @param mainController main controller used to communicate with clientSetter
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setCouncilPalaceController(this);
    }

    /**
     * setter for bonus action
     * @param maxPrivilegeChosen max privilege that you can chose during a bonus action
     */
    public void setParameters(int maxPrivilegeChosen) {
        this.maxPrivilegeChosen = maxPrivilegeChosen;
    }

    /**
     * method called to upload the images during the initialization
     */
    @Override
    public void uploadImages() {
        super.uploadImages();
        lorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        fillFamilyMember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);

    }

    /**
     * method called to update resources
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
     * methods that count the number of privileges selected
     * @return number of privileges selected
     */
    private int numberOfPrivilegeSelected() {
        int number = 0;
        for (boolean b : privilegeChosen) {
            if (b) number++;
        }
        return number;
    }

    /**
     * method used to select a privilege
     * @param number number of privilege selcted
     */
    private void selectPrivilege(int number) {
        if ((numberOfPrivilegeSelected() >= maxPrivilegeChosen) && !privilegeChosen[number]) return;
        if (!privilegeChosen[number]) {
            privilegeButtons[number].setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChosen[number] = true;
        } else {
            privilegeChosen[number] = false;
            privilegeButtons[number].setStyle(transparentStyle);
        }
    }

    /**
     * method used to select the first privilege
     */
    public void takeStoneWood() {
        selectPrivilege(0);
    }

    /**
     * method used to select the second privilege
     */
    public void takeTwoServants() {
        selectPrivilege(1);
    }

    /**
     * method used to select the third privilege
     */
    public void takeTwoCoins() {
        selectPrivilege(2);
    }

    /**
     * method used to select the fourth privilege
     */
    public void takeTwoMilitaryPoints() {
        selectPrivilege(3);
    }

    /**
     * method used to select the fifth privilege
     */
    public void takeOneFaithPoint() {
        selectPrivilege(4);
    }

    /**
     * method used to place a familiar in the council
     */
    public void placeFamiliarInCouncil() {
        if (!mainController.isMyTurn())
            return;
        if (familiarChosen.equals(""))
            return;
        if (familiarPlaced){
            if (familiarInTheCouncil.size() > 1)
                familiarInTheCouncil.remove(familiarInTheCouncil.size() - 1);
            else {
                familiarInTheCouncil.get(0).setFamiliarName("");
                familiarInTheCouncil.get(0).setImage(null);
            }
            super.placeFamiliar(familiarInTheCouncil, familiarBox);
        }
        else {
            familiarPlaced = true;
            super.placeFamiliar(familiarInTheCouncil,familiarBox);
        }
    }

    /**
     * method that make the action of going in the council and communicate it to the client setter
     */
    public void goToCouncil() {
        int privilegeSelected = -1;
        for (int i = 0; i < privilegeChosen.length; i++) {
            if (privilegeChosen[i]){
                privilegeSelected = i;
                privilegeChosen[i] = false;
                privilegeButtons[i].setStyle(transparentStyle);

            }
        }
        if (privilegeSelected == -1 || !familiarPlaced) return;
        familiarPlaced = false;
        mainController.goToCouncil(privilegeSelected, familiarChosen);
    }

    /**
     * method called to refresh the scene
     */
    @Override
    public void refresh() {
        super.refresh();
        //todo fare che mi toglie l'ultimo piazzato che non lo fa, guardare perchè mi ha lasciato quello del turno prima
        super.unselectedRadioButton(familiar);
        chatArea.setAccessibleText(loginBuilder.getChat().toString());

        if (familiarPlaced)
            familiarInTheCouncil.remove(familiarInTheCouncil.size() - 1);
        familiarPlaced = false;
        for (boolean bo : privilegeChosen)
            bo = false;
    }

    /**
     * method used to show the personal board scene
     */
    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.COUNCIL);
    }

    /**
     * method called by the server to perform an action bonus
     * @param quantityOfDifferentPrivileges number of different privilege to chose
     */
    void takeImmediatePrivilege(int quantityOfDifferentPrivileges) {
        loginBuilder.setScene(SceneType.COUNCIL, SceneType.PERSONAL_BOARD);
        blockButton();
        this.maxPrivilegeChosen = quantityOfDifferentPrivileges;
        writeOnChat("AZIONE BONUS: prendi " + maxPrivilegeChosen + "privilegi diversi");
    }

    /**
     * method used to go back to main game scene
     */
    @Override
    public void goToMainGame(){
        if (familiarPlaced)
            familiarInTheCouncil.remove(familiarInTheCouncil.size() - 1);
        familiarPlaced = false;
        super.goToMainGame();
    }

    /**
     * method used to send a chat message
     */
    public void sendChat(){
        sendChat(chatText);
    }

    /**
     * method called by submit button during a bonus action
     */
    private void takePrivilege() {
        ArrayList<Integer> privilegeSelected = new ArrayList<>();
        for (int i = 0; i < privilegeChosen.length; i++) {
            if (privilegeChosen[i]) privilegeSelected.add(i);
        }

        if (privilegeSelected.size() == maxPrivilegeChosen) {
            for (int i = 0; i < privilegeChosen.length; i++) {
                if (privilegeChosen[i]){
                    privilegeChosen[i] = false;
                    privilegeButtons[i].setStyle(transparentStyle);
            }
        }
            unlockButton();
            mainController.takeBonusPrivileges(privilegeSelected);

        }
        else writeOnChat("you don't have taken the right number of privileges!\nthe righy number is " + maxPrivilegeChosen + "\n");
    }

    /**
     * method used to block button
     */
    private void blockButton() {
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takePrivilege());
    }

    /**
     * method used to unlock button
     */
    void unlockButton() {
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> goToCouncil());
    }

    /**
     * method used to update position in the council
     * @param councilZone server council zone
     */
    void updatePosition(List<Council> councilZone) {

        super.updatePosition(councilZone,familiarInTheCouncil);

        super.updateHBox(familiarInTheCouncil,familiarBox);

    }
}
