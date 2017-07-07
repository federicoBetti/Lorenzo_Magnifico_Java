package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.model.FamilyMember;
import project.model.Market;


import java.io.IOException;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class MarketContext extends AbstractContext {
    Market[] marketZone;

    public MarketContext(Cli cli, Market[] marketZone){
        super(cli);
        this.marketZone = marketZone;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_MARKET_ZONE, this:: showMarketZone );
        printHelp();
    }

    private void showMarketZone() {
        pBlue.println("The market's zone state is:");
        int i = 0;
        for ( Market market: marketZone ) {
            pRed.print(i + ") ");pRed.print("occupied: ");pYellow.println( market.isOccupied());pYellow.print(" ");
            pRed.print("Effect: ");pYellow.println(market.getEffect().toScreen());
            i++;
        }
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey().toString());

        pRed.println("The main Action is:");
        pBlue.println("[position-familiarColour]");
        pRed.print("position: ");pYellow.println(" 0, 1, 2, 3 ");
        pRed.print("Familiar Colour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pYellow.println("");
    }

    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 )) {
            throw new InputException();
        }

        if( !(parameters[0].length() == 1) && !(Character.isDigit(parameters[0].charAt(0)))) {
            throw new InputException();
        }
        if( cli.getNumberOfPlayers() < 4 ) {
            if ((Integer.parseInt(parameters[0]) > 1)) {
                throw new InputException();
            }
        }

        checkFamilyMemberColour(parameters[1]);
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseMarketActionParameters(action);
    }
}
