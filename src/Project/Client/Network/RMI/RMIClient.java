package Project.Client.Network.RMI;


import Project.Client.Network.AbstractClient;
import Project.Server.Network.RMI.RMIClientToServerInterface;

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

    /*
    faccio copia incolla dei due metodi iniziale per capire

        @Override
    public void connect() throws ClientConnectionException {
        try {
            Registry registry = LocateRegistry.getRegistry(getAddress(), getPort());
            mServer = (RMIServerInterface) registry.lookup("RMIServerInterface");
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException | NotBoundException e) {
            throw new ClientConnectionException(e);
        }
    }

    /**
     * Login player to RMIServer with provided nickname.
     * @param nickname to use for login.
     * @throws NetworkException if server is not reachable.

    @Override
    public void loginPlayer(String nickname) throws NetworkException {
        try {
            mSessionToken = mServer.loginPlayer(nickname, this);
        } catch (LoginException e) {
            throw e;
        } catch (IOException e) {
            throw new NetworkException(e);
        }
    }

*/

}
