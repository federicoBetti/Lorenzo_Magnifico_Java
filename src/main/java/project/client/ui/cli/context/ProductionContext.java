package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;

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
        this.cli = cli;
        map = new HashMap<>();
        map.put(CliConstants.CHOOSE_PARAMETERS, this::choseProductionParameters );
    }

    private void choseProductionParameters() throws IOException, ClassNotFoundException {
        cli.chooseProductionParameters();
    }
}
