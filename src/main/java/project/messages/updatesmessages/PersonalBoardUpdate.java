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

    public PersonalBoardUpdate(PlayerHandler player ){
        super();
        personalBoard = player.getPersonalBoardReference();
    }

    @Override
    public String toString() {
        return Constants.PERSONAL_BOARD_UPDATE;
    }

    @Override
    public void doUpdate(PersonalBoard personalBoard) {
        personalBoard = this.personalBoard;
    }

    @Override
    public String toScreen() {
        return "The actual player's personal board has updated:\n" +
                "Building Cards: "); printCards(personalBoard.getBuildings());
        pRed.print("Character Cards: ");printCards(personalBoard.getCharacters());
        pRed.print("Venture Cards: ");printCards(personalBoard.getVentures());
        pRed.print("Territory Cards: ");printCards(personalBoard.getTerritories());
        pRed.print("Wood: ");pBlue.println(personalBoard.getWood());
        pRed.print("Servants: ");pBlue.println(personalBoard.getServants());
        pRed.print("Stone: "); pBlue.println(personalBoard.getStone());
        pRed.print("Coins: ");pBlue.println(personalBoard.getCoins());

    }

    public void printCards(List<? extends DevelopmentCard> cards ){
        for ( DevelopmentCard card : cards ) {
            pBlue.println("                " + card.getName());
        }
    }
}
