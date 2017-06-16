package project.server.network.socket;

import project.controller.cardsfactory.BuildingCard;
import project.controller.Constants;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.server.network.PlayerHandler;
import project.server.network.exception.CanUseBothPaymentMethodException;
import project.server.network.exception.CantDoActionException;

import java.io.Closeable;
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
        try {
            while (true) {
                System.out.println("waiting for Request...");
                Object object = objectInputStream.readObject();

                System.out.println("the client wants to do " + object);
                    serverDataHandler.handleRequest(object);

            }
        } catch (CantDoActionException e) {
             cantDoAction();
        } catch (CanUseBothPaymentMethodException e) {
            canUseBothPaymentMethod();
        } catch (IOException e) {   //todo queste due eccezioni qui
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeSocket(objectInputStream );
            closeSocket(objectOutputStream );
            closeSocket(socket );
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
            cantDoAction();
        } catch (CanUseBothPaymentMethodException e) {
            canUseBothPaymentMethod();
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
    public void harvesterRequest() throws IOException, ClassNotFoundException{
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int servantsNumber = (int)objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            harvester(position, familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    //int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct
    public void productionRequest() throws IOException, ClassNotFoundException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();

        try {
            production(position, familyMember, cards );
        } catch (CantDoActionException e) {
            cantDoAction();
        }

    }

    //ok
    //int position, FamilyMember familyM
    public void goToMarketRequest() throws IOException, ClassNotFoundException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    public void playLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName =(String)objectInputStream.readObject();

        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    public void discardLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName =(String)objectInputStream.readObject();

        try {
            discardLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
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
    public void sendActionOk( String ok ){
        try {
            objectOutputStream.writeObject(ok);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void cantDoAction() {
        sendString(Constants.CANT_DO_ACTION);
    }

    @Override
    public int canUseBothPaymentMethod() {
        sendString(Constants.BOTH_PAYMENT_METHODS_AVAILABLE);
        int choice = - 1;
        try {
            choice =  Integer.parseInt((String)objectInputStream.readObject());
            return choice;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return choice;
    }


    @Override
    public void itsMyTurn() {
        sendAnswer(Constants.YOUR_TURN);
    }

    @Override
    public void sendAskForPraying() {
        Notify notify = new Notify(Constants.ASK_FOR_PRAYING);
        sendAnswer(notify);
    }

    @Override
    public void sendString(String message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo check utility
    @Override
    public void sendNotification(Notify notifications) {
        sendAnswer(notifications);
    }

    @Override
    public void sendUpdates(Updates updates) {
        sendAnswer(updates);
    }


    //todo controllare il -1 che è feo
    @Override
    public int sendPossibleChoice(String kindOfChoice) {

        int choice = -1;
        try {
        objectOutputStream.writeObject(kindOfChoice);
        objectOutputStream.flush();
        objectOutputStream.reset();

        String choiceS = (String)objectInputStream.readObject();
        choice = Integer.parseInt(choiceS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return choice;
    }

    @Override
    public void sendBonusTowerAction( TowerAction returnFromEffect ) throws IOException, ClassNotFoundException {

        while ( true ){

            sendAnswer(returnFromEffect);
            String answer = (String)objectInputStream.readObject();

            if( answer.equals(Constants.EXIT))
                return;

            try {
                //serve passargli il returnFromEffect perchè contiene lo sconto da applicare
                takeBonusDevCard(returnFromEffect);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (CantDoActionException e) {
                objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                objectOutputStream.flush();
                objectOutputStream.reset();
                continue;
            }
            break;
        }
    }

    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) throws IOException, ClassNotFoundException {
        while ( true ) {
            try {

                sendAnswer(returnFromEffect);
                String answer = (String)objectInputStream.readObject();
                int intServantsNumber;

                switch (answer){
                    case Constants.EXIT:
                    return;

                    case Constants.BONUS_PRODUCTION:
                        intServantsNumber = Integer.parseInt((String)objectInputStream.readObject());
                        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();
                        doBonusProduct(returnFromEffect, intServantsNumber, cards);
                        break;

                    case Constants.BONUS_HARVESTER:
                        intServantsNumber = Integer.parseInt((String) objectInputStream.readObject());
                        doBonusHarv(returnFromEffect, intServantsNumber);
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (CantDoActionException e) {
                objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                objectOutputStream.flush();
                objectOutputStream.reset();
                continue;
            }
            break;

        }
    }

    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) throws IOException, ClassNotFoundException {
        sendAnswer(returnFromEffect);
        takePriviledgesInArow(returnFromEffect);
    }

    @Override
    public void takePriviledgesInArow(TakePrivilegesAction returnFromEffect) throws IOException, ClassNotFoundException {
        for ( int count = 0; count < returnFromEffect.getQuantityOfDifferentPrivileges(); count++ ){
            int privilegeNumber = Integer.parseInt((String) objectInputStream.readObject());
            takePrivilege(privilegeNumber);
        }
    }

    private void takeBonusDevCard(TowerAction returnFromEffect) throws IOException, ClassNotFoundException, CantDoActionException {

        int floor = Integer.parseInt((String)objectInputStream.readObject());
        clientTakeBonusDevelopementCard(returnFromEffect, floor);

    }

    private ArrayList<BuildingCard> receiveListOfBuildingCard() throws IOException, ClassNotFoundException {
        String messReceived = "start";
        List<BuildingCard> myBuildings = takeMyProductionCards();
        ListIterator<BuildingCard> iterator = myBuildings.listIterator();
        ArrayList<BuildingCard> cardsForProduction = new ArrayList<>();

        while (!messReceived.equals(Constants.STOP) ){
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

    private void closeSocket(Closeable closeable ) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }
}
