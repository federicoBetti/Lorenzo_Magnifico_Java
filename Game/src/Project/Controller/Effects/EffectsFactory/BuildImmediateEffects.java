package Project.Controller.Effects.EffectsFactory;

import Project.Controller.Effects.*;
import Project.Controller.Effects.RealEffects.*;
import Project.Controller.MessageObjects.ProductionHarvesterAction;

public class BuildImmediateEffects {

    public BuildImmediateEffects() {
    }

    public Effects SearchImmediateEffects(String type, String parameter, int quantity) {

        switch (type){
        	case "takeRop":

        		switch (parameter){
        			case "wood":
        				return new AddWood(quantity);
        			case "stone":
        				return new AddStone(quantity);
        			case "servant":
        				return new AddServants(quantity);
        			case "victoryPoint":
        				return new AddVicotryPoints(quantity);
        			case "faithPoint":
        				return new AddFaithPoints(quantity);
        			case "militaryPoint":
        				return new AddMilitaryPoints(quantity);
        			case "coins":
        				return new AddCoin(quantity);
        			case "privilege" :
        				//addPrivilege(effect, player);
        		}

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








