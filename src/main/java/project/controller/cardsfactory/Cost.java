package project.controller.cardsfactory;
import java.io.Serializable;

public interface Cost extends Serializable, Cloneable{
    void picoDellaMirandolaDowngrade();
    String toScreen();

    default Cost copyOf() {
        //return (Cost) ((Cost)this).clone();//todo
        return null;
    }

    void addCoin(int i);

    void addStone(int i);

    void addWood(int i);
}