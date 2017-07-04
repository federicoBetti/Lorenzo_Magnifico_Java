package project.controller.cardsfactory;
import java.io.Serializable;

public interface Cost extends Serializable, Cloneable{
    void picoDellaMirandolaDowngrade();
    String toScreen();

    Cost copyOf();

    void addCoin(int i);

    void addStone(int i);

    void addWood(int i);
}