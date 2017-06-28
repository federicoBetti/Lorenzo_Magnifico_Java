package project.client.ui.cli;

import project.client.SingletonKeyboard;
import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.*;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.Updates;
import project.model.Tile;
import project.model.Tower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by raffaelebongo on 01/06/17.
 */
public class Cli extends AbstractUI {
    //todo fare end turn context e tutto il via dicendo

    private ClientSetter clientSetter; //all the operation have to pass across this class
    private AbstractContext context;
    private volatile boolean choice = false;
    private int numberOfPlayers;
    private String playerColor;
    private volatile BlockingDeque<String> choiceQueue;


    public Cli(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
        context = new ConnectionContext(this);
        new Keyboard().start();
    }


    @Override
    public void scoreUpdate(Updates update) {
        context.getpBlue().println(update.toScreen());
        context.getpRed().println("For further information type a show command.");
    }

    @Override
    public void personalBoardUpdate(Updates update) {
        context.getpBlue().println(update.toScreen());
        context.getpRed().println("For further information type a show command.");
    }

    @Override
    public void familyMemberUpdate(Updates update) {
        context.getpBlue().println(update.toScreen());
        context.getpRed().println("For further information type a show command.");
    }

    @Override
    public void boardUpdate(Updates update) {
        context.getpBlue().println(update.toScreen());
        context.getpRed().println("For further information type a show command.");
    }

    //context methods
    @Override
    public void bothPaymentsAvailable() {
        context = new BothPaymentsVentureCardsContext(this);
    }


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
    public int booleanChoosingRMI() {
        //todo fare la print che chiedo qualcosa
        try {
            return Integer.parseInt(SingletonKeyboard.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void bonusHarvesterParameters(String input) throws InputException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.bonusHarvesterAction(Integer.parseInt(parameters[0]));

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
        context = new TowersContext(this);
    }

    public void harvester() {
        context = new HarvesterContext(this);
    }

    public void goToCouncil() {
        context = new CouncilContext(this);
    }

    public void production() {
        context = new ProductionContext(this);
    }

    public void leaderCardContext() {
        context = new LeaderCardContext(this);
    }

    public void discardLeaderCardContext() {
        context = new DiscardLeaderCardContext(this);
    }

    public void askForPraying() {
        context = new ExcomunicationContext(this);
    }

    public void marketContext() {
        context = new MarketContext(this);
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
        context = new TakeBonusCard(this, towerAction);
        context.printHelp();
    }

    public void nicknameAlreadyUsed() {
        System.out.println("Nickname already used! Please chose another one.");
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
        System.out.println("QUI");
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

    //todo show methods
    public void showTowers() {
        Tower[][] towers = clientSetter.getUiBoard().getAllTowers();
        context = new ShowTowersContext(this, towers);
    }

    public void showProductionZone() {
        //to implement
    }

    public void showCouncilZone() {
        //to implement
    }

    public void showMarketZone() {
        //to implement
    }

    public void showExcomunicationsTiles() {
        //to implement
    }

    public void showPersonalBoard() {
        //to implement
    }

    public void showLeaderCards() {
        //to implement
    }

    public void showDicesValue() {
        //to implement
    }

    public void showPoints() {
        //to implement
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
                    System.out.println("ho preso da tastiera :" + lineFromKeyBoard);
                    if (choice) {
                        System.out.println("sono qui");
                        choiceQueue.add(lineFromKeyBoard);
                        System.out.println("fattooo ");
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

    @Override
    public int tileDraft(List<Tile> tiles) {

        choice = true;
        context = new TileDraftContext(this, tiles);
        choiceQueue = new LinkedBlockingDeque<>();
        String tileChosen = null;
        System.out.println("aspetto di scegliere");
        try {
            tileChosen = choiceQueue.take();
        } catch (InterruptedException e) {
            System.out.println("eccezione qui");
        }

        System.out.println("SCELTO " + tileChosen);
        choice = false;
        return Integer.parseInt(tileChosen);

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
}





