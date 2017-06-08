package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

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

    public void defaultContextMethod(String action) throws InputException, IOException, ClassNotFoundException{}

    public void doAction(String action) throws IOException, ClassNotFoundException, InputException {
        if( map.get(action) != null ) {
            actioner = map.get(action);
            actioner.action();
        } else
            defaultContextMethod(action);
    }

    @FunctionalInterface
    public interface Actioner{
         void action() throws IOException, ClassNotFoundException, InputException;
    }


}
