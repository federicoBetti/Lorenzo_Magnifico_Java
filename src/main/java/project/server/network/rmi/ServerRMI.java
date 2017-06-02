package project.server.network.rmi;

import project.model.DevelopmentCard;
import project.model.FamilyMember;
import project.server.network.AbstractServer;
import project.server.Server;
import project.messages.BonusInteraction;
import project.server.network.socket.SocketPlayerHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//questa classe prende tutte le connessioni e i metodi chamti con rmi.
// poi in base a chi l'ha chiamato, invoca i meotid giusti sull'rmiGameActions corretto

//attenzione che è buona norma che le funzioni di implementazione del gioco (cioè non riguardanti la conessione) non ritornino niente.
// solo quelli di connessione possono ritornare qualcosa anche perchè sono gli unici metodi che non chiamano
// qualcosa su un ogetto RMIGameActions


public class ServerRMI extends AbstractServer implements RMIClientToServerInterface{


    /**
     * Internal cache that maps all logged used with an unique session token that identify the single player.
     * This is required in order to identify the rmi player when he is making a new request to the server.
     */
    private final Map<String, String> mSessionTokens;

    public ServerRMI( Server server ){
        super(server);
        mSessionTokens = new HashMap<>();
    }

    @Override
    public void loginRequest(String nickname, SocketPlayerHandler socketPlayerHandler) throws IOException {
        //to implement
    }

    public void startServerRmi(int rmiPort) {
        // to implement
    }

    @Override
    public BonusInteraction takeDevelopementCard(String towerColor, int position, FamilyMember familyM) {
        return null;
    }

    @Override
    public void harvester(int position, FamilyMember familyM) {
        // to implement
    }

    @Override
    public void production(int position, FamilyMember familyM) {
        // to implement
    }

    @Override
    public void production(int position, FamilyMember familyM, List<DevelopmentCard> cardToProduct) {
        // to implement
    }

    @Override
    public void goTOMarket(int position, FamilyMember familyM) {
        // to implement
    }

    @Override
    public void jumpTurn() {
        // to implement
    }

    @Override
    public void playLeaderCard(String leaderName) {
        // to implement
    }

    @Override
    public void discardLeaderCard(String leaderName) {
        // to implement
    }

    @Override
    public void rollDice() {
        // to implement
    }

    @Override
    public void goToCouncilPalace(int privelgeNumber) {
        // to implement
    }

    @Override
    public void goToCouncilPalace() {
        // to implement
    }
}
