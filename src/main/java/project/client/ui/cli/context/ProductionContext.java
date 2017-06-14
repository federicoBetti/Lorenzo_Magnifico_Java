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
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[positionInBuildingZone(int)-familiarColour-buildingCard-...(max 6 buildingCard in your personal board] " +
                "\n position: 0, 1, 2, 3, 4, 5 " +
                "\n familiarColour: black, neutral, orange, white " +
                "\n buildingCard: name ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if (parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0)))
            throw new InputException();
        if (Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) < 6 )
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);

        //cosa controllo del nome della carta? nulla secondo me
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException, ClassNotFoundException {
        cli.chooseProductionParameters(action);
    }
}
