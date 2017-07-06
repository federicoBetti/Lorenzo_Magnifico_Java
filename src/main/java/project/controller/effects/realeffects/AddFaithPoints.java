package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;


public class AddFaithPoints implements Effects {
    private int quantity;

    public AddFaithPoints (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setFaithPoints(player.getScore().getFaithPoints() + quantity);
        return new OkOrNo();
    }

    @Override
    public String toScreen(){
        return "Add " + quantity + " faith points";
    }
}
