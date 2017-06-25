package project.server;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.effectsfactory.LeaderCardsEffects;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.*;
import project.messages.updatesmessages.*;
import project.model.*;
import project.server.Room;
import project.server.network.PlayerHandler;

import java.util.*;

/**
 * main game actions
 */
public class GameActions {

    private Room room;
    private Timer timer;

    public GameActions( Room room ){
        this.room = room;
        timer = new Timer();
    }

    private AllSupportFunctions getSupportFunctions(Player player) {
        return room.getMySupportFunction(player);
    }

    private void takeDevelopmentCard(Tower zone, PlayerHandler player) {
        DevelopmentCard card = zone.getCardOnThisFloor();

        getSupportFunctions(player).placeCardInPersonalBoard(card);
        zone.getTowerZoneEffect().doEffect(player);

        makeImmediateEffects(player, zone.getCardOnThisFloor());

        zone.setCardOnThisFloor(null);

        TowersUpdate towersUpdate = new TowersUpdate(room.getBoard().getAllTowersUpdate(), player.getName());
       player.sendActionOk();
        broadcastUpdates(towersUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player,player.getName()));
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));

    }

    public void takeNoVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();
        System.out.println(familyM);
        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);
        getSupportFunctions(player).setFamiliar(zone, familyM);
        //prova
        System.out.println(zone.getFamiliarOnThisPosition());

        takeDevelopmentCard(zone, player);
    }

    public void takeNoVenturesCard(Tower zone, PlayerHandler player, boolean towerIsOccupied, int diceFamiliarValue) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();

        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);

        takeDevelopmentCard(zone, player);
    }


    public void takeVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payVenturesCard((VenturesCard) card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);
        getSupportFunctions(player).setFamiliar(zone, familyM);
        takeDevelopmentCard(zone, player);
    }


    public void takeVenturesCard(Tower zone, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment, int diceFamiliarValue) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();

        getSupportFunctions(player).payVenturesCard((VenturesCard) card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);

        takeDevelopmentCard(zone, player);

    }

    /**
     * @param playerHandler
     */
    public void nextTurn(PlayerHandler playerHandler) {
        PlayerHandler next;
        List<PlayerHandler> turn = room.getBoard().getTurn().getPlayerTurn();

        for ( int i = 0; i < 2; i++ )
            System.out.println(turn.get(i));

        int indexOfMe = turn.indexOf(playerHandler);
        System.out.println(indexOfMe);
        int playerNumbers = room.getRoomPlayers();
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();

        if (indexOfMe < playerNumbers - 1) { //non sono l'ultimo del turno
             next = turn.get(indexOfMe + 1);
            System.out.println(next);
             if ( next.isOn() ) {
                 timer.cancel();
                 next.itsMyTurn();
                 timer = this.room.myTimerSkipTurn(turn.get(indexOfMe + 1));
                 return;
             }
             nextTurn(next);

        } else if ( room.getBoard().getTurn().getRotation() < 4) {// sono 'ultimo del turno ma non è finito round
             next = turn.get(0);
             if (next.isOn()) {
                 timer.cancel();
                 next.itsMyTurn();
                 room.getBoard().getTurn().setRotation(room.getBoard().getTurn().getRotation() + 1);
                 timer = this.room.myTimerSkipTurn(turn.get(0));
                 return;
             }
             nextTurn(next);
         }

         else if (currentRound == 2 && currentPeriod == 3) { //fine partita
            endMatch();

        } else if (currentRound == 2) {//fine periodo
            endPeriod(currentPeriod);
            nextRound();
            nextPeriod();
            setEndRound(true);
            timer.cancel();
            firstPlayerTurn();
            timer = this.room.myTimerSkipTurn(turn.get(0));
            return;

        } else {
            endRound();
            room.getBoard().getTurn().setRotation(0);
            nextRound();
            setEndRound(true);
            timer.cancel();
            firstPlayerTurn();
            timer = this.room.myTimerSkipTurn(turn.get(0));
            return;
        }

        //todo se rimane solo un giocatore nella partita?
        //todo se non ne rimane nessuno?
    }


    //todo can we delete it?
    private void allAreOff() {
        //todo
    }

    private void firstPlayerTurn() {
        PlayerHandler firstPlayer = room.getBoard().getTurn().getPlayerTurn().get(0);
        if ( firstPlayer.isOn() )
            firstPlayer.itsMyTurn();
        nextTurn(firstPlayer);
    }

    //todo can we delete it?
    private boolean allFamiliarPlayed() {
        for( PlayerHandler player : room.getListOfPlayers()) {
            for (FamilyMember familyMember : player.getAllFamilyMembers()) {
                if (!familyMember.isPlayed())
                    return false;
            }
        }
        return true;
    }

    class MilitaryComparator implements Comparator<PlayerHandler> {

        @Override
        public int compare(PlayerHandler o1, PlayerHandler o2) {
            if (o1.getScore().getMilitaryPoints() > o2.getScore().getMilitaryPoints()) return 1;
            else if (o1.getScore().getMilitaryPoints() == o2.getScore().getMilitaryPoints()) return 0;
            else return -1;
        }
    }

    class WinnerComparator implements Comparator<PlayerHandler> {
        @Override
        public int compare(PlayerHandler o1, PlayerHandler o2) {
            if (o1.getScore().getVictoryPoints() > o2.getScore().getVictoryPoints()) return 1;
            else if (o1.getScore().getVictoryPoints() == o2.getScore().getVictoryPoints()) {
                if (o1.getTurnOrder() < o2.getTurnOrder()) return 1;
                else return -1;
            } else return -1;
        }
    }

    private int getMilitaryPoints(PlayerHandler playerHandler) {
        return playerHandler.getScore().getMilitaryPoints();
    }

    private void endMatch() {
        MilitaryComparator comparator = new MilitaryComparator();
        List<PlayerHandler> militaryStandings = room.getListOfPlayers();
        Collections.sort(militaryStandings, comparator);

        int victoryPointsToWinner = 5;
        int victoryPointsToRunnerUp = 2;
        PlayerHandler militaryStandingsWinner = militaryStandings.get(0);
        PlayerHandler militaryStandingsRunnerUp = militaryStandings.get(1);

        if (getMilitaryPoints(militaryStandingsWinner) == getMilitaryPoints(militaryStandingsRunnerUp)) {
            victoryPointsToWinner = 2;
        }

        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler playerHandler = entry.getValue();
            int pointsToAdd = 0;

            finalPray(playerHandler);

            if (playerHandler.equals(militaryStandingsWinner)) {
                pointsToAdd += victoryPointsToWinner;
            }

            else if (playerHandler.equals(militaryStandingsRunnerUp)){
                pointsToAdd += victoryPointsToRunnerUp;
            }

            pointsToAdd += getExtraFinalPoints(playerHandler);
            pointsToAdd += pointsFromResources(playerHandler);

            playerHandler.getScore().setVictoryPoints(playerHandler.getScore().getVictoryPoints() + pointsToAdd);
        }

        PlayerHandler winner = findWinner();
        //winner.YOUWIN();
      //todo  broadcastNotifications(new Notify("the winner is + " + winner.getName()));

    }

    private void finalPray(PlayerHandler playerHandler) {
        if (playerHandler.getScore().getFaithPoints() >= room.getBoard().getFaithPointsRequiredEveryPeriod()[Constants.PERIOD_NUMBER])
            pray(playerHandler);
        else {
            takeExcommunication(playerHandler);
        }
    }

    private int pointsFromResources(PlayerHandler playerHandler) {
        int numberOfResources;
        numberOfResources = playerHandler.getPersonalBoardReference().getCoins();
        numberOfResources += playerHandler.getPersonalBoardReference().getServants();
        numberOfResources += playerHandler.getPersonalBoardReference().getStone();
        numberOfResources += playerHandler.getPersonalBoardReference().getWood();
        return (numberOfResources / 5);
    }

    private int getExtraFinalPoints(PlayerHandler playerHandler) {
        int pointsToAdd = 0;

        pointsToAdd += getSupportFunctions(playerHandler).extraLostOfPoints(playerHandler);
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromCharacterCard(room.getBoard().getFinalPointsFromCharacterCards());
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromTerritoryCard(room.getBoard().getFinalPointsFromTerritoryCards());
        getSupportFunctions(playerHandler).finalPointsFromVenturesCard();

        return pointsToAdd;
    }

    private PlayerHandler findWinner() {
        WinnerComparator comparator = new WinnerComparator();
        List<PlayerHandler> finalStandings = room.getListOfPlayers();
        Collections.sort(finalStandings, comparator);
        return finalStandings.get(0);

    }

    private void changePlayerOrder() {
        List<PlayerHandler> newTurnOrder = new ArrayList<>();
        List<Council> councilZone = room.getBoard().getCouncilZone();
        List<PlayerHandler> oldTurnOrder = room.getBoard().getTurn().getPlayerTurn();

        for (Council council : councilZone) {
            if (!newTurnOrder.contains(council.getPlayer())) {
                newTurnOrder.add((PlayerHandler) council.getPlayer());
            }
        }
        for (PlayerHandler player : oldTurnOrder) {
            if (!newTurnOrder.contains(player)) {
                newTurnOrder.add(player);
            }
        }
        room.getBoard().getTurn().setPlayerTurn(newTurnOrder);
    }

    private void nextPeriod() {
        room.getBoard().nextPeriod();
    }

    private void nextRound() {
        room.getBoard().nextRound();
    }

    private void endPeriod(int period) {
        endRound();
        askForPraying(period);
    }

    private void endRound() {//cambiare le carte, pulire spazi azione, settare i familiari a false
        refactorTowers();
        changePlayerOrder();
        clearAllPosition();
    }

    private void clearAllPosition() {
        List<Harvester> harvesterZone = room.getBoard().getHarvesterZone();
        List<Production> productionZone = room.getBoard().getProductionZone();
        List<Council> councilZone = room.getBoard().getCouncilZone();
        Market[] marketZone = room.getBoard().getMarketZone();

        for (Harvester harvester : harvesterZone)
            clearSinglePosition(harvester);

        for (Production production : productionZone)
            clearSinglePosition(production);

        for (Market market : marketZone)
            clearSinglePosition(market);

        for (Council council : councilZone)
            clearSinglePosition(council);
    }

    private void clearSinglePosition(Position position) {
        position.setOccupied(false);
            if (position.getFamiliarOnThisPosition() != null) {
                position.getFamiliarOnThisPosition().setPlayed(false);
                position.setFamiliarOnThisPosition(null);
            }
    }

    void refactorTowers() {
        int j;
        int i;
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();
        Tower[][] tower = room.getBoard().getAllTowers();
        DevelopmentCard[][][] deck = room.getBoard().getDeckCard().getDevelopmentDeck();
        int roundsAdd = 0;

        if (currentRound == 1)
            roundsAdd = 4;

        else
            currentPeriod++;

        //si potrebbe fare con iteratore..
        for (i = 0; i < Constants.NNUMBER_OF_TOWERS; i++) {
            for (j = 0; j < Constants.CARD_FOR_EACH_TOWER; j++) {
                //ho fatto il ciclo passando per tutte le torri dal basso all'alto
                clearSinglePosition(tower[i][j]);
                tower[i][j].setCardOnThisFloor(deck[i][currentPeriod][roundsAdd + j]); //da testare
            }
        }

        room.getBoard().setTowers(tower);

    }

    private void setEndRound(boolean choice) {
        room.getBoard().setEndRound(choice);
    }

    private void askForPraying(int period) {
        int faithPointsNeeded = room.getBoard().getFaithPointsRequiredEveryPeriod()[period];

        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            if (player.getScore().getFaithPoints() >= faithPointsNeeded) {
                player.sendAskForPraying();
            } else {
                takeExcommunication(player);
            }
        }
    }


    /**
     * @param position
     * @param familyM
     * @param servantsNumber
     * @param player
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, PlayerHandler player) {
        int malusByField;
        int actionValue;
        int cardBonus = player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus();
        List<Harvester> harvesterList = room.getBoard().getHarvesterZone();
        Harvester harvesterZone = new Harvester();


        getSupportFunctions(player).setFamiliar(harvesterZone, familyM);
        if (position == 0) malusByField = 0;
        else malusByField = 3;
        actionValue = familyM.getMyValue() + servantsNumber - malusByField + cardBonus;
        player.getPersonalBoardReference().getMyTile().takeHarvesterResource();

        for (TerritoryCard card : player.getPersonalBoardReference().getTerritories()) {
            if (actionValue >= card.getCost().getDiceCost())
                makePermanentEffects(player, card);
        }
        harvesterList.add(harvesterZone);

        player.sendActionOk();
        HarvesterUpdate harvesterUpdate = new HarvesterUpdate((ArrayList<Harvester>) room.getBoard().getHarvesterZone(), player.getName());
        broadcastUpdates(harvesterUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }


    public void havesterBonus(int harvesterValue, PlayerHandler playerHandler) {

    }



    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @param player
     */
    public void production(int position, FamilyMember familyM, List<BuildingCard> cardToProduct, PlayerHandler player) {
        List<Production> productionSpace = room.getBoard().getProductionZone();
        Production productionZone = new Production();

        getSupportFunctions(player).setFamiliar(productionZone, familyM);
        productionSpace.add(productionZone);

        productionBonus(cardToProduct,player);

        ProductionUpdate productionUpdate = new ProductionUpdate((ArrayList<Production>) room.getBoard().getProductionZone(), player.getName());
        broadcastUpdates(productionUpdate);

    }


    public void productionBonus(List<BuildingCard> cards, PlayerHandler player) {

        player.getPersonalBoardReference().getMyTile().takeProductionResource();

        for (BuildingCard card : cards) {
            makePermanentEffects(player, card);
        }

        player.sendActionOk();
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));

    }

    /**
     * @param position
     * @param familyM
     * @return
     */
    public void goToMarket(int position, FamilyMember familyM, PlayerHandler player) {
        Position marketPosition = room.getBoard().getMarketZone()[position];

        getSupportFunctions(player).setFamiliar(marketPosition, familyM);

        MarketUpdate marketUpdate = new MarketUpdate(room.getBoard(), player.getName());

        getSupportFunctions(player).takeMarketAction(position);

        player.sendActionOk();
        //prova di stampa
        System.out.println(marketUpdate.toScreen());
        broadcastUpdates(marketUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }


    /**
     * @param leaderName
     * @param player
     */
    //todo modify
    public void playLeaderCard(String leaderName, PlayerHandler player) {
        for (LeaderCard leaderCard : player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                BonusInteraction returnFromEffect = LeaderCardsEffects.doEffect(leaderName,player);
                if (returnFromEffect instanceof LorenzoMagnifico){
                    //todo fare cose per lorenzo magnifico
                }
                leaderCard.setPlayed(true);
            }
        }

        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName, PlayerHandler player) {
        int numberToDelate = 0;
        for (LeaderCard leaderCard : player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                numberToDelate = player.getPersonalBoardReference().getMyLeaderCard().indexOf(leaderCard);
                player.sendRequestForPriviledges(new TakePrivilegesAction(1));
                break;
            }
        }

        player.getPersonalBoardReference().getMyLeaderCard().remove(numberToDelate);
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }

    /**
     * @return
     */
    public void rollDice() {
        int[] newDiceValue = new int[3];
        Random r = new Random();
        newDiceValue[0] = r.nextInt(5) + 1;
        newDiceValue[1] = r.nextInt(5) + 1;
        newDiceValue[2] = r.nextInt(5) + 1;

        room.getBoard().setDiceValue(newDiceValue);

        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            getSupportFunctions(player).setDicesValue(newDiceValue, player);
        }
        setEndRound(false);

        broadcastUpdates(new DiceValueUpdate(room.getBoard().getDiceValue()));
    }

    /**
     * @param privilegeNumber
     * @param familyMember
     * @param player          @return
     */
    public void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler player) {
        List<Council> councilZone = room.getBoard().getCouncilZone();

        getSupportFunctions(player).setFamiliarInTheCouncilPalace(councilZone, familyMember);

        Effects e = new AddCoin(1);
        e.doEffect(player);
        takeCouncilPrivilege(privilegeNumber, player);

        player.sendActionOk();
        ArrayList<Council> council = (ArrayList<Council>) room.getBoard().getCouncilZone();
        broadcastUpdates(new CouncilUpdate(council, player.getName()));
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }

    /**
     * @param privilegeNumber
     * @param player
     */
    public void takeCouncilPrivilege(int privilegeNumber, PlayerHandler player) {
        getSupportFunctions(player).takeCouncilPrivilege(privilegeNumber);

        if (privilegeNumber > 0 && privilegeNumber < 2) {
            player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        } else {
            player.sendUpdates(new ScoreUpdate(player, player.getName()));
        }
    }

    public void pray(PlayerHandler player) {
        int victoryPointsToAdd = room.getBoard().getVictoryPointsInFaithTrack()[player.getScore().getFaithPoints()];

        getSupportFunctions(player).pray(victoryPointsToAdd);
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
    }

    public void takeExcommunication(PlayerHandler player) {
        int period = room.getBoard().getPeriod();
        ExcommunicationTile card = room.getBoard().getExcommunicationZone()[period].getCardForThisPeriod();

        card.makeEffect(player);

        broadcastUpdates(new ExcomunicationUpdate( room.getBoard().getExcommunicationZone(), player.getName()));
    }

    public void broadcastNotifications(Notify notifications) {
        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendNotification(notifications);
        }
    }


    private void broadcastUpdates(Updates updates) {
        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendUpdates(updates);
        }
    }

    private void makeImmediateEffects(PlayerHandler player, DevelopmentCard card)  {
        for (Effects effect : card.getImmediateCardEffects()) {
            BonusInteraction returnFromEffect = effect.doEffect(player);

            if (returnFromEffect instanceof TowerAction) {
                player.sendBonusTowerAction((TowerAction) returnFromEffect);
            } else if (returnFromEffect instanceof BonusProductionOrHarvesterAction) {
                player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
            } else if (returnFromEffect instanceof TakePrivilegesAction)
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
        }
        player.sendActionOk();
    }

    private void makePermanentEffects(PlayerHandler player, DevelopmentCard card) {

        for (Effects effect : card.getPermanentCardEffects()) {
            if (card.isChoicePe()) {
                int choice = player.sendPossibleChoice(Constants.CHOICE_PE);
                card.getPermanentCardEffects().get(choice).doEffect(player);
            } else {
                effect.doEffect(player);
            }
        }

    }
}
