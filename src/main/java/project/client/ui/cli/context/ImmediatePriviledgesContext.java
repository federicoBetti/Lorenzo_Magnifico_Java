package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.messages.TakePrivilegesAction;

import java.io.IOException;

/**
 * Created by raffaelebongo on 15/06/17.
 */
public class ImmediatePriviledgesContext extends AbstractContext {

    private int[] priviledgesTakenInArow;   //array va inizializzato a zero o è autimatico?
    private TakePrivilegesAction numberOfpriviledges;

    public ImmediatePriviledgesContext(Cli cli, TakePrivilegesAction numberOfpriviledges) {
        super(cli);
        map.put(CliConstants.EXIT, this:: exit );
        this.numberOfpriviledges = numberOfpriviledges;
        priviledgesTakenInArow = new int[5];
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("Type " + numberOfpriviledges.getQuantityOfDifferentPrivileges() + " priviledges'numbers that you prefer" +
        " but different each others");
    }

    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        String[] parameters = input.split("-");

        if (!(parameters.length == numberOfpriviledges.getQuantityOfDifferentPrivileges()))
            throw new InputException();

        for ( String priviledgeNumber: parameters ){
            if (!(priviledgeNumber.length() == 1) && !(Character.isDigit(parameters[0].charAt(0))))
                throw new InputException();

            if ( Integer.parseInt(priviledgeNumber) < 0 || Integer.parseInt(priviledgeNumber) > 5)
                throw new InputException();

            if( priviledgesTakenInArow[Integer.parseInt(priviledgeNumber)] ==  1 )
                throw new InputException();

            priviledgesTakenInArow[Integer.parseInt(priviledgeNumber)] = 1;

        }
    }

    @Override
    public void mainContextMethod(String action) {
        try {
            checkValidInput(action);
            cli.immediatePriviledgeAction(action);
        } catch (InputException | NumberFormatException | StringIndexOutOfBoundsException e) {
            printHelp();
        }

    }
}
