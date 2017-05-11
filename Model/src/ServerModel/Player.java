package ServerModel;

import java.util.*;

/**
 * 
 */
public class Player {

    /**
     * Default constructor
     */
    public Player() {
    }

    private String name;

    private PersonalBoard personalBoardReference;

    private Score score;

    private FamilyMember[] pedone;

    private int turnOrder;

    private int finalPointBonus;



    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonalBoard getPersonalBoardReference() {
		return personalBoardReference;
	}

	public void setPersonalBoardReference(PersonalBoard personalBoardReference) {
		this.personalBoardReference = personalBoardReference;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public FamilyMember[] getPedone() {
		return pedone;
	}

	public void setPedone(FamilyMember[] pedone) {
		this.pedone = pedone;
	}

	public int getTurnOrder() {
		return turnOrder;
	}

	public void setTurnOrder(int turnOrder) {
		this.turnOrder = turnOrder;
	}

	public int getFinalPointBonus() {
		return finalPointBonus;
	}

	public void setFinalPointBonus(int finalPointBonus) {
		this.finalPointBonus = finalPointBonus;
	}


    public void checkFinalPointsterritory() {
        // TODO implement here
    }

    public void checkFinalPointCharacters() {
        // TODO implement here
    }

    public void checkFinalPointResources() {
        // TODO implement here
    }

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