package project.model;

import project.controller.cardsfactory.TileBonusFromJson;
import project.controller.effects.effectsfactory.BuildImmediateEffects;
import project.controller.effects.effectsfactory.TrisIE;
import project.controller.effects.realeffects.Effects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Tile implements Serializable{

    private int tileNumber;
    private List<Effects> harvesterBonus;
    private List<Effects> productionBonus;


    public Tile(TileBonusFromJson tile ){
        harvesterBonus = new ArrayList<>();
        productionBonus = new ArrayList<>();
        BuildImmediateEffects BuildImmediateEffects = new BuildImmediateEffects();

        this.tileNumber = tile.getTileNumber();

        for(TrisIE tris : tile.getHarvesterBonus() ) {
            harvesterBonus.add(BuildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));
        }

        for(TrisIE tris : tile.getProductionBonus() ){
            productionBonus.add(BuildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));
        }
    }
    /**
     * @return
     */
    public List<Effects> takeProductionResource() {
        return productionBonus;
    }

    /**
     * @return
     */
    public List<Effects> takeHarvesterResource() {
        return harvesterBonus;
    }

    public int getTileNumber() {
        return tileNumber;
    }
}