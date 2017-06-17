package project.client.ui.gui.controller;

import javafx.scene.control.TextField;
import project.client.ui.ClientSetter;
import project.model.PersonalBoard;


public class MainController {
    private static MainController instance;
    private ClientSetter clientSetter;
    private LoginBuilder loginBuilder;
    private TextField chatText;
    private HarvesterController harvesterController;


    private MainController(){

    }
    public static MainController getInstance(){
        if (instance == null) {
            instance = new MainController();
            return instance;
        }
        else
            return instance;
    }

    public void setClientSetter(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void showMainGame() {
        loginBuilder.startMainGame();
    }


    //DA QUA IN GIU LE COSE CHIAMATE SUL CLIENT SETTER

    public void setConnectionType(String connectionType) {
        clientSetter.setConnectionType(connectionType);
    }

    public void connect(String usernameChosen, String passwordChosen) {
        clientSetter.loginRequest(usernameChosen);
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour )  {
        clientSetter.takeDevCard(towerColour, floor, familiarColour);
    }

    public void setHarvesterController(HarvesterController harvesterController) {
        this.harvesterController = harvesterController;
    }


    //DA QUA IN GIU LE COSE CHIAMATE DAL CLIENT SETTER SULLA GRAFICA


    public void personalBoardUpdate(){
        PersonalBoard personalBoard = clientSetter.getUiPersonalBoard();
        int coins = personalBoard.getCoins();
        int wood = personalBoard.getWood();
        int stone = personalBoard.getStone();
        int servants = personalBoard.getServants();
        harvesterController.updateCards(personalBoard.getTerritories());
        harvesterController.updateResources(coins,wood,stone,servants);

    }

    public void boardUpdate() {
    }

    public void scoreUpdate() {
    }

    public void familyMemberUpdate() {
        harvesterController.updateFamilyMember(clientSetter.getUiFamilyMembers());
    }
}
