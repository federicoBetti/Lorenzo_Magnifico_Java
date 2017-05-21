package Project.Network.RMI;

import Project.MODEL.Card;
import Project.MODEL.FamilyMember;
import Project.Network.AbstractServer;
import Project.Network.ControllerInterface;
import Project.Server;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;
import java.util.HashMap;

//questa classe prende tutte le connessioni e i metodi chamti con RMI.
// poi in base a chi l'ha chiamato, invoca i meotid giusti sull'RMIGameActions corretto

//attenzione che è buona norma che le funzioni di implementazione del gioco (cioè non riguardanti la conessione) non ritornino niente.
// solo quelli di connessione possono ritornare qualcosa anche perchè sono gli unici metodi che non chiamano
// qualcosa su un ogetto RMIGameActions


public class ServerRMI extends AbstractServer implements RMIClientToServerInterface{


    /**
     * Internal cache that maps all logged used with an unique session token that identify the single player.
     * This is required in order to identify the rmi player when he is making a new request to the server.
     */
    private final HashMap<String, String> mSessionTokens;

    public ServerRMI( Server server ){
        super(server);
        mSessionTokens = new HashMap<>();
    }

    public void startServerRmi(int rmiPort) {
    }

    @Override
    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM) {
        return null;
    }

    @Override
    public void Harvester(int position, FamilyMember familyM) {

    }

    @Override
    public void Production(int position, FamilyMember familyM) {

    }

    @Override
    public void Production(int position, FamilyMember familyM, ArrayList<Card> cardToProduct) {

    }

    @Override
    public void GoTOMarket(int position, FamilyMember familyM) {

    }

    @Override
    public void JumpTurn() {

    }

    @Override
    public void PlayLeaderCard(String leaderName) {

    }

    @Override
    public void DiscardLeaderCard(String leaderName) {

    }

    @Override
    public void RollDice() {

    }

    @Override
    public void GoToCouncilPalace(int privelgeNumber) {

    }

    @Override
    public void GoToCouncilPalace() {

    }
}
