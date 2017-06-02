package project.controller.cardsfactory;
import project.controller.effects.effectsfactory.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class ImmediateEffectsFromJson {
    List<TrisIE> tris;

    public ImmediateEffectsFromJson(){

        this.tris = new ArrayList<>();
    }

    public List<TrisIE> getTris() {
        return tris;
    }

}
