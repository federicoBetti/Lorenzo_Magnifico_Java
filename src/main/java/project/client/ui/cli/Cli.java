package project.client.ui.cli;

import project.PlayerFile;
import project.client.SingletonKeyboard;
import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.*;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;

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
    boolean timerDelayed;


    public Cli(ClientSetter clientSetter) {
        firstRound = true;
        this.clientSetter = clientSetter;
        context = new ConnectionContext(this);
        new Keyboard().start();
        timerDelayed = false;
    }


    @Override
    public void scoreUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());

        }
    }

    @Override
    public void personalBoardUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());
        }
    }

    @Override
    public void familyMemberUpdate(Updates update) {
        myFamilymembers = update.doUpdateFamilyMembers();
    }

    @Override
    public void boardUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());
        }
    }

    //context methods


    public void mainContext() {
        context = new MainContext(this);
    }

    public void actionOk() {
        if (context instanceof TimerDelayedContext) {
            return;
        }

        if (context instanceof WaitingForYourTurnContext)
            return;

        context = new AfterMainActionContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    public void cantDoAction() {
        context.cantDoAction();
    }


    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        context = new BonusHarvesterContext(bonusHarv, this, clientSetter.getUiPersonalBoard().getTerritories());
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
        context = new BonusProductionContext(bonusProd, this, clientSetter.getUiPersonalBoard().getBuildings());
    }

    public void bonusProductionParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");

            List<String> buildingCards = new ArrayList<>();
            Collections.addAll(buildingCards, parameters);

            clientSetter.bonusProductionAction(buildingCards);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void takeBonusCardParameters(String input) {
        try {
            context.checkValidInput(input);
            String[] parameters = input.split("-");
            clientSetter.takeBonusCardAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }
    }


    public void immediatePriviledgeAction(String input) throws InputException {
        String[] privileges = input.split("-");
        List<Integer> privilegesChosen = new ArrayList<>();
        for (String priviledge : privileges)
            privilegesChosen.add(Integer.parseInt(priviledge));

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
        context = new HarvesterContext(this, clientSetter.getUiBoard().getHarvesterZone(), clientSetter.getUiPersonalBoard().getMyTile(), clientSetter.getUiPersonalBoard().getTerritories());
    }

    public void goToCouncil() {
        context = new CouncilContext(this, clientSetter.getUiBoard().getCouncilZone());
    }

    public void production() {
        context = new ProductionContext(this, clientSetter.getUiBoard().getProductionZone(), clientSetter.getUiPersonalBoard().getMyTile(), clientSetter.getUiPersonalBoard().getBuildings());
    }

    public void leaderCardContext() {
        context = new LeaderCardContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    public void discardLeaderCardContext() {
        context = new DiscardLeaderCardContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
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
    public void timerDelayed() {
        try {//bonus action interrupted
            if (context instanceof TakeBonusCard || context instanceof BonusHarvesterContext || context instanceof BonusProductionContext) {
                System.out.println("mando exit");
                context = new TimerDelayedContext(this);
                sendExitToBonusAction();
            }
            //praying interrupted
            else if (context instanceof ExcomunicationContext) {
                choiceQueue.add("1");
                context = new TimerDelayedContext(this);
                System.out.println("SONO IN EX CONTEXT");
                return;
            } else if (context instanceof BothPaymentsVentureCardsContext) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
                choiceQueue.add(String.valueOf(randomNum));
                context = new TimerDelayedContext(this);
                return;
            } else if (context instanceof ImmediatePriviledgesContext) {
                sendExitToBonusAction();

            } else if (context instanceof LeaderCardDraftContext || context instanceof TileDraftContext || context instanceof ChoicePeContext) {
                choiceQueue.add("-1");
                context = new TimerDelayedContext(this);
                return;
            }

        } catch (InputException e) {
            e.printStackTrace();
        }

        context = new TimerDelayedContext(this);
    }

    @Override
    public void reconnect() {
        clientSetter.reconnect();
    }

    @Override
    public void afterGame() {
        context = new AfterGameContext(this);
    }

    @Override
    public void showStatistic() {
        clientSetter.showStatistic();
    }

    @Override
    public void newGameRequest() {
        clientSetter.newGameRequest();
    }

    @Override
    public void terminate() {
        clientSetter.terminate();
    }

    @Override
    public void receiveStatistics(PlayerFile statistics) {
        context.getpBlue().print("Player name: ");
        context.getpRed().println(statistics.getPlayerName());
        context.getpBlue().print("Number of matches: ");
        context.getpRed().println(statistics.getNumberOfGames());
        context.getpBlue().print("Number of victories: ");
        context.getpRed().println(statistics.getNumberOfVictories());
        context.getpBlue().print("Number of defeats: ");
        context.getpRed().println(statistics.getNumberOfDefeats());
        context.getpRed().println("");
    }

    @Override
    public void showRanking() {
        clientSetter.showRanking();
    }

    @Override
    public void ranking(List<PlayerFile> ranking) {
        int i = 1;
        for (PlayerFile playerFile : ranking) {
            context.getpYellow().print(i + ") ");
            receiveStatistics(playerFile);
            i++;
        }
    }

    @Override
    public void disconnesionMessage(String message) {
        context.getpRed().println(message);
    }

    @Override
    public void winnerComunication(String winner) {
        context.getpBlue().println(winner);
    }

    @Override
    public void waitingForYourTurn() {
        context = new WaitingForYourTurnContext(this);
    }

    @Override
    public void setConnectionType(String kindOfConnection) {

            try {
                context.checkValidInput(kindOfConnection);
                setIpAddress(kindOfConnection);
            } catch (InputException e) {
                context.printHelp();
            }
        }


    private boolean checkIP(String ip) {
        String[] parameters = ip.split(".");
        if (parameters.length != 4)
            return false;

        for (String num : parameters)
            try {
                if (Integer.parseInt(num) < 0 || Integer.parseInt(num) > 255)
                    return false;
            } catch (NumberFormatException e) {
                return false;
            }

        return true;
    }

    private void setIpAddress(String kindOfConnection) {
        context = new SetIPaddressContext(this, kindOfConnection);
    }

    public void choseAndTakeDevCard(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.takeDevCard(parameters[0], Integer.parseInt(parameters[1]), parameters[2]);
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }
    }

    public void chooseProductionParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            List<String> buildingCards = new ArrayList<>();
            for (int i = 1; i < parameters.length; i++)
                buildingCards.add(parameters[i]);

            clientSetter.productionAction(parameters[0], buildingCards);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void chooseHarversterParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.harvesterAction(parameters[0], Integer.parseInt(parameters[1]));
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void chooseMarketActionParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.marketAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }
    }

    public void chooseCouncilParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.councilAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void chooseLeaderCardToPlay(String action) {    //todo va controllato sul server
        try {
            context.checkValidInput(action);
            clientSetter.playLeaderCard(action);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void discardLeaderCard(String name) {    //todo va controllato sul server
        clientSetter.discardLeaderCard(name);
    }

    public void sendExitToBonusAction() throws InputException {
        clientSetter.sendExitToBonusAction();
    }

    public void showExcomunicationsTiles() {
        int i = 1;
        for (ExcommunicationZone exZone : clientSetter.getUiBoard().getExcommunicationZone()) {
            context.getpRed().print(i + ") ");
            context.getpBlue().println(exZone.getCardForThisPeriod().getEffectDescription());
            i++;
            context.getpBlue().println("");
        }
    }

    public void showPersonalBoard() {
        context.getpBlue().println(clientSetter.getUiPersonalBoard().toScreen());
    }

    public void showDicesValue() {
        int[] diceToPrint = clientSetter.getUiBoard().getDiceValue();
        context.getpBlue().print("Black dice value: ");
        context.getpYellow().println(diceToPrint[0]);
        context.getpBlue().print("White dice value: ");
        context.getpYellow().println(diceToPrint[1]);
        context.getpBlue().print("Orange dice value: ");
        context.getpYellow().println(diceToPrint[2]);

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
        context = new DiscardLeaderCardAmaContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    public void playLeaderCardAma(List<LeaderCard> leaderCards) {
        context = new PlayLeadercardAmaContext(this, leaderCards);
    }


    @Override
    public int askForPraying() {
        choice = true;
        choiceQueue = new LinkedBlockingDeque<>();
        context = new ExcomunicationContext(this);
        String prayOrNot;

        while (true) {
            try {
                prayOrNot = choiceQueue.take();
                context.checkValidInput(prayOrNot);
                choice = false;
                return Integer.parseInt(prayOrNot);
            } catch (InterruptedException e) {
                System.out.println("sono in eccezione");
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    @Override
    public int choicePe() {
        choice = true;
        choiceQueue = new LinkedBlockingDeque<>();
        context = new ChoicePeContext(this);

        String effectChoosen;

        while (true) {
            try {
                effectChoosen = choiceQueue.take();
                context.checkValidInput(effectChoosen);
                choice = false;
                return Integer.parseInt(effectChoosen);
            } catch (InterruptedException | InputException | NumberFormatException e) {
                context.printHelp();
            }
        }
    }


    @Override
    public int bothPaymentsAvailable() {
        choice = true;
        choiceQueue = new LinkedBlockingDeque<>();
        context = new BothPaymentsVentureCardsContext(this);


        String costChoosen;
        while (true) {
            try {

                costChoosen = choiceQueue.take();
                System.out.println("cost choosen è: " + costChoosen);
                context.checkValidInput(costChoosen);
                choice = false;
                System.out.println("preso e chiamo giu dal both payment 1");
                return Integer.parseInt(costChoosen);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    @Override
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        choice = true;
        context = new LeaderCardDraftContext(this, leaders);
        choiceQueue = new LinkedBlockingDeque<>();
        String cardChoosen;
        while (true) {
            try {
                cardChoosen = choiceQueue.take();
                context.checkValidInput(cardChoosen);
                choice = false;
                context.getpBlue().println("Leader card choosen! Wait for the new interaction...");
                return cardChoosen;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    public void bonusHarvesterParameters(String input) {
        try {
            context.checkValidInput(input);
            String[] parameters = input.split("-");
            clientSetter.bonusHarvesterAction(Integer.parseInt(parameters[0]));
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
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
    public int tileDraft(List<Tile> tiles) {

        choice = true;
        context = new TileDraftContext(this, tiles);
        choiceQueue = new LinkedBlockingDeque<>();
        String tileChosen;

        context.getpBlue().println("Bonus Tile choosen! Wait for the other players'choiceeeee");

        while (true) {
            try {
                context.getpBlue().println("Bonus Tile choosen! Wait foe");
                tileChosen = choiceQueue.take();
                context.checkValidInput(tileChosen);
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InputException | NumberFormatException e) {
                context.printHelp();
            }
        }

        choice = false;
        context.getpBlue().println("Bonus Tile choosen! Wait for the other players'choice");
        return Integer.parseInt(tileChosen);

    }

    @Override
    public void newNickname(String nickanme) {
        clientSetter.newNickname(nickanme);
    }


    @Override
    public void prayed() {
        context.getpRed().println("You have prayed and you did not take the excommunication!");
    }

    @Override
    public void excommunicationTaken(ExcommunicationTaken update) {
        context.getpRed().println(update.toScreen());
        context.getpBlue().println(update.getExTile());
    }

    @Override
    public void notifyPlayer(Notify notify) {
        context.getpBlue().println(notify.toScreen());
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

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public FamilyMember[] getMyFamilymembers() {
        return myFamilymembers;
    }

    public void showTurns() {
        Turn turn = clientSetter.getUiBoard().getTurn();

        int count = 1;
        context.getpBlue().println("TURN ORDER: ");
        for (String nickname : turn.getPlayerName()) {
            context.getpRed().print(count + ") ");
            context.getpBlue().println(nickname);
            count++;
        }
    }

    public void setIPaddress(String kindOfConnection, String ip) {

        try {
            context.checkValidInput(ip);
        } catch (InputException e) {
            context.printHelp();
            return;
        }

        clientSetter.setConnectionType(kindOfConnection, ip);
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
                        System.out.println("Sono in scelta");
                        choiceQueue.add(lineFromKeyBoard);
                    } else if (context != null) {
                        System.out.println("Sono in Contesto");
                        context.doAction(lineFromKeyBoard);
                    }
                } catch (InputException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}