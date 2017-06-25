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

    public ScoreUpdate(PlayerHandler player, String nickname ){
        super(nickname);
        this.score = player.getScore();
    }

    @Override
    public Score doUpdate( Score scoreUi ){

        return score;
    }

    @Override
    public String toString() {
        return Constants.SCORE_UPDATE;
    }

    @Override
    public String toScreen() {
        return "The current player's score is changed:\n" +
        "Victory points: " + String.valueOf(score.getVictoryPoints()) + "\n" +
        "Faith points: " + String.valueOf(score.getFaithPoints()) + "\n" +
        "Military Points: " + String.valueOf(score.getMilitaryPoints()) + "\n";

    }
}
