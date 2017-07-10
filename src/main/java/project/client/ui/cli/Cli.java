package project.client.ui.cli;

import project.server.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.SingletonKeyboard;
import project.client.ui.AbstractUI;
import project.client.ClientSetter;
import project.client.ui.cli.context.*;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
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

/**
 * This class manage the the Command line user interface
 */
public class Cli extends AbstractUI {

    private ClientSetter clientSetter; //all the operation have to pass across this class
    private AbstractContext context;
    private volatile boolean choice = false;
    private int numberOfPlayers;
    private boolean firstRound;
    private FamilyMember[] myFamilymembers;
    private volatile BlockingDeque<String> choiceQueue;

    /**
     * Constructor
     *
     * @param clientSetter clientSetter's reference
     */
    public Cli(ClientSetter clientSetter) {
        firstRound = true;
        this.clientSetter = clientSetter;
        context = new ConnectionContext(this);
        new Keyboard().start();
    }


    /**
     * This method prints the score update
     *
     * @param update update's reference
     */
    @Override
    public void scoreUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());
        }
    }

    /**
     * This method prints the personal board update
     *
     * @param update update's reference
     */
    @Override
    public void personalBoardUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());
        }
    }

    /**
     * This method updates the family member's reference
     *
     * @param update update's reference
     */
    @Override
    public void familyMemberUpdate(Updates update) {
        myFamilymembers = update.doUpdateFamilyMembers();
    }

    /**
     * This method print's board update
     *
     * @param update update's reference
     */
    @Override
    public void boardUpdate(Updates update) {
        if (!firstRound) {
            context.getpBlue().println(update.toScreen());
        }
    }

    /**
     * This method makes the abstract context a main context
     */
    public void mainContext() {
        context = new MainContext(this);
    }

    /**
     * This method makes the abstract context an afterGame context
     */
    public void actionOk() {
        if (context instanceof TimerDelayedContext) {
            return;
        }

        if (context instanceof WaitingForYourTurnContext)
            return;

        context = new AfterMainActionContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    /**
     * This method calls the method cantDoAction on the abstract context
     */
    public void cantDoAction() {
        context.cantDoAction();
    }


    /**
     * This method makes the abstract context a BonusHarvesterContext
     *
     * @param bonusHarv object that contains the characteristics of the bonus Harverster action
     */
    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        context = new BonusHarvesterContext(bonusHarv, this, clientSetter.getUiPersonalBoard().getTerritories());
    }

    /**
     * This method makes the abstract context in a LoginContext
     */
    @Override
    public void goToLogin() {
        context = new LoginContext(this);
    }

    /**
     * This method makes the abstract context in a WaitingForMatchStarted context
     */
    @Override
    public void loginSucceded() {
        context = new WaitingForMatchStart(this);
    }


    /**
     * This method makes the abstract context a BonusProductionContext
     *
     * @param bonusProd object that contains the characteristics of the bonus production action
     */
    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) {
        context = new BonusProductionContext(bonusProd, this, clientSetter.getUiPersonalBoard().getBuildings());
    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * bonusProductionAction on the client setter
     *
     * @param lineFromKeyBoard String in input
     */
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

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * takeBonusCardAction o the client setter
     *
     * @param input
     */
    public void takeBonusCardParameters(String input) {
        try {
            context.checkValidInput(input);
            String[] parameters = input.split("-");
            clientSetter.takeBonusCardAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException e ) {
            context.printHelp();
        }
    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * ImmediatePrivilegeAction on the client setter
     *
     * @param input
     * @throws InputException
     */
    public void immediatePriviledgeAction(String input)  {
        String[] privileges = input.split("-");
        List<Integer> privilegesChosen = new ArrayList<>();
        for (String priviledge : privileges)
            privilegesChosen.add(Integer.parseInt(priviledge));

        clientSetter.immediatePriviledgeAction(privilegesChosen);

    }

    /**
     * This method makes the abstract context in a ImmediatePriviledgesContext
     *
     * @param privilegesAction object with all the characteristics of the immediate privilege action
     */
    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {
        context = new ImmediatePriviledgesContext(this, privilegesAction);
    }

    /**
     * This method makes the abstract context in a TowersContext
     */
    public void takeDevCard() {
        context = new TowersContext(this, clientSetter.getUiBoard().getAllTowers());
    }

    /**
     * This method makes the abstract context in a HarvesterContext
     */
    public void harvester() {
        context = new HarvesterContext(this, clientSetter.getUiBoard().getHarvesterZone(), clientSetter.getUiPersonalBoard().getMyTile(), clientSetter.getUiPersonalBoard().getTerritories());
    }

    /**
     * This method makes the abstract context in a CouncilContext
     */
    public void goToCouncil() {
        context = new CouncilContext(this, clientSetter.getUiBoard().getCouncilZone());
    }

    /**
     * This method makes the abstract context in a ProductionContext
     */
    public void production() {
        context = new ProductionContext(this, clientSetter.getUiBoard().getProductionZone(), clientSetter.getUiPersonalBoard().getMyTile(), clientSetter.getUiPersonalBoard().getBuildings());
    }

    /**
     * This method makes the abstract context in a LeaderCardContext
     */
    public void leaderCardContext() {
        context = new LeaderCardContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    /**
     * This method makes the abstract context in a DiscardLeaderCardContext
     */
    public void discardLeaderCardContext() {
        context = new DiscardLeaderCardContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    /**
     * This method makes the abstract context in a MarketContext
     */
    public void marketContext() {
        context = new MarketContext(this, clientSetter.getUiBoard().getMarketZone());
    }

    /**
     * This method calls loginRequest on the clientSetter
     *
     * @param lineFromKeyBoard nickname choosen
     */
    public void loginRequest(String lineFromKeyBoard) {
        clientSetter.loginRequest(lineFromKeyBoard);
    }

    /**
     * This method starts the Keyboard Thread
     */
    @Override
    public void startUI() {
        new Keyboard().start();
    }

    /**
     * This method makes the abstract context in a TakeBonusCard context
     *
     * @param towerAction the object with all the characteristics of the bonus action
     */
    @Override
    public void takeBonusCard(TowerAction towerAction) {
        context = new TakeBonusCard(this, towerAction, clientSetter.getUiBoard().getAllTowers());
    }

    /**
     * This method makes the abstract context in a NicknameAlreadyUsedContext
     */
    public void nicknameAlreadyUsed() {
        context = new NicknameAlreadyUsedContext(this);
    }

    /**
     * This method calls skipTurn on the client setter
     */
    public void skipTurn() {
        clientSetter.skipTurn();
    }

    /**
     * This method is called when the time for the turn is up. According to the current context, or is called the
     * sendExitToBonusAction method or is added a specific value in choiceQueue forcibly.
     */
    @Override
    public void timerDelayed() {
        //bonus action interrupted
            if (context instanceof TakeBonusCard || context instanceof BonusHarvesterContext || context instanceof BonusProductionContext) {
                context = new TimerDelayedContext(this);
                sendExitToBonusAction();
            }
            //praying interrupted
            else if (context instanceof ExcomunicationContext) {
                choiceQueue.add("1");
                context = new TimerDelayedContext(this);
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


        context = new TimerDelayedContext(this);
    }

    /**
     * This method calls reconnect on the clientSetter
     */
    public void reconnect() {
        clientSetter.reconnect();
    }

    /**
     * This method makes the abstract context in a AfterGameContext
     */
    @Override
    public void afterGame() {
        context = new AfterGameContext(this);
    }

    /**
     * This method calls the method showStatistic on the clientSetter
     */
    public void showStatistic() {
        clientSetter.showStatistic();
    }

    /**
     * This method calls the method newGameRequest on the clientSetter
     */
    public void newGameRequest() {
        clientSetter.newGameRequest();
    }

    /**
     * This method calls the method terminate on the clientSetter
     */
    public void terminate() {
        clientSetter.terminate();
    }

    /**
     * This method prints the statistics
     *
     * @param statistics PlayerFile.json's reference that contains the statistics to print
     */
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

    /**
     * This method calls the method showRanking on the clientSetter
     */
    public void showRanking() {
        clientSetter.showRanking();
    }

    /**
     * This method prints the ranking
     * @param ranking List of palyerFiles ordered like a ranking
     */
    @Override
    public void ranking(List<PlayerFile> ranking) {
        int i = 1;
        for (PlayerFile playerFile : ranking) {
            context.getpYellow().print(i + ") ");
            receiveStatistics(playerFile);
            i++;
        }
    }

    /**
     * This method prints the disconnection message
     *
     * @param message message as a String to print
     */
    @Override
    public void disconnesionMessage(String message) {
        String messageToPrint = message + " is disconnected!";
        context.getpRed().println(messageToPrint);
    }

    /**
     * This method print the winner's message
     *
     * @param winner winner comunication as a String
     */
    @Override
    public void winnerComunication(String winner) {
        String winnerToPrint = "The winner is " + winner;
        context.getpBlue().println(winnerToPrint);
    }

    /**
     * This method makes the abstract context in a WaitingForYourTurnContext
     */
    @Override
    public void waitingForYourTurn() {
        context = new WaitingForYourTurnContext(this);
    }

    /**
     * This method check the input according to the current context specific and calls the setIpAddress method
     *
     * @param kindOfConnection kind of connection choosen as a String
     */
    public void setConnectionType(String kindOfConnection) {

        try {
            context.checkValidInput(kindOfConnection);
            setIpAddress(kindOfConnection);
        } catch (InputException e) {
            context.printHelp();
        }
    }

    /**
     * This method makes the abstract context in a SetIPaddressContext
     *
     * @param kindOfConnection kind of connection choosen as a String
     */
    private void setIpAddress(String kindOfConnection) {
        context = new SetIPaddressContext(this, kindOfConnection);
    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * takeDevCard on the client setter
     *
     * @param lineFromKeyBoard input line from keyboard
     */
    public void choseAndTakeDevCard(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.takeDevCard(parameters[0], Integer.parseInt(parameters[1]), parameters[2]);
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }
    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * productionAction on the client setter
     *
     * @param lineFromKeyBoard input line from keyboard
     */
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

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * harvesterAction on the client setter
     *
     * @param lineFromKeyBoard input line from keyboard
     */
    public void chooseHarversterParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.harvesterAction(parameters[0], Integer.parseInt(parameters[1]));
        } catch (InputException e) {
            context.printHelp();
        }

    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * marketAction on the client setter
     *
     * @param lineFromKeyBoard input line from keyboard
     */
    public void chooseMarketActionParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.marketAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }
    }

    /**
     * This method check the input according to the current context specific, prepare the parameters and calls the
     * councilAction on the client setter
     *
     * @param lineFromKeyBoard input line from keyboard
     */
    public void chooseCouncilParameters(String lineFromKeyBoard) {

        try {
            context.checkValidInput(lineFromKeyBoard);
            String[] parameters = lineFromKeyBoard.split("-");
            clientSetter.councilAction(Integer.parseInt(parameters[0]), parameters[1]);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    /**
     * This method check the input according to the current context specific and calls the playLeaderCard on the
     * client setter
     *
     * @param action input string
     */
    public void chooseLeaderCardToPlay(String action) {
        try {
            context.checkValidInput(action);
            clientSetter.playLeaderCard(action);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    /**
     * This method calls discardLeaderCard on clietSetter
     *
     * @param name input string
     */
    public void discardLeaderCard(String name) {
        clientSetter.discardLeaderCard(name);
    }

    /**
     * This method calls sendExitToBonusAction on the client setter
     *
     * @throws InputException
     */
    public void sendExitToBonusAction() {
        clientSetter.sendExitToBonusAction();
    }

    /**
     * This method prints the information of the excomunication tiles
     */
    public void showExcomunicationsTiles() {
        int i = 1;
        for (ExcommunicationZone exZone : clientSetter.getUiBoard().getExcommunicationZone()) {
            context.getpRed().print(i + ") ");
            context.getpBlue().println(exZone.getCardForThisPeriod().getEffectDescription());
            i++;
            context.getpBlue().println("");
        }
    }

    /**
     * This method prints the informations of the player's personal board
     */
    public void showPersonalBoard() {
        context.getpBlue().println(clientSetter.getUiPersonalBoard().toScreen());
    }

    /**
     * This method prints the dices value
     */
    public void showDicesValue() {
        int[] diceToPrint = clientSetter.getUiBoard().getDiceValue();
        context.getpBlue().print("Black dice value: ");
        context.getpYellow().println(diceToPrint[0]);
        context.getpBlue().print("White dice value: ");
        context.getpYellow().println(diceToPrint[1]);
        context.getpBlue().print("Orange dice value: ");
        context.getpYellow().println(diceToPrint[2]);

    }

    /**
     * This methods prints the player's score
     */
    public void showPoints() {
        context.getpBlue().println(clientSetter.getUiScore().toScreen());
    }

    /**
     * This method makes the abstract context in a DiscardLeaderCardAmaContext
     */
    public void discardLeaderCardAma() {
        context = new DiscardLeaderCardAmaContext(this, clientSetter.getUiPersonalBoard().getMyLeaderCard());
    }

    /**
     * This method makes the abstract context in a PlayLeadercardAmaContext
     *
     * @param leaderCards the list of leader cards owned by the player
     */
    public void playLeaderCardAma(List<LeaderCard> leaderCards) {
        context = new PlayLeadercardAmaContext(this, leaderCards);
    }

    /**
     * This method wait for the choice of praying from the keyboard and add it to the choiceQueue. After the input is
     * checked and if the controls have been passed, this value is returned to the method called in client setter
     *
     * @return the int that represent the choice
     */
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
                UnixColoredPrinter.Logger.print(CliConstants.INTERRUPTED_EXCEPTION);
                Thread.currentThread().interrupt();
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    /**
     * This method wait for the choice of permanent effect from the keyboard and add it to the choiceQueue.
     * After the input is checked and if the controls have been passed, this value is returned to the method called
     * in client setter
     *
     * @return the int that represent the choice
     */
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

    /**
     * This method wait for the choice of payment from the keyboard and add it to the choiceQueue.
     * After the input is checked and if the controls have been passed, this value is returned to the method called
     * in client setter
     *
     * @return the int that represent the choice
     */
    @Override
    public int bothPaymentsAvailable() {
        choice = true;
        choiceQueue = new LinkedBlockingDeque<>();
        context = new BothPaymentsVentureCardsContext(this);


        String costChoosen;
        while (true) {
            try {

                costChoosen = choiceQueue.take();
                context.checkValidInput(costChoosen);
                choice = false;
                return Integer.parseInt(costChoosen);

            } catch (InterruptedException e) {
                UnixColoredPrinter.Logger.print(CliConstants.INTERRUPTED_EXCEPTION);
                Thread.currentThread().interrupt();
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    /**
     * This method wait for the choice of leader card from the keyboard and add it to the choiceQueue.
     * After the input is checked and if the controls have been passed, this value is returned to the method called
     * in client setter
     *
     * @return the string that represent the choice
     */
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
                UnixColoredPrinter.Logger.print(CliConstants.INTERRUPTED_EXCEPTION);
                Thread.currentThread().interrupt();
            } catch (InputException e) {
                context.printHelp();
            }
        }
    }

    /**
     * This method check the input according to the current context specific and calls the bonusHarvesterAction on the
     * client setter
     *
     * @param input input string taken from the keyboard thread
     */
    public void bonusHarvesterParameters(String input) {
        try {
            context.checkValidInput(input);
            String[] parameters = input.split("-");
            clientSetter.bonusHarvesterAction(Integer.parseInt(parameters[0]));
        } catch (InputException | NumberFormatException e) {
            context.printHelp();
        }

    }

    /**
     * This method wait for the choice of bonus tile from the keyboard and add it to the choiceQueue.
     * After the input is checked and if the controls have been passed, this value is returned to the method called
     * in client setter
     *
     * @return the int that represent the choice
     */
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
                UnixColoredPrinter.Logger.print(CliConstants.INTERRUPTED_EXCEPTION);
                Thread.currentThread().interrupt();
            } catch (InputException | NumberFormatException e) {
                context.printHelp();
            }
        }

        choice = false;
        context.getpBlue().println("Bonus Tile choosen! Wait for the other players'choice");
        return Integer.parseInt(tileChosen);

    }

    /**
     * This method calls the newNickname method on the client setter
     *
     * @param nickanme the player's nickname as a String
     */
    public void newNickname(String nickanme) {
        clientSetter.newNickname(nickanme);
    }


    /**
     * This method prints the communication that the player has acted the prayer
     */
    @Override
    public void prayed() {
        context.getpRed().println("You have prayed and you did not take the excommunication!");
    }

    /**
     * This method prints the characteristics of the excommunication taken by the player
     *
     * @param update informations about the excommunication taken
     */
    @Override
    public void excommunicationTaken(ExcommunicationTaken update) {
        context.getpRed().println(update.toScreen());
        context.getpBlue().println(update.getExTile());
    }

    /**
     * This method makes the abstract context in a MatchStartedContext and set the number of players in the match
     * and the family colour of the player
     *
     * @param roomPlayers number of players
     * @param familyColour family colour of the player as a String
     */
    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        context = new MatchStartedContext(this);
        numberOfPlayers = roomPlayers;
    }

    /**
     * Get number of players
     *
     * @return numeber of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Set the boolean firstRound
     *
     * @param firstRound boolean first round
     */
    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    /**
     * Get familymember's array
     *
     * @return family members' array
     */
    public FamilyMember[] getMyFamilymembers() {
        return myFamilymembers;
    }

    /**
     * This method prints the turn order
     */
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

    /**
     * This method check the input according to the current context specific and calls the setConnectionType on the
     * client setter
     *
     * @param kindOfConnection the kind of connection choosen as a String
     * @param ip ip address choosen as a String
     */
    public void setIPaddressWhitConnection(String kindOfConnection, String ip) {

        try {
            context.checkValidInput(ip);
        } catch (InputException e) {
            context.printHelp();
            return;
        }

        clientSetter.setConnectionType(kindOfConnection, ip);
    }

    /**
     * Keyboard Thread
     */
    private class Keyboard extends Thread {

        @Override
        public void run() {


            while (true) {
                String lineFromKeyBoard;
                try {


                    lineFromKeyBoard = SingletonKeyboard.readLine();
                    if (choice) {
                        choiceQueue.add(lineFromKeyBoard);
                    } else if (context != null) {
                        context.doAction(lineFromKeyBoard);
                    }
                } catch (InputException | IOException e) {
                    UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
                }
            }
        }
    }
}