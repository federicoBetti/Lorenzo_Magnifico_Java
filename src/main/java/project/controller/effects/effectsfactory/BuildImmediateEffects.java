package project.controller.effects.effectsfactory;

import project.controller.effects.*;
import project.controller.effects.realeffects.*;

//todo rifare con HashMap
public class BuildImmediateEffects {

    public Effects searchImmediateEffects(String type, String parameter, int quantity) {

        switch (type){
        	case "takeRop":
        		return new TakeRop().chooseRoP(parameter,quantity);

        	case "pointsForEachCardOrMP":
        		switch (parameter){
        			case "militaryPoint":
        				return new VictoryPointsForEachTwoMilitaryPoints();
        			case "greenCard":
        				return new TwoVictoryPointsForEachGreenCard();
        			case "purpleCard":
        				return new VictoryPointsForEachPurpleCard(quantity);
        			case "yellowCard":
        				return new TwoVictoryPointsForEachYellowCard();
        			case "blueCard":
        				return new VictoryPointForEachBlueCard(quantity);
        		}

        		//Riguardare questo metodo
			case "bonusTowerAction":
				return new BonusTowerAction (parameter, quantity);
			case "yellowBonustowerActionValue6WithDiscount":
				return new BonusTowerActionYellow(parameter,quantity);
			case"bluBonustowerActionValue6WithDiscount":
				return new BonusTowerActionBlue(parameter, quantity);

			case"privilege":
				return new UsePrivilege(quantity);

			case "harvOrProdAct":
				return new ProductionHarvesterAction(parameter, quantity);

			// questi tre hanno uno switch interni da sistemare
			case "reduceValueOfAction":
				return new ReduceValueOnAction(parameter, quantity);

				//Cosa Sono?
            case "setFamiliarFixedValue":
                return new SetFamiliarFixedValue(parameter, quantity);
            case "setFamiliarFixedBonus":
                return new SetFamiliarFixedBonus(parameter, quantity);
            case "setLeaderEffectsUseful":
                return new SetLeaderEffectsUseful(parameter);
            case "setExcommunicationCardUseful":
                return new SetExcommunicationCardUseful(parameter);

			case"councilSpecial":
				return new AddWoodAndStone(quantity);
            case"marketSpecial":
                return new AddMilitaryPointsAndCoins();
            default:
            	System.out.println("effetto market nullo");
                return null;


        }

    }


}








