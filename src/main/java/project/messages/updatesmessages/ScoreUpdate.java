package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Score;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ScoreUpdate extends Updates {
    Score score;

    public ScoreUpdate(PlayerHandler player ){
        this.score = player.getScore();
    }

    public void doUpdate( Score scoreUi ){
        scoreUi = score;
    }

    @Override
    public String toString() {
        return Constants.SCORE_UPDATE;
    }
}
