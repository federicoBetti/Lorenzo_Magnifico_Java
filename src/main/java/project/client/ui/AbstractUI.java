package project.client.ui;

import project.client.ui.cli.InputException;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;

/**
 * Created by raffaelebongo on 29/05/17.
 */
public abstract class AbstractUI {

    public void startUI() {
        //to implement
    }


    public void mainContext(){}

    public abstract void takeBonusCard(TowerAction towerAction);

    public abstract void choicePe();

    public abstract void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv);

    public abstract void bonusProduction(BonusProductionOrHarvesterAction bonusProd);

    public abstract void askForPraying();

    public abstract void actionOk();

    public abstract void cantDoAction();

    public abstract void takeImmediatePrivilege(TakePrivilegesAction privilegesAction);

    public abstract void bothPaymentsAvailable();



    public void startGame(int i) {

    }

    //notifica alla ui che èè stato fatto un update, serve solo a me. l'ho messo non astratto cosi da te non fa niente
    public void boardUpdate() {

    }

    public void scoreUpdate() {
    }

    public void personalBoardUpdate() {
    }

    public void familyMemberUpdate() {

    }

    public abstract void nicknameAlreadyUsed();

    public abstract void skipTurn();

    public abstract void waitingForYourTurn();

    public abstract void setConnectionType(String kindOfConnection) throws InputException;

    public abstract void goToLogin() ;

    public abstract void loginSucceded();

    public abstract int booleanChoosingRMI();
}
