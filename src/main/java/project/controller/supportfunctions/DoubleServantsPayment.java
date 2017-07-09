package project.controller.supportfunctions;

/**
 * This class is a the payServants' decorator
 */
public class DoubleServantsPayment extends SupportFunctionsDecorator{

    /**
     * Constru
     *
     * @param allSupportFunctions
     */
    public DoubleServantsPayment(AllSupportFunctions allSupportFunctions){
        super(allSupportFunctions);
    }

    @Override
    public int payServants(int cost, int value) {
        if ((cost - value) < 0)
            return 0;
        else
            return (cost - value) * 2;
        }


}

