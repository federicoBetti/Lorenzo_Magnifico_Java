package Project.Controller;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.Effects.RealEffects.TakeRoPEffects;
import Project.MODEL.*;


public class SantaRita implements SupportFunctionDecorator {
    AllSupportFunction allSupportFunction = null;

    SantaRita(AllSupportFunction allSupportFunction){
        this.allSupportFunction = allSupportFunction;
    }


    @Override
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        return allSupportFunction.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allSupportFunction.CheckTowerOccupiedByYou(tower,player);
    }

    @Override
    public int Pray(Player player) {
        return allSupportFunction.Pray(player);
    }

    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return allSupportFunction.CheckCapabilityToTakeTerritory(player);
    }

    @Override
    public void ApplyEffects(Card card, Player player) {
        for (Effects e: card.getImmediateCardEffects()){
            e.doEffect(player);
            if (e instanceof TakeRoPEffects) {
                e.doEffect(player);
            }
        }
    }

    @Override
    public boolean CheckCardCostCharacters(CharacterCard card, Player player) {
        return allSupportFunction.CheckCardCostCharacters(card,player);
    }

    @Override
    public boolean CheckCardCostBuildings(BuildingCard card, Player player) {
        return allSupportFunction.CheckCardCostBuildings(card,player);
    }

    @Override
    public boolean CheckCardCostVentures(VenturesCard card, Player player) {
        return allSupportFunction.CheckCardCostVentures(card,player);
    }
}
