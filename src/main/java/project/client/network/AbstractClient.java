package project.client.network;

import java.io.Serializable;
import java.util.List;

/**
 * queste sono tutte le funzioni che il client puo chiamare sul server che poi dovranon essere implementate sia dall'rmi sia dal socket
 */
public abstract class AbstractClient implements Serializable{


    public abstract void loginRequest(String loginParameter);

    public abstract void takeDevCard(String towerColour, int floor, String familiarColour);

    public abstract void harvesterAction(String familyMemberColour, int servantsNumber);

    public abstract void marketAction(int position, String familyColour);

    public abstract void councilAction(int priviledgeNumber, String familiarColour);

    public abstract void productionAction(String familiarColor, List<String> buidingCards);

    public abstract void takeBonusCardAction(int floor, String towerColour);

    public abstract void playLeaderCard(String action);

    public abstract void discardLeaderCard(String name);

    public abstract void sendExitToBonusAction();

    public abstract void bonusHarvesterAction(int servantsNumber);

    public abstract void immediatePriviledgeAction(List<Integer> privileges);

    public abstract void bonusProductionAction(List<String> buildingCards);

    public abstract void skipTurn();

    public abstract void newNickname(String username);

    public abstract void reconnect();

    public abstract void showStatistic();

    public abstract void newGameRequest(String nickname);

    public abstract void showRanking();

    public abstract void winnerComunication();
}
