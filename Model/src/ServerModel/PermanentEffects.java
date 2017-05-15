package ServerModel;

import java.util.*;

/**
 * 
 */
public class PermanentEffects {

    /**
     * Default constructor
     */
    public PermanentEffects() {
    }

    //va ggiunto un secondo parametro che indica la riorsa da scambiare: cosi si risparmia la scrittura di molte classi
    public Effects SearchPermanentEffects(String type, String parameter1, String parameter2, int quantity, TotalCost effectCost) {

        switch( type ){
            case"takeRop":

                switch( parameter1 ) {
                    case"VictoryPointsForEachPurpleCard":
                        return new VictoryPointsForEachPurpleCard(quantity);
                    case"VictoryPointsForEachBlueCard":
                        return new VictoryPointForEachBlueCard(quantity);
                    case"victoryPoints":
                        return new AddVicotryPoints(quantity);
                    case"privilege":
                        return new UsePrivilege(); //TO DO
                    case"militaryPoints":
                        return new AddMilitaryPoints(quantity);
                    case"coin":
                        return  new AddCoin(quantity);
                }
                //QUI VA USATO ANCHE IL TOTALCOST PERCHè CI SONO GLI SCAMBI DI RISORSE. LE QUANTITY INDICANO LE RISORSE CHE SI RICEVONO DALLO
                // SCAMBIO, IL TOTAL COST QUELLO CHE SI SPENDE PER AVERE LE RISORSE

            case "exchangeRes":
                switch( parameter1 ){
                    case"coinsFor":
                        return new ExchangeCoinsFor( quantity, effectCost, parameter2  );
                    case"woodFor":
                        return new ExchangeWoodFor( quantity, effectCost, parameter2 );
                    case"stoneFor":
                        return new ExchangeStoneFor ( quantity, effectCost, parameter2 );
                    case"servantsFor":
                        return new ExchangeServantsFor ( quantity, effectCost, parameter2 );
                    case"servantsForThreeMilitaryPointsAndOneVictoryPoint":
                        return new ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( );
                    case"servantWoodStoneForSixVictoryPoint":
                        return new ServantWoodStoneForSixVictoryPoints();
                    case"faithPointForTwoCoinsAndTwoVictoryPoint":
                        return new FaithPointForTwoCoinsAndTwoVictoryPoints ();
                }

            case"earnOneCoinForEachColouredCard":
                return new EarnOneCoinForEachColouredCard(parameter1);
            case"bonusForHarvesterOrProduction":
                return new IncreaseDiceValueForHoP(quantity, parameter1 ); //TO DO
            case"discountForTakingCard":
                return new DiscountForTakingCards( quantity, parameter1, parameter2 );
            case"increaseDiceValueForTakingCard":
                return new IncreaseDicevalueForTakingCards( quantity, parameter1 );  //TO DO
            case"noImmediateEffectTwoHighestPositionsTower":
                return new noImmediateEffectTwoHighestPositionsTower();
                //TO DO

        }
        return null;
    }

}