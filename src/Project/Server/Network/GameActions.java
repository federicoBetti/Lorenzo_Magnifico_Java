package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.MODEL.Position;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;

/**
 * Created by raffaelebongo on 22/05/17.
 */
public class GameActions {

    /**
     * TODO ad ogni metodo viene passato il player ( socket o RMI ) e alla fine deve chiamare un metodo
     * TODO di ritorno, risposta al Client sul relativo Player. I metodi chiamano delle suport function
     * TODO dedicate a ciascun player che possono essere personalizzate durante il corso della partita
     */

    public BonusInteraction takeDevelopementCard(Position zone, FamilyMember familyM, Player player){

        return null;
    };

    /**
     * @param position
     * @param familyM
     * @param servantsNumber
     *@param playerHandler @return
     */
    public void harvester(int position, FamilyMember familyM, int servantsNumber, Player player){

    };

    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     *@param playerHandler @return
     */
    public void production(int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct, Player player){

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
