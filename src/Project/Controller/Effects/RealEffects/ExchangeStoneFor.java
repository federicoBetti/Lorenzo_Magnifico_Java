package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.Controller.CardsFactory.TotalCost;
import Project.MODEL.Player;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeStoneFor implements Effects {

    int stoneRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeStoneFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.stoneRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(Player player) {

        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - stoneRequired);

        switch ( resourceRewardered ){
            case "woods":
                player.getPersonalBoardReference().setWood(player.getPersonalBoardReference().getWood() + resourceEarned);
            case "stones":
                player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + resourceEarned);
            case "servants":
                player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + resourceEarned);
            case "victoryPoints":
                player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + resourceEarned);
            case"faithPoints":
                player.getScore().setFaithPoints(player.getScore().getFaithPoints() + resourceEarned);
            case"coins":
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + resourceEarned);
            case"militaryPoints":
                player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() + resourceEarned);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
