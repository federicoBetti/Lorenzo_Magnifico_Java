package project.client.ui.cli.context;

import project.client.ui.cli.Cli;



import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MarketContext extends AbstractContext {
    Cli cli;

    public MarketContext( Cli cli ){
        this.cli = cli;
    }
}
