package project.controller.supportfunctions;

import project.model.Tower;
import project.server.network.PlayerHandler;

/**
 * This class is the towerZoneEffect's decorator
 */
public class NoBonusFromTower extends SupportFunctionsDecorator{

    /**
     * Constructor
     *
     * @param allSupportFunctions allSupportFunctions's reference
     */
    public NoBonusFromTower(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    /**
     * This method doesn't allow the player to acti the bonus effect of the towers
     *
     * @param zone tower zone
     * @param player playerHandler's reference
     */
    @Override
    public void towerZoneEffect(Tower zone, PlayerHandler player){
        return;//correct
    }
}
