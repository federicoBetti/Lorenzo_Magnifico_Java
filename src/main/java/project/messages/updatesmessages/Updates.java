package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.*;

import java.io.Serializable;

/**
 * this represent the abstract class of updates. its extenctions will inform every client of some moves
 */
public abstract class Updates extends BonusInteraction implements Serializable{

    private String nicknameCurrentPlayer;

    protected Updates(String nicknameCurrentPlayer){
        this.nicknameCurrentPlayer = nicknameCurrentPlayer;
    }

    public void doUpdate(Board board){
    }

    public PersonalBoard doUpdate(PersonalBoard personalBoard){
        return null;
    }

    public Score doUpdate(Score score){
        return null;
    }

    public FamilyMember[] doUpdateFamilyMembers(){
        return null;
    }
    public abstract String toScreen();
    public void stamp(){
    }

    public String getNicknameCurrentPlayer() {
        return nicknameCurrentPlayer;
    }
}
