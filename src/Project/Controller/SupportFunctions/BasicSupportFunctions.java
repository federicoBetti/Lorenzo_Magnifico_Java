package Project.Controller.SupportFunctions;

import Project.MODEL.*;
import Project.toDelete.BonusInteraction;

import java.util.HashMap;

/**
 * attenzione forse devo mettere nell'interfaccia tutte le funzioni non solo quelle da decorare
 */
public class BasicSupportFunctions implements AllSupportFunctions {

    /**
     * Default constructor
     */


    public BasicSupportFunctions() {
    }


    public BonusInteraction ApplyEffects (DevelopmentCard card, Player player){
        return card.makeImmediateEffects(player);
    }

    @Override
    public void setFamiliar(Position zone, FamilyMember familyMember) {
        zone.setFamiliarOnThisPosition(familyMember);
        zone.setOccupied(true);
        return;
    }

    @Override
    public void placeCardInPersonalBoard(DevelopmentCard card, Player player) {
        card.addToPersonalBoard(player.getPersonalBoardReference());
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


    public int Pray(Player player){
        // TODO bisogna importare da file quanti punti vittoria in ogni posto fede
        return  0;
    }

}