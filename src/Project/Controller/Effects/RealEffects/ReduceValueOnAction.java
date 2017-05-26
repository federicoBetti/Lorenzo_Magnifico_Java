package Project.Controller.Effects.RealEffects;


import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;
import Project.MODEL.*;

public class ReduceValueOnAction implements Effects {
    String parameter;
    int quantity;

    public ReduceValueOnAction(String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    public BonusInteraction doEffect (Player player){
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
                player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().setDiceValue(player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceValue() + quantity);
                break;
            case "ventures":
                player.getPersonalBoardReference().getBonusOnActions().setVenturesBonus(player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus() + quantity);
                break;
            case "dice":{
                int i;
                for ( i = 0; i<4; i++){
                    player.getPedone()[i].setFixedBonus(player.getPedone()[i].getFixedBonus() + quantity);
                }
            }
            case "militaryPoints": {
                player.getPersonalBoardReference().getBonusOnActions().setMilitaryPointBonus(player.getPersonalBoardReference().getBonusOnActions().getMilitaryPointBonus() + quantity);
                break;
            }
            case "coins": {
                player.getPersonalBoardReference().getBonusOnActions().setCoinsBonus(player.getPersonalBoardReference().getBonusOnActions().getCoinsBonus() + quantity);
                break;
            }
            case "servants": {
                player.getPersonalBoardReference().getBonusOnActions().setServantsBonus(player.getPersonalBoardReference().getBonusOnActions().getServantsBonus() + quantity);
                break;
            }
            case "wood": {
                player.getPersonalBoardReference().getBonusOnActions().setWoodBonus(player.getPersonalBoardReference().getBonusOnActions().getWoodBonus() + quantity);
                break;
            }
            case "stone": {
                player.getPersonalBoardReference().getBonusOnActions().setStoneBonus(player.getPersonalBoardReference().getBonusOnActions().getStoneBonus() + quantity);
                break;
            }

        }
        OkOrNo Ok = new OkOrNo(false);
        Ok.setOk(true);
        return Ok;
    }
}
