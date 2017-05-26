package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;

/**
 * Created by federico on 19/05/17.
 */
public class PicoDellaMirandola implements CheckFunctionsDecorator {
    AllCheckFunctions allCheckFunctions = null;

    PicoDellaMirandola(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        return allCheckFunctions.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allCheckFunctions.CheckTowerOccupiedByYou(tower, player);
    }


    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return allCheckFunctions.CheckCardCost(player);
    }


    @Override
    public boolean CheckCardCostCharacter(CharacterCard card, Player player, boolean coinsFee, int zoneDiceCost) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }

    @Override
    public boolean CheckCardCostBuilding(BuildingCard card, Player player, boolean coinsFee, int zoneDiceCost) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }
}
