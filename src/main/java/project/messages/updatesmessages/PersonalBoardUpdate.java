package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.DevelopmentCard;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.io.Serializable;
import java.util.List;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class PersonalBoardUpdate extends Updates implements Serializable {
    private PersonalBoard personalBoard;

    public PersonalBoardUpdate(PlayerHandler player, String nickname ){
        super(nickname);
        personalBoard = player.getPersonalBoardReference();
    }

    @Override
    public String toString() {
        return Constants.PERSONAL_BOARD_UPDATE;
    }

    @Override
    public PersonalBoard doUpdatePersonalBoard(){
        return personalBoard;
    }

    @Override
    public String toScreen() {
        return  "The current player's personal board has updated:\n" +
                "Building Cards: \n" + createCardsString(personalBoard.getBuildings())+
                "\nCharacter Cards: \n" + createCardsString(personalBoard.getCharacters()) +
                "\nVenture Cards: \n" + createCardsString(personalBoard.getVentures()) +
                "\nTerritory Cards: \n" + createCardsString(personalBoard.getTerritories()) +
                "\nWood: " + String.valueOf(personalBoard.getWood()) + "\n" +
                "Servants: " + String.valueOf(personalBoard.getServants()) + "\n" +
                "Stone: " + String.valueOf(personalBoard.getStone()) + "\n" +
                "Coins: " + String.valueOf(personalBoard.getCoins());
    }

    public String createCardsString(List<? extends DevelopmentCard> cards ){

        if ( cards.size() == 0 )
            return " ";

        int i = 0;
        String res = i + ") ";
        for ( DevelopmentCard card : cards ) {
            res += "                " + card.getName() +"\n";
            i++;
        }
        return res;
    }
}
