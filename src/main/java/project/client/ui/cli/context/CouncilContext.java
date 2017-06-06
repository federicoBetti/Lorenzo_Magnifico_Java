package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class CouncilContext extends AbstractContext {

    Map<String, Actioner> map;

    public CouncilContext( Cli cli ){
        this.cli = cli;
        map = new HashMap<>();
        map.put(CliConstants.EXIT, this::exit);
    }

}
