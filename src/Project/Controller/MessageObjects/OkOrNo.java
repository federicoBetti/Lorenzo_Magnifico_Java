package Project.Controller.MessageObjects;


import Project.Controller.MessageObjects.BonusInteraction;

public class OkOrNo extends BonusInteraction {
    private boolean ok = false;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
