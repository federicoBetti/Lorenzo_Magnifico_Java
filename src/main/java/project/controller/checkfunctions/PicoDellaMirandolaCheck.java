package project.controller.checkfunctions;

import project.controller.Constants;
import project.controller.cardsfactory.Cost;
import project.controller.cardsfactory.VenturesCard;
import project.controller.cardsfactory.VenturesCost;
import project.model.DevelopmentCard;
import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the decoration of checkCardCost and checkCardCostVentures
 */
public class PicoDellaMirandolaCheck extends CheckFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allCheckFunctions allCheckFunctions's reference
     */
    public PicoDellaMirandolaCheck(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * method used to check cost after PicoDelaMirandola leader has been played. the costs obtain a 3 coins discount
     * @param card card to pay
     * @param playerHandler player
     * @param coinsFee boolean true if tower is occupied
     * @param zoneDiceCost dice cost of zone
     * @param valueOfFamilyMember value of familiar
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
     * Check venture card's cost
     *
     * @param card card's reference
     * @param player playersHandler referencs
     * @param coinsFee boolean
     * @param zoneDiceCost zone dice cost
     * @param valueOfFamilyMember value of familiar
     * @return choice
     */
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){

        List<VenturesCost> venturesCostsNotDiscounted = new ArrayList<>();

        for (VenturesCost v: card.getVenturesCost()){
            VenturesCost oldCost = (VenturesCost) v.copyOf();
            venturesCostsNotDiscounted.add(oldCost);
            v.picoDellaMirandolaDowngrade();
        }

        int choice = allCheckFunctions.checkCardCostVentures(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember);
        card.setVenturesCost(venturesCostsNotDiscounted);
        return choice;
    }
}
