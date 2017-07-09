package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class AddVictoryPoints implements TakeRoPEffects {

    private int quantity;

    public AddVictoryPoints(int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        return new OkOrNo();

    }

    @Override
    public String toScreen(){
        return "Add " + quantity + " victory points";
    }
}
