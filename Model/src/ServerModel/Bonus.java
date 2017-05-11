package ServerModel;

/**
 * 
 */
public class Bonus {


    private int TerritoryBonus;

    /**
     * 
     */
    private int ProductionBonus;

    /**
     * 
     */
    private int HarvesterBonus;

    /**
     * 
     */
    private CharactersBonus charactersBonus;

    /**
     * 
     */
    private int VenturesBonus;

    /**
     * 
     */
    private BuildingBonus buildingsBonus;

    public Bonus(CharactersBonus charactersBonus) {
        this.charactersBonus = charactersBonus;
        this.buildingsBonus = new BuildingBonus();
    }

    public int getTerritoryBonus() {
        return TerritoryBonus;
    }

    public void setTerritoryBonus(int territoryBonus) {
        TerritoryBonus = territoryBonus;
    }

    public int getProductionBonus() {
        return ProductionBonus;
    }

    public void setProductionBonus(int productionBonus) {
        ProductionBonus = productionBonus;
    }

    public int getHarvesterBonus() {
        return HarvesterBonus;
    }

    public void setHarvesterBonus(int harvesterBonus) {
        HarvesterBonus = harvesterBonus;
    }

    public int getVenturesBonus() {
        return VenturesBonus;
    }

    public void setVenturesBonus(int venturesBonus) {
        VenturesBonus = venturesBonus;
    }

    public void setCharactersBonus(CharactersBonus charactersBonus) {
        this.charactersBonus = charactersBonus;
    }

    public BuildingBonus getBuildingsBonus() {
        return buildingsBonus;
    }

    public void setBuildingsBonus(BuildingBonus buildingsBonus) {
        this.buildingsBonus = buildingsBonus;
    }

    public CharactersBonus getCharactersBonus() {
        return charactersBonus;
    }
}