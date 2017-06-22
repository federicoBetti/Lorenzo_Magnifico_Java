package project.client.network;

import java.util.List;

/**
 * queste sono tutte le funzioni che il client puo chiamare sul server che poi dovranon essere implementate sia dall'rmi sia dal socket
 */
public abstract class AbstractClient {


    public void loginRequest(String loginParameter)  {
    }

    public void waitingForTheNewInteraction() {
    }

    public void takeDevCard(String towerColour, int floor, String familiarColour )  {
    }
    
    public void marketAction(int position, String familyColour)  {
    }

    public void councilAction(int parameter1, String parameter2)  {
    }

    public void playLeaderCard(String action)  {
    }

    public void discardLeaderCard(String name)  {
    }

    public void prayOrNot(boolean action)  {
    }

    public void sendExitToBonusAction()  {
    }

    public void choicePe() {
    }

    public void sendChoicePe(int input)  {
    }

    public void bonusHarvester()  {
    }

    public void bonusHarvesterAction(int servantsNumber)  {
    }

    public void bonusProductionAction(List<String> parameters)  {
    }

    public void takeBonusCardAction(int floor, String input)  {
    }

    public void immediatePriviledgeAction(List<Integer> privileges)  {
    }

    public void takeImmediatePrivilege()  {
    }

    public void sendChoicePaymentVc(int payment)  {
    }

    public void actionOk() {
    }

    public void harvesterAction(String familyMemberColour, int servantsNumber) {
    }

    public void productionAction(String familiarChosen, List<String> buidingCards) {
    }

    public void skipTurn() {
    }

    public abstract void timerTurnDelayed();


    //da server a client
}
