package ServerModel;

/**
 * Created by Rita1 on 08/05/2017.
 */
public class PlayerAdvanced extends Player {
    private LeaderEffectsUsefull leaderEffectsUsefull;
    private ExcommunicationEffectsUseful excommunicationEffectsUseful;

    public ExcommunicationEffectsUseful getExcommunicationEffectsUseful() {
        return excommunicationEffectsUseful;
    }

    public void setExcommunicationEffectsUseful(ExcommunicationEffectsUseful excommunicationEffectsUseful) {
        this.excommunicationEffectsUseful = excommunicationEffectsUseful;
    }

    public LeaderEffectsUsefull getLeaderEffectsUsefull() {
        return leaderEffectsUsefull;
    }

    public void setLeaderEffectsUsefull(LeaderEffectsUsefull leaderEffectsUsefull) {
        this.leaderEffectsUsefull = leaderEffectsUsefull;
    }
}
