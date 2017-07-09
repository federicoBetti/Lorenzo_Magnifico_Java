package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.*;

import java.io.Serializable;

/**
 * this represent the abstract class of updates. its extensions will inform each client of model's changes
 */
public abstract class Updates extends BonusInteraction implements Serializable{

    private String nicknameCurrentPlayer;

    /**
     * Constructor
     *
     * @param nicknameCurrentPlayer player's nickname
     */
    protected Updates(String nicknameCurrentPlayer){
        this.nicknameCurrentPlayer = nicknameCurrentPlayer;
    }

    /**
     * This method act the board's update in the client
     *
     * @param board board's reference
     */
    public void doUpdate(Board board){
    }

    /**
     * This method act the perosnal board's update in the client
     *
     * @param personalBoard personal board's reference
     * @return the personal board's reference
     */
    public PersonalBoard doUpdate(PersonalBoard personalBoard){
        return null;
    }

    /**
     * This method act the score's update in the client
     *
     * @param score score's reference
     * @return score's reference
     */
    public Score doUpdate(Score score){
        return null;
    }

    /**
     * This method act the familiars' update in the client
     *
     * @return famialiar's array
     */
    public FamilyMember[] doUpdateFamilyMembers(){
        return null;
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    public abstract String toScreen();

    /**
     * Get nicknameCurrentPlayer
     *
     * @return nicknameCurrentPlayer
     */
    public String getNicknameCurrentPlayer() {
        return nicknameCurrentPlayer;
    }
}
