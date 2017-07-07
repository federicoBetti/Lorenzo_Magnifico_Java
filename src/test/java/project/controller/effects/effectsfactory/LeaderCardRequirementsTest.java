package project.controller.effects.effectsfactory;

import org.junit.Before;
import org.junit.Test;
import project.controller.Constants;
import project.controller.cardsfactory.*;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;
import project.server.network.rmi.RMIPlayerHandler;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by federico on 07/07/17.
 */
public class LeaderCardRequirementsTest {

    LeaderCardRequirements test = new LeaderCardRequirements();
    PlayerHandler p = new RMIPlayerHandler();
    PersonalBoard personalBoard = p.getPersonalBoardReference();

    @Before
    public void before(){
        TerritoryCard territoryCard = new TerritoryCard("c",0,false,new TerritoryCost(2,2,2), new ArrayList<>(), new ArrayList<>());
        CharacterCard characterCard = new CharacterCard("c",0,false,new CharactersCost(2), new ArrayList<>(), new ArrayList<>());
        BuildingCard buildingCard = new BuildingCard("c",0,false,new BuildingCost(2,2,2,2,2), new ArrayList<>(), new ArrayList<>());
        VenturesCard venturesCard = new VenturesCard("c",0,false,new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        personalBoard.getTerritories().add(territoryCard);
        personalBoard.getTerritories().add(territoryCard);
        personalBoard.getTerritories().add(territoryCard);
        personalBoard.getTerritories().add(territoryCard);
        personalBoard.getTerritories().add(territoryCard);
        personalBoard.getTerritories().add(territoryCard);

        personalBoard.getCharacters().add(characterCard);
        personalBoard.getCharacters().add(characterCard);
        personalBoard.getCharacters().add(characterCard);
        personalBoard.getCharacters().add(characterCard);
        personalBoard.getCharacters().add(characterCard);
        personalBoard.getCharacters().add(characterCard);

        personalBoard.getBuildings().add(buildingCard);
        personalBoard.getBuildings().add(buildingCard);
        personalBoard.getBuildings().add(buildingCard);
        personalBoard.getBuildings().add(buildingCard);
        personalBoard.getBuildings().add(buildingCard);
        personalBoard.getBuildings().add(buildingCard);

        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);
        personalBoard.getVentures().add(venturesCard);

        personalBoard.setCoins(18);
        personalBoard.setWood(10);
        personalBoard.setStone(10);
        personalBoard.setServants(15);
        p.getScore().setMilitaryPoints(20);
        p.getScore().setVictoryPoints(35);
        p.getScore().setFaithPoints(10);
    }

    @Test
    public void checkRequirements() throws Exception {
        boolean result = test.checkRequirements(Constants.FRANCESCO_SFORZA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LUDVICO_ARIOSTO, p);
        assertEquals(true, result);
        personalBoard.getCharacters().remove(0);
        personalBoard.getCharacters().remove(1);
        result = test.checkRequirements(Constants.LUDVICO_ARIOSTO, p);
        assertEquals(false, result);


        result = test.checkRequirements(Constants.FILIPPO_BRUNELLESCHI, p);
        assertEquals(true, result);
        personalBoard.getBuildings().remove(0);
        personalBoard.getBuildings().remove(1);
        result = test.checkRequirements(Constants.FILIPPO_BRUNELLESCHI, p);
        assertEquals(false, result);

        result = test.checkRequirements(Constants.SIGISMONDO_MALATESTA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.GIROLAMO_SAVONAROLA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.MICHELANGELO_BUONARROTI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.GIOVANNI_DALLE_BANDE_NERE, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LEONARDO_DA_VINCI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.SANDRO_BOTTICELLI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LUDOVICO_IL_MORO, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LUCREZIA_BORGIA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.FEDERICO_DA_MONTEFELTRO, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LORENZO_DE_MEDICI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.SISTO_IV, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.CESARE_BORGIA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.SANDRO_BOTTICELLI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.COSIMO_DE_MEDICI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.BARTOLOMEO_CORLEONI, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.LUDOVICO_III_GONZAGA, p);
        assertEquals(true, result);

        result = test.checkRequirements(Constants.PICO_DELLA_MIRANDOLA, p);
        assertEquals(true, result);
    }

}