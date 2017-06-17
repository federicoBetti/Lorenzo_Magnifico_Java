package project.controller.checkfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.DevelopmentCard;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;
import project.server.network.PlayerHandler;

public abstract class CheckFunctionsDecorator implements AllCheckFunctions {
    AllCheckFunctions allCheckFunctions;

    public CheckFunctionsDecorator(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        return allCheckFunctions.checkPosition(position,zone,familyMember);
    }

    @Override
    public boolean checkTowerOccupied(Tower[] tower) {
        return allCheckFunctions.checkTowerOccupied(tower);
    }

    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return allCheckFunctions.checkCardCostVentures(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }

    @Override
    public int getServants(PlayerHandler playerHandler){
        return allCheckFunctions.getServants(playerHandler);
    }

    public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        return allCheckFunctions.checkCardCost(card,playerHandler,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length){
        return allCheckFunctions.checkMilitaryPointsForTerritory(player,length);
    }

}
