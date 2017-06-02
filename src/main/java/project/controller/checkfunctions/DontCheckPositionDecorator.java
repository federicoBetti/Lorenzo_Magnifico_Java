package project.controller.checkfunctions;


import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

public class DontCheckPositionDecorator implements CheckFunctionsDecorator {
    private AllCheckFunctions allCheckFunctions = null;

    DontCheckPositionDecorator(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (!(zone instanceof Tower[])){
            return true;
        }
        else
            return allCheckFunctions.checkPosition(position,zone,familyMember);

    }

    @Override
    public boolean checkTowerOccupiedByYou(Tower[] tower, PlayerHandler player) {
        return allCheckFunctions.checkTowerOccupiedByYou(tower, player);
    }

    @Override
    public boolean checkCardCostTerritory(TerritoryCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean checkCardCostCharacter(CharacterCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean checkCardCostBuilding(BuildingCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return 0;
    }

    @Override
    public boolean checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction) {
        return false;
    }

    @Override
    public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return false;
    }

    @Override
    public boolean checkTowerOccupied(Tower[] zone) {
        return false;
    }

    @Override
    public int getServants(Player player) {
        return 0;
    }

    //todo controllare

/*
    @Override
    public boolean checkCapabilityToTakeTerritory(PlayerHandler player) {
        return allCheckFunctions.checkCardCost(player);
    }


    @Override
    public boolean CheckCardCostCharacter(CharacterCard card, Player player, boolean coinsFee, int zoneDiceCost) {
        return allCheckFunctions.checkCardCostCharacter(card, player, coinsFee, zoneDiceCost);
    }

    @Override
    public boolean CheckCardCostBuilding(BuildingCard card, Player player, boolean coinsFee, int zoneDiceCost) {
        return allCheckFunctions.checkCardCostBuilding(card,player, coinsFee, zoneDiceCost);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allCheckFunctions.checkCardCostVentures(card,player);
    }
    */
}
