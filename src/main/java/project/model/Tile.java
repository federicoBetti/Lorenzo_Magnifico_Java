package project.model;

import project.controller.cardsfactory.TileBonusFromJson;
import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 */
public class Tile implements Serializable{

    int tileNumber;
    ArrayList<Effects> harvesterBonus;
    ArrayList<Effects> productionBonus;


    public Tile(TileBonusFromJson tile ){
        BuildImmediateEffects BuildImmediateEffects = new BuildImmediateEffects();

        this.tileNumber = tile.getTileNumber();

        for(TrisIE tris : tile.getHarvesterBonus() )
            harvesterBonus.add(BuildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));

        for(TrisIE tris : tile.getProductionBonus() )
            productionBonus.add(BuildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));
    }
    /**
     * @return
     */
    public void takeProductionResource() {
        // TODO implement here
    }

    /**
     * @return
     */
    public void takeHarvesterResource() {
        // TODO implement here
    }

    public int getTileNumber() {
        return tileNumber;
    }
}