package Project.Controller.SupportFunctions;

import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.RealEffects.TakeRoPEffects;
import Project.MODEL.*;
import Project.toDelete.BonusInteraction;


public class SantaRita implements SupportFunctionsDecorator {
    AllSupportFunctions allSupportFunctions = null;

    SantaRita(AllSupportFunctions allSupportFunctions){
        this.allSupportFunctions = allSupportFunctions;
    }

    @Override
    public int Pray(Player player) {
        return allSupportFunctions.Pray(player);
    }


    @Override
    public BonusInteraction ApplyEffects(DevelopmentCard card, Player player) {
        for (Effects e: card.getImmediateCardEffects()){
            e.doEffect(player);
            if (e instanceof TakeRoPEffects) {
                e.doEffect(player);
            }
        }
        return null;
    }

    @Override
    public int finalPointsFromTerritoryCard() {
        return 0;
    }

}
