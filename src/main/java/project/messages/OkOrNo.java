package project.messages;


import project.controller.Constants;

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

    @Override
    public String toString() {
        return Constants.OK_OR_NO;
    }
}
