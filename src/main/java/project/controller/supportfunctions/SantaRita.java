package project.controller.supportfunctions;

import project.controller.effects.realeffects.Effects;
import project.controller.effects.realeffects.TakeRoPEffects;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;

/**
 * This class is the payCard and applyEffects's decorator applyed when SantaRita is activated
 */
public class SantaRita extends SupportFunctionsDecorator {

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public SantaRita(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    /**
     * This method allows the palyer to receive two times the resources taken from immediate effects
     *
     * @param effect effect's reference
     * @param player playerHandler's reference
     */
    @Override
    public void applyEffects(Effects effect, PlayerHandler player) {
            BonusInteraction returnFromEffect = effect.doEffect(player);
            System.out.println("stampo la return from effect: " + returnFromEffect);

            if (returnFromEffect instanceof TowerAction) {
                System.out.println("if towerAction");
                player.sendBonusTowerAction((TowerAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);

            } else if (returnFromEffect instanceof BonusProductionOrHarvesterAction) {
                System.out.println("if BonusInteractionHarv");
                player.sendBonusProdOrHarv((BonusProductionOrHarvesterAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);

            } else if (returnFromEffect instanceof TakePrivilegesAction) {
                System.out.println("if TakePrivilege");
                player.sendRequestForPriviledges((TakePrivilegesAction) returnFromEffect);
                System.out.println("stampo la return from effect: " + returnFromEffect);
            }
            else if (effect instanceof TakeRoPEffects) {
                effect.doEffect(player);
            }
        }

}