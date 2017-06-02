package project.controller.cardsfactory;
import project.controller.effects.effectsfactory.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class PermanentEffectFromJson {
    List<PokerPE> poker;

    public PermanentEffectFromJson(){
        this.poker = new ArrayList<>();

    }

    public List<PokerPE> getPoker() {
        return poker;
    }

    public void setPoker(List<PokerPE> poker) {
        this.poker = poker;
    }
}
