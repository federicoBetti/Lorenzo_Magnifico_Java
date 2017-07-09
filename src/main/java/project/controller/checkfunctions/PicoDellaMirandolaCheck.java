package project.controller.checkfunctions;

import project.controller.cardsfactory.Cost;
import project.controller.cardsfactory.VenturesCard;
import project.controller.cardsfactory.VenturesCost;
import project.model.DevelopmentCard;
import project.server.network.PlayerHandler;

/**
 * This class is the decoration of checkCardCost and checkCardCostVentures
 */
public class PicoDellaMirandolaCheck extends CheckFunctionsDecorator {

    public PicoDellaMirandolaCheck(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * todo java doc
     *
     * @param card
     * @param playerHandler
     * @param coinsFee
     * @param zoneDiceCost
     * @param valueOfFamilyMember
     * @return
     */
    @Override
    public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        DevelopmentCard card1 = card;
        Cost oldCost = card.getCost();
        Cost newCost = oldCost.copyOf();
        newCost.picoDellaMirandolaDowngrade();
        card.setCost(newCost);
        boolean result =  allCheckFunctions.checkCardCost(card1,playerHandler,coinsFee,zoneDiceCost,valueOfFamilyMember);
        card.setCost(oldCost);
        return result;
    }

    /**
     * todo java doc
     *
     * @param card
     * @param player
     * @param coinsFee
     * @param zoneDiceCost
     * @param valueOfFamilyMember
     * @return
     */
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        VenturesCard card1 = card;
        VenturesCost oldCost1;
        VenturesCost oldCost2;
        //todo

        return allCheckFunctions.checkCardCostVentures(card1,player,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }
}
