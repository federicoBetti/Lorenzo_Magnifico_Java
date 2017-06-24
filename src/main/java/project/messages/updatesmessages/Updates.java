package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.Board;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.model.Score;

/**
 * this represent the abstract class of updates. its extenctions will inform every client of some moves
 */
public abstract class Updates extends BonusInteraction {

    String nicknameCurrentPlayer;

    public Updates(String nicknameCurrentPlayer){
        this.nicknameCurrentPlayer = nicknameCurrentPlayer;
    }

    public void doUpdate(Board board){}

    public PersonalBoard doUpdate(){
        return null;
    }

    public void doUpdate(Score score){}

    public void doUpdate(FamilyMember[] familyMembersUi){}
    public abstract String toScreen();
}
