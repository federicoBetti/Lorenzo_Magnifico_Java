package project.controller.effects.realeffects;


import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * This class represent the ReduceValueOnAction effects
 */
public class ReduceValueOnAction implements Effects {
    private String parameter;
    private int quantity;

    /**
     * Constructor
     *
     * @param parameter kind of action
     * @param quantity quantity of value reduced
     */
    public ReduceValueOnAction(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    /**
     * Perform the effect according to the parameters
     *
     * @param player playerHandler's reference
     * @return okOrNo instance for saying that the effect has been applied correctly
     */
    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (parameter){
            case "production":
                player.getPersonalBoardReference().getBonusOnActions().setProductionBonus(player.getPersonalBoardReference().getBonusOnActions().getProductionBonus() + quantity);
                break;
            case "harvester":
                player.getPersonalBoardReference().getBonusOnActions().setHarvesterBonus(player.getPersonalBoardReference().getBonusOnActions().getHarvesterBonus() + quantity);
                break;
            case "territory":
                player.getPersonalBoardReference().getBonusOnActions().setTerritoryBonus(player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus() + quantity);
                break;
            case "characters":
                player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus() + quantity);
                break;
            case "buildings":
                player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceBonus(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus() + quantity);
                break;
            case "ventures":
                player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantity);
                break;
            case "dice":
                int i;
                for ( i = 0; i<4; i++)
                    player.getAllFamilyMembers()[i].setFixedBonus(player.getAllFamilyMembers()[i].getFixedBonus() + quantity);
                break;
            case "militaryPoints":
                player.getPersonalBoardReference().getBonusOnActions().setMilitaryPointsBonus(player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointsBonus() + quantity);
                break;

            case "coins":
                player.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus() + quantity);
                break;

            case "servants":
                player.getPersonalBoardReference().getBonusOnActions().setServantsBonus(player.getPersonalBoardReference().getBonusOnActions().getServantsBonus() + quantity);
                break;

            case "wood":
                player.getPersonalBoardReference().getBonusOnActions().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getWoodBonus() + quantity);
                break;

            case "stone":
                player.getPersonalBoardReference().getBonusOnActions().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getStoneBonus() + quantity);
                break;
            default:
                return null;

        }
        return new OkOrNo();
    }

    /**
     * Build a string for describing the effect
     *
     * @return the description's String
     */
    @Override
    public String toScreen() {
        return "Reduce dice value necessary for " + parameter +  " of " + quantity;
    }
}
