package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public abstract class AbstractContext {
    Cli cli;
    Actioner actioner;
    Map<String, Actioner> map;

    public AbstractContext( Cli cli ){
        this.cli = cli;
        map = new HashMap<>();
    }

    void exit() {
        cli.mainContext();
    }

    public abstract void printHelp();

    public void checkValidInput( String input ) throws InputException{
    }

    public abstract void mainContextMethod(String action) throws InputException, IOException;

    public void checkFamilyMemberColour( String colour ) throws InputException {
        if ( !(colour.equals(Constants.FAMILY_MEMBER_COLOUR_BLACK) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_ORANGE) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_WHITE)))
            throw new InputException();
    }
    
    public void checkTowerColour( String towerColour ) throws InputException {
        if(!(towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD)))
            throw new InputException();
    }

    public void doAction(String action) throws InputException, IOException {
        System.out.println("sono qui");
        if( map.get(action) != null ) {
            actioner = map.get(action);
            actioner.action();
        } else
            mainContextMethod(action);
    }

    @FunctionalInterface
    public interface Actioner{
         void action() throws InputException, IOException;
    }
}













