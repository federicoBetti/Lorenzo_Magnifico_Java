package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 06/07/17.
 */
public class SetIPaddressContext extends AbstractContext {
    String kindOfConnection;

    public SetIPaddressContext(Cli cli, String kindOfConnection) {
        super(cli);
        this.kindOfConnection = kindOfConnection;
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("Insert the server IP address");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("\\.");
        System.out.println(parameters.length);
        if (parameters.length != 4)
            throw new InputException();

        System.out.println("1");
        for (String num : parameters)
            try {
                if (Integer.parseInt(num) < 0 || Integer.parseInt(num) > 255)
                    throw new InputException();
            } catch (NumberFormatException e) {
                System.out.println("2");
                throw new InputException();
            }
    }

    @Override
    protected void mainContextMethod(String ip) throws InputException, IOException {
        cli.setIPaddress(kindOfConnection, ip);
    }
}
