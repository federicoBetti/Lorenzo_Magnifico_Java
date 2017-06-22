package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class FamilyMemberUpdate extends Updates implements Serializable {
    FamilyMember[] familyMembers;

    public FamilyMemberUpdate(PlayerHandler player) {
        super();
        familyMembers = player.getAllFamilyMembers();
    }

    @Override
    public void doUpdate( FamilyMember[] familyMembersUi ){
        familyMembersUi = familyMembers;
    }

    @Override
    public String toScreen() {

    }

    @Override
    public String toString() {
        return Constants.FAMILY_MEMBER_UPDATE;
    }
}
