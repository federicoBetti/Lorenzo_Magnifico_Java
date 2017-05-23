package Project.MODEL;

/**
 * 
 */
public class Bonus {


    private int bonusForTakingTerritory = 0;

    private int productionBonus = 0;

    private int harvesterBonus = 0;

    private BonusForTakingCharacter charactersBonus;

    private BonusForTakingBuilding buildingsBonus;

    private int bonusForTakingVentures = 0;

    //Aggiustare per ReduceValueOnAction


    public Bonus (){
        this.charactersBonus = new BonusForTakingCharacter();
        this.buildingsBonus = new BonusForTakingBuilding();

    }

    public Bonus(BonusForTakingCharacter charactersBonus) {
        this.charactersBonus = charactersBonus;
        this.buildingsBonus = new BonusForTakingBuilding();
    }

    public int getVenturesBonus() {
        return bonusForTakingVentures;
    }

    public void setVenturesBonus(int venturesBonus) {
        this.bonusForTakingVentures = venturesBonus;
    }

    public int getTerritoryBonus() {
        return bonusForTakingTerritory;
    }

    public void setTerritoryBonus(int territoryBonus) {
        bonusForTakingTerritory = territoryBonus;
    }

    public int getProductionBonus() {
        return productionBonus;
    }

    public void setProductionBonus(int productionBonus) {
        this.productionBonus = productionBonus;
    }

    public int getHarvesterBonus() {
        return harvesterBonus;
    }

    public void setHarvesterBonus(int harvesterBonus) {
        this.harvesterBonus = harvesterBonus;
    }

    public void setCharactersBonus(BonusForTakingCharacter charactersBonus) {
        this.charactersBonus = charactersBonus;
    }

    public BonusForTakingBuilding getBuildingsBonus() {
        return buildingsBonus;
    }

    public void setBuildingsBonus(BonusForTakingBuilding buildingsBonus) {
        this.buildingsBonus = buildingsBonus;
    }

    public BonusForTakingCharacter getCharactersBonus() {
        return charactersBonus;
    }
}