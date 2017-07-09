package project.controller.checkfunctions;

import project.controller.cardsfactory.*;
import project.controller.Constants;
import project.model.*;
import project.server.network.PlayerHandler;

/**
 * Check functions commons to all players when the match starts
 */
public class BasicCheckFunctions implements AllCheckFunctions{

    /**
     * This method checks if the spot where you want to place the familiar is occuped or not and if there are other
     * your familiars
     *
     * @param position number of position to check
     * @param zone group of positions where to check
     * @param familyMember familiar that you have placed
     * @return true if you can place the familiar
     */
    @Override
    public boolean checkPosition(int position, Position[] zone, FamilyMember familyMember) {

        return !zone[position].isOccupied() && nobodyOfMyFamily(zone, familyMember.getFamilyColour());
    }

    /**
     * Check if there is at least another familiar of mine in a array of positions
     *
     * @param zone array of positions
     * @param familyColour player's family colour
     * @return true if there is another familiar of mine in the zone, else false
     */
    private boolean nobodyOfMyFamily(Position[] zone, String familyColour){
        for (int i=0;i<zone.length;i++){
            if ( zone[i].getFamiliarOnThisPosition() != null )
                if (zone[i].getFamiliarOnThisPosition().getFamilyColour().equals(familyColour))
                    return false;
        }
        return true;
    }

    /**
     * This method checks if the tower is occupied by at least another familiar
     *
     * @param zone array of tower
     * @return true if is occupied, else false
     */
    @Override
    public boolean checkTowerOccupied(Tower[] zone) {
        for (Tower tower: zone){
            if (tower.isOccupied())
                return true;
        }
        return false;
    }

    /**
     * This method calls the right check cost function according to the kind of card passed
     *
     * @param card development card to check
     * @param playerHandler playerHandler's reference
     * @param coinsFee coins to pay more if the tower is occupied
     * @param zoneDiceCost the minimum dice value for placing a familiar in the zone
     * @param valueOfFamilyMember my familiar's value
     * @return true if the requirements for taking the card are respected, else false
     */
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

    /**
     * Check the Character's card cost
     *
     * @param card development card to check
     * @param player playerHandler's reference
     * @param coinsFee coins to pay more if the tower is occupied
     * @param zoneDiceCost the minimum dice value for placing a familiar in the zone
     * @param valueOfFamilyMember my familiar's value
     * @return true if the requirements for taking the card are respected, else false
     */
    private boolean checkCardCostCharacter(CharacterCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        if (player.getPersonalBoardReference().getCharacters().size() == Constants.MAX_CARDS_NUMBER)
            return false;

        int coinsMore = 0;
        int coinsBonus = personalBoard(player).getBonusOnActions().getCharactersBonus().getCoinsBonus();
        int diceBonus = personalBoard(player).getBonusOnActions().getCharactersBonus().getDiceBonus();
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        return (card.getCost().getCoinsRequired() + coinsMore + coinsBonus) <= personalBoard(player).getCoins() && valueOfFamilyMember + diceBonus + getServants(player) >= zoneDiceCost;
    }

    /**
     * Get servants
     *
     * @param player playerHandler's reference
     * @return servants' number
     */
    @Override
    public int getServants(PlayerHandler player) {
        return personalBoard(player).getServants();
    }

    /**
     * Check the Building's card cost
     *
     * @param card development card to check
     * @param player playerHandler's reference
     * @param coinsFee coins to pay more if the tower is occupied
     * @param zoneDiceCost the minimum dice value for placing a familiar in the zone
     * @param valueOfFamilyMember my familiar's value
     * @return true if the requirements for taking the card are respected, else false
     */
    private boolean checkCardCostBuilding(BuildingCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        if (player.getPersonalBoardReference().getTerritories().size() == Constants.MAX_CARDS_NUMBER)
            return false;

        int coinsMore = 0;
        int diceBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getDiceBonus();
        int woodBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getWoodBonus();
        int stoneBonus = personalBoard(player).getBonusOnActions().getBuildingsBonus().getStoneBonus();
        int servantsToPay = valueOfFamilyMember + diceBonus - zoneDiceCost;
        if (servantsToPay > 0)
            servantsToPay = 0;

        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        if (card.getCost().getWoodRequired() + woodBonus <= personalBoard(player).getWood())
            if (card.getCost().getStoneRequired() + stoneBonus <= personalBoard(player).getStone())
                if ((card.getCost().getCoinsRequired() + coinsMore) <= personalBoard(player).getCoins())
                    if ((-servantsToPay + card.getCost().getServantsRequired()) <= (getServants(player))) return true;
        return false;
    }
    // questa torna int. 0=non posso prendere, 1=prendo per effetto 1, 2= prendo per effetto 2, 3 = posso predere per tutti e due gl effetti devo chiedere

    /**
     * Check the Venture's card cost
     *
     * @param card development card to check
     * @param player playerHandler's reference
     * @param coinsFee coins to pay more if the tower is occupied
     * @param zoneDiceCost the minimum dice value for placing a familiar in the zone
     * @param valueOfFamilyMember my familiar's value
     * @return true if the requirements for taking the card are respected, else false
     */
    @Override
    public int checkCardCostVentures(VenturesCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;

        if (player.getPersonalBoardReference().getVentures().size() == Constants.MAX_CARDS_NUMBER)
            return Constants.CANT_USE_ANY_PAYMENT;

        int diceBonus = personalBoard(player).getBonusOnActions().getVenturesBonus();
        boolean[] canTakeCardFromEffect = new boolean[2];
        int i = 0;
        for (VenturesCost v: card.getVenturesCost()) {
            if (v.getWoodRequired() <= personalBoard(player).getWood() &&
                    v.getStoneRequired() <= personalBoard(player).getStone() &&
                    (v.getCoinsRequired() + coinsMore)<= personalBoard(player).getCoins() &&
                    v.getMilitaryRequired() <= player.getScore().getMilitaryPoints() &&
                    (valueOfFamilyMember + diceBonus + getServants(player) )>= zoneDiceCost)
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

    /**
     * Check the Territory's card cost
     *
     * @param card development card to check
     * @param player playerHandler's reference
     * @param coinsFee coins to pay more if the tower is occupied
     * @param zoneDiceCost the minimum dice value for placing a familiar in the zone
     * @param valueOfFamilyMember my familiar's value
     * @return true if the requirements for taking the card are respected, else false
     */
    private boolean checkCardCostTerritory(TerritoryCard card, PlayerHandler player, boolean coinsFee, int zoneDiceCost, int valueOfFamilyMember){
        int coinsMore = 0;
        if (coinsFee)
            coinsMore = Constants.ADD_COINS_IF_TOWER_IS_OCCUPIED;
        int diceBonus = personalBoard(player).getBonusOnActions().getTerritoryBonus();
        int length = personalBoard(player).getTerritories().size();
        if (length == Constants.MAX_CARDS_NUMBER)
            return false;
        System.out.println(coinsFee);
        System.out.println(zoneDiceCost);
        System.out.println(valueOfFamilyMember);
        System.out.println(card.getCost().getWoodRequired());
        return checkMilitaryPointsForTerritory(player, length) && card.getCost().getWoodRequired() <= personalBoard(player).getWood() && card.getCost().getStoneRequired() <= personalBoard(player).getStone() && coinsMore <= personalBoard(player).getCoins() && (valueOfFamilyMember + diceBonus + getServants(player)) >= zoneDiceCost;

    }

    /**
     * This method check if the player has enough military points for taking the territory's card
     *
     * @param player playerHandler's reference
     * @param length index of my territory cards
     * @return true if the player has enough military points, else false
     */
    @Override
    public boolean checkMilitaryPointsForTerritory(PlayerHandler player, int length) {
            return player.getScore().getMilitaryPoints() >= player.getRoom().getBoard().getMilitaryPointsForTerritories()[length];
    }

    /**
     * Get the player's personal board
     *
     * @param playerHandler playerHandler's reference
     * @return player's personal board
     */
    private PersonalBoard personalBoard(PlayerHandler playerHandler){
        return playerHandler.getPersonalBoardReference();
    }
}
