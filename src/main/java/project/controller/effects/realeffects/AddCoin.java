package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by Rita1 on 09/05/2017.
 */
public class AddCoin implements TakeRoPEffects {
    private int quantity;

    public AddCoin (int quantity){
        this.quantity = quantity;
    }


    @Override
    public String toString(){
        return "Aggiungere soldi al giocatore che è molto ricco" + quantity;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + quantity);
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
