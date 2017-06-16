package project.client.network;

import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * queste sono tutte le funzioni che il client puo chiamare sul server che poi dovranon essere implementate sia dall'rmi sia dal socket
 */
public class AbstractClient {


    public void loginRequest(String loginParameter)  {
    }

    public void waitingForTheNewInteraction() {
    }

    public void takeDevCard(String towerColour, String floor, String familiarColour )  {
    }

    public void harvesterAction(String parameter1, String parameter2, String parameter3)  {
    }

    public void marketAction(String parameter1, String parameter2)  {
    }

    public void councilAction(String parameter1, String parameter2)  {
    }

    public void productionAction(String[] parameters)  {
    }

    public void playLeaderCard(String action)  {
    }

    public void discardLeaderCard(String name)  {
    }

    public void prayOrNot(String action)  {
    }

    public void sendExitToBonusAction()  {
    }

    public void choicePe() {
    }

    public void sendChoicePe(String input)  {
    }

    public void bonusHarvester()  {
    }

    public void bonusHarvesterAction(String servantsNumber)  {
    }

    public void bonusProductionAction(String[] parameters)  {
    }

    public void takeBonusCardAction(String floor, String input)  {
    }

    public void immediatePriviledgeAction(String[] privileges)  {
    }

    public void takeImmediatePrivilege()  {
    }

    public void sendChoicePaymentVc(String payment)  {
    }

    public void actionOk() {
    }
}
