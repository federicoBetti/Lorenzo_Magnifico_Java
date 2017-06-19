package project.model;

import java.io.Serializable;

/**
 * 
 */
public class Council extends Position implements Serializable{

    private Player player;
    /**
     * Default constructor
     * @param familyMember
     */
    public Council(FamilyMember familyMember, Player player) {
        setFamiliarOnThisPosition(familyMember);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}