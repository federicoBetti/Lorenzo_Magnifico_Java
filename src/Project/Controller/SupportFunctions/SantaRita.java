package Project.Controller.SupportFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.RealEffects.TakeRoPEffects;
import Project.MODEL.*;


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
    public void ApplyEffects(DevelopmentCard card, Player player) {
        for (Effects e: card.getImmediateCardEffects()){
            e.doEffect(player);
            if (e instanceof TakeRoPEffects) {
                e.doEffect(player);
            }
        }
    }

}
