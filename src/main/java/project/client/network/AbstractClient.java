package project.client.network;

import java.io.IOException;

/**
 * queste sono tutte le funzioni che il client puo chiamare sul server che poi dovranon essere implementate sia dall'rmi sia dal socket
 */
public class AbstractClient {


    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
    }

    public void waitingForTheNewInteraction() throws IOException, ClassNotFoundException {
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour ) throws IOException, ClassNotFoundException{
    }

    public void harvesterAction(String parameter1, String parameter2, String parameter3) throws IOException, ClassNotFoundException {
    }

    public void marketAction(String parameter1, String parameter2) throws IOException, ClassNotFoundException {
    }

    public void councilAction(String parameter1, String parameter2) throws IOException, ClassNotFoundException {
    }

    public void productionAction(String[] parameters) throws IOException, ClassNotFoundException {
    }

    public void playLeaderCard(String action) throws IOException, ClassNotFoundException {
    }

    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {
    }

    public void prayOrNot(String action) throws IOException, ClassNotFoundException {
    }

    public void sendExitToBonusAction() throws IOException, ClassNotFoundException {
    }

    public void choicePe() {
    }

    public void sendChoicePe(String input) throws IOException, ClassNotFoundException {
    }
}
