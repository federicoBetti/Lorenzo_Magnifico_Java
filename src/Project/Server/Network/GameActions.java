package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.SupportFunctions.AllSupportFunctions;
import Project.Iterator;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.MODEL.Position;
import Project.MODEL.Tower;
import Project.Server.Room;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;

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
        player.sendReturn(returnFromEffect);
    };

    /**
     * @param position
     * @param familyM
     * @param servantsNumber
     *@param playerHandler @return
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, Player player){
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
     * @param position
     * @param familyM
     * @param cardToProduct
     *@param playerHandler @return
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
    public void goToMarket(int position, FamilyMember familyM, Player player){
    };

    /**
     * @return
     */
    public void jumpTurn(){

    };

    /**
     * @param leaderName
     * @return
     */
    public void playLeaderCard(String leaderName, Player player ){};

    /**
     * @param leaderName
     * @return
     */
    public void discardLeaderCard(String leaderName, Player player){

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
    };

    /**
     * @param privelgeNumber
     * @return
     */
    public void goToCouncilPalace(int privelgeNumber){

    };

    /**
     * @return
     */
    public void goToCouncilPalace(){

    };

}
