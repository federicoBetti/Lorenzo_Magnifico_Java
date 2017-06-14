package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class PersonalBoardUpdate extends Updates {
    private PersonalBoard personalBoard;

    public PersonalBoardUpdate(PlayerHandler player ){
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
}
