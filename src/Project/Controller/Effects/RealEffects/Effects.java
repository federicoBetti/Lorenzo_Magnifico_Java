package Project.Controller.Effects.RealEffects;

import Project.Server.Network.PlayerHandler;
import Project.Messages.BonusInteraction;


public interface Effects {
    int quantity = 0;
    BonusInteraction doEffect(PlayerHandler player);
}
