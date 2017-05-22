package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;

/**
 * Created by federico on 19/05/17.
 */
public class PicoDellaMirandola implements SupportFunctionsDecorator {
    AllSupportFunctions allSupportFunctions = null;

    PicoDellaMirandola(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
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
    public void ApplyEffects(DevelopmentCard card, Player player) {
        allSupportFunctions.ApplyEffects(card,player);
    }

    @Override
    public boolean CheckCardCostCharacters(CharacterCard card, Player player) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }

    @Override
    public boolean CheckCardCostBuildings(BuildingCard card, Player player) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        // TODO fare lo stesso che c'è nella support function normale con 3 di sconto sui coins required
        return false;
    }
}
