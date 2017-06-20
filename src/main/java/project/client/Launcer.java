package project.client;

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

        SingletonKeyboard keyboard = SingletonKeyboard.getInstance();
        while (true) {
            System.out.println("1: CLI");
            System.out.println("2: GUI");
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
