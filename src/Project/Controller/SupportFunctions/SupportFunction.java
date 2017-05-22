package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.SupportFunctions.AllSupportFunction;
import Project.MODEL.*;

import java.util.*;

/**
 * 
 */
public class SupportFunction implements AllSupportFunction {

    /**
     * Default constructor
     */
    public SupportFunction() {
    }

    /**
     * @param position 
     * @param zone 
     * @return
     */
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!zone[position].isOccupied()){
            zone[position].setOccupied(true);
            zone[position].setFamiliarOnThisPosition(familyMember);
            return true;
        }
        else
            return false;
    }

    public boolean CheckCardCostTerritories (TerritoryCard card, Player player){
        if (card.getCardCost().getStoneRequired() <= player.getPersonalBoardReference().getStone() &&
                card.getCardCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCardCost().getDiceCost() <= // TODO guardare coe vengono passati i dice value
        )
            return true;
        else
            throw NotEnaughResource();
        return false;
    }

    public boolean CheckCardCostCharacters (CharacterCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCardCostBuildings (BuildingCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCardCostVentures (VenturesCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCapabilityToTakeTerritory (Player player){
        int length = player.getPersonalBoardReference().getTerritories().size();
        if (player.getScore().getMilitaryPoints() >= //TODO quanti punti militari ci vogliono per carte verdi)
                )
            return true;
        else
            return false;
    }

    public boolean CheckTowerOccupiedByYou (Tower[] tower, Player player){
        for (Tower t: tower){
            if (t.getFamiliarOnThisPosition().getFamilyColour() == player.getFamilyColour())
                return true;
        }
        return false;
    }

    public void ApplyEffects (Card card, Player player){
        card.MakeImmediateEffects(player);
    }
    /**
     * @param position 
     * @param familiar 
     * @return
     */
    public boolean Check_Dice(Position position, FamilyMember familiar) {
        // TODO implement here
        return false;
    }

    /**
     * @param player 
     * @param resource 
     * @param quantity 
     * @return
     */
    public void Update_Resource(Player player, int resource, int quantity) {
        // TODO implement here
    }

    /**
     * @param player 
     * @param point_type 
     * @param quantity 
     * @return
     */
    public void Update_Points(Player player, int point_type, int quantity) {
        // TODO implement here
    }

    /**
     * @param player 
     * @param PrivilegioBannato 
     * @return
     */
    public int  Add_Privilege(Player player, int[] PrivilegioBannato) {
        // TODO implement here
        return 0 ;
    }

    /**
     * @param player 
     * @param CardList 
     * @return
     */
    public boolean Check_Upgrade_On_Production(Player player, ArrayList<Card> CardList) {
        // TODO implement here
        return false;
    }

    public int Pray(Player player){
        // TODO bisogna importare da file quanti punti vittoria in ogni posto fede
        return  0;
    }

}