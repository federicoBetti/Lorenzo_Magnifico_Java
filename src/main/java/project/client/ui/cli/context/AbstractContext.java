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

    public void doAction(String action) throws IOException, ClassNotFoundException {
    }

    void exit() {
        cli.mainContext();
    }

    @FunctionalInterface
    public interface Actioner{
         void action() throws IOException, ClassNotFoundException;
    }
}
