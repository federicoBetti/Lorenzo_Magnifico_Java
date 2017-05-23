package Project.Controller.SupportFunctions;


import Project.MODEL.*;
import Project.toDelete.BonusInteraction;

public interface AllSupportFunctions {
    int Pray(Player player);
    BonusInteraction ApplyEffects(DevelopmentCard card, Player player);


    void setFamiliar(Position zone, FamilyMember familyMember);

    void placeCardInPersonalBoard(DevelopmentCard card, Player player);
}
