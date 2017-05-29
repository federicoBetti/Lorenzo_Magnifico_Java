package Project.Controller.CardsFactory;

/**
 * Created by raffaelebongo on 19/05/17.
 */
public class Anagrafic {
    private String type;
    private String name;
    private int period;
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

    public void setType(String type) {
        this.type = type;
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

    public void setPeriod(int period) {
        this.period = period;
    }


}
