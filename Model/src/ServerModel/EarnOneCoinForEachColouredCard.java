package ServerModel;

/**
 * Created by raffaelebongo on 10/05/17.
 */
public class EarnOneCoinForEachColouredCard implements Effects {

    String cardColor;

    public EarnOneCoinForEachColouredCard(String cardColor ) {
        this.cardColor = cardColor;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        int coinsToAdd = 0;
        switch( cardColor ){
            case"yellow":
                while ( player.getPersonalBoardReference().getBuildings()[coinsToAdd] != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);

            case"green":
                while ( player.getPersonalBoardReference().getTerritories()[coinsToAdd] != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);

            case"blue":
                while ( player.getPersonalBoardReference().getCharacters()[coinsToAdd] != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);

            case"purple":
                while ( player.getPersonalBoardReference().getVentures()[coinsToAdd] != null ){
                    coinsToAdd++;
                }
                player.getPersonalBoardReference().setCoins(player.getPersonalBoardReference().getCoins() + coinsToAdd);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
