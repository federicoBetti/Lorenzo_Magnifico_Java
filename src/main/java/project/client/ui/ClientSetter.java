package project.client.ui;

import project.client.clientexceptions.NotALlowedSelection;
import project.client.network.AbstractClient;
import project.client.network.rmi.RMIClient;
import project.client.network.socket.SocketClient;
import project.client.ui.cli.Cli;

import java.io.IOException;
import java.util.Scanner;

/**
 * this class is the "bridge" between user interface and client.
 */
public class ClientSetter {
    AbstractClient client;
    AbstractUI ui;

    public ClientSetter(String kindOfUI ) {
        if ( kindOfUI.equals("CLI"))
            ui = new Cli(this);
        else
            ui = new Gui(this);
        setConnectionType();
        ui.startUI();
    }

    private void setConnectionType() {
        System.out.println("Select kind of connection: 1-Socket, 2-RMI ");
        Scanner input = new Scanner(System.in);
        int connectionType = input.nextInt();

        switch (connectionType) {
            case 1:
                client = new SocketClient(this);
                break;
            case 2:
                client = new RMIClient(this);
                break;
            default:
                new NotALlowedSelection();
                break;
        }
    }

    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
        client.loginRequest(loginParameter);
    }

    //va giu verso il client che manda
    public void waitingForNewIntraction() throws IOException, ClassNotFoundException {
        client.waitingForTheNewInteraction();
    }

    //va su verso la UI
    public void handleMessage(String message) throws IOException, ClassNotFoundException {
        ui.handleMessage(message);
    }
}
