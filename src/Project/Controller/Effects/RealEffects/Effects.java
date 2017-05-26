package Project.Controller.Effects.RealEffects;

import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;
import Project.MODEL.*;


public interface Effects {
    int quantity = 0;
    BonusInteraction doEffect(PlayerHandler player);
}
