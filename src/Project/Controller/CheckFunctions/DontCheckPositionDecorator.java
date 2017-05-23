package Project.Controller.CheckFunctions;


import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.CheckFunctions.AllCheckFunctions;
import Project.Controller.SupportFunctions.SupportFunctionsDecorator;
import Project.MODEL.*;

public class DontCheckPositionDecorator implements CheckFunctionsDecorator {
    private AllCheckFunctions allCheckFunctions = null;

    DontCheckPositionDecorator(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!(zone instanceof Tower[])){
            return true;
        }
        else
            return allCheckFunctions.Check_Position(position,zone,familyMember);

    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allCheckFunctions.CheckTowerOccupiedByYou(tower, player);
    }


    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return allCheckFunctions.CheckCapabilityToTakeTerritory(player);
    }


    @Override
    public boolean CheckCardCostCharacters(CharacterCard card, Player player) {
        return allCheckFunctions.CheckCardCostCharacters(card,player);
    }

    @Override
    public boolean CheckCardCostBuildings(BuildingCard card, Player player) {
        return allCheckFunctions.CheckCardCostBuildings(card,player);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allCheckFunctions.CheckCardCostVentures(card,player);
    }
}
