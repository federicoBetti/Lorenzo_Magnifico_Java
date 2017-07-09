package project.configurations;

/**
 * This class is used for storing all timers' names and delay
 */
public class TimerSettings {

    private String startMatchTimerName;
    private int delayTimerStartMatch;
    private String skipTurnTimerName;
    private int delayTimerSkipTurn;
    private String delayTimerPrayingName;
    private int delayTimerPraying;

    /**
     * Get the start match timer's name
     *
     * @return timer's name
     */
    public String getStartMatchTimerName() {
        return startMatchTimerName;
    }

    /**
     * Get the start match timer's delay
     *
     * @return timer's delay
     */
    public int getDelayTimerStartMatch() {
        return delayTimerStartMatch;
    }

    /**
     * Get the skip turn timer's name
     *
     * @return timer's name
     */
    public String getSkipTurnTimerName() {
        return skipTurnTimerName;
    }

    /**
     * Get the skip turn timer's delay
     *
     * @return timer's delay
     */
    public int getDelayTimerSkipTurn() {
        return delayTimerSkipTurn;
    }

    /**
     * Get the praying timer's delay
     *
     * @return timer's delay
     */
    public long getDelayTimerPraying() {
        return delayTimerPraying;
    }

    /**
     * Get the praying timer's name
     *
     * @return timer's name
     */
    public String getDelayTimerPrayingName() {
        return delayTimerPrayingName;
    }
}
