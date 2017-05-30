package Project.Controller.CardsFactory;
import Project.Controller.Effects.EffectsFactory.*;
import java.util.ArrayList;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class ImmediateEffectsFromJson {
    ArrayList<TrisIE> tris;

    public ImmediateEffectsFromJson(){
        ArrayList<TrisIE> array = new ArrayList<TrisIE>();
        this.tris = array;
    }

    public ArrayList<TrisIE> getTris() {
        return tris;
    }

    public void setTris(ArrayList<TrisIE> tris) {
        this.tris = tris;
    }
}
