package project.controller.supportfunctions;

import project.controller.cardsfactory.VenturesCard;
import project.model.DevelopmentCard;
import project.model.Player;

/**
 * This class is the payCard and payVentureCard's decorator applyed when Pico della Mirandola is activated
 */
public class PicoDellaMirandolaSupport extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public PicoDellaMirandolaSupport(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    /**
     * This method give to the player a discount of 3 coins for taking development cards
     *
     * @param cardOnThisFloor card's reference
     * @param coinsFee
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     */
    @Override
    public void payCard(DevelopmentCard cardOnThisFloor, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
      cardOnThisFloor.getCost().picoDellaMirandolaDowngrade();
      allSupportFunctions.payCard(cardOnThisFloor,coinsFee,zoneDiceCost,valueOfFamilyMember);
    }

    /**
     * This method give to the player a discount of 3 coins for taking development cards
     *
     * @param card card's reference
     * @param player playerHandler's reference
     * @param coinsFee coins more to pay
     * @param zoneDiceCost dice's cost
     * @param valueOfFamilyMember familiar dice's value
     * @param paymentChoosen paymentChoosen
     */
    @Override
    public void payVenturesCard(VenturesCard card, Player player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember, int paymentChoosen) {
        card.getVenturesCost().get(paymentChoosen).picoDellaMirandolaDowngrade();
        allSupportFunctions.payVenturesCard(card,player,coinsFee,zoneDiceCost,valueOfFamilyMember,paymentChoosen);
    }
}
