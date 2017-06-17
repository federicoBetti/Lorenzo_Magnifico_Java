package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;
import java.util.List;

/**
 * TODO METTERE TUTTI I RITORNI DELLE ALTRE FUNZIONI COME ALLCHECKFUNCTION.altrafunzione
 */
public class CantPlaceFamiliarInMarket extends CheckFunctionsDecorator {

    public CantPlaceFamiliarInMarket(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (zone instanceof Market[]){
            return false;
        }
        else
            return allCheckFunctions.checkPosition(position,zone,familyMember);
    }

}
