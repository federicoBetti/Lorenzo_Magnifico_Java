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
import project.server.network.PlayerHandler;

import java.util.*;

/**
 * main game actions
 */
public class GameActions {

    private Room room;
    private Board board;
    private Timer timer;
    private LeaderCardsEffects leaderCardEffect;

    public GameActions( Room room ){
        this.room = room;
        leaderCardEffect = new LeaderCardsEffects();
        timer = new Timer();
    }

    private AllSupportFunctions getSupportFunctions(Player player) {
        return room.getMySupportFunction(player);
    }

    private void takeDevelopmentCard(Tower zone, PlayerHandler player) {
        DevelopmentCard card = zone.getCardOnThisFloor();

        getSupportFunctions(player).placeCardInPersonalBoard(card);
        getSupportFunctions(player).towerZoneEffect(zone,player);
        zone.getTowerZoneEffect().doEffect(player);

        makeImmediateEffects(player, zone.getCardOnThisFloor());

        zone.setCardOnThisFloor(null);

        TowersUpdate towersUpdate = new TowersUpdate(board.getAllTowersUpdate(), player.getName());
        broadcastUpdates(towersUpdate);
        player.sendUpdates(new PersonalBoardUpdate(player,player.getName()));
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        player.sendActionOk();
        System.out.println("MANDATI TUTTTI GLI UPDATE");
    }

    public void takeNoVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();
        System.out.println(familyM);

        //devo far pagare i servants!
        System.out.println("ho numero di servants: " + player.getPersonalBoardReference().getServants());
        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);
        getSupportFunctions(player).setFamiliar(zone, familyM);
        System.out.println("ho numero di servants: " + player.getPersonalBoardReference().getServants());
        //prova
        System.out.println(zone.getFamiliarOnThisPosition());


        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        takeDevelopmentCard(zone, player);
    }

    public void takeNoVenturesCard(Tower zone, PlayerHandler player, boolean towerIsOccupied, int diceFamiliarValue) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();

        getSupportFunctions(player).payCard(card, towerIsOccupied, diceCostValue, diceFamiliarValue);

        System.out.println("faccio takedevCard con zone: "+zone  + "e player: " + player);
        takeDevelopmentCard(zone, player);
    }


    public void takeVenturesCard(Tower zone, FamilyMember familyM, PlayerHandler player, boolean towerIsOccupied, int numberOfPayment) {
        DevelopmentCard card = zone.getCardOnThisFloor();
        int diceCostValue = zone.getDiceValueOfThisFloor();
        int diceFamiliarValue = familyM.getMyValue();

        getSupportFunctions(player).payVenturesCard((VenturesCard) card, player, towerIsOccupied, diceCostValue, diceFamiliarValue, numberOfPayment);
        getSupportFunctions(player).setFamiliar(zone, familyM);

        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
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
        List<PlayerHandler> turn = board.getTurn().getPlayerTurn();

        int indexOfMe = turn.indexOf(playerHandler);
        int playerNumbers = room.getRoomPlayers();
        int currentPeriod = board.getPeriod();
        int currentRound = board.getRound();

        if (indexOfMe < playerNumbers - 1) { //non sono l'ultimo del turno
             next = turn.get(indexOfMe + 1);
            System.out.println(next);
             if ( next.isOn() ) {
                 timer.cancel();
                 next.itsMyTurn();
                 timer = this.myTimerSkipTurn(turn.get(indexOfMe + 1));
                 return;
             }
             nextTurn(next);

        } else if ( board.getTurn().getRotation() < 3) {// sono 'ultimo del turno ma non è finito round (è giusto che sia 3 il numero perchè si incrementa dopo)
            System.out.println("sono l'ultimo del turno, metto la rotazione a "  + board.getTurn().getRotation());
            board.getTurn().setRotation(board.getTurn().getRotation() + 1);
            next = turn.get(0);
             if (next.isOn()) {
                 timer.cancel();
                 next.itsMyTurn();
                 timer = this.myTimerSkipTurn(turn.get(0));
                 return;
             }
             nextTurn(next);
         }

         else if (currentRound == 1 && currentPeriod == 2) { //fine partita
            endMatch();

        } else if (currentRound == 1) {//fine periodo
            System.out.println("fine periodo!" + currentPeriod);
            endPeriod(currentPeriod);
            nextRound();
            nextPeriod();
            board.getTurn().setRotation(0);
            setEndRound(true);
            timer.cancel();
            firstPlayerTurn();
            timer = this.myTimerSkipTurn(turn.get(0));
            return;

        } else {
            System.out.println("fine round!" + currentRound);
            endRound();
            board.getTurn().setRotation(0);
            nextRound();
            setEndRound(true);
            timer.cancel();
            firstPlayerTurn();
            timer = this.myTimerSkipTurn(turn.get(0));
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
        PlayerHandler firstPlayer = board.getTurn().getPlayerTurn().get(0);
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

    public void firstTurn() {
        board.getTurn().getPlayerTurn().get(0).itsMyTurn();
        timer = myTimerSkipTurn(board.getTurn().getPlayerTurn().get(0));
    }

    public void setBoard(Board board) {
        this.board = board;
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
        System.out.println("IL VINCITORE È: " + winner.getName());
        //winner.YOUWIN();
      //todo  broadcastNotifications(new Notify("the winner is + " + winner.getName()));

    }

    private void finalPray(PlayerHandler playerHandler) {
        if (playerHandler.getScore().getFaithPoints() >= board.getFaithPointsRequiredEveryPeriod()[board.getPeriod()])
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
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromCharacterCard(board.getFinalPointsFromCharacterCards());
        pointsToAdd += getSupportFunctions(playerHandler).finalPointsFromTerritoryCard(board.getFinalPointsFromTerritoryCards());
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
        List<Council> councilZone = board.getCouncilZone();
        List<PlayerHandler> oldTurnOrder = board.getTurn().getPlayerTurn();

        for (Council council : councilZone) {
            if (!newTurnOrder.contains(council.findPlayer(board, council.getFamiliarOnThisPosition().getFamilyColour()))) {
                newTurnOrder.add((PlayerHandler) council.findPlayer(board, council.getFamiliarOnThisPosition().getFamilyColour()));
            }
        }
        for (PlayerHandler player : oldTurnOrder) {
            if (!newTurnOrder.contains(player)) {
                newTurnOrder.add(player);
            }
        }
        board.getTurn().setPlayerTurn(newTurnOrder);
    }


    private void nextPeriod() {
        board.nextPeriod();
    }

    private void nextRound() {
        board.nextRound();
    }

    private void endPeriod(int period) {
        endRound();
        askForPraying(period);
    }

    private void endRound() {//cambiare le carte, pulire spazi azione, settare i familiari a false
        refactorTowers();
        changePlayerOrder();
        clearAllPosition();
        clearLeaderCardUsed();

        //to check

        for (PlayerHandler p: room.getListOfPlayers()){
            for (FamilyMember f: p.getAllFamilyMembers())
                if(f.isPlayed())
                    System.out.println("giocatore ancora giocato: " + f);
        }
    }


    private void clearLeaderCardUsed() {
        for (PlayerHandler p: room.getListOfPlayers()){
            for (LeaderCard l: p.getPersonalBoardReference().getMyLeaderCard())
                if (l.isPlayed() && l.isOnePerTurn())
                    l.setPlayed(false);
        }
    }

    private void clearAllPosition() {
        List<Harvester> harvesterZone = board.getHarvesterZone();
        List<Production> productionZone = board.getProductionZone();
        List<Council> councilZone = board.getCouncilZone();
        Market[] marketZone = board.getMarketZone();

        for (Harvester harvester : harvesterZone)
            clearSinglePosition(harvester);
        broadcastUpdates(new HarvesterUpdate(board.getHarvesterZone(),""));

        for (Production production : productionZone)
            clearSinglePosition(production);
        broadcastUpdates(new ProductionUpdate(board.getProductionZone(),""));

        for (Market market : marketZone)
            clearSinglePosition(market);
        broadcastUpdates(new MarketUpdate(board,""));

        for (Council council : councilZone)
            clearSinglePosition(council);
        broadcastUpdates(new CouncilUpdate((ArrayList<Council>) board.getCouncilZone(),""));

        for (PlayerHandler p: room.getListOfPlayers())
            p.sendUpdates(new FamilyMemberUpdate(p,p.getName()));
    }

    private void clearSinglePosition(Position position) {
        position.setOccupied(false);
            if (position.getFamiliarOnThisPosition() != null) {
                position.getFamiliarOnThisPosition().setPlayed(false);
                position.setFamiliarOnThisPosition(null);
            }
    }

    void refactorTowers() {
        System.out.println("sto per rimettere a posto tutto le carte");
        int j;
        int i;
        int currentPeriod = board.getPeriod();
        int currentRound = board.getRound();
        Tower[][] tower = board.getAllTowers();
        DevelopmentCard[][][] deck = board.getDeckCard().getDevelopmentDeck();
        int roundsAdd = 0;

        if (currentRound == 0)
            roundsAdd = 4;

        else
            currentPeriod++;

        //si potrebbe fare con iteratore..
        for (i = 0; i < Constants.NUMBER_OF_TOWERS; i++) {
            for (j = 0; j < Constants.CARD_FOR_EACH_TOWER; j++) {
                //ho fatto il ciclo passando per tutte le torri dal basso all'alto
                clearSinglePosition(tower[i][j]);
                tower[i][j].setCardOnThisFloor(null); //prova per vedere se cosi aggionra
                tower[i][j].setCardOnThisFloor(deck[i][currentPeriod][roundsAdd + j]); //da testare!!!!
            }
        }

        board.setTowers(tower);
        broadcastUpdates(new TowersUpdate(board.getAllTowers(),""));


    }

    private void setEndRound(boolean choice) {
        board.setEndRound(choice);
    }

    private void askForPraying(int period) {
        int faithPointsNeeded = board.getFaithPointsRequiredEveryPeriod()[period];

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
     * methods that perform harvester action
     * @param position
     * @param familyM
     * @param servantsNumber
     * @param player
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

        harvesterBonus(actionValue,servantsNumber,player);

        player.sendActionOk();
        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        HarvesterUpdate harvesterUpdate = new HarvesterUpdate((ArrayList<Harvester>) board.getHarvesterZone(), player.getName());
        broadcastUpdates(harvesterUpdate);
    }


    public void harvesterBonus(int harvesterValue, int intServantsNumber, PlayerHandler player) {
        int servantsUsed = getSupportFunctions(player).payServants(intServantsNumber,0);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);

        for (Effects e :player.getPersonalBoardReference().getMyTile().takeHarvesterResource())
            e.doEffect(player);

        for (TerritoryCard card : player.getPersonalBoardReference().getTerritories()) {
            if (harvesterValue >= card.getCost().getDiceCost())
                makePermanentEffects(player, card);
        }

        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
    }



    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     * @param servantsToPay
     * @param player
     */
    public void production(int position, FamilyMember familyM, List<BuildingCard> cardToProduct, int servantsToPay, PlayerHandler player) {
        List<Production> productionSpace = board.getProductionZone();
        Production productionZone = new Production();

        getSupportFunctions(player).setFamiliar(productionZone, familyM);
        productionSpace.add(productionZone);

        int servantsUsed = getSupportFunctions(player).payServants(servantsToPay,0);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);

        player.sendUpdates(new FamilyMemberUpdate(player, player.getName()));
        ProductionUpdate productionUpdate = new ProductionUpdate((ArrayList<Production>) board.getProductionZone(), player.getName());
        broadcastUpdates(productionUpdate);

        productionBonus(cardToProduct, servantsToPay, player);

    }


    public void productionBonus(List<BuildingCard> cards, int servantsToPay, PlayerHandler player) {

        int servantsUsed = getSupportFunctions(player).payServants(servantsToPay,0);
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);

        for (Effects e: player.getPersonalBoardReference().getMyTile().takeProductionResource())
            e.doEffect(player);

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
        Position marketPosition = board.getMarketZone()[position];

        getSupportFunctions(player).setFamiliar(marketPosition, familyM);

        System.out.println("il mio familiare ha valore " + familyM.getMyValue());
        if (!(familyM.getMyValue()>0)) {
            int servantsUsed = getSupportFunctions(player).payServants(1, 0);
            player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsUsed);
        }

        MarketUpdate marketUpdate = new MarketUpdate(board, player.getName());

        Effects e = board.getMarketZone()[position].getEffect();
        e.doEffect(player);
        System.out.println("effect class: " + e.getClass());
        player.sendActionOk();
        broadcastUpdates(marketUpdate);
        player.sendUpdates(new FamilyMemberUpdate(player,player.getName()));
        player.sendUpdates(new PersonalBoardUpdate(player, player.getName()));
        System.out.println("Update mandato a : " + player.getName());
    }


    /**
     * @param leaderName
     * @param player
     */
    //todo modify
    public void playLeaderCard(String leaderName, PlayerHandler player) {
        for (LeaderCard leaderCard : player.getPersonalBoardReference().getMyLeaderCard()) {
            if (leaderCard.getName().equals(leaderName)) {
                BonusInteraction returnFromEffect = leaderCardEffect.doEffect(leaderName,player);
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
        newDiceValue[0] = r.nextInt(6) + 1;
        newDiceValue[1] = r.nextInt(6) + 1;
        newDiceValue[2] = r.nextInt(6) + 1;

        board.setDiceValue(newDiceValue);

        for (Map.Entry<String, PlayerHandler> entry : room.nicknamePlayersMap.entrySet()) {
            PlayerHandler player = entry.getValue();
            getSupportFunctions(player).setDicesValue(newDiceValue, player);
        }
        setEndRound(false);

        broadcastUpdates(new DiceValueUpdate(board.getDiceValue()));
    }

    /**
     * @param privilegeNumber
     * @param familyMember
     * @param player          @return
     */
    public void goToCouncilPalace(int privilegeNumber, FamilyMember familyMember, PlayerHandler player) {
        List<Council> councilZone = board.getCouncilZone();

        getSupportFunctions(player).setFamiliarInTheCouncilPalace(councilZone, familyMember);

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
     * @param privilegeNumber
     * @param player
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

    public void pray(PlayerHandler player) {
        int victoryPointsToAdd = board.getVictoryPointsInFaithTrack()[player.getScore().getFaithPoints()];

        getSupportFunctions(player).pray(victoryPointsToAdd);
        player.sendUpdates(new ScoreUpdate(player, player.getName()));
    }

    public void takeExcommunication(PlayerHandler player) {
        int period = board.getPeriod();
        ExcommunicationTile card = board.getExcommunicationZone()[period].getCardForThisPeriod();

        card.makeEffect(player);

        broadcastUpdates(new ExcomunicationUpdate( board.getExcommunicationZone(), player.getName()));
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
            System.out.println("stampo la return from effect: " + returnFromEffect);

            if (returnFromEffect instanceof TowerAction) {
                System.out.println("if towerAction");
                player.sendBonusTowerAction((TowerAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);

            } else if (returnFromEffect instanceof BonusProductionOrHarvesterAction) {
                System.out.println("if BonusInteractionHarv");
                player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);

            } else if (returnFromEffect instanceof TakePrivilegesAction) {
                System.out.println("if TakePrivilege");
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);
            }
        }
    }

    private void makePermanentEffects(PlayerHandler player, DevelopmentCard card) {

        for (Effects effect : card.getPermanentCardEffects()) {
            if (card.isChoicePe()) {
                int choice = player.sendPossibleChoice(Constants.CHOICE_PE);
                card.getPermanentCardEffects().get(choice).doEffect(player);
            } else {
                effect.doEffect(player);
                System.out.println(" effetto permanente stampato " + effect.getClass());
            }
        }

    }

    private Timer myTimerSkipTurn(PlayerHandler player) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                player.timerTurnDelayed();
                nextTurn( player );
            }
        };

        Timer timer = new Timer(room.timerSettings.getSkipTurnTimerName());
        timer.schedule(timerTask, room.timerSettings.getDelayTimerSkipTurn());
        return timer;
    }
}
