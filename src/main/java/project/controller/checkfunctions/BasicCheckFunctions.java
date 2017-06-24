package project.controller.checkfunctions;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.model.*;
import project.server.network.PlayerHandler;


public class BasicCheckFunctions implements AllCheckFunctions{

    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {

        return !zone[position].isOccupied() && nobodyOfMyFamily(zone, familyMember.getFamilyColour());
    }

    private boolean nobodyOfMyFamily(Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if ( zone[i].getFamiliarOnThisPosition() != null )
                if (zone[i].getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
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


    public boolean checkCardCostCharacter(CharacterCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        int coinsBonus = personalBoard(player).getBonusOnActions().getCharactersBonus().getCoinsBonus();
        int diceBonus = personalBoard(player).getBonusOnActions().getCharactersBonus().getDiceBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        return (card.getCost().getCoinsRequired() + coinsMore + coinsBonus) <= personalBoard(player).getCoins() && valueOfFamilyMember + diceBonus + getServants(player) >= zoneDiceCost;
    }

    /**
     * this method has to be decorated by an excommunication todo
     * @param player
     * @return
     */

    @Override
    public int getServants(PlayerHandler player) {
        return personalBoard(player).getServants();
    }

    public boolean checkCardCostBuilding(BuildingCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        int diceBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getDiceBonus();
        int woodBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getWoodBonus();
        int stoneBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getStoneBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        return card.getCost().getWoodRequired() + woodBonus <= personalBoard(player).getWood() && card.getCost().getStoneRequired() + stoneBonus <= personalBoard(player).getStone() && (card.getCost().getCoinsRequired() + coinsMore) <= personalBoard(player).getCoins() && (valueOfFamilyMember + diceBonus + getServants(player)) >= zoneDiceCost;
    }
    // questa torna int. 0=non posso prendere, 1=prendo per effetto 1, 2= prendo per effetto 2, 3 = posso predere per tutti e due gl effetti devo chiedere
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = personalBoard(player).getBonusOnActions().getVenturesBonus();
        boolean[] canTakeCardFromEffect = new boolean[2];
        int i = 0;
        for (VenturesCost v: card.getVenturesCost()) {
            if (v.getWoodRequired() <= personalBoard(player).getWood() &&
                    v.getStoneRequired() <= personalBoard(player).getStone() &&
                    (v.getCoinsRequired() + coinsMore)<= personalBoard(player).getCoins() &&
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

    public boolean checkCardCostTerritory(TerritoryCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = personalBoard(player).getBonusOnActions().getTerritoryBonus();
        int length = personalBoard(player).getTerritories().size();
        return checkMilitaryPointsForTerritory(player, length) && card.getCost().getWoodRequired() <= personalBoard(player).getWood() && card.getCost().getStoneRequired() <= personalBoard(player).getStone() && coinsMore <= personalBoard(player).getCoins() && (valueOfFamilyMember + diceBonus + getServants(player)) >= zoneDiceCost;

    }

    @Override
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
        return player.getScore().getMilitaryPoints() >= player.getRoom().getBoard().getMilitaryPointsForTerritories()[length];
    }



    private PersonalBoard personalBoard(PlayerHandler playerHandler){
        return playerHandler.getPersonalBoardReference();
    }
}
