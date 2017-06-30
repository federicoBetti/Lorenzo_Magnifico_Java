package project.client.ui.cli;

import project.client.SingletonKeyboard;
import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.*;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.Updates;
import project.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by raffaelebongo on 01/06/17.
 */
public class Cli extends AbstractUI {

    private ClientSetter clientSetter; //all the operation have to pass across this class
    private AbstractContext context;
    private volatile boolean choice = false;
    private int numberOfPlayers;
    private String playerColor;
    private boolean firstRound;
    FamilyMember[] myFamilymembers;
    private volatile BlockingDeque<String> choiceQueue;
    Tile bonusTile;


    public Cli(ClientSetter clientSetter) {
        firstRound = true;
        this.clientSetter = clientSetter;
        context = new ConnectionContext(this);
        new Keyboard().start();
    }


    @Override
    public void scoreUpdate(Updates update) {
        if ( !firstRound ) {
            context.getpBlue().println(update.toScreen());

        }
    }

    @Override
    public void personalBoardUpdate(Updates update) {
        if ( !firstRound ) {
            context.getpBlue().println(update.toScreen());
        }
    }

    @Override
    public void familyMemberUpdate(Updates update) {
        myFamilymembers = update.doUpdateFamilyMembers();
    }

    @Override
    public void boardUpdate(Updates update) {
        if ( !firstRound ) {
            context.getpBlue().println(update.toScreen());
        }
    }

    //context methods


    public void mainContext() {
        context = new MainContext(this);
    }

    public void actionOk() {
        context = new AfterMainActionContext(this);
    }

    public void cantDoAction() {
        context.cantDoAction();
    }

    @Override
    public void choicePe() {
        context = new ChoicePeContext(this);
    }

    public void sendChoicePe(String input) {
        try {
            context.checkValidInput(input);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        clientSetter.sendChoicePe(Integer.parseInt(input));
    }

    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        context = new BonusHarvesterContext(bonusHarv, this);
    }

    @Override
    public void goToLogin() {
        context = new LoginContext(this);
    }

    @Override
    public void loginSucceded() {
        context = new WaitingForMatchStart(this);
    }



    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) {
        context = new BonusProductionContext(bonusProd, this);
    }

    public void bonusProductionParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            e.printStackTrace();
        }
        String[] parameters = lineFromKeyBoard.split("-");

        List<String> buildingCards = new ArrayList<>();
        for (String buildingCard : parameters)
            buildingCards.add(buildingCard);

        clientSetter.bonusProductionAction(buildingCards);
    }

    public void takeBonusCardParameters(String input) throws InputException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.takeBonusCardAction(Integer.parseInt(parameters[0]), parameters[1]);
    }


    public void immediatePriviledgeAction(String input) throws InputException {
        String[] privileges = input.split("-");
        List<Integer> privilegesChosen = new ArrayList<>();
        for (String priviledge : privileges)
            privilegesChosen.add(new Integer(Integer.parseInt(priviledge)));

        clientSetter.immediatePriviledgeAction(privilegesChosen);
    }

    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {
        context = new ImmediatePriviledgesContext(this, privilegesAction);
    }


    public void takeDevCard() {
        context = new TowersContext(this, clientSetter.getUiBoard().getAllTowers());
    }

    public void harvester() {
        context = new HarvesterContext(this, clientSetter.getUiBoard().getHarvesterZone(), bonusTile );
    }

    public void goToCouncil() {
        context = new CouncilContext(this, clientSetter.getUiBoard().getCouncilZone());
    }

    public void production() {
        context = new ProductionContext(this, clientSetter.getUiBoard().getProductionZone(), bonusTile, clientSetter.getUiPersonalBoard().getBuildings());
    }

    public void leaderCardContext() {
        context = new LeaderCardContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    public void discardLeaderCardContext() {
        context = new DiscardLeaderCardContext(this);
    }

    public void askForPraying() {
        context = new ExcomunicationContext(this);
    }

    public void marketContext() {
        context = new MarketContext(this, clientSetter.getUiBoard().getMarketZone());
    }

    public void loginRequest(String lineFromKeyBoard) throws InputException {
        clientSetter.loginRequest(lineFromKeyBoard);
    }

    @Override
    public void startUI() {
        new Keyboard().start();
    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {
        context = new TakeBonusCard(this, towerAction, clientSetter.getUiBoard().getAllTowers());
    }

    public void nicknameAlreadyUsed() {
        context = new NicknameAlreadyUsedContext(this);
    }

    @Override
    public void skipTurn() {
        clientSetter.skipTurn();
    }

    @Override
    public void waitingForYourTurn() {
        context = new WaitingForYourTurnContext(this);
    }

    @Override
    public void setConnectionType(String kindOfConnection) {
        clientSetter.setConnectionType(kindOfConnection);
    }

    public void choseAndTakeDevCard(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.takeDevCard(parameters[0], Integer.parseInt(parameters[1]), parameters[2]);

    }

    //todo aggiustare come parametri giusti la chiamata
    public void chooseProductionParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        String[] parameters = lineFromKeyBoard.split("-");
        List<String> buildingCards = new ArrayList<>();
        for (int i = 1; i < parameters.length; i++)
            buildingCards.add(parameters[i]);

        clientSetter.productionAction(parameters[0], buildingCards);
    }

    public void chooseHarversterParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.harvesterAction(parameters[0], Integer.parseInt(parameters[1]));
    }

    public void chooseMarketActionParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            context.printHelp();
            return;
        }

        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.marketAction(Integer.parseInt(parameters[0]), parameters[1]);
    }

    public void chooseCouncilParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.councilAction(Integer.parseInt(parameters[0]), parameters[1]);
    }

    public void chooseLeaderCardToPlay(String action) {    //todo va controllato sul server
        clientSetter.playLeaderCard(action);
    }

    public void discardLeaderCard(String name) {    //todo va controllato sul server
        clientSetter.discardLeaderCard(name);
    }

    public void prayOrNot(String action) {
        boolean yesOrNo;
        try {
            context.checkValidInput(action);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        if (action.equals("yes"))
            yesOrNo = true;
        else
            yesOrNo = false;
        clientSetter.prayOrNot(yesOrNo);
    }

    public void choosePayment(String payment) {
        try {
            context.checkValidInput(payment);
        } catch (InputException e) {
            context.printHelp();
            return;
        }
        clientSetter.sendChoicePaymentVc(Integer.parseInt(payment));
    }

    public void sendExitToBonusAction() throws InputException {
        clientSetter.sendExitToBonusAction();
    }

    public void showProductionZone() {
        //to implement
    }

    public void showCouncilZone() {
        List<Council> council = clientSetter.getUiBoard().getCouncilZone();

    }

    public void showMarketZone() {
        //to implement
    }

    public void showExcomunicationsTiles() {
        ExcommunicationZone[] toPrint = clientSetter.getUiBoard().getExcommunicationZone();
        for(int i = 0; i < 3; i++ ) {
            context.getpRed().print(i + ") ");
            context.getpYellow().println(toPrint[i].getCardForThisPeriod().getExcommunicationEffect().toScreen());
        }
    }

    public void showPersonalBoard() {
        context.getpBlue().println(clientSetter.getUiPersonalBoard().toScreen());
    }

    public void showDicesValue() {
        int[] diceToPrint = clientSetter.getUiBoard().getDiceValue();
        context.getpBlue().print("Black dice value: ");context.getpYellow().println(diceToPrint[0]);
        context.getpBlue().print("White dice value: ");context.getpYellow().println(diceToPrint[1]);
        context.getpBlue().print("Orange dice value: ");context.getpYellow().println(diceToPrint[2]);

    }

    public void showPoints() {
       context.getpBlue().println(clientSetter.getUiScore().toScreen());
    }

    public void showHarvesterZone() {
        //to implement
    }

    public void gameReport() {
        //to implement
    }

    public void showAllPlayers() {
        //to implement
    }

    void chat() {
        //to implement
    }

    public void discardLeaderCardAma() {
        context = new DiscardLeaderCardAmaContext(this);
    }

    public void playLeaderCardAma() {
        context = new PlayLeadercardAmaContext(this);
    }

    private class Keyboard extends Thread {

        @Override
        public void run() {


            SingletonKeyboard keyboard = SingletonKeyboard.getInstance();
            while (true) {
                String lineFromKeyBoard;
                try {


                    lineFromKeyBoard = keyboard.readLine();
                    if (choice) {
                        choiceQueue.add(lineFromKeyBoard);
                        continue;
                    }
                    else if (context != null) {
                        context.doAction(lineFromKeyBoard);
                    }
                    } catch(InputException e){
                        e.printStackTrace();
                    } catch(IOException e){
                        e.printStackTrace();

                    }
                }
            }
        }

    @Override
    public int bothPaymentsAvailable() {
        choice = true;
        context = new BothPaymentsVentureCardsContext(this);
        String costChoosen = null;
        try {
            costChoosen = choiceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        choice = false;
        return Integer.parseInt(costChoosen);
    }

    @Override
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        choice = true;
        context = new LeaderCardDraftContext(this, leaders );
        choiceQueue = new LinkedBlockingDeque<>();
        String cardChoosen = null;
        try {
            cardChoosen = choiceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        choice = false;
        return cardChoosen;
    }

    public void bonusHarvesterParameters(String input) throws InputException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.bonusHarvesterAction(Integer.parseInt(parameters[0]));

    }

    @Override
    public int getScelta() {

        SingletonKeyboard keyboard = SingletonKeyboard.getInstance();
        try {
            int ch = Integer.parseInt(keyboard.readLine());
            return ch;
        } catch (IOException e) {

        }
        return 0;
    }

    @Override
    public int tileDraft(List<Tile> tiles) {

        choice = true;
        context = new TileDraftContext(this, tiles);
        choiceQueue = new LinkedBlockingDeque<>();
        String tileChosen = null;

        try {
            tileChosen = choiceQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bonusTile = tiles.get(Integer.parseInt(tileChosen));
        choice = false;
        return Integer.parseInt(tileChosen);

    }

    @Override
    public void newNickname(String nickname) {
        clientSetter.newNickname(nickname);
    }


    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        context = new MatchStartedContext(this);
        numberOfPlayers = roomPlayers;
        playerColor = familyColour;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public FamilyMember[] getMyFamilymembers() {
        return myFamilymembers;
    }
}