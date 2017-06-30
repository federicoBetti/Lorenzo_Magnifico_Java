package project.controller.effects.realeffects;

import project.controller.cardsfactory.*;
import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class EarnOneCoinForEachColouredCard implements Effects {

    private String cardColor;

    public EarnOneCoinForEachColouredCard(String cardColor ) {
        this.cardColor = cardColor;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        int coinsToAdd = 0;
        switch( cardColor ){
            case"yellow":
                for (BuildingCard card : player.getPersonalBoardReference().getBuildings() )
                    coinsToAdd++;
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"green":
                for (TerritoryCard card : player.getPersonalBoardReference().getTerritories() )
                    coinsToAdd++;
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"blue":
                for (CharacterCard card : player.getPersonalBoardReference().getCharacters() )
                    coinsToAdd++;
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"purple":
                for (VenturesCard card : player.getPersonalBoardReference().getVentures() )
                    coinsToAdd++;
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            default:
                return null;
        }

        return new OkOrNo();
    }

    @Override
    public String toScreen() {
        return "Earn one coin for each " + cardColor + " card";
    }
}
