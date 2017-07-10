package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.model.Council;
import project.model.FamilyMember;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class represent a context that is openend when the player wants to place a familiar in the council zone
 */
public class CouncilContext extends AbstractContext {
    List<Council> councilZone;

    /**
     * Constructor
     *
     * @param cli cli's reference
     * @param councilZone council's zone reference
     */
    public CouncilContext(Cli cli, List<Council> councilZone){
        super(cli);
        this.councilZone = councilZone;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_COUNCIL_ZONE, this:: showCouncilZone );
        printHelp();
    }

    /**
     * This methods prints the familiars already placed in the council zone
     */
    private void showCouncilZone() {
        int i = 1;
        for ( Council council : councilZone ){
            pBlue.print(i + ") ");pRed.print("Family colour: ");pYellow.print(council.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print(" Familiar colour: ");pYellow.println(council.getFamiliarOnThisPosition().getMyColour());
        }
    }

    /**
     * This method prints the help menu
     */
    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pBlue.println("[priviledgeNumber(int)-familiarColour]");
        pRed.print("priviledgeNumber: "); pYellow.println("0, 1, 2, 3, 4 ");
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
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        if( !(parameters[0].length() == 1) && !(Character.isDigit(parameters[0].charAt(0))))
            throw new InputException();

        try {
            if (!(Integer.parseInt(parameters[0]) >= 0) && !(Integer.parseInt(parameters[0]) < 5))
                throw new InputException();
        } catch (NumberFormatException e ){
            printHelp();
        }

        checkFamilyMemberColour(parameters[1]);
    }

    /**
     * If the string in input does not correspond with no key, this method is called and it calls chooseCouncilParameters
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    @Override
    public void mainContextMethod(String action ) throws InputException, IOException {
        cli.chooseCouncilParameters(action);
    }


}
