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
 * Created by raffaelebongo on 29/05/17.
 */
public abstract class AbstractUI {

    public void startUI() {
        //to implement
    }


    public abstract void mainContext();

    public abstract void takeBonusCard(TowerAction towerAction);

    public abstract int choicePe();

    public abstract void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv);

    public abstract void bonusProduction(BonusProductionOrHarvesterAction bonusProd);

    public abstract int askForPraying();

    public abstract void actionOk();

    public abstract void cantDoAction();

    public abstract void takeImmediatePrivilege(TakePrivilegesAction privilegesAction);





    public void startGame(int i) {

    }

    //notifica alla ui che èè stato fatto un update, serve solo a me. l'ho messo non astratto cosi da te non fa niente
    public abstract void boardUpdate(Updates update);

    public abstract void scoreUpdate(Updates updates);

    public abstract void personalBoardUpdate(Updates updates);

    public abstract void familyMemberUpdate(Updates updates);

    public abstract void nicknameAlreadyUsed();

    public abstract void skipTurn();

    public abstract void waitingForYourTurn();

    public abstract void setConnectionType(String kindOfConnection) throws InputException;

    public abstract void goToLogin() ;

    public abstract void loginSucceded();

    public abstract int bothPaymentsAvailable();


    //astratto?
    public void update(Updates update) {
    }


    public abstract String getLeaderCardChosen(List<LeaderCard> leaders);

    public abstract void matchStarted(int roomPlayers, String familyColour);

    public abstract int tileDraft(List<Tile> tiles);

    public abstract void newNickname(String nickname );

    public abstract void prayed();

    public abstract void excommunicationTaken(ExcommunicationTaken update);

    public abstract void timerDelayed();

    public abstract void reconnect();

    public abstract void afterGame();

    public abstract void showStatistic();

    public abstract void newGameRequest();

    public abstract void terminate();

    public abstract void receiveStatistics(PlayerFile statistics);

    public abstract void showRanking();

    public abstract void ranking(List<PlayerFile> ranking);

    public abstract void disconnesionMessage(String message);

    public abstract void winnerComunication(String winner);
}
