package project.client;

import project.client.clientexceptions.NotAllowedSelection;
import project.client.ui.ClientSetter;

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


    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Select the UserInterface's type: 1-CLI, 2-GUI");

        int connectionType = input.nextInt();

        switch (connectionType){
            case 1:
                new ClientSetter("CLI");
                break;
            case 2:
                new ClientSetter("GUI");
                break;
            default:
                new NotAllowedSelection();
                break;
        }
    }

}
