package project.server.network;

import com.google.gson.Gson;
import project.PlayerFile;
import project.controller.cardsfactory.*;
import project.controller.checkfunctions.AllCheckFunctions;
import project.controller.checkfunctions.BasicCheckFunctions;
import project.controller.Constants;
import project.controller.effects.effectsfactory.LeaderCardRequirements;
import project.controller.effects.realeffects.*;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.*;
import project.server.GameActions;
import project.server.Room;
import project.server.network.exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class PlayerHandler extends Player {

    private transient Room room;
    private transient AllCheckFunctions checkFunctions;
    private LeaderCardRequirements leaderCardRequirements;
    private boolean callPray;
    public boolean disconnectedInDraft = false;
    private boolean matchStartedVar = false;


    protected PlayerHandler() {
        super();
        callPray = false;
        leaderCardRequirements = new LeaderCardRequirements();
        checkFunctions = new BasicCheckFunctions();
    }

    private GameActions gameActions() {
        return room.getGameActions();
    }

    /**
     * controllare se si puo fare una check functions che si chiamauguale CheckIfCanTakeCard che prendere come parametr una volta buildingCard una volta TerritoryCard e cosi via
     *
     * @param towerColor
     * @param floor
     * @param familyM
     */
    protected void clientTakeDevelopmentCard(String towerColor, int floor, FamilyMember familyM) throws CantDoActionException {
        if (familyM == null) throw new CantDoActionException();

        Position[] tower;
        DevelopmentCard card;
        int diceCost;
        int diceValueOfFamiliar;
        Tower zone;
        boolean canPlaceFamiliar;
        boolean towerOccupied;

        tower = getPosition(towerColor);
        card = getCard(towerColor, floor);
        diceCost = getDiceCost(towerColor, floor);
        diceValueOfFamiliar = familyM.getMyValue();
        zone = getZone(towerColor, floor);
        canPlaceFamiliar = checkFunctions.checkPosition(floor, tower, familyM);
        towerOccupied = checkFunctions.checkTowerOccupied((Tower[]) tower);


        if (towerColor.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD)) {//todo un giocatore non puo prendere piu di 6 carte dello stetsso tipo
            int paymentChosen = checkOnVenturesCost(card, this, towerOccupied, diceCost, diceValueOfFamiliar);
            gameActions().takeVenturesCard(zone, familyM, this, towerOccupied, paymentChosen);
        } else {
            boolean canPayCard = checkFunctions.checkCardCost(card, this, towerOccupied, diceCost, diceValueOfFamiliar);
            System.out.println("canPayCard: " + canPayCard + "  postion: " + canPlaceFamiliar);
            if (canPayCard && canPlaceFamiliar) {
                gameActions().takeNoVenturesCard(zone, familyM, this, towerOccupied);
            } else {
                throw new CantDoActionException();
            }
        }

    }

    private int checkOnVenturesCost(DevelopmentCard card, PlayerHandler playerHandler, boolean towerOccupied, int diceCost, int diceValueOfFamiliar) throws CantDoActionException {
        int canTakeVenturesCard = checkFunctions.checkCardCostVentures((VenturesCard) card, playerHandler, towerOccupied, diceCost, diceValueOfFamiliar);

        if (canTakeVenturesCard == Constants.CANT_USE_ANY_PAYMENT) {
            throw new CantDoActionException();
        } else if (canTakeVenturesCard == Constants.CAN_USE_BOTH_PAYMENT_METHOD) {
            return canUseBothPaymentMethod();
        } else return canTakeVenturesCard - 1; //indice del costo pagabile
    }

    protected void clientTakeBonusDevelopementCard(String towerColour, int floor, TowerAction returnFromEffect) throws CantDoActionException {
        if (!(returnFromEffect.getKindOfCard().equals(Constants.ALL_COLOURS) || returnFromEffect.getKindOfCard().equals(towerColour)))
            throw new CantDoActionException();

        DevelopmentCard card = getCard(towerColour, floor);

        int diceValueOfFamiliar = returnFromEffect.getNewCardDicevalue();
        int diceCost = getDiceCost(towerColour, floor);
        Position[] tower = getPosition(towerColour);
        Tower zone = getZone(towerColour, floor);
        boolean towerOccupied = checkFunctions.checkTowerOccupied((Tower[]) tower);

        if (towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD)) {
            int paymentChosen = checkOnVenturesCost(card, this, towerOccupied, diceCost, diceValueOfFamiliar);
            gameActions().takeVenturesCard(zone, this, towerOccupied, paymentChosen, diceValueOfFamiliar);
        } else {
            Cost realCost = card.getCost();
            Cost costDiscounted = realCost.copyOf();
            costDiscounted = discountCost(costDiscounted, returnFromEffect.getDiscountedResource1(), returnFromEffect.getQuantityDiscounted1());
            costDiscounted = discountCost(costDiscounted, returnFromEffect.getDiscountedResource2(), returnFromEffect.getQuantityDiscounted2());
            card.setCost(costDiscounted);
            boolean canPayCard = checkFunctions.checkCardCost(card, this, towerOccupied, diceCost, diceValueOfFamiliar);
            if (!canPayCard) {
                card.setCost(realCost);
                throw new CantDoActionException();
            } else {
                System.out.println("eseguo takeNoVenturesCard");
                gameActions().takeNoVenturesCard(zone, this, towerOccupied, diceValueOfFamiliar);
            }
        }

    }

    private Cost discountCost(Cost costDiscounted, String discountedResource, int quantityDiscounted) {
        switch (discountedResource) {
            case Constants.WOOD: {
                costDiscounted.addWood(-quantityDiscounted);
                break;
            }
            case Constants.COIN: {
                costDiscounted.addCoin(-quantityDiscounted);
                break;
            }
            case Constants.STONE: {
                costDiscounted.addStone(-quantityDiscounted);
                break;
            }
        }
        return costDiscounted;
    }

    /**
     * this method is used when the system ask to the client which of VenturesCard payment he wants to use
     *
     * @param position
     * @param familyMember
     * @param paymentChoosen
     */
    protected void clientChosenPaymentForVenturesCard(int position, FamilyMember familyMember, int paymentChoosen) {
        Position[] tower;
        Tower zone;
        boolean towerOccupied;

        tower = getPosition(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD);
        zone = getZone(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD, position);
        towerOccupied = checkFunctions.checkTowerOccupied((Tower[]) tower);

        gameActions().takeVenturesCard(zone, familyMember, this, towerOccupied, paymentChoosen);
    }

    /**
     * @param familyM
     * @param servantsNumber
     */

    protected void harvester(FamilyMember familyM, int servantsNumber) throws CantDoActionException {
        if (servantsNumber > getPersonalBoardReference().getServants()) throw new CantDoActionException();

        List<Harvester> harvesterZone = room.getBoard().getHarvesterZone();
        boolean canTakeCard;
        int position = firstFreePosition(harvesterZone, familyM.getFamilyColour());

        gameActions().harvester(position, familyM, servantsNumber, this);
    }

    private int firstFreePosition(List<? extends Position> zone, String familyColour) throws CantDoActionException {
        for (Position p : zone) {
            if (p.getFamiliarOnThisPosition().getFamilyColour().equals(familyColour)) throw new CantDoActionException();
        }
        if (room.numberOfPlayerOn() > 2 && !zone.isEmpty()) throw new CantDoActionException();

        return zone.size();
    }

    /**
     * check function on bonus harvester action
     *
     * @param returnFromEffect
     * @param intServantsNumber
     * @throws CantDoActionException
     */
    protected void doBonusHarv(BonusProductionOrHarvesterAction returnFromEffect, int intServantsNumber) throws CantDoActionException {
        int harvesterValue = returnFromEffect.getDiceValue() + getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() + intServantsNumber;

        gameActions().harvesterBonus(harvesterValue, intServantsNumber, this);
    }

    /**
     * @param familyM
     * @param cardToProduct
     * @return
     */
    protected void production(FamilyMember familyM, List<BuildingCard> cardToProduct) throws CantDoActionException {
        int maxValueOfProduction;
        List<Production> productionZone = room.getBoard().getProductionZone();
        boolean canTakeCards;
        List<Integer> choichePE = new ArrayList<>();
        int position;

        if (productionZone.isEmpty()) position = 0;
        else position = firstFreePosition(productionZone, familyM.getFamilyColour());

        maxValueOfProduction = familyM.getMyValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus();

        if (position > 0) maxValueOfProduction = maxValueOfProduction - Constants.MALUS_PROD_HARV;

        System.out.println("STO ENTANDO NEL CHECK PRODUZIONE");
        int servantsToPay = checkAvaiabiltyToProduct(cardToProduct, maxValueOfProduction, choichePE);
        System.out.println("HO FATTO LA PRODUZIONE");

        gameActions().production(position, familyM, cardToProduct, servantsToPay, choichePE, this);
    }


    protected void doBonusProduct(BonusProductionOrHarvesterAction returnFromEffect, ArrayList<BuildingCard> cards) throws CantDoActionException {
        int maxValueOfProduction;
        List<Integer> choicePe = new ArrayList<>();
        maxValueOfProduction = returnFromEffect.getDiceValue() + getPersonalBoardReference().getBonusOnActions().getProductionBonus() + checkFunctions.getServants(this);
        int servantsToPay = checkAvaiabiltyToProduct(cards, maxValueOfProduction, choicePe);
        gameActions().productionBonus(cards, servantsToPay, choicePe, this);
    }


    private int checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction, List<Integer> choicePE) throws CantDoActionException {
        TotalCost cost = new TotalCost();
        int servantsMax = 0;
        for (BuildingCard b : cardToProduct) {
            System.err.println(b.getName());
            System.err.println(b.isChoicePe());
            if (b.getCost().getDiceCost() > maxValueOfProduction + checkFunctions.getServants(this))
                throw new CantDoActionException();
            int servnatsToPay = b.getCost().getDiceCost() - maxValueOfProduction;
            if (servnatsToPay > servantsMax) servantsMax = servnatsToPay;
            List<Effects> permanentEffect = b.getPermanentCardEffects();
            if (b.isChoicePe()) {
                fillEffectChoice(permanentEffect, choicePE, cost);
            } else addCost(permanentEffect.get(0), cost);
        }

        checkTotalCost(cost);
        return servantsMax;
    }

    private void checkTotalCost(TotalCost cost) throws CantDoActionException {
        if (getPersonalBoardReference().getServants() >= cost.getServantsRequired() && getPersonalBoardReference().getStone() >= cost.getStoneRequired() && getPersonalBoardReference().getWood() >= cost.getWoodRequired() && getPersonalBoardReference().getCoins() >= cost.getCoinsRequired() && getScore().getFaithPoints() >= cost.getFaithPoints())
            return;
        else throw new CantDoActionException();
    }

    private void fillEffectChoice(List<Effects> permanentEffect, List<Integer> choicePE, TotalCost cost) {
        int choice = sendChoicePE();
        choicePE.add(choice);
        System.out.println("effetto scelto1: " + permanentEffect.get(choice).toScreen());
        if (choice == -1) return;
        System.out.println("effetto scelto2: " + permanentEffect.get(choice).toScreen());
        addCost(permanentEffect.get(choice), cost);
    }

    private void addCost(Effects effects, TotalCost cost) {
        if (effects instanceof ExchangeEffects) ((ExchangeEffects) effects).addResourceRequested(cost);
    }

    /**
     * methods that check if you can go to market
     *
     * @param position position of the market where you want to place the familiar
     * @param familyM  family member that you want to place
     * @return void
     */
    protected void goToMarket(int position, FamilyMember familyM) throws CantDoActionException {
        Position[] marketZone = room.getBoard().getMarketZone();
        boolean canGoToMarket = checkFunctions.checkPosition(position, marketZone, familyM);
        canGoToMarket = canGoToMarket && ((familyM.getMyValue() + checkFunctions.getServants(this)) > 0);
        if (canGoToMarket) gameActions().goToMarket(position, familyM, this);
        else throw new CantDoActionException();
    }

    /**
     * @return
     */
    public void jumpTurn() {
        gameActions().nextTurn(this);
    }

    /**
     * @param leaderName
     * @return
     */
    protected void playLeaderCard(String leaderName) throws CantDoActionException {
        for (LeaderCard leaderCard : getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                if (leaderCard.isPlayed()) throw new CantDoActionException();
                if (leaderCard.isRequirementsSatisfied() || leaderCardRequirements.checkRequirements(leaderName, this)) {
                    leaderCard.setRequirementsSatisfied(true);
                    gameActions().playLeaderCard(leaderName, this);
                    return;
                } else throw new CantDoActionException();
            }

        }
        throw new CantDoActionException();
    }

    /**
     * @param leaderName
     * @return
     */
    protected void discardLeaderCard(String leaderName) throws CantDoActionException {
        boolean found = false;

        for (LeaderCard l : getPersonalBoardReference().getMyLeaderCard()) {
            if (l.getName().equals(leaderName)) {
                found = true;
                if (l.isRequirementsSatisfied()) throw new CantDoActionException();
            }
        }
        if (found) gameActions().discardLeaderCard(leaderName, this);
        else throw new CantDoActionException();
    }

    /**
     * @return
     */
    public void rollDices() {
        gameActions().rollDice();
    }

    /**
     * @param privelgeNumber
     * @param familyMember
     */
    protected void goToCouncilPalace(int privelgeNumber, FamilyMember familyMember) {
        // ho supposto che posso andare nel palazzo del consiglio anche se c'è gia un altro del mio colore
        if (familyMember.getMyValue() + checkFunctions.getServants(this) > 1)
            gameActions().goToCouncilPalace(privelgeNumber, familyMember, this);
    }

    /**
     * @param privilegeNumber
     */
    protected void takePrivilege(int privilegeNumber) {
        gameActions().takeCouncilPrivilege(privilegeNumber, this);
    }

    public void pray() {
        gameActions().pray(this);
    }

    public void dontPray() {
        gameActions().takeExcommunication(this);
    }


    /**
     * qua ci sono i metodi ausialiari
     *
     * @return
     */
    public AllCheckFunctions getCheckFunctions() {
        return checkFunctions;
    }

    public void setCheckFunctions(AllCheckFunctions checkFunctions) {
        this.checkFunctions = checkFunctions;
    }

    public Room getRoom() {
        return room;
    }


    private Tower[] getPosition(String towerColor) {
        return room.getBoard().getTrueArrayList(towerColor);
    }


    private Tower getZone(String towerColor, int floor) {
        return room.getBoard().getTrueArrayList(towerColor)[floor];
    }

    private int getDiceCost(String towerColor, int floor) {
        return room.getBoard().getTrueArrayList(towerColor)[floor].getDiceValueOfThisFloor();
    }

    private DevelopmentCard getCard(String towerColor, int floor) throws CantDoActionException {
        DevelopmentCard card = room.getBoard().getTrueArrayList(towerColor)[floor].getCardOnThisFloor();
        if (card == null) throw new CantDoActionException();
        return card;
    }

    public void reconnectClient() {
        this.setOn(true);
        System.out.println("CLIENT RECONNECTED!");
    }


    /**
     * da qua iniziano a comparire i metodi di ritorno al client. che poi potrebbero essere anche lo stesso dove cambia solo il coso che mandi
     * in rmi però è più comodo avere metodi diversi
     * penso anche in socket cosi sai gia che se devo mandare dal metodo chiamato possoUsareEntrambi... so che il parametro da passare è quella
     * stringa BOTH_COST_CAN_BE_SATISFIED
     */


    public abstract void cantDoAction();

    protected abstract int canUseBothPaymentMethod();

    public abstract void itsMyTurn(); //non saprei che parametri passare

    /**
     * manda al client la richiesta se vuole pregare o meno. il client o manderà la richiest di pregare o si rimetterà in ascolto
     *
     * @param playerTurn
     */
    public abstract int sendAskForPraying(List<PlayerHandler> playerTurn); //

    public abstract void sendString(String message);

    public abstract void sendAnswer(Object returnFromEffect);

    public abstract void sendNotification(Notify notifications);

    public abstract void sendUpdates(Updates updates);

    public abstract int sendChoicePE();

    public abstract void sendBonusTowerAction(TowerAction returnFromEffect);

    protected FamilyMember findFamilyMember(String colour) {
        for (FamilyMember familyMember : getAllFamilyMembers())
            if (familyMember.getMyColour().equals(colour)) return familyMember;
        System.out.println("ho ritornato un familiar null");
        return null;
    }


    public abstract void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect);

    public abstract void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect);

    public abstract void sendActionOk();

    //todo implement

    public abstract void timerTurnDelayed();

    public abstract void nicknameAlredyUsed();

    public abstract void loginSucceded();

    public void skipTurn() {
        this.waitForYourTurn();
        room.setLastPlayer(this);
        room.getGameActions().nextTurn(this);
    }

    protected abstract void waitForYourTurn();

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * this method is used for the draft of leader cards
     *
     * @param leaders list of leader cards in which the player can choose
     * @return
     */
    public abstract String leaderCardChosen(List<LeaderCard> leaders);

    /**
     * this method notify the players that the match is started.
     *
     * @param roomPlayers  number of player in the room
     * @param familyColour colour of the player
     */
    public abstract void matchStarted(int roomPlayers, String familyColour);

    public abstract int chooseTile(ArrayList<Tile> tiles);

    public boolean isCallPray() {
        return callPray;
    }

    public void setCallPray(boolean callPray) {
        this.callPray = callPray;
    }

    public abstract void tokenNotify();

    public abstract void prayed();

    public boolean isDisconnectedInDraft() {
        return disconnectedInDraft;
    }

    public void setDisconnectedInDraft(boolean disconnectedInDraft) {
        this.disconnectedInDraft = disconnectedInDraft;
    }

    public boolean isMatchStartedVar() {
        return matchStartedVar;
    }

    public void setMatchStartedVar(boolean matchStartedVar) {
        this.matchStartedVar = matchStartedVar;
    }

    public abstract void afterMatch();

    public void takeRanking(){

        List<PlayerFile> ranking = getRoom().generateRanking();
        sendRanking(ranking);
    }

    protected abstract void sendRanking(List<PlayerFile> ranking);

    public void showStatistics() {

        Gson gson = new Gson();
        String nickname = getName();
        String statistics = getRoom().takeStatistics(nickname);
        PlayerFile playerFile = gson.fromJson(statistics, PlayerFile.class);

        sendStatistic(playerFile);

    }

    protected abstract void sendStatistic(PlayerFile playerFile);

    public abstract void afterGameIftemporarilyOff();

    public abstract void winnerComunication(String winnerString);
}
