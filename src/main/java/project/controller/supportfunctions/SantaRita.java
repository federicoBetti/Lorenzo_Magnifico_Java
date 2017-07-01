package project.controller.supportfunctions;

import project.controller.effects.realeffects.Effects;
import project.controller.effects.realeffects.TakeRoPEffects;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;


public class SantaRita extends SupportFunctionsDecorator {

    public SantaRita(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public void applyEffects(DevelopmentCard card, PlayerHandler player) {

        for (Effects effect : card.getImmediateCardEffects()) {
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
        player.sendActionOk();
    }

}