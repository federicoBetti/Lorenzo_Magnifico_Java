package ServerModel;

import java.util.Set;


public class SetFamiliarFixedValue implements Effects {
    String parameter;
    int quantity;

    SetFamiliarFixedValue (String parameter, int quantity){
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

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
