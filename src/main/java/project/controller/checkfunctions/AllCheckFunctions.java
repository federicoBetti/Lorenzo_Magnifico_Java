package project.controller.checkfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

/**
 * todo mettere qua tutte le funzioni che sono implementare in basi functions
 * todo CAMBIARE LE CHECK UTILIZZATE E METTERLE CON LETTERA MINUSCOLA
 */

public interface AllCheckFunctions {

    
    boolean checkPosition(int position, Position[] zone, FamilyMember familyMember);
    int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
   boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) ;
    int getServants(PlayerHandler player);
    boolean checkTowerOccupied(Tower[] tower);

}
