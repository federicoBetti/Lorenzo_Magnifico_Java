package project.controller.cardsfactory;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class Anagrafic {
    private String type;
    private String name;
    private int period;
    private boolean choicePe;
    private Object cost;

    public Object getCost() {
        return cost;
    }

    public void setCost(Object cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public boolean isChoicePe() {
        return choicePe;
    }
}
