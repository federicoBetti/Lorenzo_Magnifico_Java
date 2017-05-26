package Project.toDelete;


public class OkOrNo extends BonusInteraction {
    private boolean ok;

    public OkOrNo(boolean b){
        this.ok = b;
    }

    public OkOrNo(){
        this.ok = false;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
