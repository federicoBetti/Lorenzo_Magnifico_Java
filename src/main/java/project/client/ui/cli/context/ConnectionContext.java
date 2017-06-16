package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 08/06/17.
 */
public class ConnectionContext extends AbstractContext {
    public ConnectionContext(Cli cli) {
        super(cli);
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        //todo chiamare setConnectionType su ClientSetter
    }
}
