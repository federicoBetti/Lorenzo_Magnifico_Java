package project.messages.updatesmessages;

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
        //todo
        return null;
    }

    @Override
    public void doUpdate(PersonalBoard personalBoard) {
        personalBoard = this.personalBoard;
    }
}
