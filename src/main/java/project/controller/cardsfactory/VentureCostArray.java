package project.controller.cardsfactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the array of ventures' cost
 */
public class VentureCostArray implements Serializable{

    private List<VenturesCost> costArray;

    /**
     * Constructor
     */
    public VentureCostArray(){
        this.costArray = new ArrayList<>();
    }

    /**
     * Get list of ventures'cost
     *
     * @return costArray
     */
    public List<VenturesCost> getCostArray() {
        return costArray;
    }

}
