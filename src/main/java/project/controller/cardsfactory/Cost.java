package project.controller.cardsfactory;
import java.io.Serializable;

/**
 * Cost's Interface
 */
public interface Cost extends Serializable, Cloneable{

    /**
     * Pico della mirandola downgrade abstract method
     */
    void picoDellaMirandolaDowngrade();

    /**
     * Abstract toScreen
     */
    String toScreen();

    /**
     * Abstract copyOf
     */
    Cost copyOf();

    /**
     * Abstract addCoin
     */
    void addCoin(int i);

    /**
     * Abstract addStone
     */
    void addStone(int i);

    /**
     * Abstract addWood
     */
    void addWood(int i);
}