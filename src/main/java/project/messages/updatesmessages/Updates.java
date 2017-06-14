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

    public void doUpdate(Board board){}
    public void doUpdate( PersonalBoard personalBoard ){}
    public void doUpdate(Score score){}

    public void doUpdate(FamilyMember[] familyMembersUi){}
}
