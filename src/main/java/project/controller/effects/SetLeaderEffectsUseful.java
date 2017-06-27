package project.controller.effects;


import project.controller.effects.realeffects.Effects;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

//todo cambiare con la HashMap

public class SetLeaderEffectsUseful implements Effects {
    private String parameter;

    public SetLeaderEffectsUseful(String parameter){
        this.parameter = parameter;
    }

    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        switch (parameter) {
            case "Lodovico Ariosto":
                // TODO devo cambiare costruttore della support fuction in = new LudovicoAriostoCheck(oldSupportFunction)
                player.getLeaderEffectsUsefull().setCanPlaceInOccupiedPosition(true);
                break;

            case "Filippo Brunelleschi":
                //TODO cambiare costruttore di support funcitn quando vene chiamata
                player.getLeaderEffectsUsefull().setHaveToPayWhenTowerIsOccupied(false);
                break;

            case "Federico da Montefeltro":
                // TODO  allinizio del turno dici quale vuoi mettere da 6. è da mettere nelle function end turn.
                // questo deve ritornare il fatto che ogni famigliare puo essere usato come uno da sei
                player.getLeaderEffectsUsefull().setOneFamilyMemberIs6(true);
                break;

            case "Lorenzo de' Medici":
                // TODO chiedi quale carta si vuole giocare tra quelle con isPlayed == true
                break;

            case "Sisto IV":
                // TODO cambiare costruttore qundo viene chiamata
                player.getLeaderEffectsUsefull().setFiveVicotryPointsForVaticanReport(true);
                break;

            case "Cesare Borgia":
                //TODO cambiare costruttore support function
                player.getLeaderEffectsUsefull().setDontNeedToSatisfyMilitaryPointsForTerritory(true);
                break;

            case "Santa Rita":
                //TODO cambiare cstruttor support functions
                player.getLeaderEffectsUsefull().setDoubleResourceFromImmediateEffect(false);
                break;

            case "Pico della Mirandola":
                //TODO cambiare cstruttor support functions
                player.getLeaderEffectsUsefull().setDiscountOf3OnDevelepomentCard(true);
                break;
            default:
                return null;
        }

        return new OkOrNo();
    }
}
