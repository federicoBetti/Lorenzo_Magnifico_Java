package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;
import java.util.List;

/**
 * todo mettere qua tutte le funzioni che sono implementare in basi functions
 * todo CAMBIARE LE CHECK UTILIZZATE E METTERLE CON LETTERA MINUSCOLA
 */

public interface AllCheckFunctions {

    boolean checkPosition(int position, Position[] zone, FamilyMember familyMember);
    boolean checkTowerOccupiedByYou(Tower[] tower, PlayerHandler player);
    boolean checkCardCostTerritory(TerritoryCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    boolean checkCardCostCharacter(CharacterCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    boolean checkCardCostBuilding(BuildingCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);
    boolean checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction);
    boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember);

    boolean checkTowerOccupied(Tower[] zone);
    int getServants(Player player);
}
