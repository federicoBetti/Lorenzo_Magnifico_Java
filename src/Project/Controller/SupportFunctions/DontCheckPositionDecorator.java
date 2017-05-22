package Project.Controller.SupportFunctions;


import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;

public class DontCheckPositionDecorator implements SupportFunctionsDecorator {
    AllSupportFunctions allSupportFunctions = null;

    DontCheckPositionDecorator(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!(zone instanceof Tower[])){
            return true;
        }
        else
            return allSupportFunctions.Check_Position(position,zone,familyMember);

    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allSupportFunctions.CheckTowerOccupiedByYou(tower, player);
    }

    @Override
    public int Pray(Player player) {
        return allSupportFunctions.Pray(player);
    }

    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return allSupportFunctions.CheckCapabilityToTakeTerritory(player);
    }

    @Override
    public void ApplyEffects(Card card, Player player) {
        allSupportFunctions.ApplyEffects(card,player);
    }

    @Override
    public boolean CheckCardCostCharacters(CharacterCard card, Player player) {
        return allSupportFunctions.CheckCardCostCharacters(card,player);
    }

    @Override
    public boolean CheckCardCostBuildings(BuildingCard card, Player player) {
        return allSupportFunctions.CheckCardCostBuildings(card,player);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allSupportFunctions.CheckCardCostVentures(card,player);
    }
}
