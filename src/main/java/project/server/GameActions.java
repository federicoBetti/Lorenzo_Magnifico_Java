package project.server;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.controller.effects.effectsfactory.LeaderCardsEffects;
import project.controller.effects.realeffects.AddCoin;
import project.controller.effects.realeffects.Effects;
import project.controller.effects.realeffects.UsePrivilege;
import project.controller.supportfunctions.AllSupportFunctions;
import project.messages.*;
import project.messages.updatesmessages.*;
import project.model.*;
import project.server.network.PlayerHandler;
import project.server.network.exception.CantDoActionException;

import java.util.*;

/**
 * This class performs all the main actions receiving the parameters from the network's package classes.
 */
public class GameActions {

    private Room room;
    private Board board;
    private Timer timer;
    private LeaderCardsEffects leaderCardEffect;
    private int excommunicationPlayerPlayed;
    private boolean excommunicationTurn;

    GameActions(Room room) {
        this.room = room;
        excommunicationPlayerPlayed = 0;
        leaderCardEffect = new LeaderCardsEffects();
        timer = new Timer();
    }

    /**
     * Get the support function object dedicated to a player
     * @param player PlayerHandler reference
     * @return supportFunction object dedicated to the player
     */
    private AllSupportFunctions getSupportFunctions(PlayerHandler player) {
        return room.getMySupportFunction(player);
    }

    /**
     * it takes the development card choosen by the player and performs all the immediate effects and sends the updates
     * and call the "sendActionOk" method on the player.
     *
     * @param zone reference of the card's tower zone
     * @param player PlayerHandler reference that perform the action
     */
    private void takeDevelopmentCard(Tower zone, PlayerHandler player) {
        DevelopmentCard card = zone.getCardOnThisFloor();

        getSupportFunctions(player).placeCardInPersonalBoard(card);
        getSupportFunctions(player).towerZoneEffect(zone, player);

        for (Effects e : zone.getCardOnThisFloor().getImmediateCardEffects())
            getSupportFunctions(player).applyEffects(e, player);


        player.sendActionOk();
        zone.setCardOnThisFloor(null);

        TowersUpdate towersUpdate = new TowersUpdate(board.getAllTowers(), player.getName());
        broadcastUpdates(towersUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        player.sendActionOk();
    }

    /**
     * Make the payment for a no venture card choosen, whit supportFunction dedicated to the player, and call
     * "takeDevelopmentCard"
     *
     * @param zone reference of the card's tower zone
     * @param familyM familiar to place in the tower
     * @param player PlayerHandler reference
     * @param towerIsOccupied boolean that says if the tower is already occupied for performing the correct payment
     */
    public void takeNoVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);
        getSupportFunctions(player).setFamiliar(zone, familyM);

        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        takeDevelopmentCard(zone, player);
    }

    /**
     * Make the payment for a no venture card choosen whit a bonus actions, using the supportFunction dedicated to the player,
     * and call "takeDevelopmentCard"
     *
     * @param zone reference of the card's tower zone
     * @param player PlayerHandler reference
     * @param towerIsOccupied boolean that says if the tower is already occupied for performing the correct payment
     * @param diceFamiliarValue the bonus action's dice value
     */
    public void takeNoVenturesCard(Tower zone, PlayerHandler player, boolean towerIsOccupied, int diceFamiliarValue) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();

        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);

        takeDevelopmentCard(zone, player);
    }

    /**
     * Make the payment for a venture card choosen , using the supportFunction dedicated to the player,
     * and call "takeDevelopmentCard"
     *
     * @param zone reference of the card's tower zone
     * @param player PlayerHandler reference
     * @param towerIsOccupied boolean that says if the tower is already occupied for performing the correct payment
     * @param numberOfPayment the number of payment choosen
     */
    public void takeVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payVenturesCard((VenturesCard) card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);
        getSupportFunctions(player).setFamiliar(zone, familyM);

        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        takeDevelopmentCard(zone, player);
    }

    /**
     * Make the payment for a venture card choosen whit a bonus action , using the supportFunction dedicated to the player,
     * and call "takeDevelopmentCard"
     *
     * @param zone reference of the card's tower zone
     * @param player PlayerHandler reference
     * @param towerIsOccupied boolean that says if the tower is already occupied for performing the correct payment
     * @param numberOfPayment the number of payment choosen
     * @param diceFamiliarValue the bonus action's dice value
     */
    public void takeVenturesCard(Tower zone, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment, int diceFamiliarValue) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();

        getSupportFunctions(player).payVenturesCard((VenturesCard) card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);

        takeDevelopmentCard(zone, player);

    }

    /**
     * This method find who is the next player that has to play in the match and call on his PlayerHandler reference
     * the method "itsMyTurn"
     *
     * @param playerHandler current playerhandler reference
     */
    public void nextTurn(PlayerHandler playerHandler) {
        PlayerHandler next;
        List<PlayerHandler> turn = board.getTurn().getPlayerTurn();

        int indexOfMe = turn.indexOf(playerHandler);
        int playerNumbers = room.getRoomPlayers();
        int currentPeriod = board.getPeriod();
        int currentRound = board.getRound();

        if (excommunicationTurn && excommunicationPlays()){
            return;
        }

        if (board.getTurn().getRotation() == 0){
            int nextIndex = indexOfMe + 1;
            nextIndex = nextIndex % playerNumbers;
            PlayerHandler nextt = turn.get(nextIndex);
            if (board.getTurn().getSkipTurnForExcommunication().contains(nextt)){
                if (indexOfMe == playerNumbers -1) {
                    board.getTurn().setRotation(board.getTurn().getRotation() + 1);
                    sendItsMyTurn(nextt);
                }
                else
                    nextTurn(nextt);
                return;
            }
        }

        if (indexOfMe < playerNumbers - 1) { //non sono l'ultimo del turno
            next = turn.get(indexOfMe + 1);
            sendItsMyTurn(next);

        } else if (board.getTurn().getRotation() < 3) {// sono 'ultimo del turno ma non è finito round (è giusto che sia 3 il numero perchè si incrementa dopo)
            board.getTurn().setRotation(board.getTurn().getRotation() + 1);
            next = turn.get(0);
            sendItsMyTurn(next);
        } else if (currentRound == 1 && currentPeriod == 2) {//fine partita
            if (excommunicationPlays()){
                excommunicationTurn = true;
                return;
            }
            excommunicationPlayerPlayed = 0;
            excommunicationTurn = false;
            timer.cancel();
            if (room.numberOfPlayerOn() == 0) {
                System.out.println("finita la partita senza nessun giocatore");
                room.getServer().getRooms().remove(room);
                return;
            } else endMatch();

        } else if (currentRound == 1) {//fine periodo
            if (excommunicationPlays()){
                excommunicationTurn = true;
                return;
            }
            excommunicationPlayerPlayed = 0;
            excommunicationTurn = false;

            timer.cancel();
            endPeriod(currentPeriod);
            nextRound();
            nextPeriod();
            board.getTurn().setRotation(0);
            int playerIndex = firstPlayerTurn();
            if (playerIndex == -1) return;

            return;

        } else {
            if (excommunicationPlays()){
                excommunicationTurn = true;
                return;
            }
            excommunicationPlayerPlayed = 0;
            excommunicationTurn = false;

            timer.cancel();
            endRound();
            board.getTurn().setRotation(0);
            nextRound();
            //il timer cancel era qui
            int playerIndex = firstPlayerTurn();
            if (playerIndex == -1) return;

        }
    }

    /**
     * method that notify a player that it's his turn. If he's off call nextTurn();
     * @param next player to notify
     */
    private void sendItsMyTurn(PlayerHandler next) {
        if (next.isOn()) {
            timer.cancel();
            next.itsMyTurn();
            timer = this.myTimerSkipTurn(next);
            return;
        }
        else nextTurn(next);
    }

    private boolean excommunicationPlays() {
        List<PlayerHandler> exPlayer = room.getBoard().getTurn().getSkipTurnForExcommunication();

        while ( excommunicationPlayerPlayed < exPlayer.size()) {
            PlayerHandler playerHandler = exPlayer.get(excommunicationPlayerPlayed);
                if (playerHandler.isOn()) {
                    timer.cancel();
                    playerHandler.itsMyTurn();
                    timer = this.myTimerSkipTurn(playerHandler);
                    excommunicationPlayerPlayed++;
                    return true;
                }
            excommunicationPlayerPlayed++;
        }
        return false;
    }



    /**
     * This method finds the first player on in the turn
     * @return the player's index in the turn's list, or "-1" if the match is finished
     */
    int firstPlayerTurn() {
        for(int i = 0; i < board.getTurn().getPlayerTurn().size(); i++){
            PlayerHandler firstPlayer = board.getTurn().getPlayerTurn().get(i);
            if (firstPlayer.isOn()) {
                if (board.getTurn().getSkipTurnForExcommunication().contains(firstPlayer))
                    continue;
                timer = this.myTimerSkipTurn(firstPlayer);
                firstPlayer.itsMyTurn();
                return i;
            }
        }

        closeClientForGeneralDisconnection();

        ArrayList<Room> rooms = room.getServer().getRooms();
        rooms.remove(room);
        room.getServer().setRooms(rooms);
        room = null;

        return -1;
    }

    /**
     * Method called when the turn time is up for all the players or there is al least one client still connected
     * but in the disconnection context for the timer expiration. It call "afterGameIftemporarilyOff" that
     * comunicate to these clients that the match is finished and send them in the "After game context".
     */
    private void closeClientForGeneralDisconnection() {
        for ( PlayerHandler player : room.getListOfPlayers())
            player.afterGameIftemporarilyOff();
    }

    /**
     * it set the board in the local variable
     * @param board board reference
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Comparator for military points
     */
    class MilitaryComparator implements Comparator<PlayerHandler> {

        @Override
        public int compare(PlayerHandler o1, PlayerHandler o2) {
            if (o1.getScore().getMilitaryPoints() > o2.getScore().getMilitaryPoints()) return 1;
            else if (o1.getScore().getMilitaryPoints() == o2.getScore().getMilitaryPoints()) return 0;
            else return -1;
        }
    }

    /**
     * Comparator for victory points
     */
    class WinnerComparator implements Comparator<PlayerHandler> {
        @Override
        public int compare(PlayerHandler o1, PlayerHandler o2) {
            if (o1.getScore().getVictoryPoints() > o2.getScore().getVictoryPoints()) return 1;
            else if (o1.getScore().getVictoryPoints() == o2.getScore().getVictoryPoints()) {
                if (board.getTurn().getPlayerTurn().indexOf(o1) < board.getTurn().getPlayerTurn().indexOf(o2)) return 1;
                else return -1;
            } else return -1;
        }

    }

    /**
     * Get the player's military points reference
     * @param playerHandler playerhandler reference
     * @return military points reference
     */
    private int getMilitaryPoints(PlayerHandler playerHandler) {
        return playerHandler.getScore().getMilitaryPoints();
    }

    /**
     * Method that assign to all players their victory points, finds the winner and comunicates to him the victory.
     * Finally send the players in the after game context and delete the last room.
     */
    private void endMatch() {
        MilitaryComparator comparator = new MilitaryComparator();
        List<PlayerHandler> militaryStandings = room.getListOfPlayers();
        Collections.sort(militaryStandings, comparator);
        Collections.reverse(militaryStandings);

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
            } else if (playerHandler.equals(militaryStandingsRunnerUp)) {
                pointsToAdd += victoryPointsToRunnerUp;
            }

            pointsToAdd += getExtraFinalPoints(playerHandler);
            pointsToAdd += pointsFromResources(playerHandler);

            playerHandler.getScore().setVictoryPoints(playerHandler.getScore().getVictoryPoints() + pointsToAdd);
        }

        PlayerHandler winner = findWinner();
        System.out.println("IL VINCITORE È: " + winner.getName());
        winnerComunication(winner);
        room.addWinnersToTheFile(winner.getName());


        afterGame();

        ArrayList<Room> rooms = room.getServer().getRooms();
        rooms.remove(room);
        room.getServer().setRooms(rooms);
        room = null;

    }

    /**
     * Communicate the victory to the winner
     * @param winner winner playerhandler's reference
     */
    private void winnerComunication(PlayerHandler winner ) {
        for ( PlayerHandler player : room.getListOfPlayers() )
            player.winnerComunication(winner.getName());
    }

    /**
     * Call after game method on the room's reference
     */
    private void afterGame() {
        room.afterGame();
    }


    /**
     * Perform the last prayer: the players that haven't enough faith points take automatically the excommunication, to
     * the others is given the possibility to pray
     *
     * @param playerHandler playerHandler's reference
     */
    private void finalPray(PlayerHandler playerHandler) {
        if (playerHandler.getScore().getFaithPoints() >= board.getFaithPointsRequiredEveryPeriod()[board.getPeriod()])
            pray(playerHandler);
        else {
            takeExcommunication(playerHandler);
        }
    }

    /**
     * This method counts the victory points earned from the resources remained at the end of the match
     *
     * @param playerHandler playerhandler's reference
     * @return victory points earned
     */
    private int pointsFromResources(PlayerHandler playerHandler) {
        int numberOfResources;
        numberOfResources = playerHandler.getPersonalBoardReference().getCoins();
        numberOfResources += playerHandler.getPersonalBoardReference().getServants();
        numberOfResources += playerHandler.getPersonalBoardReference().getStone();
        numberOfResources += playerHandler.getPersonalBoardReference().getWood();
        return (numberOfResources / 5);
    }

    /**
     * This method counts the victory poits earned thanks to the Territory, Character and Venture cards obtained
     * during the match
     *
     * @param playerHandler playerHandler's reference
     * @return points to add
     */
    private int getExtraFinalPoints(PlayerHandler playerHandler) {
        int pointsToAdd = 0;

        pointsToAdd += getSupportFunctions(playerHandler).extraLostOfPoints(playerHandler);
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromCharacterCard(board.getFinalPointsFromCharacterCards());
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromTerritoryCard(board.getFinalPointsFromTerritoryCards());
        getSupportFunctions(playerHandler).finalPointsFromVenturesCard();

        return pointsToAdd;
    }

    /**
     * This method finds the match winner
     *
     * @return Winner playerhandler's reference
     */
    private PlayerHandler findWinner() {
        WinnerComparator comparator = new WinnerComparator();
        List<PlayerHandler> finalStandings = room.getListOfPlayers();
        Collections.sort(finalStandings, comparator);
        Collections.reverse(finalStandings);
        return finalStandings.get(0);

    }

    /**
     * This method change the player turn's order according to the council zone familiar
     */
    private void changePlayerOrder() {
        List<PlayerHandler> newTurnOrder = new ArrayList<>();
        List<Council> councilZone = board.getCouncilZone();
        List<PlayerHandler> oldTurnOrder = board.getTurn().getPlayerTurn();

        for (Council council : councilZone) {
            PlayerHandler player = council.findPlayer(room.getListOfPlayers());
            if (!newTurnOrder.contains(player)) {
                newTurnOrder.add(player);
            }
        }
        for (PlayerHandler player : oldTurnOrder) {
            if (!newTurnOrder.contains(player)) {
                newTurnOrder.add(player);
            }
        }
        board.getTurn().setPlayerTurn(newTurnOrder);
    }


    /**
     * Call nextPeriod on the board
     */
    private void nextPeriod() {
        board.nextPeriod();
    }

    /**
     * Call nextRound on the board
     */
    private void nextRound() {
        board.nextRound();
    }

    /**
     * call the endRound method and askForPraying
     *
     * @param period current period of game
     */
    private void endPeriod(int period) {
        endRound();
        askForPraying(period);
    }

    /**
     * Clean the towers and fill them with the right cards, give the right order to the turn's list, call clean Leader
     * cards and roll the dices.
     */
    private void endRound() {

        refactorTowers();
        changePlayerOrder();
        clearAllPosition();
        clearLeaderCardUsed();
        rollDice();

    }

    /**
     * set "isPlayed" in the leader cards to false
     */
    private void clearLeaderCardUsed() {
        for (PlayerHandler p : room.getListOfPlayers()) {
            for (LeaderCard l : p.getPersonalBoardReference().getMyLeaderCard())
                if (l.isPlayed() && l.isOnePerTurn()) l.setPlayed(false);
        }
    }

    /**
     * Clean all the positions for start correctly a new round
     */
    private void clearAllPosition() {
        List<Harvester> harvesterZone = board.getHarvesterZone();
        List<Production> productionZone = board.getProductionZone();
        List<Council> councilZone = board.getCouncilZone();
        Market[] marketZone = board.getMarketZone();

        for (Harvester harvester : harvesterZone)
            clearSinglePosition(harvester);
        harvesterZone = new ArrayList<>();
        board.setHarvesterZone(harvesterZone);
        broadcastUpdates(new HarvesterUpdate(board.getHarvesterZone(), ""));

        for (Production production : productionZone)
            clearSinglePosition(production);
        productionZone = new ArrayList<>();
        board.setProductionZone(productionZone);
        broadcastUpdates(new ProductionUpdate(board.getProductionZone(), ""));

        for (Market market : marketZone)
            clearSinglePosition(market);
        broadcastUpdates(new MarketUpdate(board, ""));

        for (Council council : councilZone)
            clearSinglePosition(council);
        councilZone = new ArrayList<>();
        board.setCouncilZone(councilZone);
        broadcastUpdates(new CouncilUpdate( board.getCouncilZone(), ""));

        for (PlayerHandler p : room.getListOfPlayers())
            p.sendUpdates(new FamilyMemberUpdate(p, p.getName()));
    }

    /**
     * Clear a single position
     *
     * @param position position reference to clean
     */
    private void clearSinglePosition(Position position) {
        position.setOccupied(false);
        if (position.getFamiliarOnThisPosition() != null) {
            position.getFamiliarOnThisPosition().setPlayed(false);
            position.setFamiliarOnThisPosition(null);
        }
    }

    /**
     * Put new cards in the towers
     */
    private void refactorTowers() {
        System.out.println("sto per rimettere a posto tutto le carte");
        int j;
        int i;
        int currentPeriod = board.getPeriod();
        int currentRound = board.getRound();
        Tower[][] tower = board.getAllTowers();
        DevelopmentCard[][][] deck = board.getDeckCard().getDevelopmentDeck();
        int roundsAdd = 0;

        if (currentRound == 0) roundsAdd = 4;

        else currentPeriod++;

        for (i = 0; i < Constants.NUMBER_OF_TOWERS; i++) {
            for (j = 0; j < Constants.CARD_FOR_EACH_TOWER; j++) {
                //ho fatto il ciclo passando per tutte le torri dal basso all'alto
                clearSinglePosition(tower[i][j]);
                tower[i][j].setCardOnThisFloor(null); //prova per vedere se cosi aggionra
                tower[i][j].setCardOnThisFloor(deck[i][currentPeriod][roundsAdd + j]); //da testare!!!!
            }
        }

        board.setTowers(tower);
        broadcastUpdates(new TowersUpdate(board.getAllTowers(), ""));


    }

    /**
     * Send the praying request to the players that have enough faith points for praying
     *
     * @param period period of game
     */
    private void askForPraying(int period) {
        int faithPointsNeeded = board.getFaithPointsRequiredEveryPeriod()[period];
        List<PlayerHandler> turn = room.getBoard().getTurn().getPlayerTurn();
        for (PlayerHandler player : turn) {
            if (player.isOn() && player.getScore().getFaithPoints() >= faithPointsNeeded) {

                timer = myTimerActions(player);
                int choice = player.sendAskForPraying();

                if (choice == 1 || choice == -1) takeExcommunication(player);
                else pray(player);
                timer.cancel();

            } else {
                takeExcommunication(player);
            }
        }
    }


    /**
     * methods that perform harvester action
     *
     * @param position position in the harvester zone
     * @param familyM familiar played for the harvester
     * @param servantsNumber servants number used for the action
     * @param player playerHandler's reference
     *
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, PlayerHandler player) {
        int malusByField;
        int actionValue;
        int cardBonus = player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus();
        List<Harvester> harvesterList = board.getHarvesterZone();
        Harvester harvesterZone = new Harvester();


        getSupportFunctions(player).setFamiliar(harvesterZone, familyM);
        if (position == 0) malusByField = 0;
        else malusByField = 3;
        actionValue = familyM.getMyValue() + servantsNumber - malusByField + cardBonus;
        harvesterList.add(harvesterZone);

        harvesterBonus(actionValue, servantsNumber, player);

        player.sendActionOk();
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        HarvesterUpdate harvesterUpdate = new HarvesterUpdate(board.getHarvesterZone(), player.getName());
        broadcastUpdates(harvesterUpdate);
    }


    /**
     * This method perform a bonus harvester action.
     *
     * @param harvesterValue starting dice value of the action
     * @param intServantsNumber number of servants used by the player for the action
     * @param player playerHandler's reference
     */
    public void harvesterBonus(int harvesterValue, int intServantsNumber, PlayerHandler player) {
        int servantsUsed = getSupportFunctions(player).payServants(intServantsNumber, 0);
        player.getPersonalBoardReference().addServants( - servantsUsed);

        for (Effects e : player.getPersonalBoardReference().getMyTile().takeHarvesterResource()) {
            e.doEffect(player);
        }

        for (TerritoryCard card : player.getPersonalBoardReference().getTerritories()) {
            if (harvesterValue >= card.getCost().getDiceCost())
                makePermanentEffects(player, card);
        }

        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
    }


    /**
     * Perform a production action.
     *
     * @param familyM familiar used for the action
     * @param cardToProduct list of building cards on which perform the production
     * @param servantsToPay list of building cards on which perform the production
     * @param choichePE boolean that says if the building card has two permanent effects alternatively
     * @param player playerHandler's reference
     */
    public void production( FamilyMember familyM, List<BuildingCard> cardToProduct, int servantsToPay, List<Integer> choichePE, PlayerHandler player) {
        List<Production> productionSpace = board.getProductionZone();
        Production productionZone = new Production();

        getSupportFunctions(player).setFamiliar(productionZone, familyM);
        productionSpace.add(productionZone);

        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));

        productionBonus(cardToProduct, servantsToPay, choichePE, player);

    }


    /**
     * This method perform a bonus production action.
     *
     * @param cards list of building cards on which perform the production
     * @param servantsToPay list of building cards on which perform the production
     * @param choichePE boolean that says if the building card has two permanent effects alternatively
     * @param player playerHandler's reference
     */
    public void productionBonus(List<BuildingCard> cards, int servantsToPay, List<Integer> choichePE, PlayerHandler player) {

        int servantsUsed = getSupportFunctions(player).payServants(servantsToPay, 0);
        player.getPersonalBoardReference().addServants(- servantsUsed);

        for (Effects e : player.getPersonalBoardReference().getMyTile().takeProductionResource())
            e.doEffect(player);

        for (BuildingCard card : cards) {
            makePermanentEffectsProduction(player, card, choichePE);
        }

        player.sendActionOk();
        System.out.println("PASSATO ACTION OK");
        ProductionUpdate productionUpdate = new ProductionUpdate( board.getProductionZone(), player.getName());
        broadcastUpdates(productionUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));

    }


    /**
     * This method place a familiar in a market position and perform the corresponding method
     *
     * @param position position in the market on which places the familiar
     * @param familyM familiar used for performing the action
     */
    public void goToMarket(int position, FamilyMember familyM, PlayerHandler player) {
        Position marketPosition = board.getMarketZone()[position];

        getSupportFunctions(player).setFamiliar(marketPosition, familyM);

        if (familyM.getMyValue() < 1) {
            int servantsUsed = getSupportFunctions(player).payServants(1, 0);
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
        }

        MarketUpdate marketUpdate = new MarketUpdate(board, player.getName());

        Effects e = board.getMarketZone()[position].getEffect();
        BonusInteraction returnFromEffect = e.doEffect(player);

        if (returnFromEffect instanceof TakePrivilegesAction)
            player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);

        player.sendActionOk();
        broadcastUpdates(marketUpdate);
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }


    /**
     * Play a leader card and perform his effect.
     *
     * @param leaderName leader card name
     * @param player playerhandler's reference
     */

    public void playLeaderCard(String leaderName, PlayerHandler player) throws CantDoActionException {
        LeaderCard leaderToPlay = null;
        for (LeaderCard leaderCard : player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                leaderToPlay = leaderCard;
                leaderToPlay.setPlayed(true);
                break;
            }

        }

        BonusInteraction returnFromEffect = leaderCardEffect.doEffect(leaderName, player);

        if (returnFromEffect instanceof BonusProductionOrHarvesterAction)
            player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
        else if (returnFromEffect instanceof TakePrivilegesAction)
            player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);


        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }

    /**
     * Discard a leader card and take a bonus privilege.
     *
     * @param leaderName leader card name to discard
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
     * Roll dices.
     */
    public void rollDice() {
        int[] newDiceValue = new int[3];
        Random r = new Random();
        newDiceValue[0] = r.nextInt(6) + 1;
        newDiceValue[1] = r.nextInt(6) + 1;
        newDiceValue[2] = r.nextInt(6) + 1;

        board.setDiceValue(newDiceValue);

        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            getSupportFunctions(player).setDicesValue(newDiceValue, player);
        }

        broadcastUpdates(new DiceValueUpdate(board.getDiceValue(),board.getTurn()));
    }

    /**
     * This method place a familiar in the council palace and perform the effect corresponding with the provilege number
     * choosen.
     *
     * @param privilegeNumber privilege number choosen
     * @param familyMember familiar that the client wants to place
     * @param player palyerHandler's reference
     */
    public void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler player) {
        List<Council> councilZone = board.getCouncilZone();

        getSupportFunctions(player).setFamiliarInTheCouncilPalace(councilZone, familyMember);

        if (familyMember.getMyValue() < 1) {
            int servantsUsed = getSupportFunctions(player).payServants(1, 0);
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
        }

        Effects e = new AddCoin(1);
        e.doEffect(player);
        takeCouncilPrivilege(privilegeNumber, player);

        player.sendActionOk();
        ArrayList<Council> council = (ArrayList<Council>) board.getCouncilZone();
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        broadcastUpdates(new CouncilUpdate(council, player.getName()));
    }

    /**
     * Perform the effect corresponding to the privilege number choosen by the client
     *
     * @param privilegeNumber privilege number choosen
     * @param player palyerHandler's reference
     */
    public void takeCouncilPrivilege(int privilegeNumber, PlayerHandler player) {
        CouncilPrivilege privilege = board.getCouncilPrivileges()[privilegeNumber];

        Effects e = privilege.getEffect();
        e.doEffect(player);

        if (privilegeNumber > 0 && privilegeNumber < 2) {
            player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        } else {
            player.sendUpdates(new ScoreUpdate(player, player.getName()));
        }
    }

    /**
     * This method adds the correct amount of victory points according to the amount of faith point owned in
     * the moment of the prayer.
     *
     * @param player playerHandler's reference
     */
    public void pray(PlayerHandler player) {
        int victoryPointsToAdd = board.getVictoryPointsInFaithTrack()[player.getScore().getFaithPoints()];

        getSupportFunctions(player).pray(victoryPointsToAdd);
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
    }

    /**
     * this method perform the malus due to the excommunication effect.
     *
     * @param player playerHandler's reference
     */
    public void takeExcommunication(PlayerHandler player) {
        int period = board.getPeriod();
        ExcommunicationTile exTile = board.getExcommunicationZone()[period].getCardForThisPeriod();

        exTile.makeEffect(player);
        broadcastUpdates(new ExcommunicationTaken(player, exTile.getEffectDescription()));
        broadcastUpdates(new ExcomunicationUpdate(board.getExcommunicationZone(), player.getName()));

    }

    /**
     * This method sends an update in broadcast to all players in the match
     *
     * @param updates update's reference
     */
    private void broadcastUpdates(Updates updates) {
        for (PlayerHandler player : room.getListOfPlayers()) {
            player.sendUpdates(updates);
        }
    }


    /**
     * This method perform the building card' permanent effects during the production action
     *
     * @param player playerHandler's reference
     * @param card building card's reference
     * @param choicePE boolean that says is there are two effects that could be activated alternatively
     */
    private void makePermanentEffectsProduction(PlayerHandler player, BuildingCard card, List<Integer> choicePE) {
        if (card.isChoicePe()) {
            int choice = choicePE.get(0);
            if (choice == -1)
                return;

            Effects e = card.getPermanentCardEffects().get(choice);
            BonusInteraction returnFromEffect = e.doEffect(player);
            if (returnFromEffect instanceof TakePrivilegesAction) {
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
            }

            choicePE.remove(0);
            return;
        }

        for (Effects effect : card.getPermanentCardEffects()) {
            BonusInteraction returnFromEffect = effect.doEffect(player);

            if (returnFromEffect instanceof TakePrivilegesAction) {
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
            }

            System.out.println(" effetto permanente stampato " + effect.getClass());
        }

    }

    /**
     * This method perform the territory card's permanent effect during the harvester action
     *
     * @param player playerHandler's reference
     * @param card card's reference
     */
    private void makePermanentEffects(PlayerHandler player, DevelopmentCard card) {

        for (Effects effect : card.getPermanentCardEffects()) {
            BonusInteraction returnFromEffect = effect.doEffect(player);
            if (returnFromEffect instanceof TakePrivilegesAction ){
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
            }
        }
    }

    /**
     * Timer that scan the turns
     *
     * @param player playerHandler's reference
     * @return timer's reference
     */
    private Timer myTimerSkipTurn(PlayerHandler player) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                player.setOn(false);
                player.timerTurnDelayed();

                nextTurn(player);
            }
        };

        Timer timer = new Timer(room.timerSettings.getSkipTurnTimerName());
        timer.schedule(timerTask, room.timerSettings.getDelayTimerSkipTurn());
        return timer;
    }

    /**
     * Timer that scan actions that does not entail that the turn is skipped on
     *
     * @param player
     * @return
     */
    Timer myTimerActions(PlayerHandler player) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TIMER PREGHIERA");
                System.out.println(player.getName());
                player.setOn(false);
                player.timerTurnDelayed();
            }
        };


        Timer timer = new Timer(room.timerSettings.getDelayTimerPrayingName());
        timer.schedule(timerTask, room.timerSettings.getDelayTimerPraying());
        return timer;
    }

}
