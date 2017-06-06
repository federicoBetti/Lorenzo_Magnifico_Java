package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public abstract class AbstractContext {
    Cli cli;
    Map<String, Actioner> map;

    public AbstractContext(){
        map = new HashMap<>();
        map.put(CliConstants.EXIT, this::exit );
    }

    public void exit() {
        cli.mainContext();
    }

    public void doAction(String action) throws IOException, ClassNotFoundException {
    }

    @FunctionalInterface
    public interface Actioner{
        void action() throws IOException, ClassNotFoundException;
    }
}
