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
}
