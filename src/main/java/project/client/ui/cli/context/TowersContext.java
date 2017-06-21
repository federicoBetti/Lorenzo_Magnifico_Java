package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;

import java.io.IOException;
import java.util.Map;


/**
 * Created by raffaelebongo on 05/06/17.
 */
public class TowersContext extends AbstractContext {

    public TowersContext ( Cli cli ) {
        super(cli);
        map.put(CliConstants.SHOW_TOWERS, this:: showTowers );
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    private void showTowers() {
        //todo
    }

    /*@Override
    public void doAction(String action) throws IOException, InputException {
        actioner = map.get(action);
        actioner.action();
    }*/

    @Override
    public void printHelp() {
        System.out.println("You are in the Tower Context! The available actions are:\n");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            System.out.println(entry.getKey().toString());

        System.out.println("\nFor taking the tower type:\n");
        System.out.println("[towerColour-floor-familiarColour] \n towerColour: green, yellow, purple, blue " +
                "\nfloor: 0, 1, 2, 3 \n familiarColour: black, neutral, orange, white ");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 3 ))
            throw new InputException();

        if (Integer.parseInt(parameters[1]) >= 0 && Integer.parseInt(parameters[1]) <= 3 )
            throw new InputException();

    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException{
        cli.choseAndTakeDevCard(action);
    }
}

