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
        player.getPersonalBoardReference().setServants(player.getPersonalBoardReference().getServants() + quantity);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
