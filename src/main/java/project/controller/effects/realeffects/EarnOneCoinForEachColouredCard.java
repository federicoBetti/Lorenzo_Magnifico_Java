package project.controller.effects.realeffects;

import project.messages.BonusInteraction;
import project.messages.OkOrNo;
import project.server.network.PlayerHandler;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class EarnOneCoinForEachColouredCard implements Effects {

    String cardColor;

    public EarnOneCoinForEachColouredCard(String cardColor ) {
        this.cardColor = cardColor;
    }


    @Override
    public BonusInteraction doEffect(PlayerHandler player) {
        int coinsToAdd = 0;
        switch( cardColor ){
            case"yellow":
                while ( player.getPersonalBoardReference().getBuildings().get(coinsToAdd) != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"green":
                while ( player.getPersonalBoardReference().getTerritories().get(coinsToAdd) != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"blue":
                while ( player.getPersonalBoardReference().getCharacters().get(coinsToAdd) != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            case"purple":
                while ( player.getPersonalBoardReference().getVentures().get(coinsToAdd) != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
                break;

            default:
                return null;
        }

        return new OkOrNo();
    }
}
