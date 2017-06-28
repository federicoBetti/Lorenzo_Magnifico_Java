package project.controller.effects.effectsfactory;

import project.controller.cardsfactory.TotalCost;
import project.controller.effects.realeffects.*;

//todo rifare con HAshMap
public class BuildPermanentEffects {



    //va ggiunto un secondo parametro che indica la riorsa da scambiare: cosi si risparmia la scrittura di molte classi
    public Effects searchPermanentEffects(String type, String parameter1, String parameter2, int quantity, TotalCost effectCost) {

        switch( type ){ //3 PARAMETRI -> TRIS
            case"takeRop": {
                switch (parameter1) {
                    case "VictoryPointsForEachPurpleCard":
                        return new VictoryPointsForEachPurpleCard(quantity);
                    case "VictoryPointsForEachBlueCard":
                        return new VictoryPointForEachBlueCard(quantity);
                    case "victoryPoint":
                        return new AddVicotryPoints(quantity);
                    case "militaryPoint":
                        return new AddMilitaryPoints(quantity);
                    case "coin":
                        return new AddCoin(quantity);
                    case "wood":
                        return new AddWood(quantity);
                    case "servant":
                        return new AddServants(quantity);
                    case "privilege":
                        return new UsePrivilege(quantity);
                    case "stone":
                        return new AddStone(quantity);
                    case"faithPoint":
                        return new AddFaithPoints(quantity);
                    default:{
                        System.out.println("non ho trovato l'effetto "+ " parameter " + parameter1);
                        return null;
                    }

                }
            }

            //QUI VA USATO ANCHE IL TOTALCOST PERCHè CI SONO GLI SCAMBI DI RISORSE. LE QUANTITY INDICANO LE RISORSE CHE SI RICEVONO DALLO
            // SCAMBIO, IL TOTAL COST QUELLO CHE SI SPENDE PER AVERE LE RISORSE

            case "exchangeRes": //4 PARAMETRI -> POKER
                switch( parameter1 ){
                    case"coinFor":
                        return new ExchangeCoinsFor( quantity, effectCost, parameter2  );
                    case"woodFor":
                        return new ExchangeWoodFor( quantity, effectCost, parameter2 );
                    case"stoneFor":
                        return new ExchangeStoneFor ( quantity, effectCost, parameter2 );
                    case"servantFor":
                        return new ExchangeServantsFor( quantity, effectCost, parameter2 );
                    case"servantsForThreeMilitaryPointsAndOneVictoryPoint":
                        return new ExchangeServantsForThreeMilitaryPointsAndOneVictoryPoints( );
                    case"servantWoodStoneForSixVictoryPoint":
                        return new ServantWoodStoneForSixVictoryPoints();
                    case"faithPointForTwoCoinsAndTwoVictoryPoint":
                        return new FaithPointForTwoCoinsAndTwoVictoryPoints ();
                    default: {
                        System.out.println("non ho trovato l'effetto. type  : " + type + " parameter " + parameter1);
                        return null;
                    }
                }

            case"earnOneCoinForEachColouredCard":
                return new EarnOneCoinForEachColouredCard(parameter1);
            default:
                System.out.println("sono entrato nel default permanent. type : " + type);
                return null;


        }
    }

}