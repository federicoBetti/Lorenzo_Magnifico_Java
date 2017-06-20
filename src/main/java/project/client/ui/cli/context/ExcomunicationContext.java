package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class ExcomunicationContext extends AbstractContext{

    public ExcomunicationContext (Cli cli ){
        super(cli);
        printHelp();
    }

    public void printHelp() {
        System.out.print("Available actions:" +
                    "\nDo you want to pray? [yes/no]");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        if ( !(input == "yes" || input == "no") )
            throw new InputException();
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.prayOrNot(action);
    }
}
