package project.client.ui.cli.context;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;
import project.controller.Constants;
import project.controller.cardsfactory.VenturesCard;
import project.controller.cardsfactory.VenturesCost;
import project.controller.effects.realeffects.Effects;
import project.model.Tower;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This Abstract class in extended by all the real context
 */
public abstract class AbstractContext {
    Cli cli;
    private Actioner actioner;
    Map<String, Actioner> map;
    UnixColoredPrinter pYellow;
    UnixColoredPrinter pRed;
    UnixColoredPrinter pBlue;

    /**
     * Constructor
     *
     * @param cli cli's reference
     */
    AbstractContext(Cli cli){
        this.cli = cli;
        map = new HashMap<>();
        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.YELLOW);
        this.pYellow = new UnixColoredPrinter(builder);

        builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.RED);
        this.pRed = new UnixColoredPrinter(builder);

        builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.BLUE);
        this.pBlue = new UnixColoredPrinter(builder);
    }

    /**
     * This method calls the mainContext method on the cli
     */
    void exit() {
        cli.mainContext();
    }

    /**
     * Abstract printHelp method. Every context override this method
     */
    public abstract void printHelp();

    /**
     * Abstract checkValidInput
     *
     * @param input String given in input
     * @throws InputException exception thrown when the client type an invalid input
     */
    public abstract void checkValidInput( String input ) throws InputException;

    /**
     * Abstract main context method. Methodo called when the input string does not correspond with any key of the
     * context hashMap
     *
     * @param action string in input
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    protected abstract void mainContextMethod(String action) throws InputException, IOException;

    /**
     * This method check if the family member's colour typed is correct
     *
     * @param colour colour choosen as a String
     * @throws InputException exception thrown when the client type an invalid input
     */
    void checkFamilyMemberColour(String colour) throws InputException {
        if ( !(colour.equals(Constants.FAMILY_MEMBER_COLOUR_BLACK) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_NEUTRAL) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_ORANGE) ||
                colour.equals(Constants.FAMILY_MEMBER_COLOUR_WHITE)))
            throw new InputException();
    }

    /**
     * This method check if the tower colour typed is correct
     *
     * @param towerColour tower colour choosen as a String
     * @throws InputException exception thrown when the client type an invalid input
     */
    void checkTowerColour(String towerColour) throws InputException {
        if(!(towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_BUILDING_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_CHARACTER_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_VENTURES_CARD) ||
                towerColour.equals(Constants.COLOUR_OF_TOWER_WITH_TERRITORY_CARD)))
            throw new InputException();
    }

    /**
     * If the input typed correspond to a key of the map, the method action called on the functional interface instance
     * is the method that corresponds with the key, else it is called the mainContextMethod
     *
     * @param action input typed by the player
     * @throws InputException exception thrown when the client type an invalid input
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
    public void doAction(String action) throws InputException, IOException {
        if( map.get(action) != null ) {
            actioner = map.get(action);
            actioner.action();
        } else
            mainContextMethod(action);
    }

    /**
     * This method print and error message
     */
    public void cantDoAction(){
        pRed.println("Error, action not avaiable. Please try with another input");
    }

    public void showCardsEffectsTower(Tower[][] allTowers) {

        for (int i = 0; i < Constants.NUMBER_OF_TOWERS; i++ ) {
            pBlue.print("Tower: "); pRed.println(allTowers[i][i].getColour());
            for (int j = 0; j < Constants.NUMBER_OF_FLOORS; j++) {
                if ( allTowers[i][j].getCardOnThisFloor() == null ){
                    pBlue.print("Floor: "); pRed.println( j );pBlue.println("The card has been taken");
                    continue;
                }

                pBlue.print("Floor: "); pRed.println( j );
                pBlue.print("Card name: "); pRed.println(allTowers[i][j].getCardOnThisFloor().getName());
                pBlue.print("Immediate Effects: \n"); int count1 = 1;
                for (Effects effect : allTowers[i][j].getCardOnThisFloor().getImmediateCardEffects() ){
                    pBlue.print( count1 + ") ");pYellow.println(effect.toScreen());
                    count1++;
                }
                pBlue.print("Permanent Effects: \n"); int count2 = 1;
                for (Effects effect : allTowers[i][j].getCardOnThisFloor().getPermanentCardEffects() ){
                    pBlue.print( count2 + ") ");pYellow.println(effect.toScreen());
                    count2++;
                }
                pRed.println("");
            }
        }
    }

    public void showCardCostTower(Tower[][] allTowers) {

        for (int i = 0; i < Constants.NUMBER_OF_TOWERS; i++ ) {
            pBlue.print("Tower: "); pRed.println(allTowers[i][i].getColour());
            for (int j = 0; j < Constants.NUMBER_OF_FLOORS; j++) {
                if ( allTowers[i][j].getCardOnThisFloor() == null ){
                    pBlue.print("Floor: "); pRed.println( j );pBlue.println("The card has been taken");
                    continue;
                }

                pBlue.print("Floor: "); pRed.println( j );
                pBlue.print("Card name: "); pRed.println(allTowers[i][j].getCardOnThisFloor().getName());

                if ( allTowers[i][j].getCardOnThisFloor() instanceof VenturesCard) {
                    VenturesCard card = (VenturesCard) allTowers[i][j].getCardOnThisFloor();
                    pBlue.print("Card cost: ");
                    List<VenturesCost> costs = card.getPossibleCost();
                    for ( VenturesCost cost : costs )
                        pYellow.print(cost.toScreen() + " ");
                    pYellow.println("");
                }

                else {
                    pBlue.print("Card cost: ");
                    pYellow.println(allTowers[i][j].getCardOnThisFloor().getCost().toScreen());
                    pYellow.println("");
                }
            }
        }

    }

    /**
     * Functional interface with a void method action.
     */
    @FunctionalInterface
    public interface Actioner{
         void action() throws InputException, IOException;
    }

    /**
     * Get the yellow printer
     * @return yellow printer
     */
    public UnixColoredPrinter getpYellow() {
        return pYellow;
    }

    /**
     * Get the red printer
     * @return red printer
     */
    public UnixColoredPrinter getpRed() {
        return pRed;
    }

    /**
     * Get the blue printer
     * @return blue printer
     */
    public UnixColoredPrinter getpBlue() {
        return pBlue;
    }
}













