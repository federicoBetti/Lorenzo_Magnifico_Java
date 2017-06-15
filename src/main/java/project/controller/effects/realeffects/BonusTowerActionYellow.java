package project.controller.effects.realeffects;

import javafx.scene.effect.Effect;
import project.messages.BonusInteraction;
import project.messages.TowerAction;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 06/06/17.
 */
public class BonusTowerActionYellow implements Effects {

    String cardColour;
    int diceValue;
    String[] discountedResources;
    int quantityDiscounted;

    public BonusTowerActionYellow(String parameter, int quantity ){
        cardColour = "yellow";
        diceValue = 6;
        discountedResources = parameter.split("-"); //wood-stone
        quantityDiscounted = quantity;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        String discRes1 = discountedResources[0];
        String discRes2 = discountedResources[1];

        TowerAction towerAction = new TowerAction(cardColour, diceValue, discRes1, quantityDiscounted, discRes2, quantityDiscounted);

        return  towerAction;
    }

    /*@Override
    public String toString(){
        return "ciao" ;
    }


    */}