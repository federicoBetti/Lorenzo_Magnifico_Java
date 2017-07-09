package project.controller.cardsfactory;
import project.controller.effects.effectsfactory.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the immediateEffects loaded from json
 */
public class ImmediateEffectsFromJson {
    private List<TrisIE> tris;

    ImmediateEffectsFromJson(){
        this.tris = new ArrayList<>();
    }

    /**
     * Get tris
     *
     * @return tris
     */
    public List<TrisIE> getTris() {
        return tris;
    }

}
