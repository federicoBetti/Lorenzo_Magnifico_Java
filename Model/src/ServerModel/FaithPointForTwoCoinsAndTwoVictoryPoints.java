package ServerModel;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class FaithPointForTwoCoinsAndTwoVictoryPoints implements Effects {
    int faithPointsrequired;
    int coinsEarned;
    int victoryPointsEarned;

    public FaithPointForTwoCoinsAndTwoVictoryPoints(){
        faithPointsrequired = 1;
        coinsEarned = 2;
        victoryPointsEarned = 2;
    }

    public BonusInteraction doEffect(Player player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() - faithPointsrequired );
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsEarned);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + victoryPointsEarned );

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
