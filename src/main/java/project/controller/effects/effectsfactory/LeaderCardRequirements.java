package project.controller.effects.effectsfactory;

import project.controller.Constants;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.util.HashMap;

/**
 * This class is used for checking if a player has enough requirements for playing a leader card
 */
public class LeaderCardRequirements {

    
    private HashMap<String, RequirementsBuilder> requirements;
    /**
     * Constructor
     */
    public LeaderCardRequirements() {
        requirements = new HashMap<>();
        requirements = new HashMap<>(Constants.LEADER_CARD_NUMBER);
        fillHashMapRequirements(requirements);
    }

    /**
     * This method finds the right method for checking the player's requirements
     *
     * @param leaderName as a String
     * @param playerHandler playerHandler's reference
     * @return the right method
     */
    public boolean checkRequirements(String leaderName, PlayerHandler playerHandler){
        RequirementsBuilder r = requirements.get(leaderName);
        return r.checkRequirements(playerHandler);
    }

    private void fillHashMapRequirements(HashMap<String, RequirementsBuilder> requirements) {
        this.requirements.put(Constants.FRANCESCO_SFORZA,this::francescoSforza);
        this.requirements.put(Constants.LUDVICO_ARIOSTO,this::ludovicoAriosto);
        this.requirements.put(Constants.FILIPPO_BRUNELLESCHI,this::filippoBrunelleschi);
        this.requirements.put(Constants.SIGISMONDO_MALATESTA,this::sigismondoMalatesta);
        this.requirements.put(Constants.GIROLAMO_SAVONAROLA,this::girolamoSavonarola);
        this.requirements.put(Constants.MICHELANGELO_BUONARROTI,this::michelangeloBuonarroti);
        this.requirements.put(Constants.GIOVANNI_DALLE_BANDE_NERE,this::giovanniDalleBandeNere);
        this.requirements.put(Constants.LEONARDO_DA_VINCI,this::leonardoDaVinci);
        this.requirements.put(Constants.SANDRO_BOTTICELLI,this::sandroBotticelli);
        this.requirements.put(Constants.LUDOVICO_IL_MORO,this::ludovicoIlMoro);
        this.requirements.put(Constants.LUCREZIA_BORGIA,this::lucreziaBorgia);
        this.requirements.put(Constants.FEDERICO_DA_MONTEFELTRO,this::federicoDaMontefeltro);
        this.requirements.put(Constants.LORENZO_DE_MEDICI,this::lorenzoDeMedici);
        this.requirements.put(Constants.SISTO_IV,this::sistoIV);
        this.requirements.put(Constants.CESARE_BORGIA,this::cesareBorgia);
        this.requirements.put(Constants.SANTA_RITA,this::santaRita);
        this.requirements.put(Constants.COSIMO_DE_MEDICI,this::cosimoDeMedici);
        this.requirements.put(Constants.BARTOLOMEO_CORLEONI,this::bartolometoColleoni);
        this.requirements.put(Constants.LUDOVICO_III_GONZAGA,this::ludovicoIIIGonzaga);
        this.requirements.put(Constants.PICO_DELLA_MIRANDOLA,this::picoDellaMiradola);
    }

    /**
     * Check requirements for playing picoDellaMiradola
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean picoDellaMiradola(PlayerHandler playerHandler) {
        int buildingNumber;
        int venturesNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return buildingNumber >= 2 && venturesNumber >= 4;
    }

    /**
     * Check requirements for playing ludovicoIIIGonzaga
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean ludovicoIIIGonzaga(PlayerHandler playerHandler) {
        int servantsNumber;
        servantsNumber = personalBoard(playerHandler).getServants();
        return servantsNumber >= 12;
    }

    /**
     * Check requirements for playing bartolometoColleoni
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean bartolometoColleoni(PlayerHandler playerHandler) {
        int territoriesNumber;
        int venturesNumber;
        territoriesNumber = personalBoard(playerHandler).getTerritories().size();
        venturesNumber = personalBoard(playerHandler).getVentures().size();
        return territoriesNumber >= 4 && venturesNumber >= 2;
    }

    /**
     * Check requirements for playing cosimoDeMedici
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean cosimoDeMedici(PlayerHandler playerHandler) {
        int buildingNumber;
        int charactersNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        return buildingNumber >= 4 && charactersNumber >= 2;
    }

    /**
     * Check requirements for playing santaRita
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean santaRita(PlayerHandler playerHandler) {
        int faithNumber;
        faithNumber = playerHandler.getScore().getFaithPoints();
        return faithNumber >= 8;
    }

    /**
     * Check requirements for playing cesareBorgia
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean cesareBorgia(PlayerHandler playerHandler) {
        int buildingNumber;
        int coinsNumber;
        int faithNumber;
        buildingNumber = personalBoard(playerHandler).getBuildings().size();
        coinsNumber = personalBoard(playerHandler).getCoins();
        faithNumber = playerHandler.getScore().getFaithPoints();
        return buildingNumber >= 3 && coinsNumber >= 12 && faithNumber >= 2;
    }

    /**
     * Check requirements for playing sistoIV
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
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

    /**
     * Check requirements for playing lorenzoDeMedici
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean lorenzoDeMedici(PlayerHandler playerHandler) {
        int victoryPointsNumber;
        victoryPointsNumber = playerHandler.getScore().getVictoryPoints();
        return victoryPointsNumber >= 35;
    }

    /**
     * Check requirements for playing federicoDaMontefeltro
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean federicoDaMontefeltro(PlayerHandler playerHandler) {
        int territoryNumber;
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        return territoryNumber >= 5;
    }

    /**
     * Check requirements for playing lucreziaBorgia
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
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

    /**
     * Check requirements for playing ludovicoIlMoro
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
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

    /**
     * Check requirements for playing sandroBotticelli
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean sandroBotticelli(PlayerHandler playerHandler) {
        int woodNumber;
        woodNumber = personalBoard(playerHandler).getWood();
        return woodNumber >= 10;        
    }

    /**
     * Check requirements for playing leonardoDaVinci
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean leonardoDaVinci(PlayerHandler playerHandler) {
        int charactersNumber;
        int territoryNumber;
        charactersNumber = personalBoard(playerHandler).getCharacters().size();
        territoryNumber = personalBoard(playerHandler).getTerritories().size();
        return charactersNumber >= 4 && territoryNumber >= 2;
    }

    /**
     * Check requirements for playing giovanniDalleBandeNere
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean giovanniDalleBandeNere(PlayerHandler playerHandler) {
        int militaryPointsNumber;
        militaryPointsNumber = playerHandler.getScore().getMilitaryPoints();
        return militaryPointsNumber >= 12;
    }

    /**
     * Check requirements for playing michelangeloBuonarroti
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean michelangeloBuonarroti(PlayerHandler playerHandler) {
        int stoneNumber;
        stoneNumber = personalBoard(playerHandler).getStone();
        return stoneNumber >= 10;
    }

    /**
     * Check requirements for playing girolamoSavonarola
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean girolamoSavonarola(PlayerHandler playerHandler) {
        int coins;
        coins = personalBoard(playerHandler).getCoins();
        return coins >= 18;
    }

    /**
     * Check requirements for playing sigismondoMalatesta
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean sigismondoMalatesta(PlayerHandler playerHandler) {
        int militaryPoints;
        int faithPoints;
        militaryPoints = playerHandler.getScore().getMilitaryPoints();
        faithPoints = playerHandler.getScore().getFaithPoints();
        return militaryPoints >= 7 && faithPoints >= 3;
    }

    /**
     * Check requirements for playing filippoBrunelleschi
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean filippoBrunelleschi(PlayerHandler playerHandler) {
        int buildingsCardNumber;
        buildingsCardNumber = personalBoard(playerHandler).getBuildings().size();
        return buildingsCardNumber >= 5;        
    }

    /**
     * Check requirements for playing francescoSforza
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean francescoSforza(PlayerHandler playerHandler) {
        int venturesCardNumber;
        venturesCardNumber = personalBoard(playerHandler).getVentures().size();
        return venturesCardNumber >= 5;        
    }

    /**
     * Check requirements for playing ludovicoAriosto
     *
     * @param playerHandler pleyerHandler's reference
     * @return ture if the player can play the card, else false
     */
    private boolean ludovicoAriosto(PlayerHandler playerHandler){
        int charactersCardNumber;
        charactersCardNumber = personalBoard(playerHandler).getCharacters().size();
        return charactersCardNumber >= 5;
    }

    /**
     * Functional interface for finding the right check requirement's method
     */
    @FunctionalInterface
    private interface RequirementsBuilder{
        boolean checkRequirements(PlayerHandler playerHandler);
    }

    /**
     * This method gets the player's personal board
     *
     * @param playerHandler playerHandler's reference
     * @return the palyer's personalBoard
     */
    private PersonalBoard personalBoard(PlayerHandler playerHandler){
        return playerHandler.getPersonalBoardReference();
    }
}