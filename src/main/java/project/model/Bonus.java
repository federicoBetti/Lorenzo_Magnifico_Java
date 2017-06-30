package project.model;

import java.io.Serializable;

/**
 * 
 */
public class Bonus implements Serializable {


    private int bonusForTakingTerritory;

    private int productionBonus;

    private int harvesterBonus;

    private BonusForTakingCharacter charactersBonus;

    private BonusForTakingBuilding buildingsBonus;

    private int bonusForTakingVentures;


    //todo mettere lo 0 nella creazione della carta, togliere il todo quando sono stati messi anche negli effetti

    private int militaryPointsBonus;

    private int coinsBonus;

    private int servantsBonus;

    private int stoneBonus;

    private int woodBonus;

    //Aggiustare per ReduceValueOnAction


    public Bonus (){
        this.charactersBonus = new BonusForTakingCharacter();
        this.buildingsBonus = new BonusForTakingBuilding();
        bonusForTakingTerritory = 0;
        productionBonus = 0;
        harvesterBonus = 0;
        bonusForTakingVentures = 0;
        militaryPointsBonus = 0;
        coinsBonus = 0;
        stoneBonus = 0;
        woodBonus = 0;

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

    public int getMilitaryPointsBonus() {
        return militaryPointsBonus;
    }

    public void setMilitaryPointsBonus(int militaryPointsBonus) {
        this.militaryPointsBonus = militaryPointsBonus;
    }

    public int getCoinsBonus() {
        return coinsBonus;
    }

    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonus = coinsBonus;
    }

    public int getServantsBonus() {
        return servantsBonus;
    }

    public void setServantsBonus(int servantsBonus) {
        this.servantsBonus = servantsBonus;
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
}