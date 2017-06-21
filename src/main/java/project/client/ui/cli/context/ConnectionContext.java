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
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("Choose the type of connection:\n" +
                "type: socket | rmi ");
    }

    @Override
    public void mainContextMethod(String kindOfConnection) throws InputException, IOException {
        cli.setConnectionType(kindOfConnection);
    }
}
