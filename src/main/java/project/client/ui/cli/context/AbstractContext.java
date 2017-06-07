package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
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


    void exit() {
        cli.mainContext();
    }

    public void printHelp(){
    }

    public abstract void checkValidInput( String input ) throws InputException;

    public void doAction(String action) throws IOException, ClassNotFoundException, InputException {
        actioner = map.get(action);
        actioner.action();
    }

    @FunctionalInterface
    public interface Actioner{
         void action() throws IOException, ClassNotFoundException, InputException;
    }


}
