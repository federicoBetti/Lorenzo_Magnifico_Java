package project.controller.checkfunctions;

import project.controller.cardsfactory.Cost;
import project.controller.cardsfactory.VenturesCard;
import project.controller.cardsfactory.VenturesCost;
import project.model.DevelopmentCard;
import project.server.network.PlayerHandler;

/**
 * Created by federico on 19/05/17.
 */
public class PicoDellaMirandolaCheck extends CheckFunctionsDecorator {

    public PicoDellaMirandolaCheck(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    @Override
    public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        DevelopmentCard card1 = card;
        Cost cost = card1.getCost();
        cost.picoDellaMirandolaDowngrade();
        return allCheckFunctions.checkCardCost(card1,playerHandler,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        VenturesCard card1 = card;
        for (VenturesCost cost: card1.getVenturesCost())
            cost.picoDellaMirandolaDowngrade();
        return allCheckFunctions.checkCardCostVentures(card1,player,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }
}
