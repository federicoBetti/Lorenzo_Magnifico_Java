package ServerModel;

import java.util.*;

/**
 * 
 */
public class SupportFunction {

    /**
     * Default constructor
     */
    public SupportFunction() {
    }

    /**
     * @param position 
     * @param zone 
     * @return
     */
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!zone[position].isOccupied()){
            zone[position].setOccupied(true);
            zone[position].setFamiliarOnThisPosition(familyMember);
            return true;
        }
        else
            return false;
    }

    /**
     * @param position 
     * @param familiar 
     * @return
     */
    public boolean Check_Dice(Position position, FamilyMember familiar) {
        // TODO implement here
        return false;
    }

    /**
     * @param player 
     * @param resource 
     * @param quantity 
     * @return
     */
    public void Update_Resource(Player player, int resource, int quantity) {
        // TODO implement here
        return null;
    }

    /**
     * @param player 
     * @param point_type 
     * @param quantity 
     * @return
     */
    public void Update_Points(Player player, int point_type, int quantity) {
        // TODO implement here
        return null;
    }

    /**
     * @param player 
     * @param PrivilegioBannato 
     * @return
     */
    public int  Add_Privilege(Player player, Set<int> PrivilegioBannato) {
        // TODO implement here
        return 0 ;
    }

    /**
     * @param player 
     * @param CardList 
     * @return
     */
    public boolean Check_Upgrade_On_Production(Player player, ArrayList<Card> CardList) {
        // TODO implement here
        return false;
    }

}