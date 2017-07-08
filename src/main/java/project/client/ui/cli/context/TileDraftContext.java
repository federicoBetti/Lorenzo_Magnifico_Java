package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.effects.realeffects.Effects;
import project.model.Tile;

import java.io.IOException;
import java.util.List;

/**
 * This class is a context opened during the tile drafting period
 */
public class TileDraftContext extends AbstractContext {
    private List<Tile> tiles;

    public TileDraftContext(Cli cli, List<Tile> tiles) {
        super(cli);
        this.tiles = tiles;
        printHelp();
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pBlue.print("\nChoose you personal bonus Tile. Type ");pRed.print("[number] ");pBlue.println("for choosing the corresponding tile");
        for ( Tile tile : tiles ) {
            pRed.print("TILE NUMBER: ");pBlue.println(tile.getTileNumber() );pRed.println("Production effects: ");

            int i = 1;
            for ( Effects effect : tile.takeProductionResource() ){
                pYellow.println( i + ") " + effect.toScreen());
                i++;
            }
            pRed.println("\nHarvester Bonus: ");
            int j = 1;
            for ( Effects effect : tile.takeHarvesterResource() ){
                pYellow.println( j + ") " + effect.toScreen());
                j++;
            }
        }

    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        System.out.println("sono qui 1. lenght: " + input.length());
        try {
            if (Integer.parseInt(input) == -1)
                return;
        }catch (NumberFormatException e){
            throw new InputException();
        }

        if ( input.length() != 1 )
            throw new InputException();

        if ( !Character.isDigit(input.charAt(0)))
            throw new InputException();

        for ( Tile tile : tiles )
            if ( Integer.parseInt(input) == tile.getTileNumber() )
                return;

        throw new InputException();

    }

    /**
     * If the string in input does not correspond with no key, this method is called
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException {

    }
}
