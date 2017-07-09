package project.client.network;

import java.io.Serializable;
import java.util.List;

/**
 * This class contains the methods implemented by the socketClient and RMIClient
 */
public abstract class AbstractClient implements Serializable{

    /**
     * Abstract loginRequest
     */
    public abstract void loginRequest(String loginParameter);

    /**
     * Abstract takeDevCard
     */
    public abstract void takeDevCard(String towerColour, int floor, String familiarColour);

    /**
     * Abstract harvesterAction
     */
    public abstract void harvesterAction(String familyMemberColour, int servantsNumber);

    /**
     * Abstract marketAction
     */
    public abstract void marketAction(int position, String familyColour);

    /**
     * Abstract councilAction
     */
    public abstract void councilAction(int priviledgeNumber, String familiarColour);

    /**
     * Abstract productionAction
     */
    public abstract void productionAction(String familiarColor, List<String> buidingCards);

    /**
     * Abstract takeBonusCardAction
     */
    public abstract void takeBonusCardAction(int floor, String towerColour);

    /**
     * Abstract playLeaderCard
     */
    public abstract void playLeaderCard(String action);

    /**
     * Abstract discardLeaderCard
     */
    public abstract void discardLeaderCard(String name);

    /**
     * Abstract sendExitToBonusAction
     */
    public abstract void sendExitToBonusAction();

    /**
     * Abstract bonusHarvesterAction
     */
    public abstract void bonusHarvesterAction(int servantsNumber);

    /**
     * Abstract immediatePriviledgeAction
     */
    public abstract void immediatePriviledgeAction(List<Integer> privileges);

    /**
     * Abstract bonusProductionAction
     */
    public abstract void bonusProductionAction(List<String> buildingCards);

    /**
     * Abstract skipTurn
     */
    public abstract void skipTurn();

    /**
     * Abstract newNickname
     */
    public abstract void newNickname(String username);

    /**
     * Abstract reconnect
     */
    public abstract void reconnect();

    /**
     * Abstract showStatistic
     */
    public abstract void showStatistic();

    /**
     * Abstract newGameRequest
     */
    public abstract void newGameRequest(String nickname);

    /**
     * Abstract showRanking
     */
    public abstract void showRanking();

    /**
     * Abstract winnerComunication
     */
    public abstract void winnerComunication();
}

