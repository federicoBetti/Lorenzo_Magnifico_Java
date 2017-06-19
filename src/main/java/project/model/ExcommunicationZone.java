package project.model;

import project.controller.cardsfactory.ExcommunicationTile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 */
public class ExcommunicationZone extends Position implements Serializable {


    /**
     * excomunication card on this zone
     */
    private ExcommunicationTile cardForThisPeriod;

    private ArrayList<Player> playersHaveTakenThisExcomunication;
    /**
     * Default constructor
     */
    public ExcommunicationZone(ExcommunicationTile excommunicationTile) {
        this.cardForThisPeriod = excommunicationTile;
        playersHaveTakenThisExcomunication = new ArrayList<>();
    }


    public ExcommunicationTile getCardForThisPeriod() {
        return cardForThisPeriod;
    }

    public void addPlayer(Player player){
        playersHaveTakenThisExcomunication.add(player);
    }


}