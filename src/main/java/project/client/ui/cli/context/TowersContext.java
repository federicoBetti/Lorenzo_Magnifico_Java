package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.server.network.PlayerHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by raffaelebongo on 05/06/17.
 */
public class TowersContext extends AbstractContext {

    public TowersContext ( Cli cli ) throws IOException, ClassNotFoundException, InputException {
        super(cli);
        map.put(CliConstants.SHOW_TOWERS, this:: showTowers );
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
    }

    private void showTowers() {
        //todo
    }

    @Override
    public void doAction(String action) throws IOException, ClassNotFoundException, InputException {
        actioner = map.get(action);
        actioner.action();
    }

    @Override
    public void printHelp() {
        System.out.println("the available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey());

        System.out.println("[towerColour-floor-familiarColour] \n towerColour: green, yellow, purple, blue " +
                "\nfloor: 0, 1, 2, 3 \n familiarColour: black, neutral, orange, white ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 1 ))
            throw new InputException();

        if (Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) <= 3 )
            throw new InputException();

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException, ClassNotFoundException {
        cli.choseAndTakeDevCard(action);
    }
}

