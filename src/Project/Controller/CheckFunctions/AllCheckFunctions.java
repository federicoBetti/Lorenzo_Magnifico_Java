package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;


public interface AllCheckFunctions {
    boolean Check_Position(int position, Position[] zone, FamilyMember familyMember);
    boolean CheckTowerOccupiedByYou(Tower[] tower, Player player);
    boolean CheckCapabilityToTakeTerritory(Player player);
    boolean CheckCardCostCharacters (CharacterCard card, Player player);
    boolean CheckCardCostBuildings (BuildingCard card, Player player);
    int CheckCardCostVentures (VenturesCard card, Player player);
    boolean CheckCardCost (DevelopmentCard card, Player player);
    boolean CheckAvaiabiltyToProduct(ArrayList<BuildingCard> cardToProduct, int maxValueOfProduction);
}
