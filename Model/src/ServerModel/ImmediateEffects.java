package ServerModel;

public class ImmediateEffects {

    public ImmediateEffects() {
    }

    public Effects SearchImmediateEffects(String type, String parameter, int quantity) {

        switch (type){
        	case "takeRop":

        		switch (parameter){
        			case "wood":
        				return new AddWood(quantity);
        			case "stone":
        				return new AddStone(quantity);
        			case "servants":
        				return new AddServants(quantity);
        			case "victoryPoints":
        				return new AddVicotryPoints(quantity);
        			case "faithPoints":
        				return new AddFaithPoints(quantity);
        			case "militaryPoints":
        				return new AddMilitaryPoints(quantity);
        			case "coins":
        				return new AddCoin(quantity);
        			case "privilege" :
        				//addPrivilege(effect, player);
        		}
        	
        	case "pointsForEachCardOrMP":
        		switch (parameter){
        			case "militaryPoints":
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
        	
        	case "BonusTowerActionWithDiscount":
        	    return new BonusTowerAction (parameter, quantity);

        	
        	case "harvOrProdAct": {
                return new ProductionHarvesterAction(parameter, quantity);
            }

            case "setFamiliarFixedValue":
                return new SetFamiliarFixedValue (parameter, quantity);

            case "setFamiliarFixedBonus":
                return new SetFamiliarFixedBonus (parameter, quantity);

            case "setLeaderEffectsUseful":
                return new SetLeaderEffectsUseful(parameter);


        }
       return new AddCoin(quantity); // inutile
    }


}








