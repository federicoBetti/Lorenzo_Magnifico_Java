package project.server.network;

import javafx.collections.transformation.SortedList;
import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;
import project.model.*;
import project.server.Room;
import project.messages.Notify;

import java.io.IOException;
import java.util.*;

/**
 * main game actions
 */
public class GameActions {

    private Room room;

    private AllSupportFunctions getSupportFunctions(Player player){
        return room.getMySupportFunction(player);
    }

    private void takeDevelopmentCard(Tower zone, FamilyMember familyMember, PlayerHandler player){
        DevelopmentCard card = zone.getCardOnThisFloor();

        getSupportFunctions(player).setFamiliar(zone, familyMember);
        getSupportFunctions(player).placeCardInPersonalBoard(card);
        //todo bonus da posizione torre
        TowersUpdate towersUpdate = new TowersUpdate(room.getBoard().getAllTowers());

        makeImmediateEffects( player, zone.getCardOnThisFloor() );

        broadcastUpdates(towersUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
        player.sendUpdates( new ScoreUpdate(player));
        player.sendUpdates( new FamilyMemberUpdate(player));

        nextTurn(player);
    }

    void takeNoVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue,diceFamiliarValue);
        takeDevelopmentCard(zone,familyM,player);
    }

    void takeVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment){
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payVenturesCard((VenturesCard)card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);
        takeDevelopmentCard(zone,familyM,player);
    }


    /**
     * @param playerHandler
     */
    void nextTurn(PlayerHandler playerHandler) {
        PlayerHandler next;
        List<PlayerHandler> turn = room.getBoard().getTurn().getPlayerTurn();
        int indexOfMe = turn.indexOf(playerHandler);
        int playerNumbers = room.getRoomPlayers();
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();

        if (indexOfMe < playerNumbers - 1){ //non sono l'ultimo del turno
            next = turn.get(indexOfMe + 1);
            next.itsMyTurn();
        }
        else if (!allFamiliarPlayed(playerHandler)){// sono 'ultimo del turno ma non è finito round
            next = turn.get(0);
            next.itsMyTurn();
        }
        else if (currentRound == 2 && currentPeriod == 3){ //fine partita
            endMatch();//
        }
        else if (currentRound == 2){//fine periodo
            endPeriod(currentPeriod);
            nextRound();
            nextPeriod();
            setEndTurn(true);
        }
        else {//fine round
            endRound();
            nextRound();
            setEndTurn(true);
        }
    }

    private boolean allFamiliarPlayed(PlayerHandler playerHandler) {
        for (FamilyMember familyMember: playerHandler.getAllFamilyMembers()){
            if (!familyMember.isPlayed())
                return false;
        }
        return true;
    }

    class militaryComparator implements Comparator<PlayerHandler> {

        @Override
        public int compare(PlayerHandler o1, PlayerHandler o2) {
            if (o1.getScore().getMilitaryPoints() > o2.getScore().getMilitaryPoints())
                return 1;
            else if (o1.getScore().getMilitaryPoints() == o2.getScore().getMilitaryPoints())
                return 0;
            else
                return -1;
        }
    }


    private void endMatch() { //todo
        militaryComparator comparator = new militaryComparator();
        SortedList<PlayerHandler> militaryStandings = new SortedList<PlayerHandler>(room.getListOfPlayers(),comparator);
        PlayerHandler militaryStandingsWinner = militaryStandings.get(0);
        PlayerHandler militaryStandingsRunnerUp = militaryStandings.get(1);


        //todo classifica dei military points SI PUO USARE UNA SORTED MAP! BISOGNA GUARDARE COME FUNZIONANO, quella di sopra forse funziona ma non mi va internet e non posso vedere bene la doc di SortedList

        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler playerHandler = entry.getValue();
            //todo aggiungere che se hai vinto military points ricevi bonus
            int pointsToAdd = 0;
            if (playerHandler.getScore().getFaithPoints() >= room.getBoard().getFaithPointsRequiredEveryPeriod()[Constants.PERIOD_NUMBER])
                pray(playerHandler);
            else{
                takeExcommunication(playerHandler);
            }
            pointsToAdd += getSupportFunctions(playerHandler).extraLostOfPoints(playerHandler);
            pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromCharacterCard(room.getBoard().getFinalPointsFromCharacterCards());
            pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromTerritoryCard(room.getBoard().getFinalPointsFromTerritoryCards());
            getSupportFunctions(playerHandler).finalPointsFromVenturesCard();

            int numberOfResources;
            numberOfResources = playerHandler.getPersonalBoardReference().getCoins();
            numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getServants();
            numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getStone();
            numberOfResources = numberOfResources + playerHandler.getPersonalBoardReference().getWood();
            pointsToAdd += (numberOfResources/5);
            //todo aggiungere punti relativi a classifica military points
            playerHandler.getScore().setVictoryPoints(playerHandler.getScore().getVictoryPoints() + pointsToAdd);
        }
        PlayerHandler winner;
        ArrayList<PlayerHandler> winnerSearcher = new ArrayList<>();
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
           // winnerSearcher.
            // todo completare
        }
        //todo controllare chi ha vinto mettendo in ordine i player rispetto ai victory points
       // winner.YOUWIN();
        broadcastNotifications(new Notify("the winner is + " ));

        //todo complete on with + winnerName
    }

    private void changePlayerOrder() {
        List<PlayerHandler> newTurnOrder = new ArrayList<>();
        List<Council> councilZone = room.getBoard().getCouncilZone();
        List<PlayerHandler> oldTurnOrder = room.getBoard().getTurn().getPlayerTurn();

        for (Council council: councilZone){
            if (!newTurnOrder.contains(council.getPlayer())){
                newTurnOrder.add((PlayerHandler) council.getPlayer());
            }
        }
        for (PlayerHandler player: oldTurnOrder){
            if (!newTurnOrder.contains(player)){
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

    private void endRound(){//cambiare le carte, pulire spazi azione, settare i familiari a false
        refactorTowers();
        changePlayerOrder();
        clearAllPosition();
    }

    private void clearAllPosition() {
        Harvester[] harvesterZone = room.getBoard().getHarvesterZone();
        Production[] productionZone = room.getBoard().getProductionZone();
        List<Council> councilZone = room.getBoard().getCouncilZone();
        Market[] marketZone = room.getBoard().getMarketZone();

        for (Harvester harvester: harvesterZone)
            clearSinglePosition(harvester);

        for (Production production: productionZone)
            clearSinglePosition(production);

        for (Market market: marketZone)
            clearSinglePosition(market);

        for (Council council: councilZone)
            clearSinglePosition(council);
    }

    private void clearSinglePosition(Position position) {
        position.setOccupied(false);
        position.getFamiliarOnThisPosition().setPlayed(false);
        position.setFamiliarOnThisPosition(null);
    }

    private void refactorTowers() {
        int i,j;
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
        for (i = 0; i < Constants.NNUMBER_OF_TOWERS; i++){
            for (j=0; j < Constants.CARD_FOR_EACH_TOWER; j++){
                //ho fatto il ciclo passando per tutte le torri dal basso all'alto
                clearSinglePosition(tower[j][i]);
                tower[j][i].setCardOnThisFloor(deck[i][currentPeriod][roundsAdd + j]); //da testare
            }
        }

        room.getBoard().setTowers(tower);

    }

    private void setEndTurn(boolean choice) {
        room.getBoard().setEndRound(choice);
    }

    private void askForPraying(int period) {
        int faithPointsNeeded = room.getBoard().getFaithPointsRequiredEveryPeriod()[period];

        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            if (player.getScore().getFaithPoints() >= faithPointsNeeded) {
                player.sendAskForPraying();
            }
            else{
                takeExcommunication(player);
            }
        }
    }


    private PlayerHandler nextPlayerToPlay(PlayerHandler playerHandler){
        int indexOfMe = room.getBoard().getTurn().getPlayerTurn().indexOf(playerHandler);
        int indexOfNext = room.getRoomPlayers() % indexOfMe;
        return (PlayerHandler) room.getBoard().getTurn().getPlayerTurn().get(indexOfNext);
    }



    /**
     *
     * @param position
     * @param familyM
     * @param servantsNumber
     * @param player
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, PlayerHandler player)  {
        int malusByField;
        int actionValue;
        int cardBonus = player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus();
        Harvester harvesterZone = room.getBoard().getHarvesterZone()[position];


        getSupportFunctions(player).setFamiliar(harvesterZone, familyM);
        if (position == 0)
            malusByField = 0;
        else
            malusByField = 3;
        actionValue = familyM.getMyValue() + servantsNumber - malusByField + cardBonus;
        player.getPersonalBoardReference().getMyTile().takeHarvesterResource();

        for (TerritoryCard card: player.getPersonalBoardReference().getTerritories()){
            if ( actionValue >= card.getCost().getDiceCost())
                makePermanentEffects(player, card);
        }

        HarvesterUpdate harvesterUpdate = new HarvesterUpdate(room.getBoard().getHarvesterZone());
        broadcastUpdates(harvesterUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
    }

    /**
     *
     * @param position
     * @param familyM
     * @param cardToProduct
     * @param player
     */
    public void production(int position, FamilyMember familyM, List<BuildingCard> cardToProduct, PlayerHandler player) {
        Production[] productionSpace = room.getBoard().getProductionZone();
        Production productionZone = productionSpace[position];

        getSupportFunctions(player).setFamiliar(productionZone, familyM);
        ProductionUpdate productionUpdate = new ProductionUpdate(room.getBoard().getProductionZone());

        player.getPersonalBoardReference().getMyTile().takeProductionResource();

        for (BuildingCard card: cardToProduct){
            makePermanentEffects(player, card );
        }

        broadcastUpdates(productionUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
    }


    /**
     * @param position
     * @param familyM
     * @return
     */
    void goToMarket(int position, FamilyMember familyM, PlayerHandler player){
        Position marketPosition = room.getBoard().getMarketZone()[position];

        getSupportFunctions(player).setFamiliar(marketPosition,familyM);
        MarketUpdate marketUpdate = new MarketUpdate(room.getBoard().getMarketZone());

        getSupportFunctions(player).takeMarketAction(position);

        broadcastUpdates(marketUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
    }


    /**
     *
     * @param leaderName
     * @param player
     */
    void playLeaderCard(String leaderName, PlayerHandler player){
        for (LeaderCard leaderCard: player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                leaderCard.playCard(player);
                leaderCard.setPlayed(true);
            }
        }

        player.sendUpdates(new PersonalBoardUpdate(player));
    }

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName, PlayerHandler player){
        int numberToDelate = 0;
        for (LeaderCard leaderCard: player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                numberToDelate = player.getPersonalBoardReference().getMyLeaderCard().indexOf(leaderCard);
                break;
            }
        }

        player.getPersonalBoardReference().getMyLeaderCard().remove(numberToDelate);
        player.sendUpdates(new PersonalBoardUpdate(player));
    }

    /**
     * @return
     */
    void rollDice(){
        int[] newDiceValue = new int[3];
        newDiceValue[0] = (int)(Math.random() * 6);
        newDiceValue[1] = (int)(Math.random() * 6);
        newDiceValue[2] = (int)(Math.random() * 6);
        room.getBoard().setDiceValue(newDiceValue);
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            getSupportFunctions(player).setDicesValue(newDiceValue, player);
        }
        setEndTurn(false);

        broadcastUpdates(new DiceValueUpdate(room.getBoard().getDiceValue()));
    }

    /**
     * @param privilegeNumber
     * @param familyMember
     *@param player @return
     */
    void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler player){
        List<Council> councilZone = room.getBoard().getCouncilZone();

        getSupportFunctions(player).setFamiliarInTheCouncilPalace(councilZone, familyMember);

        Effects e = new AddCoin(1);
        e.doEffect(player);
        takeCouncilPrivilege(privilegeNumber,player);

        broadcastUpdates(new CouncilUpdate(room.getBoard().getCouncilZone()));
        player.sendUpdates(new PersonalBoardUpdate(player));
    }

    /**
     *
     * @param privilegeNumber
     * @param player
     */
    public void takeCouncilPrivilege(int privilegeNumber, PlayerHandler player ) {
        getSupportFunctions(player).takeCouncilPrivilege(privilegeNumber);

        if ( privilegeNumber > 0 && privilegeNumber < 2 ){
            player.sendUpdates(new PersonalBoardUpdate(player));
        }
        else {
            player.sendUpdates(new ScoreUpdate(player));
        }
    }

    public void pray(PlayerHandler player) {
        int victoryPointsToAdd = room.getBoard().getVictoryPointsInFaithTrack()[player.getScore().getFaithPoints()];

        getSupportFunctions(player).pray(victoryPointsToAdd);
        player.sendUpdates( new ScoreUpdate(player));
    }

    void takeExcommunication(PlayerHandler playerHandler) {
        int period = room.getBoard().getPeriod();
        ExcommunitationTile card = room.getBoard().getExcommunicationZone()[period].getCardForThisPeriod();

        card.makeEffect(playerHandler);

        broadcastUpdates(new ExcomunicationUpdate(room.getBoard().getExcommunicationZone()));
    }

    void broadcastNotifications(Notify notifications){
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendNotification( notifications );
        }
    }

    private void broadcastUpdates( Updates updates ){
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendUpdates(updates);
        }
    }

    private void makeImmediateEffects(PlayerHandler player, DevelopmentCard card) {
        for (Effects effect : card.getImmediateCardEffects()) {
            BonusInteraction returnFromEffect = effect.doEffect(player);
            if ( returnFromEffect instanceof TowerAction ){
                try {
                    player.sendBonusTowerAction(returnFromEffect);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    //todo non vanno presi qua ma più sotto le eccezioni relative alla conessione
                }
            }
            player.sendAnswer(returnFromEffect);
        }
    }

    private void makePermanentEffects(PlayerHandler player, DevelopmentCard card)  {

        if ( card.isChoicePe() ) {
            int choice = player.sendPossibleChoice( Constants.CHOICE_PE );
            card.getPermanentCardEffects().get(choice).doEffect(player);
        }

        else {
            for (Effects effect : card.getPermanentCardEffects()) {
                effect.doEffect(player);
            }
        }

    }
}
