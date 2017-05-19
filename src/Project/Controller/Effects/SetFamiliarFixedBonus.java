package Project.Controller.Effects;

import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class SetFamiliarFixedBonus implements Effects {
    String parameter;
    int quantity;

    public SetFamiliarFixedBonus(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    public BonusInteraction doEffect (Player player){
        switch (parameter){
            case "neutral":
                player.getPedone()[0].setFixedBonus(quantity);
            case "white":
                player.getPedone()[1].setFixedBonus(quantity);
            case "orange":
                player.getPedone()[2].setFixedBonus(quantity);
            case "black":
                player.getPedone()[0].setFixedBonus(quantity);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
