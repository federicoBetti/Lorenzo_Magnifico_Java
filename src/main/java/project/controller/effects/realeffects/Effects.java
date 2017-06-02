package project.controller.effects.realeffects;

import project.server.network.PlayerHandler;
import project.messages.BonusInteraction;


public interface Effects {
    int quantity = 0 ;

    BonusInteraction doEffect(PlayerHandler player);
}
