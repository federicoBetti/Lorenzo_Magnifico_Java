package project.model;

import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents a council position
 */
public class Council extends Position implements Serializable{

    /**
     * Constructor
     * @param familyMember familiar
     */
    public Council(FamilyMember familyMember ) {
        setFamiliarOnThisPosition(familyMember);
        setOccupied(true);
    }

    /**
     * This method is used for finding a player in a list of players
     *
     * @param players list of players
     * @return the player
     */
    public PlayerHandler findPlayer(List<PlayerHandler> players ){
        for ( PlayerHandler player : players ){
            if (getFamiliarOnThisPosition().getFamilyColour().contains(player.getFamilyColour()))
                return player;
        }
        return null;
    }

}