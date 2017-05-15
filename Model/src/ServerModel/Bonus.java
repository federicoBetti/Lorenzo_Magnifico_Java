package ServerModel;

/**
 * 
 */
public class Bonus {


    private int bonusForTakingTerritory = 0;

    private int ProductionBonus = 0;

    private int HarvesterBonus = 0;

    private BonusForTakingCharacter charactersBonus;

    private BonusForTakingBuilding buildingsBonus;

    private int coinsBonusForTakingCharactersCard = 0;

    private int bonusForTakingVentures = 0;


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

    public int getCoinsBonus() {
        return coinsBonusForTakingCharactersCard;
    }

    public void setCoinsBonus(int coinsBonus) {
        this.coinsBonusForTakingCharactersCard = coinsBonus;
    }


}