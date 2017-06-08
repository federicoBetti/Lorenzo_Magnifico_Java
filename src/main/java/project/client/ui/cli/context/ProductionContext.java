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
public class ProductionContext extends AbstractContext {
    Cli cli;
    Map<String, Actioner> map;

    public ProductionContext(Cli cli ){
        super(cli);
        map.put(CliConstants.CHOOSE_PARAMETERS, this::choseProductionParameters );
    }

    @Override
    public void printHelp() {

    }

    private void choseProductionParameters() throws IOException, ClassNotFoundException {
        cli.chooseProductionParameters();
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }
}
