package project.controller.cardsfactory;

/**
 * This class is the class created loading a generic development card with the json file
 */
public class CardFromJson {
    private Anagrafic anagrafic;
    private ImmediateEffectsFromJson immediateEffect;
    private PermanentEffectFromJson permanentEffect;

    /**
     * Constructor
     */
    public CardFromJson(){

       this.immediateEffect = new ImmediateEffectsFromJson();
       this.permanentEffect = new PermanentEffectFromJson();
       this.anagrafic = new Anagrafic();
    }

    /**
     * Get anagrafic
     *
     * @return anagrafic
     */
    public Anagrafic getAnagrafic() {
        return anagrafic;
    }

    /**
     * Get Immediate effect
     *
     * @return immediate effects
     */
    public ImmediateEffectsFromJson getImmediateEffect() {
        return immediateEffect;
    }

    /**
     * Get permanent effects
     *
     * @return permanent effects
     */
    public PermanentEffectFromJson getPermanentEffect() {
        return permanentEffect;
    }

}
