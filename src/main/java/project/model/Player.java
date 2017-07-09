package project.model;

import project.configurations.Configuration;
import project.controller.Constants;
import project.controller.cardsfactory.*;

import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * This class represents the player in the model
 */
public class Player implements Serializable{

	private String name;

	private PersonalBoard personalBoardReference;

	volatile private boolean isOn;

	private Score score;

	private FamilyMember[] allFamilyMembers;

	private String familyColour;

	/**
	 * Constructor
	 */
    protected Player(){
    	Configuration configuration = new Configuration();
    	
    	//personalBoardReference = new PersonalBoard();
		personalBoardReference = new PersonalBoard(1000);
    	score = new Score();
		allFamilyMembers = new FamilyMember[Constants.NUMBER_OF_FAMILIAR];
		try {
			configuration.loadFamilyMembers(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
    public String getName() {
        return name;
    }

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

	public String getFamilyColour() {
		return familyColour;
	}

	public void setFamilyColour(String familyColour) {
		this.familyColour = familyColour;
	}

	public void setFamilyColourInFamilyMembers( ){
		for ( FamilyMember familyMember : getAllFamilyMembers() ){
			if (!familyMember.getMyColour().equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL))
				familyMember.setFamilyColour(getFamilyColour());
			else
				familyMember.setFamilyColour("empty" + getFamilyColour());
		}
	}


}