package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.List;

/**
 * Object sends to the client for notifying that the player's personal board has been changed
 */
public class PersonalBoardUpdate extends Updates implements Serializable {
    private PersonalBoard personalBoard;

    /**
     * Constructor
     *
     * @param player playerHandler's reference
     * @param nickname player's nickname
     */
    public PersonalBoardUpdate(PlayerHandler player, String nickname ){
        super(nickname);
        personalBoard = player.getPersonalBoardReference();
    }

    /**
     * String describes the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.PERSONAL_BOARD_UPDATE;
    }

    /**
     * This method act the perosnal board's update in the client
     *
     * @param personalB personal board's reference
     * @return the personal board's reference
     */
    @Override
    public PersonalBoard doUpdate(PersonalBoard personalB) {
        return personalBoard;
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return  getNicknameCurrentPlayer() + "'s personal board has updated:\n" +
                "Building Cards: \n" + createCardsString(personalBoard.getBuildings())+
                "\nCharacter Cards: \n" + createCardsString(personalBoard.getCharacters()) +
                "\nVenture Cards: \n" + createCardsString(personalBoard.getVentures()) +
                "\nTerritory Cards: \n" + createCardsString(personalBoard.getTerritories()) +
                "\nWood: " + String.valueOf(personalBoard.getWood()) + "\n" +
                "Servants: " + String.valueOf(personalBoard.getServants()) + "\n" +
                "Stone: " + String.valueOf(personalBoard.getStone()) + "\n" +
                "Coins: " + String.valueOf(personalBoard.getCoins());
    }

    /**
     * This method creates the cards' string
     *
     * @param cards list of cards
     * @return the string
     */
    private String createCardsString(List<? extends DevelopmentCard> cards){

        if ( cards.size() == 0 )
            return " ";

        int i = 1;
        String res = i + ") ";
        for ( DevelopmentCard card : cards ) {
            res += card.getName() +"\n";
            i++;
        }
        return res;
    }
}
