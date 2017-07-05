package project.controller.supportfunctions;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by raffaelebongo on 05/07/17.
 */
public class DoubleServantsPaymentTest {
    @Test
    public void payServants() throws Exception {
        int cost = 5;
        int value = 15;

        BasicSupportFunctions bsf = new BasicSupportFunctions();
        DoubleServantsPayment dsp = new DoubleServantsPayment(bsf);
        int res = dsp.payServants(cost,value);

        assertEquals(0, res);

        cost = 15;
        value = 5;

        res = dsp.payServants(cost,value);
        assertEquals( 20, res);
    }

}