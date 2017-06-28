package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

public class AddServants implements TakeRoPEffects {

    private int quantity;

    public AddServants (int quantity){
        this.quantity = quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        quantity += player.getPersonalBoardReference().getBonusOnActions().getServantsBonus();
        if (quantity<0)
            quantity = 0;
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + quantity);
        return new OkOrNo();
    }

    @Override
    public String toScreen(){
        return "Add " + quantity + " servants";
    }
}
