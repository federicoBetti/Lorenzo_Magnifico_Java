package project.client;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.ClientSetter;

import java.io.IOException;

/**
 * Created by raffaelebongo on 20/05/17.
 */
class Launcher {

    //todo completare
    private int x;

    public Launcher(int x) {
        this.x = x;
    }

    private static String selectUi() {

        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.YELLOW);
        UnixColoredPrinter p = new UnixColoredPrinter(builder);

        SingletonKeyboard keyboard = SingletonKeyboard.getInstance();
        while (true) {
            p.println("1: CLI");
            p.println("2: GUI");
            String choice = null;
            int choiceNum = 0;
            try {
                choice = keyboard.readLine();

                for (int i = 0; i < choice.length(); i++)
                    if (!(Character.isDigit(choice.charAt(i))))
                        continue;

                choiceNum = Integer.parseInt(choice);

            } catch (IOException e) {
                e.printStackTrace();
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
