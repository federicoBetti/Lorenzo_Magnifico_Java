package Project.Controller;


import Project.MODEL.*;

public interface AllSupportFunction {
    boolean Check_Position(int position, Position[] zone, FamilyMember familyMember);
    boolean CheckTowerOccupiedByYou(Tower[] tower, Player player);
    int Pray(Player player);
    boolean CheckCapabilityToTakeTerritory(Player player);
    void ApplyEffects(Card card, Player player);

}
