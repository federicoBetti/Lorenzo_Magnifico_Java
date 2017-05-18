package Project.Controller.Effects;


import Project.Controller.Effects.RealEffects.Effects;
import Project.Controller.MessageObjects.BonusInteraction;
import Project.Controller.MessageObjects.OkOrNo;
import Project.MODEL.Player;

public class SetExcommunicationCardUseful implements Effects {
    String parameter;

    public SetExcommunicationCardUseful(String parameter){
        this.parameter = parameter;
    }

    public BonusInteraction doEffect (Player player){
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
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
