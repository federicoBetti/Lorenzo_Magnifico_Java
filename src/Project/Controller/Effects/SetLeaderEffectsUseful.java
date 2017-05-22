package Project.Controller.Effects;


import Project.Controller.Effects.RealEffects.Effects;
import Project.MODEL.*;
import Project.toDelete.BonusInteraction;
import Project.toDelete.OkOrNo;

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
                // TODO  allinizio del turno dici quale vuoi mettere da 6. è da mettere nelle function end turn.
                // questo deve ritornare il fatto che ogni famigliare puo essere usato come uno da sei
                player.getLeaderEffectsUsefull().setOneFamilyMemberIs6(true);
                break;
            }
            case "Lorenzo de' Medici": {
                // TODO chiedi quale carta si vuole giocare tra quelle con isPlayed == true
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
                //TODO cambiare costruttore support function
                player.getLeaderEffectsUsefull().setDiscountOf3OnDevelepomentCard(true);
                break;
            }
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
