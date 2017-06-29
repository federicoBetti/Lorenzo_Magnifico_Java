package project.model;

import project.server.network.PlayerHandler;

import java.io.Serializable;

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
    }

    public Player findPlayer( Board board, String familyCoulor ){
        for ( PlayerHandler player : board.getTurn().getPlayerTurn() ){
            if ( player.getFamilyColour().equals(familyCoulor))
                return player;
        }
        return null;
    }

}