package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico on 13/06/17.
 */
public class CouncilPalaceController extends AbstractController {


    /**
     * radio button in which you can chose the familiar to use
     */
    public RadioButton familiarOrange;
    public RadioButton familiarWhite;
    public RadioButton familiarBlack;
    public RadioButton familiarNull;

    /**
     * queste sono le immagini el familiar, vanno cariicate quelle giuste in base al colore della famiglia
     */

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
    private ImageView LorenzoMagnifico;

    private Button[] privilegeButtons;
    private boolean[] privilegeChoosen;
    private int maxPrivilegeChosen;


    @FXML
    private ImageView imageInTheCouncil0;
    private List<FamiliarPosition> familiarInTheCouncil;
    @FXML
    private TextField chatText;
    @FXML
    private Label chatArea;
    private boolean familiarPlaced;


    public CouncilPalaceController() {
        super();
        int numberOfDifferentPrivileges = 5;
        privilegeButtons = new Button[numberOfDifferentPrivileges];
        privilegeChoosen = new boolean[numberOfDifferentPrivileges];
        familiarInTheCouncil = new ArrayList<>();
        familiarPlaced = false;
        this.maxPrivilegeChosen = 1;

    }

    public void initialize() {
        super.initialize();
        familiarInTheCouncil.add(new FamiliarPosition(imageInTheCouncil0));
        familiarBox.getChildren().addAll(imageInTheCouncil0);

        privilegeButtons[0] = buttonStoneWood;
        privilegeButtons[1] = buttonServants;
        privilegeButtons[2] = buttonTwoCoins;
        privilegeButtons[3] = buttonMilitaryPoints;
        privilegeButtons[4] = buttonFaithPoints;

    }


    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setCouncilPalaceController(this);
    }


    public void setParameters(int maxPrivilegeChosen) {
        this.maxPrivilegeChosen = maxPrivilegeChosen;
    }

    public void uploadImages() {
        super.uploadImages();
        LorenzoMagnifico.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/LorenzoMagnifico" + mainController.getColour() + ".png"))));
        fillFamilymember(imageFamiliarNull,imageFamiliarBlack,imageFamiliarWhite,imageFamiliarOrange);
        fillRadioButton(familiarNull,familiarBlack,familiarWhite,familiarOrange);

    }

    @Override
    public void updateResources(int coins, int wood, int stone, int servants) {

        updateOneResource(coins,numberOfCoins);
        updateOneResource(wood,numberOfWood);
        updateOneResource(stone,numberOfStone);
        updateOneResource(servants,numberOfServants);
    }

    private int numberOfPrivilegeSelected() {
        int number = 0;
        for (boolean b : privilegeChoosen) {
            if (b) number++;
        }
        return number;
    }

    private void selectPrivilege(int number) {
        if (!(numberOfPrivilegeSelected() < maxPrivilegeChosen) && !privilegeChoosen[number]) return;
        if (!privilegeChoosen[number]) {
            privilegeButtons[number].setStyle("-fx-background-color: transparent;-fx-border-color: chocolate; ");
            privilegeChoosen[number] = true;
        } else {
            privilegeChoosen[number] = false;
            privilegeButtons[number].setStyle("-fx-background-color: transparent;-fx-border-color: transparent; ");
        }
    }

    public void takeStoneWood() {
        selectPrivilege(0);
    }

    public void takeTwoServants() {
        selectPrivilege(1);
    }

    public void takeTwoCoins() {
        selectPrivilege(2);
    }

    public void takeTwoMiliaryPoints() {
        selectPrivilege(3);
    }

    public void takeOneFaithPoint() {
        selectPrivilege(4);
    }

    public void placeFamiliarInCouncil() {
        if (familiarPlaced){
            System.out.println("devo cambiare familiare perchè lho gia piazzato");
            familiarInTheCouncil.remove(familiarInTheCouncil.size() - 1);
            super.placeFamiliar(familiarInTheCouncil, familiarBox);
        }
        else {
            System.out.println("è la prima volta che entro qua");
            familiarPlaced = true;
            super.placeFamiliar(familiarInTheCouncil,familiarBox);
        }
    }


    public void goToCouncil() {
        int privilegeSelected = -1;
        for (int i = 0; i < privilegeChoosen.length; i++) {
            if (privilegeChoosen[i]) privilegeSelected = i;
        }
        if (privilegeSelected == -1 || !familiarPlaced) return;
        familiarPlaced = false;
        mainController.goToCouncil(privilegeSelected, familiarChosen);
    }

    @Override
    public void refresh() {
        chatArea.setText(loginBuilder.getChat().toString());

        for (boolean b : privilegeChoosen)
            b = false;
    }

    public void showPersonalBoard() {
        super.showPersonalBoard(SceneType.COUNCIL);
    }

    public void takeImmediatePrivilege(int quantityOfDifferentPrivileges) {
        loginBuilder.setScene(SceneType.COUNCIL, SceneType.PERSONAL_BOARD);
        blockButton();
        this.maxPrivilegeChosen = quantityOfDifferentPrivileges;
        writeOnChat("AZIONE BONUS: prendi " + maxPrivilegeChosen + "privilegi diversi");
    }

    public void goToMainGame(ActionEvent actionEvent){
        if (familiarPlaced)
            familiarInTheCouncil.remove(familiarInTheCouncil.size() - 1);
        familiarPlaced = false;
        super.goToMainGame(actionEvent);
    }

    public void sendChat(ActionEvent actionEvent){
        sendChat(chatText);
    }

    private void takePrivilege() {
        ArrayList<Integer> privilegeSelected = new ArrayList<>();
        for (int i = 0; i < privilegeChoosen.length; i++) {
            if (privilegeChoosen[i]) privilegeSelected.add(i);
        }

        if (privilegeSelected.size() == maxPrivilegeChosen) {
            mainController.takeBonusPrivileges(privilegeSelected);
            unlockButton();
        }
    }

    protected void blockButton() {
        super.blockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> takePrivilege());
    }

    protected void unlockButton() {
        super.unlockButton(mainGameButton,personalBoard,buttonPlaceFamiliar);
        submit.setOnAction(event -> goToCouncil());
    }
}
