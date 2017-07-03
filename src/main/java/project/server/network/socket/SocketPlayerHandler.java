package project.server.network.socket;

import project.controller.cardsfactory.BuildingCard;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.*;
import project.messages.updatesmessages.Updates;
import project.model.FamilyMember;
import project.model.Player;
import project.model.Tile;
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

/**
 * questa classe rappresenta la casse ponte tra il model e la view. da qua ogni volta che vinee aggiornato qualcosa il player dice al suo
 * client che qualcosa è stato aggiornato e quindi fa aggiornare la UI
 */

//todo implementare per ogni metodo i messaggi di ritorno al client o gia fatto in gameAction? controllare
public class SocketPlayerHandler extends PlayerHandler implements Runnable {

    private transient SocketServer socketServer;
    private transient Socket socket;
    private transient ObjectInputStream objectInputStream;
    private transient ObjectOutputStream objectOutputStream;
    private transient ServerDataHandler serverDataHandler;
    final Object token;
    final Object token1;

    public SocketPlayerHandler(SocketServer socketServer, Socket socket) throws IOException {
        super();
        token = new Object();
        token1 = new Object();
        this.setOn(true);
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

            System.out.println("waiting for Request...");
            Object object = objectInputStream.readObject();
            System.out.println(object);
            System.out.println("the client wants to do " + object);
            serverDataHandler.handleRequest(object);


            synchronized (token) {
                try {
                    token.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (true) {
                System.out.println("SONO NEL WHILE TRUE. PLAYER: " + this);
                object = objectInputStream.readObject();
                System.out.println(object);
                System.out.println("the client wants to do " + object);
                serverDataHandler.handleRequest(object);
            }

        } catch (CantDoActionException e) {
            cantDoAction();
        } catch (CanUseBothPaymentMethodException e) {      //todo questa deve esserci?
            canUseBothPaymentMethod();
        } catch (IOException e) {   //todo queste due eccezioni qui
            this.setOn(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        closeSocket(objectInputStream);
        closeSocket(objectOutputStream);
        closeSocket(socket);
    }


    //ok
    public void loginRequestAnswer(String nickname) throws IOException, ClassNotFoundException {
        socketServer.loginRequest(nickname, this);
    }

    public void socketSkipTurn() {
        skipTurn();
    }

    //ok
    public void takeDevCard() throws IOException, ClassNotFoundException {

        String towerColour = (String) objectInputStream.readObject();
        int floor = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            clientTakeDevelopmentCard(towerColour, floor, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }


    public void choosePaymentForVentureCard() throws IOException, ClassNotFoundException {
        int position = (int) objectInputStream.readObject();
        String familyMemberColour = (String) objectInputStream.readObject();
        int paymentChosen = (int) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        clientChosenPaymentForVenturesCard(position, familyMember, paymentChosen);
    }

    //ok
    public void harvesterRequest() throws IOException, ClassNotFoundException {
        String familyMemberColour = (String) objectInputStream.readObject();
        int servantsNumber = (int) objectInputStream.readObject();
        FamilyMember familyMember = findFamilyMember(familyMemberColour);

        try {
            harvester(familyMember, servantsNumber);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    //int position, FamilyMember familyM, ArrayList<BuildingCard> cardToProduct
    public void productionRequest() throws IOException, ClassNotFoundException {
        String familyMemberColour = (String) objectInputStream.readObject();
        System.out.println("family colour: " + familyMemberColour);
        FamilyMember familyMember = findFamilyMember(familyMemberColour);
        System.out.println("familiar: " + familyMember);
        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();

        try {
            production(familyMember, cards);
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
        System.out.println("parametri mercato ricevuti :  " + familyMemberColour + " " + position);

        try {
            goToMarket(position, familyMember);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    public void playLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName = (String) objectInputStream.readObject();

        try {
            playLeaderCard(leaderCardName);
        } catch (CantDoActionException e) {
            cantDoAction();
        }
    }

    //ok
    public void discardLeaderCardRequest() throws IOException, ClassNotFoundException {
        String leaderCardName = (String) objectInputStream.readObject();

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


    private List<BuildingCard> takeMyProductionCards() {
        return getPersonalBoardReference().getBuildings();
    }

    @Override
    public void sendAnswer(Object returnFromEffect) {
        if (isOn()) {
            try {
                objectOutputStream.writeObject(returnFromEffect.toString());

                objectOutputStream.writeObject(returnFromEffect);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendActionOk() {
        if (isOn()) {
            try {
                objectOutputStream.writeObject(Constants.OK_OR_NO);
                objectOutputStream.flush();
                objectOutputStream.reset();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void timerTurnDelayed() {
        sendString(Constants.TIMER_TURN_DELAYED);
        setOn(false);
    }

    @Override
    public void nicknameAlredyUsed() {
        sendString(Constants.NICKNAME_USED);
        try {
            String newNickname = (String) objectInputStream.readObject();
            loginRequestAnswer(newNickname);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginSucceded() {
        sendString(Constants.LOGIN_SUCCEDED);
    }

    @Override
    protected void waitForYourTurn() {
        sendString(Constants.WAITING_FOR_YOUR_TURN);
    }

    @Override
    public String leaderCardChosen(List<LeaderCard> leaders) {

        sendString(Constants.LEADER_DRAFT);

        try {
            objectOutputStream.writeObject(leaders.size());

            for (LeaderCard leaderCard : leaders) {

                objectOutputStream.writeObject(leaderCard);
                objectOutputStream.flush();
                objectOutputStream.reset();
            }

            return (String) objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int chooseTile(ArrayList<Tile> tiles) {

        sendString(Constants.TILE_DRAFT);
        try {
            objectOutputStream.writeObject(tiles.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Tile tile : tiles) {
            try {
                objectOutputStream.writeObject(tile);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            System.out.println("sono in attesa qui");
            Object choice = objectInputStream.readObject();
            System.out.println(choice.getClass());
            System.out.println("la scelta è " + choice);
            return (int) choice;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void matchStarted(int roomPlayers, String familyColour) {
        try {
            synchronized (token) {
                token.notify();
            }
            objectOutputStream.writeObject(Constants.MATCH_STARTED);
            objectOutputStream.writeObject(roomPlayers);
            objectOutputStream.writeObject(familyColour);
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
        try {
            int choice = (int) objectInputStream.readObject();
            sendString(Constants.ACTION_DONE_ON_TIME);

            System.out.println("CHOICE E': " + choice);
            return choice;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public void itsMyTurn() {
        sendString(Constants.YOUR_TURN);
    }

    @Override
    public int sendAskForPraying(List<PlayerHandler> playerTurn) {
        System.out.println("SONO IL PLAYER: " + this);

        while (true) {
            try {
                if (playerTurn.indexOf(this) == playerTurn.size() - 1) {
                    sendString(Constants.ASK_FOR_PRAYING_LAST_PLAYER);
                    int answer = (int) objectInputStream.readObject();
                    sendString(Constants.ACTION_DONE_ON_TIME);
                    return answer;
                }

                sendString(Constants.ASK_FOR_PRAYING);
                System.out.println(" mandata ask ");
                synchronized (token) {
                    token.wait();
                }

                int answer = (int) objectInputStream.readObject();
                System.out.println("risposta arrivata " + answer);
                sendString(Constants.ACTION_DONE_ON_TIME);

                synchronized (token1) {
                    token1.notify();
                }

                return answer;

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void waitForPraying() {
        System.out.println("SBLOCCO LA READ SULLA PREGHIERA\n");
        synchronized (token) {
            token.notify();
        }
        System.out.println("BLOCCO LA READ DEL WHILE TRUE\n");
        synchronized (token1) {
            try {
                token1.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SBLOCCO LA READ Del while true\n");
        }
    }

/*    @Override
    public int sendAskForPraying() {
        if (isOn()) {
            sendAnswer(Constants.ASK_FOR_PRAYING);
            int answer = -1;
            try {
                System.out.println("Ho mandato la richiesta di preghiere");
                answer = (int) objectInputStream.readObject();
                System.out.println(answer);
                return answer;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }   */


    @Override
    public void sendString(String message) {

        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            System.out.println("errore invio su socket");
        }

    }

    //todo check utility
    @Override
    public void sendNotification(Notify notifications) {
        sendAnswer(notifications);
    }

    @Override
    public void sendUpdates(Updates updates) {
        if (isOn()) {
            System.out.println(updates.getClass());
            sendAnswer(updates);
        }
    }


    //todo controllare il -1 che è feo
    @Override
    public int sendPossibleChoice(String kindOfChoice) {

        int choice = -1;
        try {
            objectOutputStream.writeObject(kindOfChoice);
            objectOutputStream.flush();
            objectOutputStream.reset();

            String choiceS = (String) objectInputStream.readObject();
            choice = Integer.parseInt(choiceS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return choice;
    }

    @Override
    public void sendBonusTowerAction(TowerAction returnFromEffect) {

        //todo riguarda questo metodo, ho cancellato delle cose per le eccezioni, in toeria solo roba relativa alle eccezioni ma non vorrei aver fatto qualche danno
        while (true) {
            System.out.println("SONO QUI NEL SEND BONUS TOWER ACTION");
            sendAnswer(returnFromEffect);
            String answer = null;
            try {
                answer = (String) objectInputStream.readObject();
                System.out.println("è arrivato: " + answer);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (answer.equals(Constants.EXIT))
                return;

            //serve passargli il returnFromEffect perchè contiene lo sconto da applicare
            try {
                System.out.println("faccio takebonus");
                takeBonusDevCard(answer, returnFromEffect);
                System.out.println("faccio return");
                return;
            } catch (CantDoActionException c) {
                try {
                    System.out.println("SONO IN ECCEZIONE");
                    objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    @Override
    public void sendBonusProdOrHarv(BonusProductionOrHarvesterAction returnFromEffect) {
        while (true) {
            try {

                sendAnswer(returnFromEffect);
                String answer = (String) objectInputStream.readObject();
                int intServantsNumber;

                switch (answer) {
                    case Constants.EXIT:
                        return;

                    case Constants.BONUS_PRODUCTION:
                        intServantsNumber = Integer.parseInt((String) objectInputStream.readObject());
                        ArrayList<BuildingCard> cards = receiveListOfBuildingCard();
                        doBonusProduct(returnFromEffect, cards);
                        break;

                    case Constants.BONUS_HARVESTER:
                        intServantsNumber = Integer.parseInt((String) objectInputStream.readObject());
                        doBonusHarv(returnFromEffect, intServantsNumber);
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (CantDoActionException e) {
                try {
                    objectOutputStream.writeObject(Constants.NOT_ENOUGH_RESOURCES);
                    objectOutputStream.flush();
                    objectOutputStream.reset();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void sendRequestForPriviledges(TakePrivilegesAction returnFromEffect) {
        sendAnswer(returnFromEffect);
        takePriviledgesInArow(returnFromEffect);
    }


    private void takePriviledgesInArow(TakePrivilegesAction returnFromEffect) {

        try {
            String message = (String) objectInputStream.readObject();
            if ( message.equals(Constants.ACTION_DONE_ON_TIME)) {

                for (int count = 0; count < returnFromEffect.getQuantityOfDifferentPrivileges(); count++) {
                    int privilegeNumber = 0;

                    privilegeNumber = (int) objectInputStream.readObject();
                    System.out.println();
                    takePrivilege(privilegeNumber);
                }
            } else if ( message.equals(Constants.EXIT ))
                return;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void takeBonusDevCard(String towerColour, TowerAction returnFromEffect) throws CantDoActionException {

        int floor = 0;
        try {
            floor = (int) objectInputStream.readObject();
            System.out.println("floor: " + floor);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientTakeBonusDevelopementCard(towerColour, floor, returnFromEffect);

    }

    private ArrayList<BuildingCard> receiveListOfBuildingCard() {

        List<BuildingCard> myBuildings = takeMyProductionCards();

        ArrayList<BuildingCard> cardsForProduction = new ArrayList<>();
        try {
            int size = (int) objectInputStream.readObject();

            for (int i = 0; i < size; i++) {
                String cardName = (String) objectInputStream.readObject();
                for (BuildingCard card : myBuildings) {
                    if (card.getName().equals(cardName))
                        cardsForProduction.add(card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cardsForProduction;
    }

    private void closeSocket(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }


}
