package project.controller.cardsfactory;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class TotalCostTest {
    @Test
    public void picoDellaMirandolaDowngrade() throws Exception {
        TotalCost totalCost = new TotalCost();
        totalCost.setCoinsRequired(3);
        totalCost.picoDellaMirandolaDowngrade();

        assertEquals(0, totalCost.getCoinsRequired());
    }

    @Test
    public void addCoin() throws Exception {
        int num = 5;
        TotalCost totalCost = new TotalCost();
        totalCost.setCoinsRequired(0);
        totalCost.addCoin(num);

        assertEquals(5, totalCost.getCoinsRequired());
    }

    @Test
    public void addStone() throws Exception {
        int num = 5;
        TotalCost totalCost = new TotalCost();
        totalCost.setStoneRequired(0);
        totalCost.addStone(num);

        assertEquals(5, totalCost.getStoneRequired());
    }

    @Test
    public void addWood() throws Exception {
        int num = 5;
        TotalCost totalCost = new TotalCost();
        totalCost.setWoodRequired(0);
        totalCost.addWood(num);

        assertEquals(5, totalCost.getWoodRequired());
    }

    @Test
    public void addServants() throws Exception {
        int num = 5;
        TotalCost totalCost = new TotalCost();
        totalCost.setServantsRequired(0);
        totalCost.addServants(num);

        assertEquals(5, totalCost.getServantsRequired());
    }

    @Test
    public void addFaithPoint(){
        int num = 5;
        TotalCost totalCost = new TotalCost();
        totalCost.setFaithPonts(0);
        totalCost.addFaithPoints(num);

        assertEquals(5, totalCost.getFaithPoints());
    }

}