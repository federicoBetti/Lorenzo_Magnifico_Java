package project.controller.cardsfactory;

/**
 * 
 */
public class TerritoryCost implements Cost {

    private int woodRequired;

    private int stoneRequired;

    private int diceCost;


    public TerritoryCost() {
        //da fare
    }

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