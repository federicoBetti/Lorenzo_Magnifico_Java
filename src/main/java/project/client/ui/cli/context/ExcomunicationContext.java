package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class ExcomunicationContext extends AbstractContext {

    public ExcomunicationContext (Cli cli ){
        this.cli = cli;
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }
}
