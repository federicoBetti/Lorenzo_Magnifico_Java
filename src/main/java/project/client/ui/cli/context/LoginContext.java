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
        map = new HashMap<>();
        this.cli = cli;
        System.out.println("You are in the Login Context. Write your nickname");
        map.put(CliConstants.LOGIN, this::login );
    }

    @Override
    public void printHelp() {

    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    public void login() throws IOException, ClassNotFoundException {
        cli.loginRequest();
        cli.waitingForNewInteraction();
    }

}

