package ServerModel;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints implements Effects {
    int servantsRequired;
    int victoryPointsEarned;
    int militaryPointsEarned;

    public ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( ) {
        servantsRequired = 1;
        victoryPointsEarned = 1;
        militaryPointsEarned = 3;
    }

    public BonusInteraction doEffect(Player player) {

        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() - servantsRequired);
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() - victoryPointsEarned);
        player.getScore().setMilitaryPoints(player.getScore().getMilitaryPoints() - militaryPointsEarned);

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
