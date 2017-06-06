package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by raffaelebongo on 05/06/17.
 */
public class TowersContext extends AbstractContext {
    Cli cli;
    Map<String, Actioner> map;

    public TowersContext ( Cli cli ) throws IOException, ClassNotFoundException {
        this.cli = cli;
        map = new HashMap<>();
        map.put(CliConstants.EXIT, this::exit);
        map.put(cli.lineFromKeyBoard.split("-")[0], cli::choseAndTakeDevCard);
    }
}
