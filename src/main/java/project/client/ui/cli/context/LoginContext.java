package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;

import java.io.IOException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class LoginContext extends AbstractContext {
    Actioner actioner;
    String parameter;

    public LoginContext( Cli cli ){
        super();
        this.cli = cli;
        System.out.println("You are in the login context!");
        map.put(CliConstants.LOGIN, this::login );
    }

    @Override
    public void doAction(String action) throws IOException, ClassNotFoundException {
        actioner = map.get(action);
        parameter = action;
        actioner.action();
    }

    public void login() throws IOException, ClassNotFoundException {
        if(parameter != null ){
            cli.loginRequest(parameter);
            cli.waitingForNewInteraction();
        }
    }

}

