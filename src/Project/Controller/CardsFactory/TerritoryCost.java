package Project.Controller.CardsFactory;

import Project.Controller.CardsFactory.Cost;

/**
 * 
 */
public class TerritoryCost extends Cost {

    /**
     * Default constructor
     */
    public TerritoryCost() {
    }

    /**
     * 
     */
    private int woodRequired;

    /**
     * 
     */
    private int stoneRequired;

    /**
     * 
     */
    private int diceCost;

    public int getWoodRequired() {
        return woodRequired;
    }

    public int getStoneRequired() {
        return stoneRequired;
    }

    public int getDiceCost() {
        return diceCost;
    }
}