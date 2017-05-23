package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.BuildingCard;
import Project.Controller.CardsFactory.CharacterCard;
import Project.Controller.CardsFactory.TerritoryCard;
import Project.Controller.CardsFactory.VenturesCard;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;


public class BasicCheckFunctions implements AllCheckFunctions{

    //TODO controllare se vogliamo le position come array o arralist, secondo me più comodo array normale
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!zone[position].isOccupied() && NobodyOfMyFamily(zone,familyMember.getFamilyColour())){
            zone[position].setOccupied(true);
            zone[position].setFamiliarOnThisPosition(familyMember);
            return true;
        }
        else
            return false;
    }

    private boolean NobodyOfMyFamily (Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if (zone[i].getFamiliarOnThisPosition().getFamilyColour() == familyColour)
                return false;
        }
        return true;
    }

    public boolean CheckCardCostTerritories (TerritoryCard card, Player player){
        if (card.getCardCost().getStoneRequired() <= player.getPersonalBoardReference().getStone() &&
                card.getCardCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCardCost().getDiceCost() <= // TODO guardare coe vengono passati i dice value
                )
            return true;
        else
            throw NotEnaughResource();
        return false;
    }

    public boolean CheckCardCost (DevelopmentCard card, Player player){

    }

    @Override
    public boolean CheckAvaiabiltyToProduct(ArrayList<BuildingCard> cardToProduct, PlayerHandler playerHandler, FamilyMember familyMember) {
        int maxValueOfCardProducted = familyMember.getMyValue() + playerHandler.getPersonalBoardReference().getServants() + playerHandler.getPersonalBoardReference().getBonusOnActions().getProductionBonus();
        //TODO vedere come fare a prendere le cose che mi servono per attivare la prouzione (e quini l'effetto permanente della carta)
    }

    public boolean CheckCardCostCharacters (CharacterCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCardCostBuildings (BuildingCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCardCostVentures (VenturesCard card, Player player){
        // TODO da fare in base anche ai dice required
    }
    public boolean CheckCapabilityToTakeTerritory (Player player){
        int length = player.getPersonalBoardReference().getTerritories().size();
        if (player.getScore().getMilitaryPoints() >= //TODO quanti punti militari ci vogliono per carte verdi)
                )
            return true;
        else
            return false;
    }

    public boolean CheckTowerOccupiedByYou (Tower[] tower, Player player){
        for (Tower t: tower){
            if (t.getFamiliarOnThisPosition().getFamilyColour() == player.getFamilyColour())
                return true;
        }
        return false;
    }

    /**
     * @param player
     * @param CardList
     * @return
     */
    public boolean Check_Upgrade_On_Production(Player player, ArrayList<BuildingCard> CardList) {
        // TODO implement here
        return false;
    }


}
