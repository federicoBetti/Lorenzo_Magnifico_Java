package project.controller.supportfunctions;

import project.model.Position;
import project.model.Tower;
import project.server.network.PlayerHandler;

/**
 * Created by federico on 28/06/17.
 */
public class NoBonusFromTower extends SupportFunctionsDecorator{

    public NoBonusFromTower(AllSupportFunctions allSupportFunctions) {
        super(allSupportFunctions);
    }

    @Override
    public void towerZoneEffect(Tower zone, PlayerHandler player){
        return;//correct
    }
}
