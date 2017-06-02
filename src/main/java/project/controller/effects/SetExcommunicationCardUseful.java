package project.controller.effects;


import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.model.Player;
import project.server.network.PlayerHandler;

public class SetExcommunicationCardUseful implements Effects {
    String parameter;

    public SetExcommunicationCardUseful(String parameter){
        this.parameter = parameter;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (parameter){
            case "cantPlaceFamilyMemberInMarket":
                player.getExcommunicationEffectsUseful().setCantPlaceFamiliyMemberInMarket(true);
                break;
            case "doubleServantsValue":
                player.getExcommunicationEffectsUseful().setValueOfServantsDoubled(true);
                break;
            case "dontPlayFirseRound":
                player.getExcommunicationEffectsUseful().setSkipYourFirstTurn(true);
                break;
            case "dontScorePointsForTerritoies":
                player.getExcommunicationEffectsUseful().setDontTakeVictoryPointsFromTerritories(true);
                break;
            case "dontScorePointsForCharacters":
                player.getExcommunicationEffectsUseful().setDontTakeVictoryPointsFromCharacters(true);
                break;
            case "dontScorePointsForBuildings":
                player.getExcommunicationEffectsUseful().setLooseOneVictoryPointEveryWoodStoneOnBuildings(true);
                break;
            case "dontScorePointsForVentures":
                player.getExcommunicationEffectsUseful().setDontTakeVictoryPointsFromVentures(true);
                break;
            case "loseOnePointsEachFive":
                player.getExcommunicationEffectsUseful().setLooseOneVictoryPointEveryFive(true);
                break;
            case "loseOnePointsEachMilitaryPoints":
                player.getExcommunicationEffectsUseful().setLooseOneVictoryPointEveryMilitaryPoints(true);
                break;
            case "loseOnePointsForEachWoodStoneOnBuildings":
                player.getExcommunicationEffectsUseful().setLooseOneVictoryPointEveryWoodStoneOnBuildings(true);
                break;
            case "loseOnePointsForEveryResource":
                player.getExcommunicationEffectsUseful().setLooseOneVictoryPointEveryResource(true);
                break;
            default:
                return null;
        }

        //todo controllare qui l'ok or no
        OkOrNo ok = new OkOrNo(false);
        ok.setOk(true);
        return ok;
    }
}
