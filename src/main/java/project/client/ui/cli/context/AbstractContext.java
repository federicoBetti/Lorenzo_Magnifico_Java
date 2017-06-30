package project.client.ui.cli.context;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
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
    private Actioner actioner;
    Map<String, Actioner> map;
    UnixColoredPrinter pYellow;
    UnixColoredPrinter pRed;
    UnixColoredPrinter pBlue;

    AbstractContext(Cli cli){
        this.cli = cli;
        map = new HashMap<>();
        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.YELLOW);
        this.pYellow = new UnixColoredPrinter(builder);

        builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.RED);
        this.pRed = new UnixColoredPrinter(builder);

        builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.BLUE);
        this.pBlue = new UnixColoredPrinter(builder);
    }

    void exit() {
        cli.mainContext();
    }

    public abstract void printHelp();

    public abstract void checkValidInput( String input ) throws InputException;

    protected abstract void mainContextMethod(String action) throws InputException, IOException;

    void checkFamilyMemberColour(String colour) throws InputException {
        if ( !(colour.equals(Constants.FAMILY_MEMBER_COLOUR_BLACK) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_ORANGE) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_WHITE)))
            throw new InputException();
    }
    
    void checkTowerColour(String towerColour) throws InputException {
        if(!(towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD)))
            throw new InputException();
    }

    public void doAction(String action) throws InputException, IOException {
        if( map.get(action) != null ) {
            actioner = map.get(action);
            actioner.action();
        } else
            mainContextMethod(action);
    }

    public void cantDoAction(){
        pRed.println("Error, action not avaiable. Please try with another input");
    }

    @FunctionalInterface
    public interface Actioner{
         void action() throws InputException, IOException;
    }

    public UnixColoredPrinter getpYellow() {
        return pYellow;
    }

    public UnixColoredPrinter getpRed() {
        return pRed;
    }

    public UnixColoredPrinter getpBlue() {
        return pBlue;
    }
}













