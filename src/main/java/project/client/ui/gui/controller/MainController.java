package project.client.ui.gui.controller;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import project.client.ui.ClientSetter;
import project.client.ui.cli.CliConstants;
import project.model.Board;
import project.model.PersonalBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class MainController {
    private static MainController instance;
    private ClientSetter clientSetter;
    private LoginBuilder loginBuilder;
    private TextField chatText;
    private HarvesterController harvesterController;
    private CouncilPalaceController councilPalaceController;
    private GeneralMainGameController generalGameController;
    private LeaderCardController leaderCardController;
    private MarketController marketController;
    private PersonalBoardController personalBoardController;
    private ProductionController productionController;
    private TowersController towerController;

    private List<AbstractController> controllers;
    private Object token;

    private int numberOfPlayer = 2;
    private String colour = "rosso";
    private String nickName;
    private String usernameChosen;

    private BlockingQueue<Integer> integerQueue;
    private BlockingQueue<String> stringQueue;
    private boolean myTurn;


    private MainController(){
        controllers = new ArrayList<>();
        token = new Object();
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
        loginBuilder.showPrimo();
    }


    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayer = numberOfPlayers;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public String getColour() {
        return colour;
    }

    void setHarvesterController(HarvesterController harvesterController) {
        this.harvesterController = harvesterController;
        controllers.add(this.harvesterController);
    }


    void setCouncilPalaceController(CouncilPalaceController councilPalaceController) {
        this.councilPalaceController = councilPalaceController;
        controllers.add(this.councilPalaceController);
    }

    void setGeneralGameController(GeneralMainGameController generalGameController) {
        this.generalGameController = generalGameController;
        controllers.add(this.generalGameController);
    }

    void setLeaderCardController(LeaderCardController leaderCardController) {
        this.leaderCardController = leaderCardController;
        controllers.add(this.leaderCardController);
    }

    void setMarketController(MarketController marketController) {
        this.marketController = marketController;
        controllers.add(this.marketController);
    }

    void setPersonalBoardController(PersonalBoardController personalBoardController) {
        this.personalBoardController = personalBoardController;
        controllers.add(this.personalBoardController);
    }

    void setProductionController(ProductionController productionController) {
        this.productionController = productionController;
        controllers.add(this.productionController);
    }

    void setTowerController(TowersController towerController) {
        this.towerController = towerController;
        controllers.add(this.towerController);
    }


    //DA QUA IN GIU LE COSE CHIAMATE SUL CLIENT SETTER

    void setConnectionType(String connectionType, String usernameChosen) {
        this.usernameChosen = usernameChosen;
        clientSetter.setConnectionType(connectionType);
    }

    void connect(String connectionType, String usernameChosen, String passwordChosen) {
        nickName = usernameChosen;
        System.out.println(nickName);
        clientSetter.setConnectionType(connectionType);
    }

    public void takeDevCard(String towerColour, int floor, String familiarColour )  {
        clientSetter.takeDevCard(towerColour, floor, familiarColour);
    }


    void doProduction(String familiarChosen, List<String> buildingCardSelected) {
        clientSetter.productionAction(familiarChosen, buildingCardSelected);
    }

    void doHarvester(int servants, String familiarChosen) {
        clientSetter.harvesterAction(familiarChosen,servants);
    }

    void goToCouncil(int privilegeSelected, String familiarChosen) {
        clientSetter.councilAction(privilegeSelected,familiarChosen);
    }

    void takeBonusPrivileges(ArrayList<Integer> privilegeSelected) {
        clientSetter.immediatePriviledgeAction(privilegeSelected);
    }

    void doBonusHarvester(int servants) {
        clientSetter.bonusHarvesterAction(servants);
    }

    void doBonusProduction(List<String> buildingCardSelected) {
        clientSetter.bonusProductionAction(buildingCardSelected);
    }

    void takeBonusCardAction(int floor, String towerColourString) {
        clientSetter.takeBonusCardAction(floor,towerColourString);
    }


    void setChoice(String text, int i) {
        integerQueue.add(new Integer(i));
        /*
        switch (text){
            case CliConstants.BOTH_PAYMENT_AVAIABLE:{
                clientSetter.sendChoicePaymentVc(i);
                break;
            }
            case CliConstants.ASK_FOR_PRAYING:{
                if (i==1)
                    clientSetter.prayOrNot(true);
                else
                    clientSetter.prayOrNot(false);
                break;
            }
            case CliConstants.CHOOSE_PERMANENT_EFFECT:{
                clientSetter.sendChoicePe(i);
                break;
            }
        }
        */

    }

    public void playLeaderCard(int cardSelected) {
        clientSetter.playLeaderCard(cardSelected);
    }

    public void discardLeaderCard(int cardSelected) {
        clientSetter.discardLeaderCard(cardSelected);
    }

    public void goToMarket(int positionSelected, String familiarChosen) {
        clientSetter.marketAction(positionSelected,familiarChosen);
    }

    public void takeNickname() {
        clientSetter.loginRequest(usernameChosen);
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
        productionController.updateCards(personalBoard.getBuildings());
        leaderCardController.updateCards(personalBoard.getMyLeaderCard());
        personalBoardController.update(personalBoard);

    }

    public void boardUpdate() {
        Board board = clientSetter.getUiBoard();
        productionController.updatePosition(board.getProductionZone());
        harvesterController.updatePosition(board.getHarvesterZone());
        towerController.updatePosition(board.getAllTowers());
        generalGameController.updateTurn(board.getTurn().getPlayerTurn());
    }

    public void scoreUpdate() {
    }

    public void familyMemberUpdate() {
        for (AbstractController c: controllers){
            c.updateFamilyMember(clientSetter.getUiFamilyMembers());
        }
    }


    public void takePrivilege(int quantityOfDifferentPrivileges) {
        councilPalaceController.takeImmediatePrivilege(quantityOfDifferentPrivileges);
    }

    public void bonusHarvester(int diceValue) {
        harvesterController.bonusHarvester(diceValue);
    }

    public void bonusProduction(int diceValue) {
        productionController.bonusProduction(diceValue);
    }

    public void takeBonusCard(String kindOfCard, String printBonusAction) {
        towerController.takeBonusCard(kindOfCard,printBonusAction);
    }

    public void bothPaymentAvaiable() {
        loginBuilder.showChoice(CliConstants.BOTH_PAYMENT_AVAIABLE, "payment 1", "payment 2");
    }

    public void choosePermanentEffect() {
        loginBuilder.showChoice(CliConstants.CHOOSE_PERMANENT_EFFECT, "effect 1", "effect 2");
    }

    public void askForPraying() {
        loginBuilder.showChoice(CliConstants.ASK_FOR_PRAYING, "yes", "no");
    }

    public void endTurnContext() {
        leaderCardController.endTurnContext();
    }

    public void sendChat(String s) {
        generalGameController.writeOnChat(s);
    }

    public void loginSucceded() {
    }

    public void initializeMainGame() {
        loginBuilder.initalizeMainGame();
    }

    public void showPrimo() {
        loginBuilder.showPrimo();
    }

    public void startMainGame() {
        loginBuilder.startMainGame();
    }

    public void waitingLogin() {
        loginBuilder.waitingScene();
    }

// da qui in giu prove per risposta

    public void skipTurn() {
        myTurn = false;
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("ho fatto partire il nuovo thread che va");
                clientSetter.skipTurn();
            }
        };
        new Thread(a).start();
    }


    public int getScelta() {
        integerQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("sono nel runlater");
                generalGameController.setScelta();
            }
        });
        Integer i = new Integer(0);
        try {
            System.out.println("mi metto in attesa della integerQueue");
             i = integerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i.intValue();
        /*
        while (true){
            System.out.println("sono nel while true");
        try {
            return integerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }

    public void wakeUp(int choiceDone) {
        integerQueue.add(new Integer(choiceDone));
        }

    public String startDraft(List<String> leaderName) {

        stringQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("sono nel runlater");
                loginBuilder.setDraft(leaderName);
            }
        });
        String i = "";
        try {
            System.out.println("mi metto in attesa della integerQueue");
            i = stringQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void addStringQueue(String s) {
        stringQueue.add(s);
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void updateChat(StringBuffer stringBuffer) {
        for (AbstractController c: controllers)
            c.refresh();
    }
}
