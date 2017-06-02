package project.controller.effects;

import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;


public class SetFamiliarFixedValue implements Effects {
    String parameter;
    int quantity;

    public SetFamiliarFixedValue(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (parameter){
            case "neutral":
                player.getAllFamilyMembers()[0].setFixedValue(quantity);
                break;
            case "white":
                player.getAllFamilyMembers()[1].setFixedValue(quantity);
                break;
            case "orange":
                player.getAllFamilyMembers()[2].setFixedValue(quantity);
                break;
            case "black":
                player.getAllFamilyMembers()[0].setFixedValue(quantity);
                break;
            default:
                return null;
        }

        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
