package project.server.network.socket;

import project.controller.cardsfactory.BuildingCard;
import project.controller.Constants;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
                } catch (CantDoActionException e) {
                    e.printStackTrace();
                } catch (CanUseBothPaymentMethodException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    //ok
    public void loginRequestAnswer(String nickname) throws IOException, ClassNotFoundException {
        socketServer.loginRequest(nickname, this);
    }


    //ok
    public void takeDevCard() throws IOException, ClassNotFoundException {
        String towerColour = (String) objectInputStream.readObject();
        int floor = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            clientTakeDevelopementCard(towerColour, floor, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction(new OkOrNo());
        } catch (CanUseBothPaymentMethodException e) {
            canUseBothPaymentMethod(new BothCostCanBeSatisfied());
        }
    }

    public void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        int position = (int)objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int paymentChosen = (int)objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        clientChosenPaymentForVenturesCard(position, familyMember, paymentChosen );
    }

    //ok
    public void harvesterRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int servantsNumber = (int)objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        harvester(position, familyMember, servantsNumber);
    }

    //ok
    //int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct
    public void productionRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();

        production(position, familyMember, cards );

    }

    //ok
    //int position, FamilyMember familyM
    public void goToMarketRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        goToMarket(position, familyMember);
    }

    //ok
    public void playLeaderCardRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        String leaderCardName =(String)objectInputStream.readObject();

        playLeaderCard(leaderCardName);
    }

    //ok
    public void discardLeaderCardRequest() throws IOException, ClassNotFoundException, CantDoActionException {
        String leaderCardName =(String)objectInputStream.readObject();

        discardLeaderCard(leaderCardName);
    }

    //ok
    //int privelgeNumber, FamilyMember familyMember
    public void goToCouncilPalaceRequest() throws IOException, ClassNotFoundException {
        int privilegeNumber = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        goToCouncilPalace(privilegeNumber, familyMember);
    }


    public void takePrivilegeRequest() throws IOException, ClassNotFoundException {
        int privilegeNumber = (int) objectInputStream.readObject();

        takePrivilege(privilegeNumber);
    }


    private List<BuildingCard> takeMyProductionCards(){
        return getPersonalBoardReference().getBuildings();
    }

    @Override
    public void sendAnswer(Object returnFromEffect) {
        try {
            objectOutputStream.writeObject(returnFromEffect.toString());

            objectOutputStream.writeObject(returnFromEffect);
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
    public int canUseBothPaymentMethod(BothCostCanBeSatisfied bothCosts) {
        Notify notify = new Notify(Constants.BOTH_PAYMENT_METHODS_AVAILABLE);

        try {
            objectOutputStream.writeObject(bothCosts.toString());


        objectOutputStream.writeObject(notify);
        objectOutputStream.flush();
        objectOutputStream.reset();


            return Integer.parseInt((String) objectInputStream.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
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

    @Override
    public void sendNotification(Notify notifications) {
        sendAnswer(notifications);
    }

    @Override
    public void sendUpdates(Updates updates) {
        sendAnswer(updates);
    }

    @Override
    public int sendPossibleChoice(String kindOfChoice) {

        int readObject = -1;
        try {
        objectOutputStream.writeObject(kindOfChoice);
        objectOutputStream.flush();
        objectOutputStream.reset();
        readObject =  (int)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readObject;
    }


    private ArrayList<BuildingCard> receiveListOfBuildingCard() throws IOException, ClassNotFoundException {
        String messReceived = null;
        List<BuildingCard> myBuildings = takeMyProductionCards();
        ListIterator<BuildingCard> iterator = myBuildings.listIterator();
        ArrayList<BuildingCard> cardsForProduction = new ArrayList<>();

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
