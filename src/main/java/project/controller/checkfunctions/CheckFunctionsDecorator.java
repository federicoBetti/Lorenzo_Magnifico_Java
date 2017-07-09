package project.controller.checkfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.DevelopmentCard;
import project.model.FamilyMember;
import project.model.Position;
import project.model.Tower;
import project.server.network.PlayerHandler;

/**
 * This class contains all the check functions that could be decorated
 */
public abstract class CheckFunctionsDecorator implements AllCheckFunctions {
    AllCheckFunctions allCheckFunctions;

    CheckFunctionsDecorator(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }

    /**
     * Checkposition method
     */
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        return allCheckFunctions.checkPosition(position,zone,familyMember);
    }

    /**
     * checkTowerOccupied method
     */
    @Override
    public boolean checkTowerOccupied(Tower[] tower) {
        return allCheckFunctions.checkTowerOccupied(tower);
    }

    /**
     * checkCardCostVentures method
     */
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        return allCheckFunctions.checkCardCostVentures(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }

    /**
     * getServants method
     */
    @Override
    public int getServants(PlayerHandler playerHandler){
        return allCheckFunctions.getServants(playerHandler);
    }

    /**
     * checkCardCost method
     */
    public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        return allCheckFunctions.checkCardCost(card,playerHandler,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }

    /**
     * checkMilitaryPointsForTerritory method
     */
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length){
        return allCheckFunctions.checkMilitaryPointsForTerritory(player,length);
    }

}
