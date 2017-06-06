package project.client.ui;

import project.client.clientexceptions.NotAllowedSelection;
import project.client.network.AbstractClient;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.rmi.RMIClient;
import project.client.network.socket.SocketClient;
import project.client.ui.cli.Cli;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.TowerAction;

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
                try {
                    client = new SocketClient(this);
                } catch (ClientConnectionException e) {
                    // errore nella conessione socket
                }
                break;
            case 2:
                try {
                    client = new RMIClient(this);
                } catch (ClientConnectionException e) {
                   // errore nella conessione RMI
                }
                break;
            default:
                try {
                    throw new NotAllowedSelection();
                } catch (NotAllowedSelection notAllowedSelection) {
                    //scelta non voluta
                }
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

    public void takeBonusCard(TowerAction towerAction ){
        ui.takeBonusCard(towerAction);
    }

    public void takeDevCard(String[] parameters) throws IOException {
        client.takeDevCard(parameters);
    }

    public void mainContext() {
        ui.mainContext();
    }

    public void takeBonusCard(TowerAction towerAction) {

    }

    public void doProductionHarvester(BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction) {

    }

    public void notifyClient(Notify notify) {

    }

    public void endTurn() {
    }

    public void takePrivilege(TakePrivilegesAction takePrivilegesAction) {

    }
}
