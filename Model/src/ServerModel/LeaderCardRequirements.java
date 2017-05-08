package ServerModel;

import SERVER.Card;
import SERVER.PlayerPackage.*;
/**
 * 
 */
public class LeaderCardRequirements {


    /**
     * Default constructor
     */
    public LeaderCardRequirements() {
    }


    /**
     * @param NameLeader 
     * @param player 
     * @return
     */
    public boolean CheckRequirements(String NameLeader, Player player) {
        switch (NameLeader){
            case "Francesco Sforza":{
                int venturesCardNumber;
                venturesCardNumber = CountCard(player.getPersonalBoardReference().getVentures());
                return venturesCardNumber >= 5;
            }
            case "Lodovico Ariosto":{
                int charactersCardNumber;
                charactersCardNumber = CountCard(player.getPersonalBoardReference().getCharacters());
                return charactersCardNumber >= 5;
            }
            case "Filippo Brunelleschi":{
                int buildingsCardNumber;
                buildingsCardNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                return buildingsCardNumber >= 5;
                }
            case "Sigismondo Malatesta":{
                int militaryPoints, faithPoints;
                militaryPoints = player.getScore().getMilitaryPoints();
                faithPoints = player.getScore().getFaithPoints();
                return militaryPoints >= 7 && faithPoints >= 3;
            }
            case "Girolamo Savonarola":{
                int coins;
                coins = player.getPersonalBoardReference().getCoins();
                return coins >= 18;
            }
            case "Michelangelo Buonarroti":{
                int stoneNumber;
                stoneNumber = player.getPersonalBoardReference().getStone();
                return stoneNumber >= 10;
            }
            case "Giovanni dalle Bande Nere":{
                int militaryPointsNumber;
                militaryPointsNumber = player.getScore().getMilitaryPoints();
                return militaryPointsNumber >= 12;
            }
            case "Leonardo da Vinci":{
                int charactersNumber, territoryNumber;
                charactersNumber = CountCard(player.getPersonalBoardReference().getCharacters());
                territoryNumber = CountCard(player.getPersonalBoardReference().getTerritories());
                return charactersNumber >= 4 && territoryNumber >= 2;
            }
            case "Sandro Botticelli":{
                int woodNumber;
                woodNumber = player.getPersonalBoardReference().getWood();
                return woodNumber >= 10;
            }
            case "Ludovico il Moro":{
                int territoryNumber, buildingNumber, charactersNumber, venturesNumber;
                territoryNumber = CountCard(player.getPersonalBoardReference().getTerritories());
                buildingNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                charactersNumber = CountCard(player.getPersonalBoardReference().getCharacters());
                venturesNumber = CountCard(player.getPersonalBoardReference().getVentures());
                return territoryNumber >= 2 && buildingNumber >= 2 && charactersNumber >= 2 && venturesNumber >= 2;
            }
            case "Lucrezia Borgia":{
                int territoryNumber, buildingNumber, charactersNumber, venturesNumber;
                territoryNumber = CountCard(player.getPersonalBoardReference().getTerritories());
                buildingNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                charactersNumber = CountCard(player.getPersonalBoardReference().getCharacters());
                venturesNumber = CountCard(player.getPersonalBoardReference().getVentures());
                return territoryNumber == 6 || buildingNumber == 6 || charactersNumber == 6 || venturesNumber == 6;
            }
            case "Federico da Montefeltro":{
                int territoryNumber;
                territoryNumber = CountCard(player.getPersonalBoardReference().getTerritories());
                return territoryNumber >= 5;
            }
            case "Lorenzo de' Medici":{
                int victoryPointsNumber;
                victoryPointsNumber = player.getScore().getVictoryPoints();
                return victoryPointsNumber >= 35;
            }
            case "Sisto IV":{
                int wood, stone, coin, servant;
                wood = player.getPersonalBoardReference().getWood();
                servant = player.getPersonalBoardReference().getServants();
                stone = player.getPersonalBoardReference().getStone();
                coin = player.getPersonalBoardReference().getCoins();
                return wood >= 6 && stone >= 6 && coin >= 6 && servant >= 6;
            }
            case "Cesare Borgia":{
                int buildingNumber, coinsNumber, faithNumber;
                buildingNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                coinsNumber = player.getPersonalBoardReference().getCoins();
                faithNumber = player.getScore().getFaithPoints();
                return buildingNumber >= 3 && coinsNumber >= 12 && faithNumber >= 2;
            }
            case "Santa Rita":{
                int faithNumber;
                faithNumber = player.getScore().getFaithPoints();
                return faithNumber >= 8;
            }
            case "Cosimo de' Medici":{
                int buildingNumber, charactersNumber;
                buildingNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                charactersNumber = CountCard(player.getPersonalBoardReference().getCharacters());
                return buildingNumber >= 4 && charactersNumber >= 2;
            }
            case "Bartolomeo Colleoni":{
                int territoriesNumber, venturesNumber;
                territoriesNumber = CountCard(player.getPersonalBoardReference().getTerritories());
                venturesNumber = CountCard(player.getPersonalBoardReference().getVentures());
                return territoriesNumber >= 4 && venturesNumber >= 2;
            }
            case "Lodovico III Gonzaga":{
                int servantsNumber;
                servantsNumber = player.getPersonalBoardReference().getServants();
                return servantsNumber >= 12;
            }
            case "Pico della Mirandola":{
                int buildingNumber, venturesNumber;
                buildingNumber = CountCard(player.getPersonalBoardReference().getBuildings());
                venturesNumber = CountCard(player.getPersonalBoardReference().getVentures());
                return buildingNumber >= 2 && venturesNumber >= 4;
            }
        }
        return false;
    }

    private int CountCard(Card[] cards){
        int number = 0;
        for (int i = 0; cards[i] != null; i++){
            number++;
        }
        return number;
    }

}