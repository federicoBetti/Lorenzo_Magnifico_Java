package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.Board;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.model.Score;

import java.io.Serializable;

/**
 * this represent the abstract class of updates. its extenctions will inform every client of some moves
 */
public abstract class Updates extends BonusInteraction implements Serializable{

    String nicknameCurrentPlayer;

    public Updates(String nicknameCurrentPlayer){
        this.nicknameCurrentPlayer = nicknameCurrentPlayer;
    }

    public void doUpdate(Board board){
    }

    public PersonalBoard doUpdate(){
        return null;
    }

    public void doUpdate(Score score){}

    public FamilyMember[] doUpdate(FamilyMember[] familyMembersUi){
        return familyMembersUi;
    }
    public abstract String toScreen();
}
