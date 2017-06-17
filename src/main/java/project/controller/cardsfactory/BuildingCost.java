package project.controller.cardsfactory;

/**
 * 
 */
public class BuildingCost extends TerritoryCost {

    private int coinsRequired;
    /**
     * Default constructor
     */
    public BuildingCost() {
        //TODO
    }

    /**
     * 
     */


    public int getCoinsRequired() {
        return coinsRequired;
    }

    public void setCoinsRequired(int coinsRequired) {
        this.coinsRequired = coinsRequired;
    }

    @Override
    public void picoDellaMirandolaDowngrade() {
        coinsRequired -= 3;
    }
}