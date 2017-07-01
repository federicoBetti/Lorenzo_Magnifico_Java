package project.client.network;

import java.io.Serializable;
import java.util.List;

/**
 * queste sono tutte le funzioni che il client puo chiamare sul server che poi dovranon essere implementate sia dall'rmi sia dal socket
 */
public abstract class AbstractClient implements Serializable{


    public abstract void loginRequest(String loginParameter)  ;

    public abstract void waitingForTheNewInteraction() ;

    public abstract void takeDevCard(String towerColour, int floor, String familiarColour )  ;

    public abstract void actionOk();

    public abstract void marketAction(int position, String familyColour)  ;

    public abstract void councilAction(int parameter1, String parameter2)  ;

    public abstract void playLeaderCard(String action)  ;

    public abstract void discardLeaderCard(String name)  ;


    public abstract void sendExitToBonusAction()  ;

    public abstract void sendChoicePe(int input)  ;

    public abstract void bonusHarvesterAction(int servantsNumber)  ;

    public abstract void bonusProductionAction(List<String> parameters)  ;

    public abstract void takeBonusCardAction(int floor, String input)  ;

    public abstract void immediatePriviledgeAction(List<Integer> privileges)  ;

    public abstract void sendChoicePaymentVc(int payment)  ;

    public abstract void harvesterAction(String familyMemberColour, int servantsNumber) ;

    public abstract void productionAction(String familiarChosen, List<String> buidingCards) ;


    public abstract void skipTurn();

    public abstract void timerTurnDelayed();

    public abstract void boardUpdate();

    public void scelta() {

    }

    public abstract void matchStarted();

    public abstract void cantDoAction();

    public abstract void newNickname(String nickname);

    public abstract void prayed();

    public abstract void excommunicationTake();

    public abstract void askForPrayingLastPlayer();


    //da server a client
}
