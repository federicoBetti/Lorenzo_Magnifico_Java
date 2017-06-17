package project.controller.cardsfactory;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class CardFromJson {
    Anagrafic anagrafic;
    ImmediateEffectsFromJson immediateEffect;
    PermanentEffectFromJson permanentEffect;

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
