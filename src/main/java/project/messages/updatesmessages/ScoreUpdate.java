package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Score;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class ScoreUpdate extends Updates implements Serializable{
    Score score;

    public ScoreUpdate(PlayerHandler player ){
        super();
        this.score = player.getScore();
    }

    public void doUpdate( Score scoreUi ){
        scoreUi = score;
    }

    @Override
    public String toString() {
        return Constants.SCORE_UPDATE;
    }

    @Override
    public String toScreen() {
        return "The actual player's score is changed:\n" +
        "Victory points: " + String.valueOf(score.getVictoryPoints()) + "\n" +
        "Faith points: " + String.valueOf(score.getFaithPoints()) + "\n" +
        "Military Points: " + String.valueOf(score.getMilitaryPoints()) + "\n";

    }
}
