package Project.Controller.Effects;

import Project.Controller.Effects.RealEffects.Effects;
import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.Player;


public class SetFamiliarFixedValue implements Effects {
    String parameter;
    int quantity;

    public SetFamiliarFixedValue(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    public BonusInteraction doEffect (Player player){
        switch (parameter){
            case "neutral":
                player.getPedone()[0].setFixedValue(quantity);
                break;
            case "white":
                player.getPedone()[1].setFixedValue(quantity);
                break;
            case "orange":
                player.getPedone()[2].setFixedValue(quantity);
                break;
            case "black":
                player.getPedone()[0].setFixedValue(quantity);
                break;
        }

        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
