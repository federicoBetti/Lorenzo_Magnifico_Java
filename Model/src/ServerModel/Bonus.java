package ServerModel;

/**
 * 
 */
public class Bonus {


    private int TerritoryBonus = 0;

    private int ProductionBonus = 0;

    private int HarvesterBonus = 0;

    private CharactersBonus charactersBonus;

    private int VenturesBonus = 0;

    private BuildingBonus buildingsBonus;

    private int coinsBonus = 0;

    private int militaryPointBonus = 0;

    private int stoneBonus = 0;

    private int woodBonus = 0;

    private int servantsBonus = 0;

    public Bonus (){
        this.charactersBonus = new CharactersBonus();
        this.buildingsBonus = new BuildingBonus();

    }

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

    public int getCoinsBonus() {
        return coinsBonus;
    }

    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonus = coinsBonus;
    }

    public int getMilitaryPointBonus() {
        return militaryPointBonus;
    }

    public void setMilitaryPointBonus(int militaryPointBonus) {
        this.militaryPointBonus = militaryPointBonus;
    }

    public int getStoneBonus() {
        return stoneBonus;
    }

    public void setStoneBonus(int stoneBonus) {
        this.stoneBonus = stoneBonus;
    }

    public int getWoodBonus() {
        return woodBonus;
    }

    public void setWoodBonus(int woodBonus) {
        this.woodBonus = woodBonus;
    }

    public int getServantsBonus() {
        return servantsBonus;
    }

    public void setServantsBonus(int servantsBonus) {
        this.servantsBonus = servantsBonus;
    }
}