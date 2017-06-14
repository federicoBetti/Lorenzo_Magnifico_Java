package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class AddVicotryPoints implements TakeRoPEffects {

    private int quantity;

    public AddVicotryPoints (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        return new OkOrNo();

    }
}
