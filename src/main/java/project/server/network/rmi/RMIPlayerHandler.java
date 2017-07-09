package project.server.network.rmi;

import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.network.rmi.RMIServerToClientInterface;
import project.controller.Constants;
import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.model.Tile;
import project.server.network.PlayerHandler;
import project.server.network.exception.CantDoActionException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is created when an RMI connection is generated. Each RMI clients has its own RMI playerhandler that is saved in the server.
 * this class received lots of method calls from the server and call method on the RMI client through myClient variable, an instance of its client
 */
public class RMIPlayerHandler extends PlayerHandler {

    private transient RMIServerToClientInterface myClient;
    private final transient Object tokenn;
    private transient String towerColourChosen;
    private int floorChosen;
    private int servantsNumber;
    private ArrayList<BuildingCard> productionCards;
    private List<Integer> privileges;

    /**
     * constructor
     * @param rmiServerToClientInterface the interface of its client
     */
    protected RMIPlayerHandler(RMIServerToClientInterface rmiServerToClientInterface) {
        this.myClient = rmiServerToClientInterface;
        tokenn = new Object();
    }

    /**
     * constructur used for testing
     */
    public RMIPlayerHandler() {
        tokenn = new Object();
    }

    /**
     * method used to stop the actions of RMI payer ìhandler while waiting to user decision
     */
    private void waitForClient() {
        synchronized (tokenn) {
            try {
                tokenn.wait();
            } catch (InterruptedException e) {
                this.setOn(false);
                UnixColoredPrinter.Logger.print("wait interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * methods callled when the timer is expired during a bonus action, it notify all the treads that was waiting with the default values of variables
     */
    void exitOnBonusAction() {
        towerColourChosen = null;
        productionCards = null;
        servantsNumber = -1;
        privileges = null;

        synchronized (tokenn) {
            tokenn.notifyAll();
        }
    }

    /**
     * method that send updates to cient
     * @param updates update to send
     */
    @Override
    public void sendUpdates(Updates updates) {
        try {
            if (isOn()) myClient.sendUpdates(updates);
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method that asks to the client which permanent effect of a building card wants to perform
     * @return client's decision
     */
    @Override
    public int sendChoicePE() {
        try {
            return myClient.sendChoicePE();
        } catch (RemoteException e) {
            playerDisconnected();
        }
        return -1;
    }

    /**
     * methods that ask to the client to perform a bonus tower action
     * @param returnFromEffect tower effect that contains the colour of the card and the bonus values
     */
    @Override
    public void sendBonusTowerAction(TowerAction returnFromEffect) {
        while (true) {
            try {
                myClient.bonusTowerAction(returnFromEffect);
            } catch (RemoteException e) {
                this.setOn(false);
            }

            waitForClient();

            if (towerColourChosen == null) return;

            try {
                clientTakeBonusDevelopementCard(towerColourChosen, floorChosen, returnFromEffect);
                break;
            } catch (CantDoActionException e) {
                //it's correct that continue
            }

        }
    }


    /**
     * methods that notify sendBonusTowerAction that the bunus action has been performed
     * @param floor floor chosen
     * @param towerColour tower colour chosen
     */
    void takeBonusCardAction(int floor, String towerColour) {
        this.floorChosen = floor;
        this.towerColourChosen = towerColour;

        synchronized (tokenn) {
            tokenn.notifyAll();
        }

    }

    /**
     * methods that ask to the client to perform a bonus action f harvester or production
     * @param returnFromEffect return from effect that contains the action type and the bonuses
     */
    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        while (true) {
            try {
                myClient.sendBonusProdHarv(returnFromEffect);
            } catch (RemoteException e) {
                this.setOn(false);
            }

            waitForClient();

            try {

                if (returnFromEffect.toString().equals(Constants.BONUS_HARVESTER)) {
                    if (servantsNumber == -1) return;
                    doBonusHarv(returnFromEffect, servantsNumber);
                } else {
                    if (productionCards == null) return;
                    doBonusProduct(returnFromEffect, productionCards);
                }

                break;
            } catch (CantDoActionException e) {
                //continue
            }
        }
    }

    /**
     * method that notify sendBonusProdOrHarv method that the action bonus has been performed
     * @param servantsNumber number of servants used in the bonus action
     */
    void doBonusHarvester(int servantsNumber) {
        this.servantsNumber = servantsNumber;

        synchronized (tokenn) {
            tokenn.notifyAll();
        }
    }

    /**
     * method that notify sendBonusProdOrHarv method that the action bonus has been performed
     * @param parameters list of building cards name chosen for production bonus action
     */
    void doBonusProduction(List<String> parameters) {
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard : getPersonalBoardReference().getBuildings()) {
            if (parameters.contains(buildingCard.getName())) {
                buildingCards.add(buildingCard);
            }
        }
        this.productionCards = buildingCards;

        synchronized (tokenn) {
            tokenn.notifyAll();
        }
    }

    /**
     * methods that notify the client that has t perform a council bonus action
     * @param returnFromEffect bonus privileges action, it contains the number of different privileges that the client should chose
     */
    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) {
            privileges = new ArrayList<>();
            try {
                if (isOn()) {
                    myClient.sendRequestForPrivileges(returnFromEffect);
                } else {
                    return;
                }
            } catch (RemoteException e) {
                playerDisconnected();
            }

            waitForClient();

            if (privileges == null) return;

            for (Integer i : privileges)
                takePrivilege(i);

    }

    /**
     * methods that notify sendRequestForPrivieges that the privileges for the bonus actions have been selected
     * @param privileges list of privileges chosen
     */
    void takeImmediatePrivilegesNotify(List<Integer> privileges) {
        this.privileges = privileges;
        synchronized (tokenn) {
            tokenn.notifyAll();
        }
    }

    /**
     * method that notify the client that he can't to the last action performed
     */
    @Override
    public void cantDoAction() {
        try {
            myClient.cantDoAction();
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method the asks to the client to perform a decision n which payment he wants to use on the ventures card chosen
     * @return client's decision
     */
    @Override
    public int canUseBothPaymentMethod() {
        try {
            return myClient.canUseBothPaymentMethod();
        } catch (RemoteException e) {
            playerDisconnected();
        }
        return 0;
    }

    /**
     * method that notify the client that it's his turn
     */
    @Override
    public void itsMyTurn() {
        try {
            myClient.itMyTurn();
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method that asks to the client to pray or not
     * @return client's choice
     */
    @Override
    public int sendAskForPraying() {
        try {
            return myClient.askForPraying();
        } catch (RemoteException e) {
            playerDisconnected();
        }
        return 0;
    }

    /**
     * method that notify the client that he has performed an action correctly and should move to end turn context
     */
    @Override
    public void sendActionOk() {
        try {
            if (isOn()) {
                myClient.actionOk();
            }
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method called when the timer is expired
     */
    @Override
    public void timerTurnDelayed() {
        try {
            myClient.timerTurnDelayed();
        } catch (RemoteException e) {
            setOn(false);
        }
    }

    /**
     * method called when the client wants to use a nickname already used
     */
    @Override
    public void nicknameAlredyUsed() {
        try {
            myClient.nicknameAlreadyUsed();
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method called when the login has been performed correctly
     */
    @Override
    public void loginSucceded() {
        try {
            myClient.loginSucceded();
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * methods that notify the client that its turn is over
     */
    @Override
    protected void waitForYourTurn() {
        try {
            if (isOn())
                myClient.waitForYourTurn();
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method used for the draft of leaders
     * @param leaders leaders to draft
     * @return leader card chosen
     */
    @Override
    public String leaderCardChosen(List<LeaderCard> leaders) {
        try {
            return myClient.leaderCardChosen(leaders);
        } catch (RemoteException e) {
            this.setOn(false);
            return "-1";
        }
    }

    /**
     * method that notify the client that the match is started
     * @param roomPlayers number of players in the room
     * @param familyColour client's familiar colour
     */
    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        try {
            if (isOn()) myClient.matchStarted(roomPlayers, familyColour);
        } catch (RemoteException e) {
            playerDisconnected();
        }
    }

    /**
     * method used for the draft of tiles
     * @param tiles tiles to draft
     * @return tiles chosen
     */
    @Override
    public int chooseTile(List<Tile> tiles) {
        try {
            return myClient.tileChoosen((ArrayList<Tile>) tiles);
        } catch (RemoteException e) {
            this.setOn(false);
            return -1;
        }
    }

    /**
     * methos used too send statistics to the client
     * @param playerFile class of statistic to send
     */
    @Override
    protected void sendStatistic(PlayerFile playerFile) {
        try {
            myClient.sendStatistics(playerFile);
        } catch (RemoteException e) {
            this.setOn(false);
        }
    }

    /**
     * method that notify a disconnected player that the game is finished and move his ui in the end match context
     */
    @Override
    public void afterGameIftemporarilyOff() {
        try {
            myClient.afterGameIfTemporaryOff();
        } catch (RemoteException e) {
            return;
        }
    }

    /**
     * method that tells the name of the winner
     * @param winnerString winner's name
     */
    @Override
    public void winnerComunication(String winnerString) {
        try {
            myClient.winnerComunication(winnerString);
        } catch (RemoteException e) {
            setOn(false);
        }
    }

    /**
     * useless method in RMI
     */
    @Override
    public void tokenNotify() {
        //correct empty
    }

    /**
     * method used to notify the client that the praying action is done correctly
     */
    @Override
    public void prayed() {
        try {
            myClient.prayed();
        } catch (RemoteException e) {
            this.setOn(false);
        }
    }

    /**
     * method to move the client in afterMatch context
     */
    @Override
    public void afterMatch() {
        try {
            myClient.afterMatch();
        } catch (RemoteException e) {
            this.setOn(false);
        }
    }

    /**
     * method used to send rankings to the client
     * @param ranking list of statistics files ordered
     */
    @Override
    protected void sendRanking(List<PlayerFile> ranking) {
        try {
            myClient.sendRanking(ranking);
        } catch (RemoteException e) {
            this.setOn(false);
        }
    }

    /**
     * method called from the client that want to perform a take development card action
     * @param towerColour tower color chosen
     * @param floor floor chosen
     * @param familyMemberColour color of the familiar chosen
     */
    void takeDevCard(String towerColour, int floor, String familyMemberColour)  {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            clientTakeDevelopmentCard(towerColour, floor, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to perform an harvester action
     * @param familyMemberColour color of the familiar chosen
     * @param servantsNumber servnts number used to perform the harvester action
     */
    void harvesterRequest(String familyMemberColour, int servantsNumber) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            harvester(familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to perform a production action
     * @param familyMemberColour color of the familiar chosen
     * @param cards list of building card chosen for the production
     */
    void productionRequest(String familyMemberColour, List<String> cards) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> buildingCards = new ArrayList<>();
        for (BuildingCard buildingCard : getPersonalBoardReference().getBuildings()) {
            if (cards.contains(buildingCard.getName())) {
                buildingCards.add(buildingCard);
            }
        }
        try {
            production(familyMember, buildingCards);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to perform a market action
     * @param position position of the market selected
     * @param familyMemberColour color of the familiar chosen
     */
    void goToMarketRequest(int position, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to play a leader card
     * @param leaderCardName leader card name
     */
    void playLeaderCardRequest(String leaderCardName) {
        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to discard a leader card
     * @param leaderCardName leader card name
     */
    void discardLeaderCardRequest(String leaderCardName) {
        try {
            discardLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called from the client that want to go to council palace
     * @param privilegeNumber number of the privilege chosen
     * @param familyMemberColour color of the familiar chosen
     */
    void goToCouncilPalaceRequest(int privilegeNumber, String familyMemberColour) {
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        try {
            goToCouncilPalace(privilegeNumber, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    /**
     * method called when Remote Exception occured
     */
    private void playerDisconnected() {
        this.setOn(false);
        getRoom().getGameActions().nextTurn(this);
    }
}
