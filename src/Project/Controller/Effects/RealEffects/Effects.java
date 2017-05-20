package Project.Controller.Effects.RealEffects;

import Project.toDelete.BonusInteraction;
import Project.MODEL.*;


public interface Effects {
    int quantity = 0;
    BonusInteraction doEffect(Player player);
}
