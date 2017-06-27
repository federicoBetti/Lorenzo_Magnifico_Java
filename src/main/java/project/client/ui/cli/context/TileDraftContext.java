package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.model.Tile;

import java.io.IOException;
import java.util.List;

/**
 * Created by raffaelebongo on 27/06/17.
 */
public class TileDraftContext extends AbstractContext {
    List<Tile> tiles;

    public TileDraftContext(Cli cli, List<Tile> tiles) {
        super(cli);
        this.tiles = tiles;
        printHelp();
    }

    @Override
    public void printHelp() {
        pBlue.println("Choose you personal bonus Tile.\nType [number] for choosing the corresponding tile\n ");
        for ( Tile tile : tiles )
            pYellow.print(tile.getTileNumber() + " " );
        pYellow.println("");
    }



    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
