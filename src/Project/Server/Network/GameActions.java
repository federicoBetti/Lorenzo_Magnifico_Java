package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.LeaderCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.Constants;
import Project.Controller.Effects.RealEffects.AddCoin;
import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.FakeFamiliar;
import Project.Controller.SupportFunctions.AllSupportFunctions;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.MODEL.Tower;
import Project.Server.Room;
import Project.toDelete.BonusInteraction;
import Project.toDelete.Notify;
import Project.toDelete.OkOrNo;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 22/05/17.
 */
public class GameActions {

    Room room;
    /**
     * TODO ad ogni metodo viene passato il player ( socket o RMI ) e alla fine deve chiamare un metodo
     * TODO di ritorno, risposta al Client sul relativo Player. I metodi chiamano delle suport function
     * TODO dedicate a ciascun player che possono essere personalizzate durante il corso della partita
     */
    private AllSupportFunctions getRightSupportFunctions (Player player){
        return room.getMySupportFunction(player);
    }

    public void takeDevelopementCard(Tower zone, FamilyMember familyM, PlayerHandler player){
        //deve mettere la pedina nel posto giusto, mettere la carta nella board giocatore, prendere effetti imeddiati.
        // in base alla bonus interaction che ritronano gli effetti manderà una rispsota
        getRightSupportFunctions(player).setFamiliar(zone, familyM);
        getRightSupportFunctions(player).placeCardInPersonalBoard(zone.getCardOnThisFloor(), player);
        BonusInteraction returnFromEffect = getRightSupportFunctions(player).ApplyEffects(zone.getCardOnThisFloor(),player);
        player.sendAnswer(returnFromEffect);
        if (returnFromEffect instanceof OkOrNo){
            player.sendAnswer(new Notify("i have finished my turn"));
            nextTurn(player);
        }

    }

    /**
     *
     * @param player
     */
    private void nextTurn(PlayerHandler playerHandler) {
        PlayerHandler next;
        int playerNumber = room.getRoomPlayers().size();
        int indexOfMe = room.getBoard().getTurnOrder().indexOf(playerHandler);
        int currentPeriod = room.getBoard().getPeriod();
        int currentRound = room.getBoard().getRound();
        if (indexOfMe != playerNumber){ //caso in mezzo al round
            next = (PlayerHandler) room.getBoard().getTurnOrder().get(indexOfMe++);
            broadcastNotifications(new Notify("it's " + next.getName() + " turn"));
            next.itsMyTurn();
        }
        //ora siamo nel caso in cui è finito un round o un periodo
        else if (currentRound == 2 && currentPeriod == 3){ //
            endMatch(); //TODO
        }
        else if (currentRound == 2){
            endPeriod();
            setEndTurn(true);
        }
        else {
            endRound();
            setEndTurn(true);
        }
    }

    private void endPeriod() {
        changeCardInTowers();
        askForPraying();
    }

    private void askForPraying() {
        for (PlayerHandler p: room.getRoomPlayers()){
            p.sendAskForPraying( );
        }
    }

    private void setEndTurn(boolean choice) {
        room.getBoard().setEndRound(choice);
    }

    private PlayerHandler nextPlayerToPlay(PlayerHandler playerHandler){
        int indexOfMe = room.getBoard().getTurnOrder().indexOf(playerHandler);
        int indexOfNext = room.getRoomPlayers().size() % indexOfMe;
        return (PlayerHandler) room.getBoard().getTurnOrder().get(indexOfNext);
    }

    private void endRound(){
        changeCardInTowers();
    }

    private void changeCardInTowers() {
        int i = 0;
        int j = 0;
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
        player.getPersonalBoardReference().getMyTile().TakeHarvesterResource();
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
    public void production(int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct, Player player){
        getRightSupportFunctions(player).setFamiliar(room.getBoard().getTrueArrayList("production")[position], familyM);
        player.getPersonalBoardReference().getMyTile().TakeProductionResource();
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
     * questo metodo deve rappresnetare la fine del turno, deve usare un metodo che mi dici chi c'è dopo e notiicare al player giusto che è il suo
     * @return
     */
    public void endTurn(){

    };

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
        for (Player p: room.getRoomPlayers()){
            getRightSupportFunctions(p).setDicesValue(newDiceValue,p);
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
        for (PlayerHandler p: room.getRoomPlayers()){
            p.sendAnswer( notifications);
        }
    }

}
