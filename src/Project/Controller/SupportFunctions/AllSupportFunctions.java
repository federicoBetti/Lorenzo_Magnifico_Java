package Project.Controller.SupportFunctions;


import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;
import Project.toDelete.BonusInteraction;

import java.util.ArrayList;

public interface AllSupportFunctions {
    int Pray(Player player);
    BonusInteraction ApplyEffects(DevelopmentCard card, Player player);


    void setFamiliar(Position zone, FamilyMember familyMember);

    void placeCardInPersonalBoard(DevelopmentCard card, Player player);

    void setDicesValue(int[] newDiceValue, Player p);

    void setFamiliarInTheCouncilPalace(ArrayList<Council> councilZone, FamilyMember familyMember);

    void takeCouncilPrivilege(int privelgeNumber);

    void takeMarketAction(int position);
}
