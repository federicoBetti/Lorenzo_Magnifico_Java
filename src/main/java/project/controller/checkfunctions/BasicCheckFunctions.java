package project.controller.checkfunctions;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.model.*;
import project.server.network.PlayerHandler;

import java.util.List;


public class BasicCheckFunctions implements AllCheckFunctions{

    //TODO controllare se vogliamo le position come array o arralist, secondo me più comodo array normale
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {
        if (!zone[position].isOccupied() && nobodyOfMyFamily(zone,familyMember.getFamilyColour())){
            return true;
        }
        else
            return false;
    }

    private boolean nobodyOfMyFamily(Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if (zone[i].getFamiliarOnThisPosition().getFamilyColour() == familyColour)
                return false;
        }
        return true;
    }



    @Override
    public boolean checkAvaiabiltyToProduct(List<BuildingCard> cardToProduct, int maxValueOfProduction) {
        for (BuildingCard b: cardToProduct){
            if (b.getCost().getDiceCost() > maxValueOfProduction)
                return false;
        }
        return true;
    }

    @Override
    public boolean checkTowerOccupied(Tower[] zone) {
        for (Tower tower: zone){
            if (tower.isOccupied())
                return true;
        }
        return false;
    }

    @Override
        public boolean checkCardCost(DevelopmentCard card, PlayerHandler playerHandler, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember) {
        if (card instanceof TerritoryCard)
            return checkCardCostTerritory((TerritoryCard)card,playerHandler,coinsFee,zoneDiceCost, valueOfFamilyMember);
        if (card instanceof BuildingCard)
            return checkCardCostBuilding((BuildingCard)card,playerHandler,coinsFee,zoneDiceCost, valueOfFamilyMember);
        if (card instanceof CharacterCard)
            return checkCardCostCharacter((CharacterCard)card,playerHandler,coinsFee,zoneDiceCost,valueOfFamilyMember);
        return false;
    }


    @Override
    public boolean checkCardCostCharacter(CharacterCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        int coinsBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getCoinsBonus();
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getCharactersBonus().getDiceBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        if ((card.getCost().getCoinsRequired() + coinsMore + coinsBonus )<= player.getPersonalBoardReference().getCoins() &&
                valueOfFamilyMember + diceBonus + getServants(player) >= zoneDiceCost)
            return true;
        return false;
    }

    /**
     * this method has to be decorated by an excommunication todo
     * @param player
     * @return
     */

    @Override
    public int getServants(Player player) {
        return player.getPersonalBoardReference().getServants();
    }

    @Override
    public boolean checkCardCostBuilding(BuildingCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getDiceBonus();
        int woodBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getWoodBonus();
        int stoneBonus = player.getPersonalBoardReference().getBonusOnActions().getBuildingsBonus().getStoneBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        if (card.getCost().getWoodRequired() + woodBonus <= player.getPersonalBoardReference().getWood() &&
                card.getCost().getStoneRequired() + stoneBonus <= player.getPersonalBoardReference().getStone() &&
                (card.getCost().getCoinsRequired() + coinsMore)<= player.getPersonalBoardReference().getCoins() &&
                (valueOfFamilyMember + diceBonus + getServants(player) )>= zoneDiceCost
                )
            return true;
        return false;
    }
    // questa torna int. 0=non posso prendere, 1=prendo per effetto 1, 2= prendo per effetto 2, 3 = posso predere per tutti e due gl effetti devo chiedere
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getVenturesBonus();
        boolean[] canTakeCardFromEffect = new boolean[2];
        int i = 0;
        for (VenturesCost v: card.getVenturesCost()) {
            if (v.getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                    v.getStoneRequired() <= player.getPersonalBoardReference().getStone() &&
                    (v.getCoinsRequired() + coinsMore)<= player.getPersonalBoardReference().getCoins() &&
                    v.getMilitaryRequired() <= player.getScore().getMilitaryPoints() &&
                    (valueOfFamilyMember + diceBonus + getServants(player) )>= zoneDiceCost
                    )
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

    @Override
    public boolean checkCardCostTerritory(TerritoryCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = player.getPersonalBoardReference().getBonusOnActions().getTerritoryBonus();
        int length = player.getPersonalBoardReference().getTerritories().size();
        /*if (player.getScore().getMilitaryPoints() >= //TODO quanti punti militari ci vogliono per carte verdi)
                //&&
                card.getCost().getWoodRequired() <= player.getPersonalBoardReference().getWood() &&
                card.getCost().getStoneRequired() <= player.getPersonalBoardReference().getStone()  &&
                coinsMore <= player.getPersonalBoardReference().getCoins() &&
                (valueOfFamilyMember + diceBonus + getServants(player) )>= zoneDiceCost)
        return true;
        */
        return false;

    }


    @Override
    public boolean checkTowerOccupiedByYou(Tower[] tower, PlayerHandler player){
        for (Tower t: tower){
            if (t.getFamiliarOnThisPosition().getFamilyColour() == player.getFamilyColour())
                return true;
        }
        return false;
    }

}
