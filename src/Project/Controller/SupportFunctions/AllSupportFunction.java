package Project.Controller.SupportFunctions;


import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;

public interface AllSupportFunction {
    boolean Check_Position(int position, Position[] zone, FamilyMember familyMember);
    boolean CheckTowerOccupiedByYou(Tower[] tower, Player player);
    int Pray(Player player);
    boolean CheckCapabilityToTakeTerritory(Player player);
    void ApplyEffects(Card card, Player player);
    boolean CheckCardCostCharacters (CharacterCard card, Player player);
    boolean CheckCardCostBuildings (BuildingCard card, Player player);
    boolean CheckCardCostVentures (VenturesCard card, Player player);

}
