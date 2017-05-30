package Project.Controller.Effects.EffectsFactory;

import Project.Controller.Effects.*;
import Project.Controller.Effects.RealEffects.*;
import Project.Messages.ProductionHarvesterAction;

public class BuildImmediateEffects {

    public BuildImmediateEffects() {
    }

    public Effects SearchImmediateEffects(String type, String parameter, int quantity) {

        switch (type){
        	case "takeRop":
        		return new TakeRop().ChooseRoP(parameter,quantity);

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

        }

       return null;

    }


}








