package Project.Network.Socket;

import Project.MODEL.Card;
import Project.MODEL.FamilyMember;
import Project.Network.ControllerInterface;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;


public class SocketGameActions implements ControllerInterface{

    @Override
    public BonusInteraction TakeDevelopementCard(String towerColor, int position, FamilyMember familyM) {

        return null;
    }

    @Override
    public void Harvester(int position, FamilyMember familyM) {

    }

    @Override
    public void Production(int position, FamilyMember familyM) {

    }

    @Override
    public void Production(int position, FamilyMember familyM, ArrayList<Card> cardToProduct) {

    }

    @Override
    public void GoTOMarket(int position, FamilyMember familyM) {

    }

    @Override
    public void JumpTurn() {

    }

    @Override
    public void PlayLeaderCard(String leaderName) {

    }

    @Override
    public void DiscardLeaderCard(String leaderName) {

    }

    @Override
    public void RollDice() {

    }

    @Override
    public void GoToCouncilPalace(int privelgeNumber) {

    }

    @Override
    public void GoToCouncilPalace() {

    }
}
