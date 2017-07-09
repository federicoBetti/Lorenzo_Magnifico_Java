package project.model;

import project.controller.cardsfactory.ExcommunicationTile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This calss represent the excommunication zone
 */
public class ExcommunicationZone extends Position implements Serializable {

    private ExcommunicationTile cardForThisPeriod;

    /**
     * Constructor
     */
    public ExcommunicationZone(ExcommunicationTile excommunicationTile) {
        this.cardForThisPeriod = excommunicationTile;
    }

    /**
     * Get cardForThisPeriod
     *
     * @return cardForThisPeriod
     */
    public ExcommunicationTile getCardForThisPeriod() {
        return cardForThisPeriod;
    }


}