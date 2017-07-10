package project.controller.cardsfactory;
import project.controller.effects.effectsfactory.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the permanent effects loaded from Json
 */
public class PermanentEffectFromJson {
    private List<PokerPE> poker;

    /**
     * Constructor
     */
    PermanentEffectFromJson(){
        this.poker = new ArrayList<>();

    }

    /**
     * Get poker
     *
     * @return poker
     */
    public List<PokerPE> getPoker() {
        return poker;
    }
}
