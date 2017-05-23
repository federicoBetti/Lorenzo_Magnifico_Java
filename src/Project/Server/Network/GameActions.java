package Project.Server.Network;

import Project.Controller.CardsFactory.BuildingCard;
import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
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

    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM, Player player){

        return null;
    };

    /**
     * @param position
     * @param familyM
     * @param servantsNumber
     *@param playerHandler @return
     */
    public void Harvester(int position, FamilyMember familyM, int servantsNumber, Player playerHandler){

    };

    /**
     * @param position
     * @param familyM
     * @param cardToProduct
     *@param playerHandler @return
     */
    public void Production(int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct, PlayerHandler playerHandler){

    };


    /**
     * @param position
     * @param familyM
     * @return
     */
    public void GoTOMarket(int position, FamilyMember familyM){
    };

    /**
     * @return
     */
    public void JumpTurn(){

    };

    /**
     * @param leaderName
     * @return
     */
    public void PlayLeaderCard(String leaderName ){};

    /**
     * @param leaderName
     * @return
     */
    public void DiscardLeaderCard(String leaderName){

    };

    /**
     * @return
     */
    public void RollDice(){

    };

    /**
     * @param privelgeNumber
     * @return
     */
    public void GoToCouncilPalace(int privelgeNumber){

    };

    /**
     * @return
     */
    public void GoToCouncilPalace(){

    };

}
