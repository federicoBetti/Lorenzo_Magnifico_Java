package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * This class is a context opened when the player has to choice the server ip address
 */
public class SetIPaddressContext extends AbstractContext {
    String kindOfConnection;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param kindOfConnection kind of connection
     */
    public SetIPaddressContext(Cli cli, String kindOfConnection) {
        super(cli);
        this.kindOfConnection = kindOfConnection;
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("Insert the server IP address");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("\\.");
        if (parameters.length != 4)
            throw new InputException();

        for (String num : parameters)
            try {
                if (Integer.parseInt(num) < 0 || Integer.parseInt(num) > 255)
                    throw new InputException();
            } catch (NumberFormatException e) {
                throw new InputException();
            }
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls setIPaddress
     *
     * @param ip string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    protected void mainContextMethod(String ip) throws InputException, IOException {
        cli.setIPaddress(kindOfConnection, ip);
    }
}
