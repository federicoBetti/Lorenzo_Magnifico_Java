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

    public void setAnagrafic(Anagrafic anagrafic) {
        this.anagrafic = anagrafic;
    }

    public ImmediateEffectsFromJson getImmediateEffect() {
        return immediateEffect;
    }

    public void setImmediateEffect(ImmediateEffectsFromJson immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public PermanentEffectFromJson getPermanentEffect() {
        return permanentEffect;
    }

    public void setPermanentEffect(PermanentEffectFromJson permanentEffect) {
        this.permanentEffect = permanentEffect;
    }


}
