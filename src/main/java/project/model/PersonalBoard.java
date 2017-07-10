package project.model;


import project.controller.cardsfactory.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the personal Board
 */
public class PersonalBoard implements Serializable{


    private List<TerritoryCard> territories;


    private List<BuildingCard> buildings;


    private List<VenturesCard> ventures;


    private List<CharacterCard> characters;


    private Bonus bonusOnActions;


    private Tile myTile;


    private int stone;


    private int wood;


    private int coins;

    private int servants;

    private List<LeaderCard> myLeaderCard;

	/**
	 * Constructor
	 */
	public PersonalBoard(){
    	bonusOnActions = new Bonus();
    	territories = new ArrayList<>();
    	buildings = new ArrayList<>();
    	characters = new ArrayList<>();
    	ventures = new ArrayList<>();
    	myLeaderCard = new ArrayList<>();
	}


	/**
	 * This method build a string for representing the personal board
	 *
	 * @return the string
	 */
	public String toScreen() {
		return "Building Cards: \n" + createCardsString(getBuildings())+
				"\nCharacter Cards: \n" + createCardsString(getCharacters()) +
				"\nVenture Cards: \n" + createCardsString(getVentures()) +
				"\nTerritory Cards: \n" + createCardsString(getTerritories()) +
				"\nWood: " + String.valueOf(getWood()) + "\n" +
				"Servants: " + String.valueOf(getServants()) + "\n" +
				"Stone: " + String.valueOf(getStone()) + "\n" +
				"Coins: " + String.valueOf(getCoins());
	}

	/**
	 * This method creates the string for represents the list of cards
	 *
	 * @param cards list of cards
	 * @return the string
	 */
	private String createCardsString(List<? extends DevelopmentCard> cards){

		if ( cards.size() == 0 )
			return " ";

		String res = "";
		for ( DevelopmentCard card : cards ) {
			res += card.getName() +"\n";
		}
		return res;
	}

	/**
	 * Get myLeaderCard
	 *
	 * @return myLeaderCard
	 */
    public List<LeaderCard> getMyLeaderCard() {
        return myLeaderCard;
    }

	/**
	 * Get territories
	 *
	 * @return territories
	 */
    public List<TerritoryCard> getTerritories() {
		return territories;
	}

	/**
	 * Get buildings
	 *
	 * @return buildings
	 */
	public List<BuildingCard> getBuildings() {
		return buildings;
	}

	/**
	 * Get ventures
	 *
	 * @return ventures
	 */
	public List<VenturesCard> getVentures() {
		return ventures;
	}

	/**
	 * Get characters
	 *
	 * @return characters
	 */
	public List<CharacterCard> getCharacters() {
		return characters;
	}

	/**
	 * Get bonusOnActions
	 *
	 * @return bonusOnActions
	 */
	public Bonus getBonusOnActions() {
		return bonusOnActions;
	}

	/**
	 * Set bonusOnActions
	 *
	 * @param bonusOnActions bonusOnActions
	 */
	void setBonusOnActions(Bonus bonusOnActions) {
		this.bonusOnActions = bonusOnActions;
	}

	/**
	 * Get myTile
	 *
	 * @return myTile
	 */
	public Tile getMyTile() {
		return myTile;
	}

	/**
	 * Set myTile
	 *
	 * @param myTile myTile
	 */
	public void setMyTile(Tile myTile) {
		this.myTile = myTile;
	}

	/**
	 * Get stone
	 *
	 * @return stone
	 */
	public int getStone() {
		return stone;
	}

	/**
	 * Set stone
	 *
	 * @param stone stone
	 */
	public void setStone(int stone) {
		this.stone = stone;
	}

	/**
	 * Get wood
	 *
	 * @return wood
	 */
	public int getWood() {
		return wood;
	}

	/**
	 * Set wood
	 *
	 * @param wood wood
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}

	/**
	 * Get coins
	 *
	 * @return coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Set coins
	 *
	 * @param coins coins
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * Get servants
	 *
	 * @return servants
	 */
	public int getServants() {
		return servants;
	}

	/**
	 * Set servants
	 *
	 * @param servants servants
	 */
	public void setServants(int servants) {
		this.servants = servants;
	}

	/**
	 * Set buildings
	 *
	 * @param buildings buildings
	 */
	public void setBuildings(List<BuildingCard> buildings) {
		this.buildings = buildings;
	}

	/**
	 * This method add coins to the personal board
	 *
	 * @param coinsToAdd coinsToAdd
	 */
	public void addCoins(int coinsToAdd) {
		int coinsBonus = bonusOnActions.getCoinsBonus();
		if (coinsToAdd < 0){
			coins += coinsToAdd;
			if (coins < 0)
				coins = 0;
			return;
		}
		if (coinsToAdd + coinsBonus < 0) return;
		coins = coins + coinsToAdd + coinsBonus;
	}

	/**
	 * This method add stone to the personal board
	 *
	 * @param stoneToAdd stoneToAdd
	 */
	public void addStone(int stoneToAdd) {
		int stoneBonus = bonusOnActions.getStoneBonus();
		if (stoneToAdd < 0){
			stone += stoneToAdd;
			if (stone < 0)
				stone = 0;
			return;
		}
		if (stoneToAdd + stoneBonus < 0) return;
		stone = stone + stoneToAdd + stoneBonus;
	}

	/**
	 * This method add wood to the personal board
	 *
	 * @param woodToAdd woodToAdd
	 */
	public void addWood(int woodToAdd) {
		int woodBonus = bonusOnActions.getWoodBonus();
		if (woodToAdd < 0){
			wood += woodToAdd;
			if (wood < 0)
				wood = 0;
			return;
		}
		if (woodToAdd + woodBonus < 0)
			return;
		wood = wood + woodToAdd + woodBonus;
	}

	/**
	 * This method add servants to the personal board
	 *
	 * @param servantsToAdd servantsToAdd
	 */
	public void addServants(int servantsToAdd){
		int servantsBonus = bonusOnActions.getServantsBonus();
		if (servantsToAdd < 0){
			servants += servantsToAdd;
			if (servants < 0)
				servants = 0;
			return;
		}
		if (servantsToAdd + servantsBonus < 0)
			return;
		servants = servants + servantsToAdd + servantsBonus;
	}
}