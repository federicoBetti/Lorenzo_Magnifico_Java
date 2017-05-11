package ServerModel;


public class SetLeaderEffectsUseful implements Effects {
    String parameter;

    SetLeaderEffectsUseful (String parameter){
        this.parameter = parameter;
    }

    public BonusInteraction doEffect (Player player){
        switch (parameter) {
            case "Lodovico Ariosto": {
                player.getLeaderEffectsUsefull().setCanPlaceInOccupiedPosition(true);
                break;
            }
            case "Filippo Brunelleschi": {
                player.getLeaderEffectsUsefull().setHaveToPayWhenTowerIsOccupied(false);
                break;
            }
            case "Federico da Montefeltro": {
                player.getLeaderEffectsUsefull().setOneFamilyMemberIs6(true);
                break;
            }
            case "Lorenzo de' Medici": {
                //chiedi quale carta si vuole giocare tra quelle con isPlayed == true
                break;
            }
            case "Sisto IV": {
                player.getLeaderEffectsUsefull().setFiveVicotryPointsForVaticanReport(true);
                break;
            }
            case "Cesare Borgia": {
                player.getLeaderEffectsUsefull().setDontNeedToSatisfyMilitaryPointsForTerritory(true);
                break;
            }
            case "Santa Rita": {
                player.getLeaderEffectsUsefull().setDoubleResourceFromImmediateEffect(false);
                break;
            }
            case "Pico della Mirandola": {
                player.getLeaderEffectsUsefull().setDiscountOf3OnDevelepomentCard(true);
                break;
            }
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
