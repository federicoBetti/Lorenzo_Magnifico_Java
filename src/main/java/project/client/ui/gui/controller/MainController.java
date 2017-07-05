package project.client.ui.gui.controller;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import project.client.ui.ClientSetter;
import project.client.ui.cli.CliConstants;
import project.controller.cardsfactory.LeaderCard;
import project.model.Board;
import project.model.PersonalBoard;
import project.model.Tile;

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

    private Board board;
    private int numberOfPrivelege;
    private boolean actionBonusOn;
    private InitialLogin initialLoginController;
    private ChoiceController choicheController;
    private boolean firstTime;


    private MainController(){
        controllers = new ArrayList<>();
        token = new Object();
        firstTime = true;
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

        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("ho fatto partire il nuovo thread per richiesta per la torre");
                clientSetter.takeDevCard(towerColour, floor, familiarColour);
            }
        };
        new Thread(a).start();
    }


    void doProduction(String familiarChosen, List<String> buildingCardSelected) {
        Runnable a = () -> clientSetter.productionAction(familiarChosen, buildingCardSelected);;;
        new Thread(a).start();

    }

    void doHarvester(int servants, String familiarChosen) {
        Runnable a = () -> clientSetter.harvesterAction(familiarChosen,servants);
        new Thread(a).start();
        System.out.println("sto mandando ichiesta di harvester con numero di familiari: " + servants);

    }

    void goToCouncil(int privilegeSelected, String familiarChosen) {
        Runnable a = () -> clientSetter.councilAction(privilegeSelected,familiarChosen);
        new Thread(a).start();
    }

    void takeBonusPrivileges(ArrayList<Integer> privilegeSelected) {
        actionBonusOn = false;
        clientSetter.immediatePriviledgeAction(privilegeSelected);
    }

    void doBonusHarvester(int servants) {
        actionBonusOn = false;
        clientSetter.bonusHarvesterAction(servants);
    }

    void doBonusProduction(List<String> buildingCardSelected) {
        actionBonusOn = false;
        clientSetter.bonusProductionAction(buildingCardSelected);
    }

    void takeBonusCardAction(int floor, String towerColourString) {
        actionBonusOn = false;
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("ho fatto partire il nuovo thread per richiesta per la torre BONUS");
                clientSetter.takeBonusCardAction(floor,towerColourString);
            }
        };
        new Thread(a).start();
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

    public void playLeaderCard(String cardSelected) {

        Runnable a = () -> clientSetter.playLeaderCard(cardSelected);;
        new Thread(a).start();

    }

    public void discardLeaderCard(String cardSelected) {
        Runnable a = () -> clientSetter.discardLeaderCard(cardSelected);;
        new Thread(a).start();

    }

    public void goToMarket(int positionSelected, String familiarChosen) {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("ho fatto partire il nuovo thread per richiesta mercato con parametri" + positionSelected +"  "+familiarChosen);
                clientSetter.marketAction(positionSelected,familiarChosen);
            }
        };
        new Thread(a).start();

    }

    public void takeNickname() {
        clientSetter.loginRequest(usernameChosen);
    }


    public void takeNickname(String usernameChosen) {
        clientSetter.newNickname(usernameChosen);
    }
    //DA QUA IN GIU LE COSE CHIAMATE DAL CLIENT SETTER SULLA GRAFICA

    

    public void personalBoardUpdate(){
        Platform.runLater(() -> {
            PersonalBoard personalBoard = clientSetter.getUiPersonalBoard();
            int coins = personalBoard.getCoins();
            int wood = personalBoard.getWood();
            int stone = personalBoard.getStone();
            int servants = personalBoard.getServants();
            for (AbstractController c: controllers){
                c.updateResources(coins,wood,stone,servants);
            }

            personalBoardController.setBonusTile(personalBoard.getMyTile().getTileNumber());
            harvesterController.updateCards(personalBoard.getTerritories());
            productionController.updateCards(personalBoard.getBuildings());
            leaderCardController.updateCards(personalBoard.getMyLeaderCard());
            personalBoardController.update(personalBoard);
        });
    }

    public void boardUpdate() {
        board = clientSetter.getUiBoard();
        Platform.runLater(() -> {
            if (firstTime){
                firstTime = false;
            }
            harvesterController.updatePosition(board.getHarvesterZone());
            productionController.updatePosition(board.getProductionZone());
            councilPalaceController.updatePosition(board.getCouncilZone());
            marketController.updatePosition(board.getMarketZone());
            towerController.updatePosition(board.getAllTowers());
            generalGameController.updatePosition(board.getAllTowers());
            generalGameController.updateTurn(board.getTurn().getPlayerTurn());
            generalGameController.excommunicationUpdate(board.getExcommunicationZone());
            generalGameController.setDice(board.getDiceValue());
        });
    }

    public void scoreUpdate() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBuilder.setUiScore(clientSetter.getUiScore());
            }
        });
    }

    public void familyMemberUpdate() {
        Platform.runLater(() -> {
            generalGameController.updateFamilyMember(clientSetter.getUiFamilyMembers());
            harvesterController.updateFamilyMember(clientSetter.getUiFamilyMembers());
            productionController.updateFamilyMember(clientSetter.getUiFamilyMembers());
            towerController.updateFamilyMember(clientSetter.getUiFamilyMembers());
            marketController.updateFamilyMember(clientSetter.getUiFamilyMembers());
            councilPalaceController.updateFamilyMember(clientSetter.getUiFamilyMembers());
        });
    }


    public void takePrivilege(int quantityOfDifferentPrivileges) {
        actionBonusOn = true;
        numberOfPrivelege = quantityOfDifferentPrivileges;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("sono nel runlater per i privilegi");
                loginBuilder.setScene(SceneType.COUNCIL,SceneType.LEADER);
                councilPalaceController.takeImmediatePrivilege(numberOfPrivelege);
            }
        });
    }

    public void bonusHarvester(int diceValue) {
        actionBonusOn = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBuilder.setScene(SceneType.HARVESTER,SceneType.PERSONAL_BOARD);
                harvesterController.bonusHarvester(diceValue);
            }
        });
    }

    public void bonusProduction(int diceValue) {
        actionBonusOn = true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBuilder.setScene(SceneType.PRODUCTION, SceneType.PERSONAL_BOARD);
                productionController.bonusProduction(diceValue);
            }
        });
    }

    public void takeBonusCard(String kindOfCard, String printBonusAction) {
        actionBonusOn = true;
        String kind = kindOfCard;
        String print = printBonusAction;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                towerController.takeBonusCard(kind,print);
            }
        });
    }

    public int bothPaymentAvaiable() {
        return getScelta(CliConstants.BOTH_PAYMENT_AVAIABLE, "payment 1", "payment 2");
    }

    public int choosePermanentEffect() {
        return getScelta(CliConstants.CHOOSE_PERMANENT_EFFECT, "effect 1", "effect 2");
    }

    public int askForPraying() {
        return getScelta(CliConstants.ASK_FOR_PRAYING, "yes", "no");
    }

    public void endTurnContext() {
        Platform.runLater(() -> {
            System.out.println("metto il contesto di fine turno");
            loginBuilder.setScene(SceneType.LEADER,SceneType.MAIN);
            leaderCardController.endTurnContext();

        });
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
        loginBuilder.writeOnMyChat("you have finished your turn\n");
        Runnable a = new Runnable() {
            @Override
            public void run() {

                clientSetter.skipTurn();
            }
        };
        new Thread(a).start();
    }


    public int getScelta(String bothPaymentAvaiable, String s, String s1) {
        integerQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("sono nel runlater");
                generalGameController.setScelta(bothPaymentAvaiable,s,s1);
            }
        });
        Integer i = 0;
        try {
            System.out.println("mi metto in attesa della integerQueue");
             i = integerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        integerQueue = null;
        return i;
    }

    public void wakeUp(int choiceDone) {
        integerQueue.add(choiceDone);
        }

    public void addStringQueue(String s) {
        stringQueue.add(s);
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    private void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void updateChat() {
        Platform.runLater(() -> {
            for (AbstractController c: controllers)
                c.refresh();
        });
    }

    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        stringQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("sono nel runlater");
                loginBuilder.setDraft(leaders);
            }
        });
        String i = "";
        try {
            System.out.println("mi metto in attesa della integerQueue");
            i = stringQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stringQueue = null;
        return i;
    }

    public void matchStarted(int roomPlayers, String familyColour) {
        this.numberOfPlayer = roomPlayers;
        this.colour = familyColour;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("comincio inizializzo main game");
                loginBuilder.initalizeMainGame();
                loginBuilder.startMainGame();
                System.out.println("fine inizializzazione");
                generalGameController.setName(usernameChosen);
                loginBuilder.setResizeOn(true);
            }
        });
    }

    public void startTurn() {
        setMyTurn(true);
        Platform.runLater(() -> {
            loginBuilder.writeOnMyChat("it's your turn, you can play!\n");
            loginBuilder.showPrimo();
            loginBuilder.inFront();
        });
    }

    public void nicknameAlreadyUsed() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initialLoginController.nicknameUsed();
                loginBuilder.popUp("nickname already used");
                loginBuilder.showFirstPage();
            }
        });
    }

    public void timerDelayed() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (clearStages()){
                    clearBlockingQueue();
                }
                else
                    cleanActionBonus();
                loginBuilder.writeOnMyChat("the turn is over\n");
                loginBuilder.setScene(SceneType.MAIN,SceneType.PERSONAL_BOARD);
                myTurn = false;
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBuilder.popUp("you are disconnected, click ok to reconnect");
            }
        });
    }

    private void clearBlockingQueue() {
        if (integerQueue!=null){
            integerQueue.add(1);
        }

        if (stringQueue!=null)
            stringQueue.add("-1");
    }

    private boolean clearStages() {
        if (choicheController != null) {
            choicheController.closeStage();
            return true;
        }
        return false;
    }

    private void cleanActionBonus() {
        if (actionBonusOn){
            actionBonusOn = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    loginBuilder.setScene(SceneType.MAIN,SceneType.PERSONAL_BOARD);
                    generalGameController.writeOnChat("timer delyed to do bonus action!\n");
                }
            });
            clientSetter.sendExitToBonusAction();
        }
    }

    private void cleanQueue() {
        if (integerQueue != null) {
            integerQueue.add(1);
            System.out.println("ho pulito la coda");
        }
        else System.out.println("non ho pulito la coda");
    }

    public void cantDoAction() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginBuilder.writeOnMyChat("you can't perform this action\n");
                loginBuilder.popUp("you can't do thi action");
                myTurn = false;
            }
        });
    }

    public int tileDraft(List<Tile> tiles) {
        integerQueue = new LinkedBlockingQueue<>();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("sono nel runlater");
                loginBuilder.setDraft((ArrayList<Tile>) tiles);
            }
        });
        int i = 0;
        try {
            System.out.println("mi metto in attesa della integerQueue");
            i = integerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void addIntQueue(int i) {
        Integer num = new Integer(i);
        integerQueue.add(num);
    }

    public void sendExitOnChoice() {
        clientSetter.sendExitToBonusAction();
    }

    public void setInitialLoginController(InitialLogin initialLoginController) {
        this.initialLoginController = initialLoginController;
    }


    public void waitForYourTurn() {
        myTurn = false;
    }

    public void reconnect() {
        Runnable a = () -> clientSetter.reconnect();
        new Thread(a).start();

    }

    public void setChoicheController(ChoiceController choicheController) {
        this.choicheController = choicheController;
    }
}
