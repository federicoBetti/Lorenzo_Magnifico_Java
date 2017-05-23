package Project.Controller.SupportFunctions;


import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;

public interface AllSupportFunctions {
    int Pray(Player player);
    void ApplyEffects(DevelopmentCard card, Player player);


}
