package project.messages.updatesmessages;


import project.messages.BonusInteraction;
import project.model.*;
import sun.jvm.hotspot.oops.Mark;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this represent the abstract class of updates. its extenctions will inform every client of some moves
 */
public abstract class Updates extends BonusInteraction implements Serializable{

    String nicknameCurrentPlayer;

    public Updates(String nicknameCurrentPlayer){
        this.nicknameCurrentPlayer = nicknameCurrentPlayer;
    }

    public Tower[][] doUpdateTowers(){
        return null;
    }

    public ArrayList<Council> doUpdateCouncil(){
        return null;
    }

    public ExcommunicationZone[] doUpdateExcommunicatio(){
        return null;
    }

    public PersonalBoard doUpdatePersonalBoard(){
        return null;
    }

    public Score doUpdateScore(){return null;}

    public int[] doupdateDiceValue(){
        return null;
    }

    public ArrayList<Harvester> doUpdateHarvester(){
        return null;
    }

    public Market[] doUpdateMarket(){
        return null;
    }

    public ArrayList<Production> doUpdateProduction(){
        return null;
    }

    public FamilyMember[] doUpdateFamilyMembers(FamilyMember[] familyMembersUi){
        return familyMembersUi;
    }

    public abstract String toScreen();
}
