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
        pRed.println("The available action is:");
        pYellow.println("\nDo you want to pray? ");pBlue.println("[0 = yes / 1 = no]");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        if  (!(input.equals("0")))
            if ( !(input.equals("1")))
                throw new InputException();
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
