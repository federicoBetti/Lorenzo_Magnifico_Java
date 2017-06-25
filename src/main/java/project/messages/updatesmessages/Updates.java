package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.*;

import java.io.Serializable;
import java.util.ArrayList;

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
}
