package project.client;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.ClientSetter;
import project.controller.Constants;

import java.io.IOException;

/**
 * The player can start the game running the main method of this class
 */
class Launcher {

    private Launcher(){
        ClientSetter clientSetter = new ClientSetter(selectUi());
    }

    /**
     * This method is used for asking to the player if he wants to play with the Cli or Gui user interface
     *
     * @return the string that represents the choice
     */
    private static String selectUi() {

        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.YELLOW);
        UnixColoredPrinter p = new UnixColoredPrinter(builder);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.BLUE);
        UnixColoredPrinter p1 = new UnixColoredPrinter(builder);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.RED);
        UnixColoredPrinter p2 = new UnixColoredPrinter(builder);
        UnixColoredPrinter.Logger logger = new UnixColoredPrinter.Logger();
        logger.toString();
        while (true) {
            p1.println("Welcome to LORENZO IL MAGNIFICO!");
            p2.println("Please choose the kind of User interface experience between: ");
            p.println("1: CLI");
            p.println("2: GUI");
            String choice;
            int choiceNum = 0;
            try {
                choice = SingletonKeyboard.readLine();

                for (int i = 0; i < choice.length(); i++)
                    if (!(Character.isDigit(choice.charAt(i))))
                        continue;

                choiceNum = Integer.parseInt(choice);

            } catch (IOException e) {
                UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
            } catch (NumberFormatException e) {
                UnixColoredPrinter.Logger.print("wrong choice");
                continue;
            }
            switch (choiceNum) {
                case 1:
                    return "CLI";
                case 2:
                    return "GUI";
                default:

            }
        }
    }

    /**
     * main methods
     *
     * @param args args
     */
    public static void main(String[] args) {
        new Launcher();
    }


}
