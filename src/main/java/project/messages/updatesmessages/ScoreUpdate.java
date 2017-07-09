package project.messages.updatesmessages;

import project.controller.Constants;
import project.model.Score;
import project.server.network.PlayerHandler;

import java.io.Serializable;

/**
 * Object sends to the client for notifying that the player's score has been changed
 */
public class ScoreUpdate extends Updates implements Serializable{
    private Score score;

    /**
     * Constructor
     *
     * @param player playerHandler's reference
     * @param nickname player's nickname
     */
    public ScoreUpdate(PlayerHandler player, String nickname ){
        super(nickname);
        this.score = player.getScore();
    }

    /**
     * This method act the score's update in the client
     *
     * @param scoreUi score's reference
     * @return score's reference
     */
    @Override
    public Score doUpdate( Score scoreUi ){

        return score;
    }

    /**
     * String the descibe the class
     *
     * @return the constants
     */
    @Override
    public String toString() {
        return Constants.SCORE_UPDATE;
    }

    /**
     * This method build a string that describes the update
     *
     * @return the description
     */
    @Override
    public String toScreen() {
        return "\n" + getNicknameCurrentPlayer() + "'s score is :\n" +
        "Victory points: " + String.valueOf(score.getVictoryPoints()) + "\n" +
        "Faith points: " + String.valueOf(score.getFaithPoints()) + "\n" +
        "Military Points: " + String.valueOf(score.getMilitaryPoints()) + "\n";

    }
}
