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
import java.util.List;

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
        String message;
        while (true) {
            try {
                message = (String) objectInputStream.readObject();
                messageHandler.handleMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //send requests
    @Override
    public void loginRequest(String loginParameter)  {
        sendGenericObject(Constants.LOGIN_REQUEST);
        sendGenericObject(loginParameter);
    }

    @Override
    public void takeDevCard(String towerColour, int floor, String familiarColour )   {
        sendGenericObject(Constants.TAKE_DEV_CARD);
        send3Parameters(towerColour, floor, familiarColour);
    }

    @Override
    public void actionOk(){
        clientSetter.actionOk();
    }

    @Override
    public void marketAction( int position, String familyColour )  {
        sendGenericObject(Constants.GO_TO_MARKET);
        send2Parameters(position, familyColour);
    }

    @Override
    public void councilAction(int priviledgeNumber, String familyColour)   {
        sendGenericObject(Constants.GO_TO_COUNCIL_PALACE);
        send2Parameters(priviledgeNumber, familyColour);
    }

    @Override
    public void productionAction(String familiarColor, List<String> buildingCards )   {
        sendGenericObject(Constants.PRODUCTION);
        sendGenericObject(familiarColor);
        sendAllStrings(buildingCards);
    }

    @Override
    public void immediatePriviledgeAction(List<Integer> privileges)  {
        sendAllIntegers(privileges);
    }

    @Override
    public void playLeaderCard(String name )   {

        sendGenericObject(Constants.PLAY_LEADER_CARD);
        sendGenericObject(name);
    }

    @Override
    public void discardLeaderCard(String name)   {
        sendGenericObject(Constants.DISCARD_LEADER_CARD);
        sendGenericObject(name);
    }

    @Override
    public void prayOrNot(boolean action)   {

        if (action)
            sendGenericObject(Constants.PRAY);
        else
            sendGenericObject(Constants.DONT_PRAY);
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
    }

    public void takeBonusCardAction(int floor, String towerColour )  {
        send2Parameters(towerColour, floor);
    }

    @Override
    public void harvesterAction(String familyColour, int servantsNumber)   {
        sendGenericObject(Constants.HARVESTER);
        send2Parameters(familyColour, servantsNumber);
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

    public void bonusHarvesterAction(int servantsNumber)   {
        sendGenericObject(Constants.BONUS_HARVESTER);
        sendGenericObject(servantsNumber);
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

    public void bonusProductionAction(List<String> parameters)   {
        sendGenericObject(Constants.BONUS_PRODUCTION);
        sendAllStrings(parameters);
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
    public void sendChoicePe(int input)   {
        sendGenericObject(input);
    }

    public void bothPaymentsAvailable() {
        clientSetter.bothPaymentsAvailable();
    }

    @Override
    public void sendChoicePaymentVc(int payment)  {
        sendGenericObject(payment);
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
    }


    //sending methods
    void send2Parameters(Object parameter1, Object parameter2 )   {
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
    }

    void send3Parameters(Object parameter1, Object parameter2, Object parameter3 )   {

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
    }

    void sendAllStrings(List<String> parameters )   {

        for( String elem: parameters ){
            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void sendAllIntegers(List<Integer> parameters ){

        for( Integer elem: parameters ){
            try {
                objectOutputStream.writeObject(elem);
                objectOutputStream.flush();
                objectOutputStream.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void sendGenericObject(Object kindOfRequest ) {

        try {
            objectOutputStream.writeObject(kindOfRequest);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void nicknameAlreadyUsed() {
        clientSetter.nicknameAlreadyUsed();
    }
}

