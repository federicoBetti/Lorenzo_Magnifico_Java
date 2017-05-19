package Project.Controller;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;


public class DontPayForTerritories implements SupportFunctionDecorator {
    AllSupportFunction allSupportFunction = null;

    DontPayForTerritories(AllSupportFunction allSupportFunction){
        this.allSupportFunction = allSupportFunction;
    }

    @Override
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        return allSupportFunction.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allSupportFunction.CheckTowerOccupiedByYou(tower,player);
    }

    @Override
    public int Pray(Player player) {
        return allSupportFunction.Pray(player);
    }

    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return true;
    }

    @Override
    public void ApplyEffects(Card card, Player player) {
        allSupportFunction.ApplyEffects(card, player);
    }

    @Override
    public boolean CheckCardCostCharacters(CharacterCard card, Player player) {
        return allSupportFunction.CheckCardCostCharacters(card,player);
    }

    @Override
    public boolean CheckCardCostBuildings(BuildingCard card, Player player) {
        return allSupportFunction.CheckCardCostBuildings(card,player);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allSupportFunction.CheckCardCostVentures(card,player);
    }
}
