package project.model;

import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.List;

/**
 * 
 */
public class Council extends Position implements Serializable{

    /**
     * Default constructor
     * @param familyMember
     */
    public Council(FamilyMember familyMember ) {
        setFamiliarOnThisPosition(familyMember);
        setOccupied(true);
    }

    public PlayerHandler findPlayer(List<PlayerHandler> players ){
        for ( PlayerHandler player : players ){
            if (getFamiliarOnThisPosition().getFamilyColour().contains(player.getFamilyColour()))
                return player;
        }
        return null;
    }

}