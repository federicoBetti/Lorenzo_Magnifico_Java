package project.controller.checkfunctions;

import project.controller.cardsfactory.BuildingCard;
import project.controller.cardsfactory.CharacterCard;
import project.controller.cardsfactory.TerritoryCard;
import project.controller.cardsfactory.VenturesCard;
import project.model.*;
import project.server.network.PlayerHandler;
import java.util.List;

/**
 * todo mettere tutti i ritorni delle altre funzioni come allcheckfunctions.funzione(param....)
 */
public class DoubleServantsValue extends CheckFunctionsDecorator {


    public DoubleServantsValue(AllCheckFunctions allCheckFunctions){
        super(allCheckFunctions);
    }


    @Override
    public int getServants(PlayerHandler player) {
        return player.getPersonalBoardReference().getServants() / 2;
    }
}
