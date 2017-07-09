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

    /**
     * Default constructor
     */
    public ExcommunicationZone(ExcommunicationTile excommunicationTile) {
        this.cardForThisPeriod = excommunicationTile;
    }


    public ExcommunicationTile getCardForThisPeriod() {
        return cardForThisPeriod;
    }


}