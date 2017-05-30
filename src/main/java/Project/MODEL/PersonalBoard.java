package Project.MODEL;


import Project.Controller.CardsFactory.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 */
public class PersonalBoard {


    /**
     * 
     */
    private LinkedList<TerritoryCard>territories;

    /**
     * 
     */
    private LinkedList<BuildingCard> buildings;

    /**
     * 
     */
    private LinkedList<VenturesCard> ventures;

    /**
     * 
     */
    private LinkedList<CharacterCard> characters;

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

    private ArrayList<LeaderCard> myLeaderCard;

    PersonalBoard(){
    	territories = new LinkedList<>();
    	buildings = new LinkedList<>();
	}

    public ArrayList<LeaderCard> getMyLeaderCard() {
        return myLeaderCard;
    }

    public LinkedList<TerritoryCard> getTerritories() {
		return territories;
	}


	public LinkedList<BuildingCard> getBuildings() {
		return buildings;
	}


	public LinkedList<VenturesCard> getVentures() {
		return ventures;
	}


	public LinkedList<CharacterCard> getCharacters() {
		return characters;
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