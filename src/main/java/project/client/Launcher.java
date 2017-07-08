package project.client;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.ClientSetter;

import java.io.IOException;

/**
 * The player can start the game running the main method of this class
 */
class Launcher {


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
        SingletonKeyboard keyboard = SingletonKeyboard.getInstance();
        while (true) {
            p1.println("Welcome to LORENZO IL MAGNIFICO!");
            p2.println("Please choose the kind of User interface experience between: ");
            p.println("1: CLI");
            p.println("2: GUI");
            String choice;
            int choiceNum = 0;
            try {
                choice = keyboard.readLine();

                for (int i = 0; i < choice.length(); i++)
                    if (!(Character.isDigit(choice.charAt(i))))
                        continue;

                choiceNum = Integer.parseInt(choice);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
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

    public static void main(String[] args) {
        new ClientSetter(selectUi());
    }


}
