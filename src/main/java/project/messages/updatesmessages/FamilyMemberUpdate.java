package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class FamilyMemberUpdate extends Updates {
    FamilyMember[] familyMembers;

    public FamilyMemberUpdate(PlayerHandler player) {
        familyMembers = player.getAllFamilyMembers();
    }

    @Override
    public void doUpdate( FamilyMember[] familyMembersUi ){
        familyMembersUi = familyMembers;
    }

    @Override
    public String toString() {
        return Constants.FAMILY_MEMBER_UPDATE;
    }
}
