package Project.toDelete;


import Project.toDelete.BonusInteraction;

public class OkOrNo extends BonusInteraction {
    private boolean ok;

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
