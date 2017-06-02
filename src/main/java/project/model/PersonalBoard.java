package project.model;


import project.controller.cardsfactory.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class PersonalBoard {



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


    private int endBonus;

    private List<LeaderCard> myLeaderCard;

    PersonalBoard(){
    	territories = new LinkedList<>();
    	buildings = new LinkedList<>();
    	myLeaderCard = new ArrayList<>();
	}

    public List<LeaderCard> getMyLeaderCard() {
        return myLeaderCard;
    }

    public List<TerritoryCard> getTerritories() {
		return territories;
	}


	public List<BuildingCard> getBuildings() {
		return buildings;
	}


	public List<VenturesCard> getVentures() {
		return ventures;
	}


	public List<CharacterCard> getCharacters() {
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