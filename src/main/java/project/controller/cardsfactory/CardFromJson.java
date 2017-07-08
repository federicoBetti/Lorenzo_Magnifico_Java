package project.controller.cardsfactory;

/**
 * This
 */
public class CardFromJson {
    private Anagrafic anagrafic;
    private ImmediateEffectsFromJson immediateEffect;
    private PermanentEffectFromJson permanentEffect;

    public CardFromJson(){

       this.immediateEffect = new ImmediateEffectsFromJson();
       this.permanentEffect = new PermanentEffectFromJson();
       this.anagrafic = new Anagrafic();
    }

    public Anagrafic getAnagrafic() {
        return anagrafic;
    }

    public ImmediateEffectsFromJson getImmediateEffect() {
        return immediateEffect;
    }

    public PermanentEffectFromJson getPermanentEffect() {
        return permanentEffect;
    }

}
