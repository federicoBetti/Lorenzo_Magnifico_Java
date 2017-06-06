package project.client.network.rmi;

import project.messages.BonusProductionOrHarvesterAction;
import project.messages.Notify;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;

import java.rmi.Remote;

/**
 * questi sono i metodi che il server puo chiamare sul client,
 * spesso di risposta a qualche chiamata del client precedente. devono essere implementati per forza dall'RMIClient
 */
public interface RMIServerToClientInterface extends Remote{

    public void takeAnotherCard(TowerAction towerAction);

    public void doProductionHarvester (BonusProductionOrHarvesterAction bonusProductionOrHarvesterAction);

    void notify (Notify notify);

    void endTurn();

    void takePrivilege(TakePrivilegesAction takePrivilegesAction);
}
