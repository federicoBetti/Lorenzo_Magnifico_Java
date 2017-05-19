package Project.Controller.CardsFactory;
import Project.Controller.Effects.EffectsFactory.*;
import java.util.ArrayList;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class PermanentEffectFromJson {
    ArrayList<PokerPE> poker;

    public PermanentEffectFromJson(){
        ArrayList<PokerPE> array = new ArrayList<PokerPE>();
        this.poker = array;
    }

    public ArrayList<PokerPE> getPoker() {
        return poker;
    }

    public void setPoker(ArrayList<PokerPE> poker) {
        this.poker = poker;
    }
}
