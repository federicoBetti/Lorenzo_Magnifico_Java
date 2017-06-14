package project.client;

import project.client.clientexceptions.NotAllowedSelection;
import project.client.ui.ClientSetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("1 -> CLI");
            System.out.println("2 -> GUI");
            try {
                int choice = Integer.parseInt(keyboard.readLine());
                switch (choice) {
                    case 1:
                        return "CLI";
                    case 2:
                        return "GUI";
                    default:
                        throw new NumberFormatException("Not a valid choice");
                }
            } catch (IOException | NumberFormatException e) {
            }
        }
    }

    public static void main(String[] args){
        new ClientSetter(selectUi());
    }

}
