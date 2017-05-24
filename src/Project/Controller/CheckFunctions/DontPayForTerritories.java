package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;


public class DontPayForTerritories implements CheckFunctionsDecorator {
    AllCheckFunctions allCheckFunctions = null;

    DontPayForTerritories(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    @Override
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        return allCheckFunctions.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allCheckFunctions.CheckTowerOccupiedByYou(tower,player);
    }


    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return true;
    }


    @Override
    public boolean CheckCardCostCharacter(CharacterCard card, Player player) {
        return allCheckFunctions.CheckCardCostCharacter(card,player);
    }

    @Override
    public boolean CheckCardCostBuilding(BuildingCard card, Player player) {
        return allCheckFunctions.CheckCardCostBuilding(card,player);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allCheckFunctions.CheckCardCostVentures(card,player);
    }
}
