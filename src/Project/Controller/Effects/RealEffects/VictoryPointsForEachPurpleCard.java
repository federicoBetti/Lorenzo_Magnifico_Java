package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class VictoryPointsForEachPurpleCard implements Effects {

    int quantity;
    public VictoryPointsForEachPurpleCard( int quantity ){
        this.quantity = quantity;
    }

    public BonusInteraction doEffect(Player player){

        int i = 0;
        while ( player.getPersonalBoardReference().getVentures()[i] != null ) {
            i++;
        }

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
