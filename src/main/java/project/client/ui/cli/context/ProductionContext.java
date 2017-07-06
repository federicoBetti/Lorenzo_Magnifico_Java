package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.CliConstants;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.BuildingCard;
import project.controller.effects.realeffects.Effects;
import project.model.FamilyMember;
import project.model.Production;
import project.model.Tile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class ProductionContext extends AbstractContext {
    List<Production> productionZone;
    Tile bonusTile;
    List<BuildingCard> buildings;
    public ProductionContext(Cli cli, List<Production> productionZone, Tile bonusTile, List<BuildingCard> buildings){
        super(cli);
        this.productionZone = productionZone;
        this.bonusTile = bonusTile;
        this.buildings = buildings;
        map.put(CliConstants.EXIT, this::exit);
        map.put(CliConstants.HELP, this::printHelp);
        map.put(CliConstants.SHOW_PRODUCTION_ZONE, this:: showProductionZone );
        map.put(CliConstants.SHOW_BONUS_TILE, this:: showBonusTile );
        map.put(CliConstants.SHOW_BUILDING_CARDS, this:: showBuildingCards );
        printHelp();
    }

    private void showBuildingCards() {
        int count1 = 1;
        for (BuildingCard card : buildings) {
            pBlue.print(count1 + ") Card name: ");
            pRed.println(card.getName());
            pBlue.println("Permanent Effects: ");
            int count2 = 1;
            for (Effects effect : card.getPermanentCardEffects()) {
                pBlue.print(count2 + ") ");
                pYellow.println(effect.toScreen());
                count2++;
            }
            count1++;
        }
    }

    private void showBonusTile() {
        pBlue.print(bonusTile.getTileNumber() + ") ");pRed.println("Production effects: ");

        int i = 1;
        for ( Effects effect : bonusTile.takeProductionResource() ){
            pYellow.println( i + ") " + effect.toScreen());
            i++;
        }
        pRed.println("\nHarvester Bonus: ");
        int j = 1;
        for ( Effects effect : bonusTile.takeHarvesterResource() ){
            pYellow.println( j + ") " + effect.toScreen());
            j++;
        }
    }

    private void showProductionZone() {
        int i = 1;
        pRed.println("Production zone: ");
        for ( Production production : productionZone ){
            pBlue.print(i + ") ");pRed.print("Player colour: ");pYellow.println(production.getFamiliarOnThisPosition().getFamilyColour());
            pRed.print("   Familiar colour: ");pRed.print("Familiar Colour: ");
            for (FamilyMember familyMember : cli.getMyFamilymembers() ){
                if ( !familyMember.isPlayed() )
                    pYellow.print(familyMember.getMyColour() + "   ");
            }
            i++;
        }
    }



    @Override
    public void printHelp() {
       pRed.println("The available actions are:");
        for (Map.Entry<String, Actioner> entry: map.entrySet())
            pYellow.println(entry.getKey());

        pBlue.println("[familiarColour-buildingCard-...(max 6 buildingCard in your personal board]");
        pRed.print("familiarColour: ");
        for (FamilyMember familyMember : cli.getMyFamilymembers() ){
            if ( !familyMember.isPlayed() )
                pYellow.print(familyMember.getMyColour() + "   ");
        }
        pRed.println("");
        pRed.print("buildingCard: ");pYellow.println("name1 - name2 - ... ");
        pYellow.println("");
    }

    @Override
    public void checkValidInput(String input) throws InputException {
        boolean cardAvailable = false;
        String[] parameters = input.split("-");

        if ( !(parameters.length < 7))
            throw new InputException();

        checkFamilyMemberColour(parameters[0]);

        for ( int j = 1; j < parameters.length; j++  ) {
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).getName().equals(parameters[j])) {
                    cardAvailable = true;
                    break;
                }
            }
            if (!cardAvailable )
                throw new InputException();
            cardAvailable = false;
        }
    }

    @Override
    public void mainContextMethod(String action) throws InputException, IOException {
        cli.chooseProductionParameters(action);
    }
}
