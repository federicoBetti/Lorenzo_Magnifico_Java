package Project.Controller.CheckFunctions;

import Project.Controller.CardsFactory.*;
import Project.MODEL.*;
import Project.Server.Network.PlayerHandler;

import java.util.ArrayList;


public class BasicCheckFunctions implements AllCheckFunctions{

    //TODO controllare se vogliamo le position come array o arralist, secondo me più comodo array normale
    public boolean Check_Position(int position, Position[] zone, FamilyMember familyMember) {
        if (!zone[position].isOccupied() && NobodyOfMyFamily(zone,familyMember.getFamilyColour())){
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
        if (card.getCost().getStoneRequired() <= player.getPersonalBoardReference().getStone() &&
                card.getCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCost().getDiceCost() <= // TODO guardare coe vengono passati i dice value
                )
            return true;
        else
            throw NotEnaughResource();
        return false;
    }

    public boolean CheckCardCost (DevelopmentCard card, Player player){

    }

    public boolean CheckAvaiabiltyToProduct(ArrayList<BuildingCard> cardToProduct, int maxValueOfProduction) {
        for (BuildingCard b: cardToProduct){
            if (b.getCost().getDiceCost() > maxValueOfProduction)
                return false
        }
        return true;
    }

    public boolean CheckCardCostCharacters (CharacterCard card, Player player){
        if (card.getCost().getCoinsRequired() <= player.getPersonalBoardReference().getCoins())
            return true;
        return false;
    }
    public boolean CheckCardCostBuildings (BuildingCard card, Player player){
        if (card.getCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCost().getStoneRequired() <= player.getPersonalBoardReference().getStone() &&
                card.getCost().getCoinsRequired() <= player.getPersonalBoardReference().getCoins()
                )
            return true;
        return false;
    }

    // questa torna int. 0=non posso prendere, 1=prendo per effetto 1, 2= prendo per effetto 2, 3 = posso predere per tutti e due gl effetti devo chiedere
    public int CheckCardCostVentures (VenturesCard card, Player player){
        boolean[] canTakeCardFromEffect = new boolean[2];
        int i = 0;
        for (VenturesCost v: card.getVenturesCost()) {
            if (v.getWoodRequired() <= player.getPersonalBoardReference().getWood() && v.getStoneRequired() <= player.getPersonalBoardReference().getStone() && v.getCoinsRequired() <= player.getPersonalBoardReference().getCoins() && v.getMilitaryRequired() <= player.getScore().getMilitaryPoints())
                canTakeCardFromEffect[i] = true;
            i++;
        }
        if (canTakeCardFromEffect[0] && canTakeCardFromEffect[1])
            return 3;
        else if (canTakeCardFromEffect[0] && !canTakeCardFromEffect[1]){
            card.getVenturesCost().get(0).setToPaid(true);
            return 1;
        }
        else if (!canTakeCardFromEffect[0] && canTakeCardFromEffect[1]){
            card.getVenturesCost().get(1).setToPaid(true);
            return 2;
        }
        else
            return 0;

    }
    public boolean CheckCapabilityToTakeTerritory (TerritoryCard card, Player player){
        int length = player.getPersonalBoardReference().getTerritories().size();
        if (player.getScore().getMilitaryPoints() >= //TODO quanti punti militari ci vogliono per carte verdi)
                )
                card.getCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCost().getStoneRequired() <= player.getPersonalBoardReference().getStone()
                )
            return true;
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
