package Project.Controller;

import Project.MODEL.FamilyMember;
import Project.MODEL.Player;
import Project.MODEL.Position;
import Project.MODEL.Tower;


public class FivePointsMoreForPray implements SupportFunctionDecorator {
    AllSupportFunction allSupportFunction = null;

    FivePointsMoreForPray(AllSupportFunction allSupportFunction){
        this.allSupportFunction = allSupportFunction;
    }

    @Override
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        return allSupportFunction.Check_Position(position,zone,familyMember);
    }

    @Override
    public boolean CheckTowerOccupiedByYou(Tower[] tower, Player player) {
        return allSupportFunction.CheckTowerOccupiedByYou(tower,player);
    }

    @Override
    public int Pray(Player player) {
        return 5 + allSupportFunction.Pray(player);
    }

    @Override
    public boolean CheckCapabilityToTakeTerritory(Player player) {
        return allSupportFunction.CheckCapabilityToTakeTerritory(player);
    }
}
