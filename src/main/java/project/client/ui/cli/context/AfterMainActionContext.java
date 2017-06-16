package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;

import java.io.IOException;

/**
 * Created by raffaelebongo on 16/06/17.
 */
public class AfterMainActionContext extends AbstractContext {

    public AfterMainActionContext(Cli cli) {
        super(cli);
        printHelp();
        System.out.println("");
        map.put(Constants.SKIP_TURN, this::skipTurn );
    }

    private void skipTurn() {
    }

    @Override
    public void printHelp() {
        System.out.println("Type Leader Card name [leader-card-name]");
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
