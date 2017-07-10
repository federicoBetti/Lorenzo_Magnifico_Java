package project.model;

import java.io.Serializable;

/**
 * This class represent the bonus objects
 */
public class Bonus implements Serializable {


    private int bonusForTakingTerritory;

    private int productionBonus;

    private int harvesterBonus;

    private BonusForTakingCharacter charactersBonus;

    private BonusForTakingBuilding buildingsBonus;

    private int bonusForTakingVentures;

    private int militaryPointsBonus;

    private int coinsBonus;

    private int servantsBonus;

    private int stoneBonus;

    private int woodBonus;

    //Aggiustare per ReduceValueOnAction


    /**
     * Constructor
     */
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

    /**
     * Constructor
     *
     * @param charactersBonus bonusForTakingCharacter's reference
     */
    public Bonus(BonusForTakingCharacter charactersBonus) {
        this.charactersBonus = charactersBonus;
        this.buildingsBonus = new BonusForTakingBuilding();
    }

    /**
     * Get bonusForTakingVentures
     *
     * @return bonusForTakingVentures
     */
    public int getVenturesBonus() {
        return bonusForTakingVentures;
    }

    /**
     * Set venturesBonus
     *
     * @param venturesBonus venturesBonus
     */
    public void setVenturesBonus(int venturesBonus) {
        this.bonusForTakingVentures = venturesBonus;
    }

    /**
     * Get bonusForTakingTerritory
     *
     * @return bonusForTakingTerritory
     */
    public int getTerritoryBonus() {
        return bonusForTakingTerritory;
    }

    /**
     * Set territoryBonus
     *
     * @param territoryBonus territoryBonus
     */
    public void setTerritoryBonus(int territoryBonus) {
        bonusForTakingTerritory = territoryBonus;
    }

    /**
     * Get productionBonus
     *
     * @return productionBonus
     */
    public int getProductionBonus() {
        return productionBonus;
    }

    /**
     * Set productionBonus
     *
     * @param productionBonus productionBonus
     */
    public void setProductionBonus(int productionBonus) {
        this.productionBonus = productionBonus;
    }

    /**
     * Get harvesterBonus
     *
     * @return harvesterBonus
     */
    public int getHarvesterBonus() {
        return harvesterBonus;
    }

    /**
     * Set harvesterBonus
     *
     * @param harvesterBonus harvesterBonus
     */
    public void setHarvesterBonus(int harvesterBonus) {
        this.harvesterBonus = harvesterBonus;
    }

    /**
     * Set charactersBonus
     *
     * @param charactersBonus charactersBonus
     */
    public void setCharactersBonus(BonusForTakingCharacter charactersBonus) {
        this.charactersBonus = charactersBonus;
    }

    /**
     * Get buildingsBonus
     *
     * @return buildingsBonus
     */
    public BonusForTakingBuilding getBuildingsBonus() {
        return buildingsBonus;
    }

    /**
     * Set buildingsBonus
     *
     * @param buildingsBonus buildingsBonus
     */
    public void setBuildingsBonus(BonusForTakingBuilding buildingsBonus) {
        this.buildingsBonus = buildingsBonus;
    }

    /**
     * Get charactersBonus
     *
     * @return charactersBonus
     */
    public BonusForTakingCharacter getCharactersBonus() {
        return charactersBonus;
    }

    /**
     * Get militaryPointsBonus
     *
     * @return militaryPointsBonus
     */
    public int getMilitaryPointsBonus() {
        return militaryPointsBonus;
    }

    /**
     * Set militaryPointsBonus
     *
     * @param militaryPointsBonus militaryPointsBonus
     */
    public void setMilitaryPointsBonus(int militaryPointsBonus) {
        this.militaryPointsBonus = militaryPointsBonus;
    }

    /**
     * Get coinsBonus
     *
     * @return coinsBonus
     */
    public int getCoinsBonus() {
        return coinsBonus;
    }

    /**
     * Set coinsBonus
     *
     * @param coinsBonus coinsBonus
     */
    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonus = coinsBonus;
    }

    /**
     * Get servantsBonus
     *
     * @return servantsBonus
     */
    public int getServantsBonus() {
        return servantsBonus;
    }

    /**
     * Set servantsBonus
     *
     * @param servantsBonus servantsBonus
     */
    public void setServantsBonus(int servantsBonus) {
        this.servantsBonus = servantsBonus;
    }

    /**
     * Get stoneBonus
     *
     * @return stoneBonus
     */
    public int getStoneBonus() {
        return stoneBonus;
    }

    /**
     * Set stoneBonus
     *
     * @param stoneBonus stoneBonus
     */
    public void setStoneBonus(int stoneBonus) {
        this.stoneBonus = stoneBonus;
    }

    /**
     * Get woodBonus
     *
     * @return woodBonus
     */
    public int getWoodBonus() {
        return woodBonus;
    }

    /**
     * Set woodBonus
     *
     * @param woodBonus woodBonus
     */
    public void setWoodBonus(int woodBonus) {
        this.woodBonus = woodBonus;
    }
}