package project.controller.checkfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

/**
 * Check functions interface
 */
public interface AllCheckFunctions {

    /**
     * Abstract checkPosition
     */
    boolean checkPosition(int position, Position[] zone, FamilyMember familyMember);

    /**
     * Abstract checkCardCostVentures
     */
    int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);

    /**
     * Abstract checkCardCost
     */
    boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);

    /**
     * Abstract checkMilitaryPointsForTerritory
     */
    boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) ;

    /**
     * Abstract getServants
     */
    int getServants(PlayerHandler player);

    /**
     * Abstract checkTowerOccupied
     */
    boolean checkTowerOccupied(Tower[] tower);

}
