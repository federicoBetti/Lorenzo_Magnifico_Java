package Project.Controller.Effects;


import Project.Controller.Effects.RealEffects.Effects;
import Project.MODEL.*;
import Project.Controller.MessageObjects.*;

public class SetLeaderEffectsUseful implements Effects {
    String parameter;

    public SetLeaderEffectsUseful(String parameter){
        this.parameter = parameter;
    }

    public BonusInteraction doEffect (Player player){
        switch (parameter) {
            case "Lodovico Ariosto": {
                // TODO devo cambiare costruttore della support fuction in = new DontCheckPositionDecorator(oldSupportFunction)
                player.getLeaderEffectsUsefull().setCanPlaceInOccupiedPosition(true);
                break;
            }
            case "Filippo Brunelleschi": {
                //TODO cambiare costruttore di support funcitn quando vene chiamata
                player.getLeaderEffectsUsefull().setHaveToPayWhenTowerIsOccupied(false);
                break;
            }
            case "Federico da Montefeltro": {
                //questo deve ritornare il fatto che ogni famigliare puo essere usato come uno da sei
                player.getLeaderEffectsUsefull().setOneFamilyMemberIs6(true);
                break;
            }
            case "Lorenzo de' Medici": {
                //chiedi quale carta si vuole giocare tra quelle con isPlayed == true
                break;
            }
            case "Sisto IV": {
                // TODO cambiare costruttore qundo viene chiamata
                player.getLeaderEffectsUsefull().setFiveVicotryPointsForVaticanReport(true);
                break;
            }
            case "Cesare Borgia": {
                //TODO cambiare costruttore support function
                player.getLeaderEffectsUsefull().setDontNeedToSatisfyMilitaryPointsForTerritory(true);
                break;
            }
            case "Santa Rita": {
                //TODO cambiare cstruttor support functions
                player.getLeaderEffectsUsefull().setDoubleResourceFromImmediateEffect(false);
                break;
            }
            case "Pico della Mirandola": {
                //questo con un bell'if
                player.getLeaderEffectsUsefull().setDiscountOf3OnDevelepomentCard(true);
                break;
            }
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
