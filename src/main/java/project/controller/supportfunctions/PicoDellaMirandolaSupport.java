package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.DevelopmentCard;
import project.model.Player;

/**
 * Created by federico on 17/06/17.
 */
public class PicoDellaMirandolaSupport extends SupportFunctionsDecorator {

    public PicoDellaMirandolaSupport(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
      cardOnThisFloor.getCost().picoDellaMirandolaDowngrade();
      allSupportFunctions.payCard(cardOnThisFloor,coinsFee,zoneDiceCost,valueOfFamilyMember);
       }
    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        card.getVenturesCost().get(paymentChoosen).picoDellaMirandolaDowngrade();
        allSupportFunctions.payVenturesCard(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember,paymentChoosen);
    }
}
