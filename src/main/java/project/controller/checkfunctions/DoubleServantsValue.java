package project.controller.checkfunctions;

import project.server.network.PlayerHandler;

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
