package project.controller.effects.realeffects;

import project.controller.cardsfactory.TotalCost;
import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;

import java.io.Serializable;


public interface Effects extends Serializable {
    int quantity = 0 ;

    BonusInteraction doEffect(PlayerHandler player);

    String toScreen();

}
