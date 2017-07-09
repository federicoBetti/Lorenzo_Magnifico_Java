package project.controller.cardsfactory;

/**
 * This class is used in the CardFromJson objects for storing the card's anagrafic characteristics
 */
public class Anagrafic {
    private String type;
    private String name;
    private int period;
    private boolean choicePe;
    private Object[] cost;

    /**
     * Get cost variable
     *
     * @return cost
     */
    public Object getCost() {
        return cost;
    }

    /**
     * Set cost varibale
     *
     * @param cost cost
     */
    public void setCost(Object[] cost) {
        this.cost = cost;
    }

    /**
     * Get type variable
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * get name variable
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name varibale
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get period variable
     *
     * @return period
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Get choicePe variable
     *
     * @return choicePE
     */
    public boolean isChoicePe() {
        return choicePe;
    }
}
