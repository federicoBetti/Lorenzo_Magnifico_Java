package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represents the TwoVictoryPointsForEachYellowCard effect
 */
public class TwoVictoryPointsForEachYellowCard implements Effects {

    /**
     * Perform the right effect
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {

        int yellowCards = player.getPersonalBoardReference().getBuildings().size();
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + 2*yellowCards);

        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Take 2 victory points for each yellow card";
    }
}
