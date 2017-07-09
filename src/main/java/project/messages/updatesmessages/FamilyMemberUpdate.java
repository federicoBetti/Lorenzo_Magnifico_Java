package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * Object sends to the client for notifying that the players' family members have been changed
 */
public class FamilyMemberUpdate extends Updates implements Serializable {
    private FamilyMember[] familyMembers;

    /**
     * Constructor
     *
     * @param player playerHandler's reference
     * @param nickname player's nickname
     */
    public FamilyMemberUpdate(PlayerHandler player, String nickname) {
        super(nickname);
        familyMembers = player.getAllFamilyMembers();
    }

    /**
     * This method act the familiars' update in the client
     *
     * @return famialiar's array
     */
    @Override
    public FamilyMember[] doUpdateFamilyMembers( ){
        return familyMembers;
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return null;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.FAMILY_MEMBER_UPDATE;
    }
}
