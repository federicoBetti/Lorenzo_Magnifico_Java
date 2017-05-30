package Project.MODEL;

/**
 * 
 */
public class Council extends Position {

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