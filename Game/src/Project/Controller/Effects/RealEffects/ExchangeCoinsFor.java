package Project.Controller.Effects.RealEffects;

import Project.Controller.CardsFactory.TotalCost;
import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.*;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeCoinsFor implements Effects {

    int coinsRequired;
    int resourceEarned;
    String resourceRewardered;

    public ExchangeCoinsFor (int quantity, TotalCost effectCost, String resourceRewardered ){
         this.coinsRequired = effectCost.getCoinsRequired();
         this.resourceEarned = quantity;
         this.resourceRewardered = resourceRewardered;
    }

    @Override
    public BonusInteraction doEffect(Player player) {

        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() - coinsRequired);

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
            case"privilege":
               UsePrivilege p = new UsePrivilege();
               BonusInteraction response =  p.doEffect(player); //TO CHECK
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
