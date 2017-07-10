package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.model.FamilyMember;
import project.model.Tower;

import java.io.IOException;
import java.util.Map;


/**
 * This method is a context that is opened when the players wants to take a card form a tower
 */
public class TowersContext extends AbstractContext {

    private Tower[][] allTowers;
    public TowersContext(Cli cli, Tower[][] allTowers) {
        super(cli);
        this.allTowers = allTowers;
        map.put(CliConstants.SHOW_TOWERS_CARDS_COST, this:: showCardsCost );
        map.put(CliConstants.SHOW_TOWERS_CARDS_EFFECTS, this::showCardsEffects);
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        printHelp();
    }

    /**
     * This method pritns all tower cards' effects
     */
    private void showCardsEffects() {
        super.showCardsEffectsTower(allTowers);
        pRed.println("Type help for see the available commands.");

    }

    /**
     * This method prints all tower cards' cost
     */
    private void showCardsCost() {
        super.showCardCostTower(allTowers);
        pRed.println("Type ");pBlue.print("[help]");pRed.println("for watching the other commands.");
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("You are in the Tower Context! The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pBlue.println(entry.getKey());

        pRed.println("The main Action is:");
        pBlue.println("[towerColour-floor-familiarColour] ");
        pRed.print("towerColour: "); pYellow.println("green, yellow, purple, blue");
        pRed.print("floor: ");  pYellow.println("0, 1, 2, 3");
        pRed.print("Familiar Colour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pYellow.println("");
    }

    /**
     * Check if the input is valid for this context
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    @Override
    public void checkValidInput(String input) throws InputException, NumberFormatException {
        String[] parameters = input.split("-");

        if(( parameters.length != 3 ))
            throw new InputException();

        checkTowerColour(parameters[0]);

        try {
            if (Integer.parseInt(parameters[1]) < 0 || Integer.parseInt(parameters[1]) > 3)
                throw new InputException();
        }
        catch (NumberFormatException e){
            throw new InputException();
        }

        checkFamilyMemberColour(parameters[2]);
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls loginRequest
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action) throws InputException, IOException{
        cli.choseAndTakeDevCard(action);
    }
}

