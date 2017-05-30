package Project.Controller.Effects.RealEffects;

import Project.Messages.BonusInteraction;
import Project.Messages.OkOrNo;
import Project.Controller.CardsFactory.TotalCost;
import Project.MODEL.Player;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeServantsFor implements Effects {
    int servantsRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeServantsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
        this.servantsRequired = effectCost.getStoneRequired();
        this.resourceEarned = quantity;
        this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(Player player) {

        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() - servantsRequired);

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
        }

        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
