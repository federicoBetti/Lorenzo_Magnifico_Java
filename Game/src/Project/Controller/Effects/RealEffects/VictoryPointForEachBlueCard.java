package Project.Controller.Effects.RealEffects;

import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class VictoryPointForEachBlueCard implements Effects {
    int quantity;
    public VictoryPointForEachBlueCard ( int quantity ){
        this.quantity = quantity;
    }

    public BonusInteraction doEffect(Player player){

        int i = 0;
        while ( player.getPersonalBoardReference().getCharacters()[i] != null ) {
            i++;
        }

        player.getScore().setVictoryPoints( player.getScore().getVictoryPoints() + this.quantity*i);

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
