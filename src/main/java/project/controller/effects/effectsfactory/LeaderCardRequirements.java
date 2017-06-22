package project.controller.effects.effectsfactory;

import project.controller.Constants;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.util.HashMap;


public class LeaderCardRequirements {

    
    private static HashMap<String, RequirementsBuilder> requirements;
    /**
     * Default constructor
     */
    public LeaderCardRequirements() {
        fillHashMapRequirements();
    }

    public static boolean checkRequirements(String leaderName, PlayerHandler playerHandler){
        return requirements.get(leaderName).checkRequirements(playerHandler);
    }

    private void fillHashMapRequirements() {
        requirements.put(Constants.FRANCESCO_SFORZA,this::francescoSforza);
        requirements.put(Constants.LUDVICO_ARIOSTO,this::ludovicoAriosto);
        requirements.put(Constants.FILIPPO_BRUNELLESCHI,this::filippoBrunelleschi);
        requirements.put(Constants.SIGISMONDO_MALATESTA,this::sigismondoMalatesta);
        requirements.put(Constants.GIROLAMO_SAVONAROLA,this::girolamoSavonarola);
        requirements.put(Constants.MICHELANGELO_BUONARROTI,this::michelangeloBuonarroti);
        requirements.put(Constants.GIOVANNI_DALLE_BANDE_NERE,this::giovanniDalleBandeNere);
        requirements.put(Constants.LEONARDO_DA_VINCI,this::leonardoDaVinci);
        requirements.put(Constants.SANDRO_BOTTICELLI,this::sandroBotticelli);
        requirements.put(Constants.LUDOVICO_IL_MORO,this::ludovicoIlMoro);
        requirements.put(Constants.LUCREZIA_BORGIA,this::lucreziaBorgia);
        requirements.put(Constants.FEDERICO_DA_MONTEFELTRO,this::federicoDaMontefeltro);
        requirements.put(Constants.LORENZO_DE_MEDICI,this::lorenzoDeMedici);
        requirements.put(Constants.SISTO_IV,this::sistoIV);
        requirements.put(Constants.CESARE_BORGIA,this::cesareBorgia);
        requirements.put(Constants.SANTA_RITA,this::santaRita);
        requirements.put(Constants.COSIMO_DE_MEDICI,this::cosimoDeMedici);
        requirements.put(Constants.BARTOLOMEO_CORLEONI,this::bartolometoColleoni);
        requirements.put(Constants.LUDOVICO_III_GONZAGA,this::ludovicoIIIGonzaga);
        requirements.put(Constants.PICO_DELLA_MIRANDOLA,this::picoDellaMiradola);
    }



    private boolean picoDellaMiradola(PlayerHandler playerHandler) {
        int buildingNumber;
        int venturesNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return buildingNumber >= 2 && venturesNumber >= 4;
    }

    private boolean ludovicoIIIGonzaga(PlayerHandler playerHandler) {
        int servantsNumber;
        servantsNumber = personalBoard(playerHandler).getServants();
        return servantsNumber >= 12;
    }

    private boolean bartolometoColleoni(PlayerHandler playerHandler) {
        int territoriesNumber;
        int venturesNumber;
        territoriesNumber = personalBoard(playerHandler).getTerritories().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return territoriesNumber >= 4 && venturesNumber >= 2;
    }

    private boolean cosimoDeMedici(PlayerHandler playerHandler) {
        int buildingNumber;
        int charactersNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        return buildingNumber >= 4 && charactersNumber >= 2;
    }

    private boolean santaRita(PlayerHandler playerHandler) {
        int faithNumber;
        faithNumber = playerHandler.getScore().getFaithPoints();
        return faithNumber >= 8;
    }

    private boolean cesareBorgia(PlayerHandler playerHandler) {
        int buildingNumber;
        int coinsNumber;
        int faithNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        coinsNumber = personalBoard(playerHandler).getCoins();
        faithNumber = playerHandler.getScore().getFaithPoints();
        return buildingNumber >= 3 && coinsNumber >= 12 && faithNumber >= 2;
    }

    private boolean sistoIV(PlayerHandler playerHandler) {
        int wood;
        int stone;
        int coin;
        int servant;
        wood = personalBoard(playerHandler).getWood();
        servant = personalBoard(playerHandler).getServants();
        stone = personalBoard(playerHandler).getStone();
        coin = personalBoard(playerHandler).getCoins();
        return wood >= 6 && stone >= 6 && coin >= 6 && servant >= 6;
    }

    private boolean lorenzoDeMedici(PlayerHandler playerHandler) {
        int victoryPointsNumber;
        victoryPointsNumber = playerHandler.getScore().getVictoryPoints();
        return victoryPointsNumber >= 35;
    }

    private boolean federicoDaMontefeltro(PlayerHandler playerHandler) {
        int territoryNumber;
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        return territoryNumber >= 5;
    }

    private boolean lucreziaBorgia(PlayerHandler playerHandler) {
        int territoryNumber;
        int buildingNumber;
        int charactersNumber;
        int venturesNumber;
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return territoryNumber == 6 || buildingNumber == 6 || charactersNumber == 6 || venturesNumber == 6;
    }

    private boolean ludovicoIlMoro(PlayerHandler playerHandler) {
        int territoryNumber;
        int buildingNumber;
        int charactersNumber;
        int venturesNumber;
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return territoryNumber >= 2 && buildingNumber >= 2 && charactersNumber >= 2 && venturesNumber >= 2;
    }

    private boolean sandroBotticelli(PlayerHandler playerHandler) {
        int woodNumber;
        woodNumber = personalBoard(playerHandler).getWood();
        return woodNumber >= 10;        
    }

    private boolean leonardoDaVinci(PlayerHandler playerHandler) {
        int charactersNumber;
        int territoryNumber;
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        return charactersNumber >= 4 && territoryNumber >= 2;
    }

    private boolean giovanniDalleBandeNere(PlayerHandler playerHandler) {
        int militaryPointsNumber;
        militaryPointsNumber = playerHandler.getScore().getMilitaryPoints();
        return militaryPointsNumber >= 12;
    }

    private boolean michelangeloBuonarroti(PlayerHandler playerHandler) {
        int stoneNumber;
        stoneNumber = personalBoard(playerHandler).getStone();
        return stoneNumber >= 10;
    }

    private boolean girolamoSavonarola(PlayerHandler playerHandler) {
        int coins;
        coins = personalBoard(playerHandler).getCoins();
        return coins >= 18;
    }

    private boolean sigismondoMalatesta(PlayerHandler playerHandler) {
        int militaryPoints;
        int faithPoints;
        militaryPoints = playerHandler.getScore().getMilitaryPoints();
        faithPoints = playerHandler.getScore().getFaithPoints();
        return militaryPoints >= 7 && faithPoints >= 3;
    }

    private boolean filippoBrunelleschi(PlayerHandler playerHandler) {
        int buildingsCardNumber;
        buildingsCardNumber = personalBoard(playerHandler).getBuildings().size();
        return buildingsCardNumber >= 5;        
    }

    private boolean francescoSforza(PlayerHandler playerHandler) {
        int venturesCardNumber;
        venturesCardNumber = personalBoard(playerHandler).getVentures().size();
        return venturesCardNumber >= 5;        
    }


    
    private boolean ludovicoAriosto(PlayerHandler playerHandler){
        int charactersCardNumber;
        charactersCardNumber = personalBoard(playerHandler).getCharacters().size();
        return charactersCardNumber >= 5;
    }

    @FunctionalInterface
    private interface RequirementsBuilder{
        boolean checkRequirements(PlayerHandler playerHandler);
    }

    private PersonalBoard personalBoard(PlayerHandler playerHandler){
        return playerHandler.getPersonalBoardReference();
    }
}