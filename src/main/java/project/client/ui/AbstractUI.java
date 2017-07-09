package project.client.ui;

import project.PlayerFile;
import project.client.ui.cli.InputException;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.Tile;

import java.util.List;

/**
 * Abstract class extended by Cli and Gui, the two kinds of user interface implemented
 */
public abstract class AbstractUI {

    public void startUI() {
        //to implement
    }

    /**
     * Abstract mainContext
     */
    public abstract void mainContext();

    /**
     * Abstract takeBonusCard
     *
     */
    public abstract void takeBonusCard(TowerAction towerAction);

    /**
     * Abstract choicePe
     */
    public abstract int choicePe();

    /**
     * Abstract bonusHarvester
     */
    public abstract void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv);

    /**
     * Abstract bonusProduction
     */
    public abstract void bonusProduction(BonusProductionOrHarvesterAction bonusProd);

    /**
     * Abstract askForPraying
     */
    public abstract int askForPraying();

    /**
     * Abstract actionOk
     */
    public abstract void actionOk();

    /**
     * Abstract cantDoAction
     */
    public abstract void cantDoAction();

    /**
     * Abstract takeImmediatePrivilege
     */
    public abstract void takeImmediatePrivilege(TakePrivilegesAction privilegesAction);

    /**
     * Abstract boardUpdate
     */
    public abstract void boardUpdate(Updates update);

    /**
     * Abstract scoreUpdate
     */
    public abstract void scoreUpdate(Updates updates);

    /**
     * Abstract personalBoardUpdate
     */
    public abstract void personalBoardUpdate(Updates updates);

    /**
     * Abstract familyMemberUpdate
     */
    public abstract void familyMemberUpdate(Updates updates);

    /**
     * Abstract nicknameAlreadyUsed
     */
    public abstract void nicknameAlreadyUsed();

    /**
     * Abstract waitingForYourTurn
     */
    public abstract void waitingForYourTurn();

    /**
     * Abstract goToLogin
     */
    public abstract void goToLogin() ;

    /**
     * Abstract loginSucceded
     */
    public abstract void loginSucceded();

    /**
     * Abstract bothPaymentsAvailable
     */
    public abstract int bothPaymentsAvailable();

    /**
     * Abstract getLeaderCardChosen
     */
    public abstract String getLeaderCardChosen(List<LeaderCard> leaders);

    /**
     * Abstract matchStarted
     */
    public abstract void matchStarted(int roomPlayers, String familyColour);

    /**
     * Abstract tileDraft
     */
    public abstract int tileDraft(List<Tile> tiles);


    /**
     * Abstract prayed
     */
    public abstract void prayed();

    /**
     * Abstract excommunicationTaken
     */
    public abstract void excommunicationTaken(ExcommunicationTaken update);

    /**
     * Abstract timerDelayed
     */
    public abstract void timerDelayed();


    /**
     * Abstract afterGame
     */
    public abstract void afterGame();

    /**
     * Abstract receiveStatistics
     */
    public abstract void receiveStatistics(PlayerFile statistics);

    /**
     * Abstract ranking
     */
    public abstract void ranking(List<PlayerFile> ranking);

    /**
     * Abstract disconnesionMessage
     */
    public abstract void disconnesionMessage(String message);

    /**
     * Abstract winnerComunication
     */
    public abstract void winnerComunication(String winner);
}
