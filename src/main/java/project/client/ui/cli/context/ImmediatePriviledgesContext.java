package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.messages.TakePrivilegesAction;

/**
 * This class is a context opened when the a card taken by the player makes taking a privilege as immediate effect
 */
public class ImmediatePriviledgesContext extends AbstractContext {

    private int[] priviledgesTakenInArow;
    private TakePrivilegesAction numberOfpriviledges;

    public ImmediatePriviledgesContext(Cli cli, TakePrivilegesAction numberOfpriviledges) {
        super(cli);
        map.put(CliConstants.EXIT, this:: exit );
        this.numberOfpriviledges = numberOfpriviledges;
        priviledgesTakenInArow = new int[5];
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Type " + numberOfpriviledges.getQuantityOfDifferentPrivileges() + " priviledges'numbers that you prefer" +
        " but different each others");
        pBlue.println("[priviledgeNumber1 - priviledgeNumber2 - ... ]");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
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


    /**
     * If the string in input does not correspond with no key, this method is called and it calls immediatePriviledgeAction
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
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
