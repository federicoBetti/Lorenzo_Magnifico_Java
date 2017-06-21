package project.configurations;

/**
 * Created by raffaelebongo on 19/06/17.
 */
public class TimerSettings {

    String startMatchTimerName;
    int delayTimerStartMatch;
    String skipTurnTimerName;
    int delayTimerSkipTurn;

    public String getStartMatchTimerName() {
        return startMatchTimerName;
    }

    public void setStartMatchTimerName(String startMatchTimerName) {
        this.startMatchTimerName = startMatchTimerName;
    }

    public int getDelayTimerStartMatch() {
        return delayTimerStartMatch;
    }

    public void setDelayTimerStartMatch(int delayTimerStartMatch) {
        this.delayTimerStartMatch = delayTimerStartMatch;
    }

    public String getSkipTurnTimerName() {
        return skipTurnTimerName;
    }

    public void setSkipTurnTimerName(String skipTurnTimerName) {
        this.skipTurnTimerName = skipTurnTimerName;
    }

    public int getDelayTimerSkipTurn() {
        return delayTimerSkipTurn;
    }

    public void setDelayTimerSkipTurn(int delayTimerSkipTurn) {
        this.delayTimerSkipTurn = delayTimerSkipTurn;
    }
}
