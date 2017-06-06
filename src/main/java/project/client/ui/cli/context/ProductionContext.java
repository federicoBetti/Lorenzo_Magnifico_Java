package project.client.ui.cli.context;

import project.client.ui.cli.Cli;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class ProductionContext extends AbstractContext {
    Cli cli;

    public ProductionContext(Cli cli ){
        this.cli = cli;
    }
}
