package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 14/06/17.
 */
public class BothPaymentsVentureCardsContext extends AbstractContext {

    public BothPaymentsVentureCardsContext(Cli cli) {
        super(cli);
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("The main action is:");
        pYellow.println("[0] for the first cost\n" +
                        "[1] for the second cost");
    }

    public void checkValidInput( String input ) throws InputException{
        String[] parameters = input.split("-");

        if ( !((parameters.length == 1) && Character.isDigit(Integer.parseInt(parameters[0]))))
           throw new InputException();
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.choosePayment(action);
    }
}
