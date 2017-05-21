package Project.Network.Socket;

/**
 * Created by raffaelebongo on 21/05/17.
 */

import Project.Network.PlayerHandler;

/**
 * Ha un riferimento al PlayerHandler. Dopo che il PlayerHandler ha ricevuto il primo paccjetto TCP con
 * il tipo di mossa da effettuare, invia l'oggetto contenente tale informazione al DataActionHandler che,
 * in base al tipo di mossa ( attraverso un'interfaccia funzionale ), si prepara a ricevere determinati
 * dati dal client a cui è connesso. Successivamente in callback ritorna al PlayerHandler che chiama la sua
 * room e da li il preciso metodo dalla classe delle azioni che modifica lo stato della partita.
 */

public class DataActionHandler {

    PlayerHandler playerHandler;
}
