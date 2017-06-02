package project.server.network;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;
import project.controller.FakeFamiliar;
import project.controller.supportfunctions.AllSupportFunctions;
import project.model.Council;
import project.model.FamilyMember;
import project.model.Player;
import project.model.Tower;
import project.server.Room;
import project.messages.BonusInteraction;
import project.messages.Notify;
import project.messages.OkOrNo;

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

    private void takeDevelopementCard(Tower zone, FamilyMember familyMember, PlayerHandler player){
        getRightSupportFunctions(player).setFamiliar(zone, familyMember);
        getRightSupportFunctions(player).placeCardInPersonalBoard(zone.getCardOnThisFloor());
        BonusInteraction returnFromEffect = getRightSupportFunctions(player).applyEffects(zone.getCardOnThisFloor(),player);
        player.sendAnswer(returnFromEffect);
        if (returnFromEffect instanceof OkOrNo){
            player.sendAnswer(new Notify("i have finished my turn"));
            nextTurn(player);
        }
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
            if (player.getScore().getFaithPoints() >= room.getBoard().getFaithPointsRequiredEveryPeriod()[period])
                player.sendAskForPraying();
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

    }

    /**
     *
     * @param position
     * @param familyM
     * @param servantsNumber
     * @param player
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, PlayerHandler player){
        int malusByField;
        if (position == 0)
            malusByField = 0;
        else
            malusByField = 3;
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("harvester")[position], familyM);
        player.getPersonalBoardReference().getMyTile().takeHarvesterResource();
        for (TerritoryCard t: player.getPersonalBoardReference().getTerritories()){
            if (familyM.getMyValue() + servantsNumber - malusByField + player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus()
                    >= t.getCost().getDiceCost())
                t.makePermannetEffects(player);
        }

        return;
    };

    /**
     *
     * @param position
     * @param familyM
     * @param cardToProduct
     * @param player
     */
    public void production(int position, FamilyMember familyM, List<BuildingCard> cardToProduct, Player player){
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("production")[position], familyM);
        player.getPersonalBoardReference().getMyTile().takeProductionResource();
        for (BuildingCard b: cardToProduct){
            b.makePermannetEffects(player);
            //qua il controllo se puo essere fatto va fatto nel player handler
        }
        return;
    };


    /**
     * @param position
     * @param familyM
     * @return
     */
    public void goToMarket(int position, FamilyMember familyM, PlayerHandler player){
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("market")[position],familyM);
        getRightSupportFunctions(player).takeMarketAction(position);
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
    }

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName, Player player){
        int numberToDelate = 0;
        for (LeaderCard leaderCard: player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                numberToDelate = player.getPersonalBoardReference().getMyLeaderCard().indexOf(leaderCard);
                break;
            }
        }
        player.getPersonalBoardReference().getMyLeaderCard().remove(numberToDelate);
    };

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
        // TODO fare notifica a tutti
    };

    /**
     * @param privilegeNumber
     * @param familyMember
     *@param playerHandler @return
     */
    public void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler playerHandler){
        getRightSupportFunctions(playerHandler).setFamiliarInTheCouncilPalace(room.getBoard().getCouncilZone(), familyMember);
        Effects e = new AddCoin(1);
        e.doEffect(playerHandler);
        takeCouncilPrivilege(privilegeNumber,playerHandler);
    };

    /**
     *
     * @param privilegeNumber
     * @param playerHandler
     */
    public void takeCouncilPrivilege(int privilegeNumber, PlayerHandler playerHandler) {
        getRightSupportFunctions(playerHandler).takeCouncilPrivilege(privilegeNumber);
    }

    public void broadcastNotifications(Notify notifications){
        for (Map.Entry<String, PlayerHandler> entry: room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            player.sendAnswer( notifications );
        }
    }

    public void pray(PlayerHandler playerHandler) {
        int victoryPointsToAdd = room.getBoard().getVictoryPointsInFaithTrack()[playerHandler.getScore().getFaithPoints()];
        getRightSupportFunctions(playerHandler).pray(victoryPointsToAdd);
    }

    public void takeExcommunication(PlayerHandler playerHandler) {
        ExcommunitationTile card = room.getBoard().getExcommunicationZone()[room.getBoard().getPeriod()].getCardForThisPeriod();
        card.makeEffect(playerHandler);
    }
}
