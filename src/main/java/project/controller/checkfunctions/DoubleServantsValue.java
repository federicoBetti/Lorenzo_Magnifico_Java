package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;
import java.util.List;

/**
 * todo mettere tutti i ritorni delle altre funzioni come allcheckfunctions.funzione(param....)
 */
public class DoubleServantsValue implements CheckFunctionsDecorator {

    AllCheckFunctions allCheckFunctions;

    public DoubleServantsValue(AllCheckFunctions allCheckFunctions){
        this.allCheckFunctions = allCheckFunctions;
    }


    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        return false;
    }

    @Override
    public boolean checkTowerOccupiedByYou(Tower[] tower, PlayerHandler player) {
        return false;
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
        return player.getPersonalBoardReference().getServants() / 2;
    }
}
