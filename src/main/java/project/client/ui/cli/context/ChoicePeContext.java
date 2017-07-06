package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 15/06/17.
 */
public class ChoicePeContext extends AbstractContext {

    public ChoicePeContext(Cli cli) {
        super(cli);
        map.put(CliConstants.HELP, this::printHelp );
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pYellow.println("Choose the permanent effect to use:" +
                "\n[0] for the first PE;" +
                "\n[1] for the second PE");
    }

    @Override
    public void checkValidInput(String input) throws InputException {

        if ( input.equals("-1"))
            return;

        if( !(input.length() < 2))
            throw new InputException();

        if( !(Character.isDigit(input.charAt(0))) )
            throw new InputException();

        try {
            if (!(Integer.parseInt(input) == 0) && !(Integer.parseInt(input) == 1))
                throw new InputException();
        }catch (NumberFormatException e ){
            printHelp();
        }
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
