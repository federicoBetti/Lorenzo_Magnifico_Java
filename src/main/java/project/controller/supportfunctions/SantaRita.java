package project.controller.supportfunctions;

import project.controller.effects.realeffects.Effects;
import project.controller.effects.realeffects.TakeRoPEffects;
import project.model.*;
import project.messages.BonusInteraction;
import project.server.network.PlayerHandler;


public class SantaRita extends SupportFunctionsDecorator {

    public SantaRita(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public BonusInteraction applyEffects(DevelopmentCard card, PlayerHandler player) {
        for (Effects e : card.getImmediateCardEffects()) {
            e.doEffect(player);
            if (e instanceof TakeRoPEffects) {
                e.doEffect(player);
            }
        }
        return null;
    }

}