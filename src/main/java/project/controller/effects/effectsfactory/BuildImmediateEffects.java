package project.controller.effects.effectsfactory;

import project.controller.effects.*;
import project.controller.effects.realeffects.*;
import project.controller.effects.realeffects.ProductionHarvesterAction;

//todo rifare con HashMap
public class BuildImmediateEffects {

    public Effects SearchImmediateEffects(String type, String parameter, int quantity) {

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
			case "bonusTowerActionWithDiscount":
				return new BonusTowerAction (parameter, quantity);
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
            default:
                return null;

        }

    }


}








