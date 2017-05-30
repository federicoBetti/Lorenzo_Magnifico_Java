package Project.Server.Network.Socket;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.Constants;
import Project.MODEL.FamilyMember;
import Project.Server.Network.PlayerHandler;
import Project.Server.NetworkException.canUseBothPaymentMethodException;
import Project.Server.NetworkException.cantDoActionException;
import Project.Messages.BonusInteraction;
import Project.Messages.Notify;
import Project.Messages.OkOrNo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * questa classe rappresenta la casse ponte tra il model e la view. da qua ogni volta che vinee aggiornato qualcosa il player dice al suo
 * client che qualcosa è stato aggiornato e quindi fa aggiornare la UI
 */

//todo implementare per ogni metodo i messaggi di ritorno al client o gia fatto in gameAction? controllare
public class SocketPlayerHandler extends PlayerHandler implements Runnable {

    SocketServer socketServer;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    ServerDataHandler serverDataHandler;

    public SocketPlayerHandler(SocketServer socketServer, Socket socket) throws IOException {
        super();
        this.socketServer = socketServer;
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        serverDataHandler = new ServerDataHandler(this, objectInputStream, objectOutputStream);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("waiting for Request...");
                Object object = objectInputStream.readObject();

                System.out.println("the client wants to do " + object);
                try {
                    serverDataHandler.handleRequest(object);
                } catch (cantDoActionException e) {
                    e.printStackTrace();
                } catch (canUseBothPaymentMethodException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void loginRequestAnswer(String nickname) throws IOException, ClassNotFoundException {
        socketServer.loginRequest(nickname, this);
    }


    public void takeDevCard() throws IOException, ClassNotFoundException, cantDoActionException, canUseBothPaymentMethodException {
        String towerColour = (String) objectInputStream.readObject();
        int floor = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        clientTakeDevelopementCard(towerColour, floor, familyMember);
    }

    public void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        int position = (int)objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int paymentChoosen = (int)objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        clientChoosenPaymentForVenturesCard(position, familyMember, paymentChoosen );
    }

    public void harvesterRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int servantsNumber = (int)objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        harvester(position, familyMember, servantsNumber);
    }

    //int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct
    public void productionRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();

        production(position, familyMember, cards );

    }

    //int position, FamilyMember familyM

    public void goToMarketRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        goToMarket(position, familyMember);
    }

    public void playLeaderCardRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        String leaderCardName =(String)objectInputStream.readObject();

        playLeaderCard(leaderCardName);
    }

    public void discardLeaderCardRequest() throws IOException, ClassNotFoundException, cantDoActionException {
        String leaderCardName =(String)objectInputStream.readObject();

        discardLeaderCard(leaderCardName);
    }

    //int privelgeNumber, FamilyMember familyMember
    public void goToCouncilPalaceRequest() throws IOException, ClassNotFoundException {
        int priviledgeNumber = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        goToCouncilPalace(priviledgeNumber, familyMember);
    }

    public void takePrivilegeRequest() throws IOException, ClassNotFoundException {
        int priviledgeNumber = (int) objectInputStream.readObject();

        takePrivilege(priviledgeNumber);
    }


    private LinkedList<BuildingCard> takeMyProductionCards(){
        return getPersonalBoardReference().getBuildings();
    }

    @Override
    public void sendAnswer(BonusInteraction returnFromEffect) {
        try {
            Object object = returnFromEffect;
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cantDoAction(OkOrNo okOrNo) {
        sendAnswer(okOrNo);
    }

    @Override
    public void canUseBothPaymentMethod() {
        Notify notify = new Notify(Constants.BOTH_PAYMENT_METHODS_AVAILABLE);
        sendAnswer(notify);
    }

    @Override
    public void itsMyTurn() {
        Notify notify = new Notify(Constants.YOUR_TURN);
        sendAnswer(notify);
    }

    @Override
    public void sendAskForPraying() {
        Notify notify = new Notify(Constants.ASK_FOR_PRAYING);
        sendAnswer(notify);
    }


    public FamilyMember findFamilyMember(String colour) {
        for (FamilyMember familyMember : getAllFamilyMembers())
            if (familyMember.getMyColour().equals(colour))
                return familyMember;

        return null;
    }

    private ArrayList<BuildingCard> receiveListOfBuildingCard() throws IOException, ClassNotFoundException {
        String messReceived = null;
        LinkedList<BuildingCard> myBuildings = takeMyProductionCards();
        ListIterator<BuildingCard> iterator = myBuildings.listIterator();
        ArrayList<BuildingCard> cardsForProduction = new ArrayList<BuildingCard>();

        while (!messReceived.equals(Constants.STOP)){
            messReceived = (String)objectInputStream.readObject();

            int currentCard = 0;
            while ( iterator.hasNext() ){
                if ( myBuildings.get(currentCard).equals(messReceived))
                    cardsForProduction.add(myBuildings.get(currentCard));
                currentCard++;
            }
        }
        return cardsForProduction;
    }
}
