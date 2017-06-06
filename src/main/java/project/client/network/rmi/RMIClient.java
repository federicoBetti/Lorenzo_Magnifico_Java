package project.client.network.rmi;


import project.client.ui.AbstractUI;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.server.network.rmi.RMIClientToServerInterface;

/**
 * qua devono essere implementati sia i metodi di AbstracClient (cioè tutti quelli che il client deve chiamare sul server, sia quelli della
 * interfaccia (quelli che il server chiama sul client)
 * devo avere un riferimento al main game, cioè al client vero e proprio. all'RMIClient arrivano le risposte e le passa al main game
 *  che poi a sua volta chiama il relatiivo aggiornamento di GUI.
 *  questi metodi che ritornano sono anche quelli dell'update della UI in tempo reale
 */
public class RMIClient extends AbstractClient implements RMIServerToClientInterface {

    RMIClientToServerInterface server;
    String myUniqueId;

    public RMIClient(ClientSetter ui) {
        super();
    }
}
