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


    public void takeDevCard(String towerColour, String floor, String familiarColour ) throws IOException, ClassNotFoundException {
        client.takeDevCard(towerColour, floor, familiarColour);
    }

    public void mainContext() {
        ui.mainContext();
    }

    public void harvesterAction(String parameter1, String parameter2, String parameter3) throws IOException, ClassNotFoundException {
        client.harvesterAction(parameter1, parameter2, parameter3);
    }

    public void marketAction(String parameter1, String parameter2) throws IOException, ClassNotFoundException {
        client.marketAction(parameter1, parameter2);
    }

    public void councilAction(String parameter1, String parameter2 ) throws IOException, ClassNotFoundException {
        client.councilAction( parameter1, parameter2);
    }

    public void productionAction(String[] parameters) throws IOException, ClassNotFoundException {
        client.productionAction(parameters);
    }

    public void waitingForNewIntraction() throws IOException, ClassNotFoundException {
        client.waitingForTheNewInteraction();
    }
    //metodi di ritorno

    public void takeBonusCard(TowerAction towerAction ){
        ui.takeBonusCard(towerAction);
    }


    public void doProductionHarvester(BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction) {
        //to implement
    }

    public void notifyClient(Notify notify) {
        //to implement
    }

    public void endTurn() {
        //to implement
    }

    public void takePrivilege(TakePrivilegesAction takePrivilegesAction) {
        //to implement
    }


    public void playLeaderCard(String action) throws IOException, ClassNotFoundException {
        client.playLeaderCard(action);
    }

    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {
        client.discardLeaderCard(name);
    }
}
