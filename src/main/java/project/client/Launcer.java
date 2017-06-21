package project.client;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.ClientSetter;

import java.io.IOException;

/**
 * Created by raffaelebongo on 20/05/17.
 */
public class Launcer {

    //todo completare
    int x;
    public Launcer(int x){
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
            int choice = 0;
            try {
                choice = Integer.parseInt(keyboard.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (choice) {
                    case 1:
                        return "CLI";
                    case 2:
                        return "GUI";
                    default:
                        throw new NumberFormatException("Not a valid choice");
                }
        }
    }

    public static void main(String[] args){
        new ClientSetter(selectUi());
    }


}
