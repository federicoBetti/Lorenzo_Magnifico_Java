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
 * Created by raffaelebongo on 05/06/17.
 */
public class CouncilContext extends AbstractContext {
    List<Council> councilZone;

    public CouncilContext(Cli cli, List<Council> councilZone){
        super(cli);
        this.councilZone = councilZone;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_COUNCIL_ZONE, this:: showCouncilZone );
        printHelp();
    }

    private void showCouncilZone() {
        int i = 1;
        for ( Council council : councilZone ){
            pBlue.print(i + ") ");pRed.print("Family colour: ");pYellow.print(council.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print(" Familiar colour: ");pYellow.println(council.getFamiliarOnThisPosition().getMyColour());
        }
    }

    @Override
    public void printHelp() {
        pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pRed.println("The main action is:");
        pBlue.println("[priviledgeNumber(int)-familiarColour]");
        pRed.print("priviledgeNumber: "); pYellow.println("0, 1, 2, 3, 4, 5 ");
        pRed.print("Familiar Colour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pYellow.println("");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        String[] parameters = input.split("-");

        if(!( parameters.length == 2 ))
            throw new InputException();

        if( !(parameters[0].length() == 1 && Character.isDigit(parameters[0].charAt(0))))
            throw new InputException();

        if (!(Integer.parseInt(parameters[0]) >= 0 && Integer.parseInt(parameters[0]) < 6 ))
            throw new InputException();

        checkFamilyMemberColour(parameters[1]);
    }

    @Override
    public void mainContextMethod(String action ) throws InputException, IOException {
        cli.chooseCouncilParameters(action);
    }


}
