package Project.MODEL;

import Project.Controller.CardsFactory.ExcommunitationTile;

import java.util.ArrayList;

/**
 * 
 */
public class ExcommunicationZone extends Position {


    /**
     * excomunication card on this zone
     */
    private ExcommunitationTile cardForThisPeriod;

    private ArrayList<Player> playersHaveTakenThisExcomunication;
    /**
     * Default constructor
     */
    public ExcommunicationZone(ExcommunitationTile excommunitationTile) {
        this.cardForThisPeriod = excommunitationTile;
        playersHaveTakenThisExcomunication = new ArrayList<>();
    }


    public ExcommunitationTile getCardForThisPeriod() {
        return cardForThisPeriod;
    }

    public void addPlayer(Player player){
        playersHaveTakenThisExcomunication.add(player);
    }


}