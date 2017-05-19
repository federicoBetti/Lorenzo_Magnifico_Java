package Project.MODEL;


import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;

/**
 * 
 */
public class PersonalBoard {

    /**
     * Default constructor
     */
    public PersonalBoard() {
    }

    /**
     * 
     */
    private TerritoryCard[] territories;

    /**
     * 
     */
    private BuildingCard[] buildings;

    /**
     * 
     */
    private VenturesCard[] ventures;

    /**
     * 
     */
    private CharacterCard[] characters;

    /**
     * 
     */
    private Bonus bonusOnActions;

    /**
     * 
     */
    private Tile myTile;

    /**
     * 
     */
    private int stone;

    /**
     * 
     */
    private int wood;

    /**
     * 
     */
    private int coins;

    /**
     * 
     */
    private int servants;

    /**
     * 
     */
    private int endBonus;

	public TerritoryCard[] getTerritories() {
		return territories;
	}

	public void setTerritories(TerritoryCard[] territories) {
		this.territories = territories;
	}

	public BuildingCard[] getBuildings() {
		return buildings;
	}

	public void setBuildings(BuildingCard[] buildings) {
		this.buildings = buildings;
	}

	public VenturesCard[] getVentures() {
		return ventures;
	}

	public void setVentures(VenturesCard[] ventures) {
		this.ventures = ventures;
	}

	public CharacterCard[] getCharacters() {
		return characters;
	}

	public void setCharacters(CharacterCard[] characters) {
		this.characters = characters;
	}

	public Bonus getBonusOnActions() {
		return bonusOnActions;
	}

	public void setBonusOnActions(Bonus bonusOnActions) {
		this.bonusOnActions = bonusOnActions;
	}

	public Tile getMyTile() {
		return myTile;
	}

	public void setMyTile(Tile myTile) {
		this.myTile = myTile;
	}

	public int getStone() {
		return stone;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getServants() {
		return servants;
	}

	public void setServants(int servants) {
		this.servants = servants;
	}

	public int getEndBonus() {
		return endBonus;
	}

	public void setEndBonus(int endBonus) {
		this.endBonus = endBonus;
	}


}