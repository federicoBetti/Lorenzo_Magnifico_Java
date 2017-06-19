package project.controller.cardsfactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class VentureCostArray implements Serializable{

    private List<VenturesCost> costArray;

    public VentureCostArray(){
        this.costArray = new ArrayList<>();
    }

    public List<VenturesCost> getCostArray() {
        return costArray;
    }

    public void setCostArray(List<VenturesCost> costArray) {
        this.costArray = costArray;
    }
}
