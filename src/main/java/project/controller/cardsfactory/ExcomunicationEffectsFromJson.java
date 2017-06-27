package project.controller.cardsfactory;

import project.controller.effects.effectsfactory.TrisIE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 17/06/17.
 */
class ExcomunicationEffectsFromJson {
    private List<TrisIE> tris;

    public ExcomunicationEffectsFromJson(){
        this.tris = new ArrayList<>();
    }

    public List<TrisIE> getTris() {
        return tris;
    }
}
