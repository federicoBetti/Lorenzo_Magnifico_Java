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
        BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();

        this.tileNumber = tile.getTileNumber();

        for(TrisIE tris : tile.getHarvesterBonus() ) {
            harvesterBonus.add(buildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));
        }

        for(TrisIE tris : tile.getProductionBonus() ){
            productionBonus.add(buildImmediateEffects.searchImmediateEffects(tris.getType(), tris.getParameter(), tris.getQuantity()));
        }
    }

    /**
     * constructor used for test
     * @param trisHarv harvester effect
     * @param trisProd production effect
     */
    public Tile(TrisIE trisHarv, TrisIE trisProd) {
        harvesterBonus = new ArrayList<>();
        productionBonus = new ArrayList<>();
        BuildImmediateEffects buildImmediateEffects = new BuildImmediateEffects();

        harvesterBonus.add(buildImmediateEffects.searchImmediateEffects(trisHarv.getType(), trisHarv.getParameter(), trisHarv.getQuantity()));
        productionBonus.add(buildImmediateEffects.searchImmediateEffects(trisProd.getType(), trisProd.getParameter(), trisProd.getQuantity()));
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