package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;

/**
 * TODO METTERE TUTTI I RITORNI DELLE ALTRE FUNZIONI COME ALLCHECKFUNCTION.altrafunzione
 */
public class CantPlaceFamiliarInMarket implements CheckFunctionsDecorator {

    AllCheckFunctions allCheckFunctions = null;

    public CantPlaceFamiliarInMarket(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }


    @Override
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (zone instanceof Market[]){
            return false;
        }
        else
            allCheckFunctions.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return false;
    }

    @Override
    public boolean CheckCardCostTerritory(TerritoryCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean CheckCardCostCharacter(CharacterCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean CheckCardCostBuilding(BuildingCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public int CheckCardCostVentures(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return 0;
    }

    @Override
    public boolean CheckAvaiabiltyToProduct(ArrayList<BuildingCard> cardToProduct, int maxValueOfProduction) {
        return false;
    }

    @Override
    public boolean CheckCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean CheckTowerOccupied(Tower[] zone) {
        return false;
    }
}
