package project.model;

import project.controller.cardsfactory.*;
import project.messages.LeaderEffectsUsefull;

import java.io.Serializable;

/**
 * 
 */
public class Player implements Serializable{

	private String name;

	private PersonalBoard personalBoardReference;

	private boolean isOn;

	private Score score;

	private FamilyMember[] allFamilyMembers;

	private int turnOrder;

	private String familyColour;

	public String getName() {
		return name;
	}

    private LeaderEffectsUsefull leaderEffectsUsefull;

    private ExcommunicationEffectsUseful excommunicationEffectsUseful;



	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean on) {
		isOn = on;
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

	public FamilyMember[] getAllFamilyMembers() {
		return allFamilyMembers;
	}

	public void setAllFamilyMembers(FamilyMember[] allFamilyMembers) {
		this.allFamilyMembers = allFamilyMembers;
	}

	public int getTurnOrder() {
		return turnOrder;
	}

	public void setTurnOrder(int turnOrder) {
		this.turnOrder = turnOrder;
	}

    public void checkFinalPointsTerritory() {
        // TODO implement here
    }

    public void checkFinalPointCharacters() {
        // TODO implement here
    }

    public void checkFinalPointResources() {
        // TODO implement here
    }

    // TODO eliminare le cose qua sotto




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

	public String getFamilyColour() {
		return familyColour;
	}

	public void setFamilyColour(String familyColour) {
		this.familyColour = familyColour;
	}


}