package project.client.ui.gui.controller;

import javafx.application.Platform;
import project.PlayerFile;
import project.client.ui.ClientSetter;
import project.client.ui.cli.CliConstants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.model.Board;
import project.model.PersonalBoard;
import project.model.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 *
 */
public class MainController {
    private static MainController instance;
    private ClientSetter clientSetter;
    private LoginBuilder loginBuilder;
    private HarvesterController harvesterController;
    private CouncilPalaceController councilPalaceController;
    private GeneralMainGameController generalGameController;
    private LeaderCardController leaderCardController;
    private MarketController marketController;
    private PersonalBoardController personalBoardController;
    private ProductionController productionController;
    private TowersController towerController;

    private List<AbstractController> controllers;

    private int numberOfPlayer = 2;
    private String colour = "rosso";
    private String usernameChosen;

    private BlockingQueue<Integer> integerQueue;
    private BlockingQueue<String> stringQueue;

    private boolean myTurn;

    private int numberOfPrivelege;
    private boolean actionBonusOn;
    private InitialLogin initialLoginController;
    private boolean firstTime;
    private boolean draft = true;
    private boolean endTurnContext;
    private int currentPeriod = 0;
    private PlayerFile statisticPlayer;
    private List<PlayerFile> ranking;
    private boolean winner;


    private MainController() {
        controllers = new ArrayList<>();
        Object token = new Object();
        firstTime = true;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
            return instance;
        } else return instance;
    }

    /**
     * setter
     *
     * @param clientSetter parameter to set
     */
    public void setClientSetter(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
    }

    /**
     * setter
     *
     * @param loginBuilder parameter to set
     */
    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    /**
     * method to show main page on gui
     */
    public void showMainGame() {
        loginBuilder.showPrimo();
    }

    /**
     * getter
     *
     * @return number of players in the match
     */
    int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    /**
     * getter
     *
     * @return colour of the family
     */
    public String getColour() {
        return colour;
    }

    /**
     * setter
     *
     * @param harvesterController parameter to set
     */
    void setHarvesterController(HarvesterController harvesterController) {
        this.harvesterController = harvesterController;
        controllers.add(this.harvesterController);
    }

    /**
     * setter
     *
     * @param councilPalaceController parameter to set
     */
    void setCouncilPalaceController(CouncilPalaceController councilPalaceController) {
        this.councilPalaceController = councilPalaceController;
        controllers.add(this.councilPalaceController);
    }

    /**
     * setter
     *
     * @param generalGameController parameter to set
     */
    void setGeneralGameController(GeneralMainGameController generalGameController) {
        this.generalGameController = generalGameController;
        controllers.add(this.generalGameController);
    }

    /**
     * setter
     *
     * @param leaderCardController parameter to set
     */
    void setLeaderCardController(LeaderCardController leaderCardController) {
        this.leaderCardController = leaderCardController;
        controllers.add(this.leaderCardController);
    }

    /**
     * setter
     *
     * @param marketController parameter to set
     */
    void setMarketController(MarketController marketController) {
        this.marketController = marketController;
        controllers.add(this.marketController);
    }

    /**
     * setter
     *
     * @param personalBoardController parameter to set
     */
    void setPersonalBoardController(PersonalBoardController personalBoardController) {
        this.personalBoardController = personalBoardController;
        controllers.add(this.personalBoardController);
    }

    /**
     * setter
     *
     * @param productionController parameter to set
     */
    void setProductionController(ProductionController productionController) {
        this.productionController = productionController;
        controllers.add(this.productionController);
    }

    /**
     * setter
     *
     * @param towerController parameter to set
     */
    void setTowerController(TowersController towerController) {
        this.towerController = towerController;
        controllers.add(this.towerController);
    }


    //DA QUA IN GIU LE COSE CHIAMATE SUL CLIENT SETTER

    /**
     * method called by gui when connect button is pressed
     * @param connectionType type of connection
     * @param IP
     * @param usernameChosen usernamen of the player
     */
    void setConnectionType(String connectionType, String IP, String usernameChosen) {
        this.usernameChosen = usernameChosen;
        clientSetter.setConnectionType(connectionType, IP);
    }

    /**
     * setter
     *
     * @param initialLoginController parameter to sett
     */
    void setInitialLoginController(InitialLogin initialLoginController) {
        this.initialLoginController = initialLoginController;
    }

    /**
     * setter
     *
     * @param choiceController parameter to set
     */
    void setChoiceController(ChoiceController choiceController) {
        ChoiceController choiceController1 = choiceController;
    }

    /**
     * main method to take a development card
     *
     * @param towerColour    colour of the tower of the card
     * @param floor          floor of the card
     * @param familiarColour colour of familiar chosen
     */
    public void takeDevCard(String towerColour, int floor, String familiarColour) {

        Runnable a = () -> clientSetter.takeDevCard(towerColour, floor, familiarColour);
        new Thread(a).start();
    }

    /**
     * method to do the production
     *
     * @param familiarChosen       colour of familiar chosen
     * @param buildingCardSelected building card selected by the user
     */
    void doProduction(String familiarChosen, List<String> buildingCardSelected) {
        Runnable a = () -> clientSetter.productionAction(familiarChosen, buildingCardSelected);
        new Thread(a).start();

    }

    /**
     * main method to do the harvester
     *
     * @param servants       number of servants used in the harvester action
     * @param familiarChosen colour of familiar chosen
     */
    void doHarvester(int servants, String familiarChosen) {
        Runnable a = () -> clientSetter.harvesterAction(familiarChosen, servants);
        new Thread(a).start();

    }

    /**
     * main method to go to the palace of council
     *
     * @param privilegeSelected number of the privilege selected
     * @param familiarChosen    colour of familiar chosen
     */
    void goToCouncil(int privilegeSelected, String familiarChosen) {
        Runnable a = () -> clientSetter.councilAction(privilegeSelected, familiarChosen);
        new Thread(a).start();
    }

    /**
     * action bonus in which you can take privileges
     *
     * @param privilegeSelected array that indicates the privileges you want to take
     */
    void takeBonusPrivileges(ArrayList<Integer> privilegeSelected) {
        actionBonusOn = false;

        Runnable a = () -> clientSetter.immediatePriviledgeAction(privilegeSelected);;
        new Thread(a).start();

        if (endTurnContext){
            loginBuilder.setScene(SceneType.LEADER,SceneType.PERSONAL_BOARD);
        }
    }

    /**
     * bonus action on harvester
     *
     * @param servants number of servants used
     */
    void doBonusHarvester(int servants) {
        actionBonusOn = false;
        Runnable a = () -> clientSetter.bonusHarvesterAction(servants);;
        new Thread(a).start();
    }

    /**
     * bonus action of production
     *
     * @param buildingCardSelected building card selected fr the production
     */
    void doBonusProduction(List<String> buildingCardSelected) {
        actionBonusOn = false;
        Runnable a = () -> clientSetter.bonusProductionAction(buildingCardSelected);
        new Thread(a).start();
    }

    /**
     * bonus action of take development card
     *
     * @param floor             floor of the card
     * @param towerColourString tower color of the card
     */
    void takeBonusCardAction(int floor, String towerColourString) {
        actionBonusOn = false;
        Runnable a = () -> clientSetter.takeBonusCardAction(floor, towerColourString);
        new Thread(a).start();
    }

    /**
     * method used to play a leader card
     *
     * @param cardSelected name of the card selected
     */
    public void playLeaderCard(String cardSelected) {
        if (endTurnContext){
            Platform.runLater(() -> leaderCardController.endTurnContext());
        }

        Runnable a = () -> clientSetter.playLeaderCard(cardSelected);

        new Thread(a).start();

    }

    /**
     * method used to discarda leader card
     *
     * @param cardSelected name of the leader card
     */
    public void discardLeaderCard(String cardSelected) {
        Runnable a = () -> clientSetter.discardLeaderCard(cardSelected);
        ;
        new Thread(a).start();

    }

    /**
     * method used to go to market
     *
     * @param positionSelected position of the market selcted
     * @param familiarChosen   colour of familiar chosen
     */
    void goToMarket(int positionSelected, String familiarChosen) {
        Runnable a = () -> clientSetter.marketAction(positionSelected, familiarChosen);
        new Thread(a).start();

    }

    /**
     * method to call the login method with the selcted username
     */
    void takeNickname() {
        clientSetter.loginRequest(usernameChosen);
    }


    /**
     * method to reconnect if the nickname is already used
     *
     * @param usernameChosen username chosen by the user
     */
    void takeNickname(String usernameChosen) {
        clientSetter.newNickname(usernameChosen);
    }

    /**
     * to update the gui following an update message from the server
     */
    public void personalBoardUpdate() {
        Platform.runLater(() -> {
            PersonalBoard personalBoard = clientSetter.getUiPersonalBoard();
            int coins = personalBoard.getCoins();
            int wood = personalBoard.getWood();
            int stone = personalBoard.getStone();
            int servants = personalBoard.getServants();
            for (AbstractController c : controllers) {
                c.updateResources(coins, wood, stone, servants);
            }

            personalBoardController.setBonusTile(personalBoard.getMyTile().getTileNumber());
            harvesterController.updateCards(personalBoard.getTerritories());
            productionController.updateCards(personalBoard.getBuildings());
            leaderCardController.updateCards(personalBoard.getMyLeaderCard());
            personalBoardController.update(personalBoard);
        });
    }

    /**
     * to update the gui following an update message from the server
     */
    public void boardUpdate() {
        Board board = clientSetter.getUiBoard();
        Platform.runLater(() -> {
            if (firstTime) {
                firstTime = false;
            }
            harvesterController.updatePosition(board.getHarvesterZone());
            productionController.updatePosition(board.getProductionZone());
            councilPalaceController.updatePosition(board.getCouncilZone());
            marketController.updatePosition(board.getMarketZone());
            towerController.updatePosition(board.getAllTowers());
            generalGameController.updatePosition(board.getAllTowers());
            generalGameController.updateTurn(board.getTurn());
            generalGameController.excommunicationUpdate(board.getExcommunicationZone());
            generalGameController.setDice(board.getDiceValue());
        });
    }

    /**
     * to update the gui following an update message from the server
     */
    public void scoreUpdate() {
        Platform.runLater(() -> {
            loginBuilder.setUiScore(clientSetter.getUiScore());
            generalGameController.setScore(clientSetter.getUiScore());
        });
    }

    /**
     * to update the gui following an update message from the server
     */
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

    /**
     * method that notify the gui to perform a bonus action of privileges
     *
     * @param quantityOfDifferentPrivileges number of different privileges that you can take
     */
    public void takePrivilege(int quantityOfDifferentPrivileges) {
        actionBonusOn = true;
        numberOfPrivelege = quantityOfDifferentPrivileges;
        Platform.runLater(() -> {
            loginBuilder.setScene(SceneType.COUNCIL, SceneType.LEADER);
            councilPalaceController.takeImmediatePrivilege(numberOfPrivelege);
        });
    }

    /**
     * method that notify the gui to perform a bonus action of harvester
     *
     * @param diceValue dice value of the action
     */
    public void bonusHarvester(int diceValue) {
        actionBonusOn = true;
        Platform.runLater(() -> {
            loginBuilder.setScene(SceneType.HARVESTER, SceneType.PERSONAL_BOARD);
            harvesterController.bonusHarvester(diceValue);
        });
    }

    /**
     * method that notify the gui to perform a bonus action of production
     *
     * @param diceValue dice value of the action
     */
    public void bonusProduction(int diceValue) {
        actionBonusOn = true;
        Platform.runLater(() -> {
            loginBuilder.setScene(SceneType.PRODUCTION, SceneType.PERSONAL_BOARD);
            productionController.bonusProduction(diceValue);
        });
    }

    /**
     * method that notify the gui to perform a bonus action of take a development card
     *
     * @param kindOfCard       tower of the card
     * @param printBonusAction string to print
     */
    public void takeBonusCard(String kindOfCard, String printBonusAction) {
        actionBonusOn = true;
        Platform.runLater(() -> towerController.takeBonusCard(kindOfCard, printBonusAction));
    }

    /**
     * method used to chose which payment of ventures card use
     *
     * @return number of the payment to use
     */
    public int bothPaymentAvaiable() {
        return getChoice(CliConstants.BOTH_PAYMENT_AVAIABLE, "payment 1", "payment 2");
    }

    /**
     * method to chose between two different permanent effect of a building card
     *
     * @return user's choice
     */
    public int choosePermanentEffect() {
        return getChoice(CliConstants.CHOOSE_PERMANENT_EFFECT, "effect 1", "effect 2");
    }

    /**
     * method used to ask to pray or not
     *
     * @return user's choice
     */
    public int askForPraying() {
        return getChoice(CliConstants.ASK_FOR_PRAYING, "yes", "no");
    }

    /**
     * method to set the gui in the end turn context
     */
    public void endTurnContext() {
        endTurnContext = true;
        Platform.runLater(() -> {
            loginBuilder.setScene(SceneType.LEADER, SceneType.MAIN);
            leaderCardController.endTurnContext();

        });
    }

    /**
     * method to send a message in the chat
     *
     * @param s message
     */
    public void sendChat(String s) {
        generalGameController.writeOnChat(s);
    }

    /**
     * method that confirm the login
     */
    public void loginSucceeded() {
        //correct void
    }

    /**
     * method to change scene in waiting scene
     */
    public void waitingLogin() {
        loginBuilder.waitingScene();
    }

// da qui in giu prove per risposta

    /**
     * method called to skip turn
     */
    public void skipTurn() {
        myTurn = false;
        endTurnContext = false;
        loginBuilder.writeOnMyChat("you have finished your turn\n");

        towerController.unlockButton();
        councilPalaceController.unlockButton();
        harvesterController.unlockButton();
        productionController.unlockButton();

        Runnable a = () -> clientSetter.skipTurn();
        new Thread(a).start();
    }

    /**
     * method that wat for user's choice
     *
     * @param choiceType choiche typw
     * @param s          first choice
     * @param s1         second choice
     * @return choice done
     */
    public int getChoice(String choiceType, String s, String s1) {
        integerQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(() -> generalGameController.setScelta(choiceType, s, s1));
        Integer i = 0;
        try {
            i = integerQueue.take();
        } catch (InterruptedException e) {
        }
        integerQueue = null;
        return i;
    }

    /**
     * add integer in the queue
     *
     * @param choiceDone integer to add
     */
    void addIntegerQueue(int choiceDone) {
        if (integerQueue != null)
            integerQueue.add(choiceDone);
    }

    /**
     * add string in the queue
     *
     * @param s string to add
     */
    void addStringQueue(String s) {
        if (stringQueue != null)
            stringQueue.add(s);
    }

    /**
     * getter
     *
     * @return myTurn variable
     */
    boolean isMyTurn() {
        return myTurn;
    }

    /**
     * setter
     *
     * @param myTurn variable
     */
    private void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    /**
     * method used to update chat of all scene
     */
    void updateChat() {
        Platform.runLater(() -> {
            for (AbstractController c : controllers)
                c.refresh();
        });
    }

    /**
     * methd used in leader draft
     *
     * @param leaders leader to choose
     * @return leader chosen
     */
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        stringQueue = new LinkedBlockingQueue<>(1);
        Platform.runLater(() -> {

            loginBuilder.setDraft(leaders);
        });
        String i = "";
        try {
            i = stringQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stringQueue = null;
        return i;
    }

    /**
     * methd to notify the match started
     *
     * @param roomPlayers  number of player in the room
     * @param familyColour your family color
     */
    public void matchStarted(int roomPlayers, String familyColour) {
        this.numberOfPlayer = roomPlayers;
        this.colour = familyColour;
        draft = false;
        Platform.runLater(() -> {
            loginBuilder.initalizeMainGame();
            loginBuilder.startMainGame();
            generalGameController.setName(usernameChosen);
            loginBuilder.setResizeOn(true);
        });
    }

    /**
     * method to notify that it is your turn
     */
    public void startTurn() {
        setMyTurn(true);
        Platform.runLater(() -> {
            loginBuilder.writeOnMyChat("it's your turn, you can play!\n");
            loginBuilder.showPrimo();
            loginBuilder.inFront();
        });
    }

    /**
     * method to notify that the nickname is already used
     */
    public void nicknameAlreadyUsed() {
        Platform.runLater(() -> {
            initialLoginController.nicknameUsed();
            loginBuilder.popUp("nickname already used");
            loginBuilder.showFirstPage();
        });
    }

    /**
     * method to notify that the timer is delayed
     */
    public void timerDelayed() {
        Platform.runLater(() -> {
            if (!draft) {
                loginBuilder.writeOnMyChat("the turn is over\n");
                loginBuilder.setScene(SceneType.MAIN, SceneType.PERSONAL_BOARD);
            }
            myTurn = false;

            if (clearStages()) {
                clearBlockingQueue();
            } else cleanActionBonus();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //
        }

        Platform.runLater(() -> loginBuilder.popUp("you are disconnected, click ok to reconnect"));
    }

    /**
     * methos ude to clear the queues
     */
    private void clearBlockingQueue() {
        if (integerQueue != null) {
            integerQueue.add(-1);
        }

        if (stringQueue != null) stringQueue.add("-1");
    }

    /**
     * method used to close the stages
     *
     * @return if there is a stage open
     */
    private boolean clearStages() {
        if (loginBuilder.getLastStageOpened() != null) {
            loginBuilder.getLastStageOpened().hide();
            return true;
        }
        return false;
    }

    /**
     * unlock button used for bonus actions
     */
    private void cleanActionBonus() {
        if (actionBonusOn) {
            actionBonusOn = false;
            Platform.runLater(() -> {
                loginBuilder.setScene(SceneType.MAIN, SceneType.PERSONAL_BOARD);
                towerController.unlockButton();
                councilPalaceController.unlockButton();
                harvesterController.unlockButton();
                productionController.unlockButton();
                generalGameController.writeOnChat("timer delyed to do bonus action!\n");
            });
            clientSetter.sendExitToBonusAction();
        }
    }

    /**
     * method called when you can't do actions
     */
    public void cantDoAction() {

        Platform.runLater(() -> {
            loginBuilder.writeOnMyChat("you can't perform this action\n");
            loginBuilder.popUp("you can't do this action");
        });
    }

    /**
     * used in tile draft
     *
     * @param tiles tile to chose
     * @return tile chosen
     */
    public int tileDraft(List<Tile> tiles) {
        integerQueue = new LinkedBlockingQueue<>();

        Platform.runLater(() -> loginBuilder.setDraft((ArrayList<Tile>) tiles));
        int i = 0;
        try {
            i = integerQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * method used to add an integer in the queue
     *
     * @param i int to add
     */
    void addIntQueue(int i) {
        Integer num = i;
        integerQueue.add(num);
    }

    /**
     * your turn is finished
     */
    public void waitForYourTurn() {
        myTurn = false;
    }

    /**
     * method used to reconnect the player
     */
    public void reconnect() {
        Runnable a = () -> clientSetter.reconnect();
        new Thread(a).start();

    }

    /**
     *
     *setter
     * @return usernamen of the player
     */
    public String getUsernameChosen() {
        return usernameChosen;
    }

    /**
     * sending a request of a new match at the end of the game
     */
    public void newGame() {
        clientSetter.newGameRequest();
    }

    public void afterGame() {
        currentPeriod = 0;
        System.err.println("mettoo l'after game context");
        Platform.runLater(() -> loginBuilder.setScene(SceneType.AFTER_GAME,SceneType.MAIN));
    }

    public void prayed() {
        currentPeriod++;
        Platform.runLater(() -> loginBuilder.writeOnMyChat("you have prayed!\n"));
    }

    public void excommunicationTaken(ExcommunicationTaken update) {
        if (update.getNicknameCurrentPlayer().equals(clientSetter.getNickname()))
            currentPeriod++;
        Platform.runLater(() -> loginBuilder.writeOnMyChat(update.toScreen()));
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public PlayerFile getStatistics() {
        return statisticPlayer;
    }

    public void setStatistics(PlayerFile statistics) {
        this.statisticPlayer = statistics;
    }

    public void setRanking(List<PlayerFile> ranking) {
        this.ranking = ranking;
    }

    public void youWin() {
        winner = true;
    }

    public List<PlayerFile> getRanking() {
        return ranking;
    }

    public boolean isWin() {
        return winner;
    }
}
