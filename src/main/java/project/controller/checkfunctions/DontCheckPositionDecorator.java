package project.controller.checkfunctions;


import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;

public class DontCheckPositionDecorator extends CheckFunctionsDecorator {

    DontCheckPositionDecorator(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }

    /**
     * dovrebbe essere di una carta leader, ludovico ariosto
     * todo check che questo è facile da implementare perche basta mettere che ritorna true qua ma il roblema è che poi nella zona ci dovrebbero stare due familiari
     * @param position
     * @param zone
     * @param familyMember
     * @return
     */
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (!(zone instanceof Tower[])){
            return true;
        }
        else
            return allCheckFunctions.checkPosition(position,zone,familyMember);

    }

}
