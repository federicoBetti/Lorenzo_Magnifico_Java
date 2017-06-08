package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class LoginContext extends AbstractContext {

    public LoginContext( Cli cli ){
        super(cli);
        System.out.println("You are in the Login Context. Write your nickname");
        map.put(CliConstants.LOGIN, this::login );
        map.put(CliConstants.HELP, this::printHelp);
    }

    @Override
    public void printHelp() {
        //to implement
    }

    public void login() throws IOException, ClassNotFoundException {
        cli.loginRequest();
        cli.waitingForNewInteraction();
    }

}

