package project.client.network.socket;

import project.client.clientexceptions.ClientConnectionException;
import project.client.network.AbstractClient;
import project.client.ui.ClientSetter;
import project.controller.Constants;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public class SocketClient extends AbstractClient {

    ClientSetter clientSetter;
    MessagesFromServerHandler messageHandler;
    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    // cosi si collega con la user interface scelta e creata appositamente
    public SocketClient(ClientSetter clientSetter) throws ClientConnectionException {
        this.clientSetter = clientSetter;
        this.messageHandler = new MessagesFromServerHandler(this);
        try {
            socket = new Socket(Constants.LOCAL_ADDRESS, Constants.SOCKET_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new ClientConnectionException(e);
        }
    }

    @Override
    public void waitingForTheNewInteraction() {
        String message = null;
        try {
            message = (String)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            messageHandler.handleMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //send requests
    @Override
    public void loginRequest(String loginParameter)  {
        sendKindOfRequest(Constants.LOGIN_REQUEST);
        sendKindOfRequest(loginParameter);
        waitingForTheNewInteraction();
    }

    @Override
    public void takeDevCard(String towerColour, String floor, String familiarColour )   {
        sendKindOfRequest(Constants.TAKE_DEV_CARD);
        send3Parameters(towerColour, floor, familiarColour);
    }



    @Override
    public void marketAction( String parameter1, String parameter2 )  {
        sendKindOfRequest(Constants.GO_TO_MARKET);
        send2Parameters(parameter1, parameter2);
    }

    @Override
    public void councilAction(String parameter1, String parameter2)   {
        sendKindOfRequest(Constants.GO_TO_COUNCIL_PALACE);
        send2Parameters(parameter1, parameter2);
    }

    @Override
    public void productionAction(String[] parameters)   {
        sendKindOfRequest(Constants.PRODUCTION);
        sendAllParameters(parameters);
        waitingForTheNewInteraction();
    }

    @Override
    public void immediatePriviledgeAction(String[] privileges)  {
        sendAllParameters(privileges);
    }

    @Override
    public void playLeaderCard(String name )   {

        sendKindOfRequest(Constants.PLAY_LEADER_CARD);
        sendKindOfRequest(name);
        waitingForTheNewInteraction();
    }

    @Override
    public void discardLeaderCard(String name)   {
        sendKindOfRequest(Constants.DISCARD_LEADER_CARD);
        sendKindOfRequest(name);
        waitingForTheNewInteraction();
    }

    @Override
    public void prayOrNot(String action)   {

        if ( action.equals("yes"))
            sendKindOfRequest(Constants.PRAY);
        else
            sendKindOfRequest(Constants.DONT_PRAY);

        waitingForTheNewInteraction();

    }

    public void askForPraying() {
        clientSetter.askForPraying();
    }

    public void itsMyTurn() {
        clientSetter.itsMyTurn();
    }

    //receive answers: bonus interaction
    @Override
    public void sendExitToBonusAction()   {
        try {
            objectOutputStream.writeObject(Constants.EXIT);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitingForTheNewInteraction();
    }

    public void takeBonusCardAction(String floor, String towerColour )  {
        send2Parameters(floor, towerColour);
        waitingForTheNewInteraction();
    }

    @Override
    public void harvesterAction(String parameter1, String parameter2, String parameter3)   {
        sendKindOfRequest(Constants.HARVESTER);
        send3Parameters(parameter1, parameter2, parameter3);
    }


    @Override
    public void bonusHarvester()  {
        BonusProductionOrHarvesterAction bonusHarv = null;
        try {
            bonusHarv = (BonusProductionOrHarvesterAction)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.bonusHarvester(bonusHarv);
    }

    public void takeImmediatePrivilege()  {
        TakePrivilegesAction privilegesAction = null;
        try {
            privilegesAction = (TakePrivilegesAction)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.takeImmediatePrivilege(privilegesAction);
    }

    public void bonusHarvesterAction(String servantsNumber)   {
        sendKindOfRequest(Constants.BONUS_HARVESTER);
        sendKindOfRequest(servantsNumber);
        waitingForTheNewInteraction();
    }

    public void bonusProduction()   {
        BonusProductionOrHarvesterAction bonusProd = null;
        try {
            bonusProd = (BonusProductionOrHarvesterAction)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.bonusProduction(bonusProd);
    }

    public void bonusProductionAction(String[] parameters)   {
        sendKindOfRequest(Constants.BONUS_PRODUCTION);
        sendAllParameters(parameters);
        waitingForTheNewInteraction();
    }

    public void takeBonusCard()  {
        TowerAction towerAction = null;
        try {
            towerAction = (TowerAction) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.takeBonusCard(towerAction);
    }

    @Override
    public void choicePe(){
        clientSetter.choicePe();
    }

    @Override
    public void sendChoicePe(String input)   {
        sendKindOfRequest(input);
        waitingForTheNewInteraction();
    }

    public void bothPaymentsAvailable() {
        clientSetter.bothPaymentsAvailable();
    }

    @Override
    public void sendChoicePaymentVc(String payment)  {
        sendKindOfRequest(payment);
        waitingForTheNewInteraction();
    }

    //updates

    public void scoreUpdate()  {
        Updates update = null;
        try {
            update = (ScoreUpdate)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.scoreUpdate(update);
        waitingForTheNewInteraction();
    }

    public void personalBoardUpdate() {
        Updates update = null;
        try {
            update = (PersonalBoardUpdate)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.personalBoardUpdate(update);
   
            waitingForTheNewInteraction();
    }

    public void familyMemberUpdate() {
        Updates update = null;
        try {
            update = (FamilyMemberUpdate)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.familyMemberUpdate(update);
            waitingForTheNewInteraction();
    }


    public void boardUpdate()  {
        Updates update = null;
        try {
            update = (DiceValueUpdate)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientSetter.boardUpdate(update);
        waitingForTheNewInteraction();
    }


    //sending methods
    void send2Parameters(String parameter1, String parameter2 )   {
        try {
            objectOutputStream.writeObject(parameter1);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter2);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

        waitingForTheNewInteraction();
    }

    void send3Parameters(String parameter1, String parameter2, String parameter3 )   {

        try {
            objectOutputStream.writeObject(parameter1);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter2);
            objectOutputStream.flush();
            objectOutputStream.reset();

            objectOutputStream.writeObject(parameter3);
            objectOutputStream.flush();
            objectOutputStream.reset();

        } catch (IOException e) {
            e.printStackTrace();
        }
        waitingForTheNewInteraction();
    }

    void sendAllParameters(String[] parameters )   {

        for( String elem: parameters ){
            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        waitingForTheNewInteraction();
    }

    void sendKindOfRequest(String kindOfRequest ) {

        try {
            objectOutputStream.writeObject(kindOfRequest);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

