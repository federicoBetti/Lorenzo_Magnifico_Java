package project.client.ui.gui.controller;

import javafx.scene.control.TextField;
import project.client.ui.ClientSetter;
import project.model.PersonalBoard;

import java.util.ArrayList;
import java.util.List;


public class MainController {
    private static MainController instance;
    private ClientSetter clientSetter;
    private LoginBuilder loginBuilder;
    private TextField chatText;
    private HarvesterController harvesterController;
    private project.client.ui.gui.controller.councilPalaceController councilPalaceController;
    private GeneralMainGameController generalGameController;
    private LeaderCardController leaderCardController;
    private MarketController marketController;
    private PersonalBoardController personalBoardController;
    private ProductionController productionController;
    private TowersController towerController;

    private List<AbstractController> controllers;


    private MainController(){
        controllers = new ArrayList<>();
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
        controllers.add(this.harvesterController);
    }


    public void setCouncilPalaceController(councilPalaceController councilPalaceController) {
        this.councilPalaceController = councilPalaceController;
        controllers.add(this.councilPalaceController);
    }

    public void setGeneralGameController(GeneralMainGameController generalGameController) {
        this.generalGameController = generalGameController;
        controllers.add(this.generalGameController);
    }

    public void setLeaderCardController(LeaderCardController leaderCardController) {
        this.leaderCardController = leaderCardController;
        controllers.add(this.leaderCardController);
    }

    public void setMarketController(MarketController marketController) {
        this.marketController = marketController;
        controllers.add(this.marketController);
    }

    public void setPersonalBoardController(PersonalBoardController personalBoardController) {
        this.personalBoardController = personalBoardController;
        controllers.add(this.personalBoardController);
    }

    public void setProductionController(ProductionController productionController) {
        this.productionController = productionController;
        controllers.add(this.productionController);
    }

    public void setTowerController(TowersController towerController) {
        this.towerController = towerController;
        controllers.add(this.towerController);
    }

    //DA QUA IN GIU LE COSE CHIAMATE DAL CLIENT SETTER SULLA GRAFICA


    public void personalBoardUpdate(){
        PersonalBoard personalBoard = clientSetter.getUiPersonalBoard();
        int coins = personalBoard.getCoins();
        int wood = personalBoard.getWood();
        int stone = personalBoard.getStone();
        int servants = personalBoard.getServants();
        for (AbstractController c: controllers){
            c.updateResources(coins,wood,stone,servants);
        }
        harvesterController.updateCards(personalBoard.getTerritories());

    }

    public void boardUpdate() {
    }

    public void scoreUpdate() {
    }

    public void familyMemberUpdate() {
        harvesterController.updateFamilyMember(clientSetter.getUiFamilyMembers());
    }


}
