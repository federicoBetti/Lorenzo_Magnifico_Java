package project.controller.cardsfactory;

/**
 * Created by raffaelebongo on 18/06/17.
 */
class TimerFromJson {

    private String startMatchTimerName;
    private int delaySm;
    private String skipTurnTimerName;
    private int delaySt;

    public TimerFromJson(){
    }

    public String getStartMatchTimerName() {
        return startMatchTimerName;
    }

    public void setStartMatchTimerName(String startMatchTimerName) {
        this.startMatchTimerName = startMatchTimerName;
    }

    public int getDelaySm() {
        return delaySm;
    }

    public void setDelaySm(int delaySm) {
        this.delaySm = delaySm;
    }

    public String getSkipTurnTimerName() {
        return skipTurnTimerName;
    }

    public void setSkipTurnTimerName(String skipTurnTimerName) {
        this.skipTurnTimerName = skipTurnTimerName;
    }

    public int getDelaySt() {
        return delaySt;
    }

    public void setDelaySt(int delaySt) {
        this.delaySt = delaySt;
    }
}
