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

	/**
	 * Get isOn
	 *
	 * @return isOn
	 */
	public boolean isOn() {
		return isOn;
	}

	/**
	 * Set on
	 *
	 * @param on on
	 */
	public void setOn(boolean on) {
		isOn = on;
	}

	/**
	 * Set name
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get personalBoardReference
	 *
	 * @return personalBoardReference
	 */
	public PersonalBoard getPersonalBoardReference() {
		return personalBoardReference;
	}

	/**
	 * Set personalBoardReference
	 *
	 * @param personalBoardReference personalBoardReference
	 */
	public void setPersonalBoardReference(PersonalBoard personalBoardReference) {
		this.personalBoardReference = personalBoardReference;
	}

	/**
	 * Get score
	 *
	 * @return score
	 */
	public Score getScore() {
		return score;
    }

	/**
	 * Set score
	 *
	 * @param score score
	 */
	public void setScore(Score score) {
		this.score = score;
	}

	/**
	 * Get allFamilyMembers
	 *
	 * @return allFamilyMembers
	 */
	public FamilyMember[] getAllFamilyMembers() {
		return allFamilyMembers;
	}

	/**
	 * Set allFamilyMembers
	 *
	 * @param allFamilyMembers allFamilyMembers
	 */
	public void setAllFamilyMembers(FamilyMember[] allFamilyMembers) {
		this.allFamilyMembers = allFamilyMembers;
	}

	/**
	 * Get familyColour
	 *
	 * @return familyColour
	 */
	public String getFamilyColour() {
		return familyColour;
	}

	/**
	 * Set familyColour
	 *
	 * @param familyColour familyColour
	 */
	public void setFamilyColour(String familyColour) {
		this.familyColour = familyColour;
	}

	/**
	 * Set family colors in the family members
	 */
	public void setFamilyColourInFamilyMembers( ){
		for ( FamilyMember familyMember : getAllFamilyMembers() ){
			if (!familyMember.getMyColour().equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL))
				familyMember.setFamilyColour(getFamilyColour());
			else
				familyMember.setFamilyColour("empty" + getFamilyColour());
		}
	}


}