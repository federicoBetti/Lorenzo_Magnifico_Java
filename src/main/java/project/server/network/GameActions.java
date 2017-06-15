package project.server.network;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;
import project.controller.FakeFamiliar;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.BonusInteraction;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;
import project.model.*;
import project.server.Room;
import project.messages.Notify;

import javax.sql.rowset.BaseRowSet;
import java.io.IOException;
import java.util.*;

/**
 * main game actions
 */
public class GameActions {

    Room room;
    /**
     * TODO ad ogni metodo viene passato il player ( socket o rmi ) e alla fine deve chiamare un metodo
     * TODO di ritorno, risposta al client sul relativo Player. I metodi chiamano delle suport function
     * TODO dedicate a ciascun player che possono essere personalizzate durante il corso della partita
     */
    private AllSupportFunctions getRightSupportFunctions (Player player){
        return room.getMySupportFunction(player);
    }

    private void takeDevelopementCard(Tower zone, FamilyMember familyMember, PlayerHandler player){

        getRightSupportFunctions(player).setFamiliar(zone, familyMember);
        getRightSupportFunctions(player).placeCardInPersonalBoard(zone.getCardOnThisFloor());
        TowersUpdate towersUpdate = new TowersUpdate(room.getBoard().getAllTowers());

        makeImmediateEffects( player, zone.getCardOnThisFloor() );

        broadcastUpdates(towersUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
        player.sendUpdates( new ScoreUpdate(player));
        player.sendUpdates( new FamilyMemberUpdate(player));

    }

    /**
     * ho fatto due passaggi diversi per le carte venutres, che possono avere più tipi di pagamento e qundi voglo avere come paramentro quale pagamento usare
     * @param zone
     * @param familyM
     * @param player
     * @param towerIsOccupied
     */
    public void takeNoVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied) {
        getRightSupportFunctions(player).payCard(zone.getCardOnThisFloor(), towerIsOccupied, zone.getDiceValueOfThisFloor(), familyM.getMyValue());
        takeDevelopementCard(zone,familyM,player);
    }

    public void takeVenturesCard (Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment){
        getRightSupportFunctions(player).payVenturesCard((VenturesCard)zone.getCardOnThisFloor(),player,towerIsOccupied,zone.getDiceValueOfThisFloor(),familyM.getMyValue(),numberOfPayment);
        takeDevelopementCard(zone,familyM,player);
    }


    /**
     * todo check this method!!!
     * @param playerHandler
     */
    public void nextTurn(PlayerHandler playerHandler) {
        PlayerHandler next;
        int playerNumber = room.getRoomPlayers();
        int indexOfMe = room.getBoard().getTurn().getPlayerTurn().indexOf(playerHandler);
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();
        if (room.getBoard().getNumberOfFamilyMemberPlayedInThisRound() < 5 ){ //it's not the end of a round
            if (indexOfMe != playerNumber) {// i'm not the last
                next = (PlayerHandler) room.getBoard().getTurn().getPlayerTurn().get(indexOfMe++);
                broadcastNotifications(new Notify("it's " + next.getName() + " turn"));
                next.itsMyTurn();
            }
            else{ //i'm the last
                next = (PlayerHandler) room.getBoard().getTurn().getPlayerTurn().get(0);
                broadcastNotifications(new Notify("it's " + next.getName() + " turn"));
                next.itsMyTurn();
                room.getBoard().setNumberOfFamilyMemberPlayedInThisRound(room.getBoard().getNumberOfFamilyMemberPlayedInThisRound() + 1);
            }
        }
        //ora siamo nel caso in cui è finito un round o un periodo
        else if (currentRound == 2 && currentPeriod == 3){
            endMatch();//
        }
        else if (currentRound == 2){
            endPeriod(currentPeriod);
            nextRound();
            nextPeriod();
            room.getBoard().setNumberOfFamilyMemberPlayedInThisRound(1);
            changePlayerOrder();
            setEndTurn(true);
        }
        else {
            endRound();
            nextRound();
            room.getBoard().setNumberOfFamilyMemberPlayedInThisRound(1);
            changePlayerOrder();
            setEndTurn(true);
        }
    }

    private void endMatch() { //todo

        //todo classifica dei military points SI PUO USARE UNA SORTED MAP! BISOGNA GUARDARE COME FUNZIONANO
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler playerHandler = entry.getValue();
            int pointsToAdd = 0;
            if (playerHandler.getScore().getFaithPoints() >= room.getBoard().getFaithPointsRequiredEveryPeriod()[Constants.PERIOD_NUMBER])
                pray(playerHandler);
            else{
                takeExcommunication(playerHandler);
            }
            pointsToAdd += getRightSupportFunctions(playerHandler).extraLostOfPoints(playerHandler);
            pointsToAdd += getRightSupportFunctions(playerHandler).finalPointsFromCharacterCard(room.getBoard().getFinalPointsFromCharacterCards());
            pointsToAdd += getRightSupportFunctions(playerHandler).finalPointsFromTerritoryCard(room.getBoard().getFinalPointsFromTerritoryCards());
            getRightSupportFunctions(playerHandler).finalPointsFromVenturesCard();

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

    private void changePlayerOrder() { //todo controllare
        //devo controllare il palazzo del consiglio, metto in ordine quelli in un nuovo arrayList e poi prendo da quello vecchio.
        // ogni volta devo controlare che non metta due volte lo stesso player
        List<PlayerHandler> newTurnOrder = new ArrayList<>();
        for (Council council: room.getBoard().getCouncilZone()){
            if (!newTurnOrder.contains(council.getPlayer())){
                newTurnOrder.add((PlayerHandler) council.getPlayer());
            }
        }
        for (PlayerHandler player: room.getBoard().getTurn().getPlayerTurn()){
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
        changeCardInTowers();
        askForPraying(period); //todo controllare che non ho fatto che si puo chiedere se pregare o no solo a quelli che possono
    }

    private void askForPraying(int period) {
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            if (player.getScore().getFaithPoints() >= room.getBoard().getFaithPointsRequiredEveryPeriod()[period]) {
                player.sendAskForPraying();
                player.sendUpdates( new ScoreUpdate(player));
            }
            else{
                takeExcommunication(player);
            }
        }
    }

    private void setEndTurn(boolean choice) {
        room.getBoard().setEndRound(choice);
    }

    private PlayerHandler nextPlayerToPlay(PlayerHandler playerHandler){
        int indexOfMe = room.getBoard().getTurn().getPlayerTurn().indexOf(playerHandler);
        int indexOfNext = room.getRoomPlayers() % indexOfMe;
        return (PlayerHandler) room.getBoard().getTurn().getPlayerTurn().get(indexOfNext);
    }

    private void endRound(){
        changeCardInTowers();
    }

    private void changeCardInTowers() {
        int i,j;
        FakeFamiliar fakeFamiliar = new FakeFamiliar();
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();
        Tower[][] tower = room.getBoard().getAllTowers();
        int roundsAdd = 0;
        if (currentRound == 1)
            roundsAdd = 4;
        else
            currentPeriod++;
        for (i = 0; i < Constants.NNUMBER_OF_TOWERS; i++){
            for (j=0; j < Constants.CARD_FOR_EACH_TOWER; j++){
                //ho fatto il ciclo passando per tutte le torri dal basso all'alto
                tower[j][i].setOccupied(false);
                tower[j][i].setFamiliarOnThisPosition(fakeFamiliar);
                tower[j][i].setCardOnThisFloor(room.getBoard().getDeckCard().getDevelopmentdeck()[i][currentPeriod][roundsAdd + j]); //da testare
            }
        }
        room.getBoard().setTowers(tower);
    }

    private void setFamilyMemberHome() {
        //todo
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
        if (position == 0)
            malusByField = 0;
        else
            malusByField = 3;
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("harvester")[position], familyM);

        HarvesterUpdate harvesterUpdate = new HarvesterUpdate(room.getBoard().getHarvesterZone());

        player.getPersonalBoardReference().getMyTile().takeHarvesterResource();
        for (TerritoryCard card: player.getPersonalBoardReference().getTerritories()){
            if (familyM.getMyValue() + servantsNumber - malusByField + player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() >= card.getCost().getDiceCost())
                makePermannetEffects(player, card);
        }

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
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("production")[position], familyM);
        ProductionUpdate productionUpdate = new ProductionUpdate(room.getBoard().getProductionZone());

        player.getPersonalBoardReference().getMyTile().takeProductionResource();

        for (BuildingCard card: cardToProduct){
            makePermannetEffects(player, card );
        }

        broadcastUpdates(productionUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));
    }


    /**
     * @param position
     * @param familyM
     * @return
     */
    public void goToMarket(int position, FamilyMember familyM, PlayerHandler player){
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("market")[position],familyM);
        MarketUpdate marketUpdate = new MarketUpdate(room.getBoard().getMarketZone());

        getRightSupportFunctions(player).takeMarketAction(position);

        broadcastUpdates(marketUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player));

        nextTurn(player);
    }


    /**
     *
     * @param leaderName
     * @param player
     */
    public void playLeaderCard(String leaderName, PlayerHandler player ){
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
    public void rollDice(){
        int[] newDiceValue = new int[3];
        newDiceValue[0] = (int)(Math.random() * 6);
        newDiceValue[1] = (int)(Math.random() * 6);
        newDiceValue[2] = (int)(Math.random() * 6);
        room.getBoard().setDiceValue(newDiceValue);
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            getRightSupportFunctions(player).setDicesValue(newDiceValue, player);
        }
        setEndTurn(false);

        broadcastUpdates(new DiceValueUpdate(room.getBoard().getDiceValue()));
    }

    /**
     * @param privilegeNumber
     * @param familyMember
     *@param player @return
     */
    public void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler player){
        getRightSupportFunctions(player).setFamiliarInTheCouncilPalace(room.getBoard().getCouncilZone(), familyMember);
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
        getRightSupportFunctions(player).takeCouncilPrivilege(privilegeNumber);

        if ( privilegeNumber > 0 && privilegeNumber < 2 ){
            player.sendUpdates(new PersonalBoardUpdate(player));
        }
        else {
            player.sendUpdates(new ScoreUpdate(player));
        }
    }

    public void broadcastNotifications(Notify notifications){
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendNotification( notifications );
        }
    }

    public void pray(PlayerHandler player) {
        int victoryPointsToAdd = room.getBoard().getVictoryPointsInFaithTrack()[player.getScore().getFaithPoints()];
        getRightSupportFunctions(player).pray(victoryPointsToAdd);
        player.sendUpdates( new ScoreUpdate(player));
    }

    public void takeExcommunication(PlayerHandler playerHandler) {
        ExcommunitationTile card = room.getBoard().getExcommunicationZone()[room.getBoard().getPeriod()].getCardForThisPeriod();
        card.makeEffect(playerHandler);

        broadcastUpdates(new ExcomunicationUpdate(room.getBoard().getExcommunicationZone()));
    }

    private void broadcastUpdates( Updates updates ){
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendUpdates(updates);
        }
    }

    public void makeImmediateEffects(PlayerHandler player, DevelopmentCard card ) {
        for (Effects effect : card.getImmediateCardEffects()) {
            BonusInteraction returnFromEffect = effect.doEffect(player);
            try {
                if ( returnFromEffect instanceof TowerAction ){
                    player.sendBonusTowerAction((TowerAction) returnFromEffect);
                }

                else if ( returnFromEffect instanceof BonusProductionOrHarvesterAction ){
                    player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            player.sendAnswer(returnFromEffect);
        }
    }

    public void makePermannetEffects(PlayerHandler player, DevelopmentCard card )  {

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
