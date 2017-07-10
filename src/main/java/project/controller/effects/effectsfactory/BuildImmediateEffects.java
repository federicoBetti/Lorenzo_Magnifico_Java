package project.controller.effects.effectsfactory;

import project.controller.effects.realeffects.*;

import java.util.HashMap;

/**
 * This class is used for building the immediate effects taking parameters from Json's files
 */
public class BuildImmediateEffects {

	private HashMap<String, typeBuilder> typeHashMap;
	private HashMap<String, PointsForEachCardBuilder> pointsForEachCardHasMap;

	public BuildImmediateEffects(){
		pointsForEachCardHasMap = new HashMap<>();
		typeHashMap = new HashMap<>();
		fillPointsForEachCardHasMap();
		fillTypeHasMap();
	}
	/**
	 * Search and build the immediate effects
	 *
	 * @param type of effect
	 * @param parameter that specify the effect
	 * @param quantity that specify the effect
	 * @return the right effects
	 */
    public Effects searchImmediateEffects(String type, String parameter, int quantity) {
    	return typeHashMap.get(type).searchType(parameter, quantity);

    }

    private void fillTypeHasMap(){
		typeHashMap.put("takeRop" , this::takeRop);
		typeHashMap.put("pointsForEachCardOrMP" , this::pointsForEachCardOrMP);
		typeHashMap.put("bonusTowerAction" , this::bonusTowerAction);
		typeHashMap.put("yellowBonustowerActionValue6WithDiscount" , this::yellowBonustowerActionValue6WithDiscount);
		typeHashMap.put("bluBonustowerActionValue6WithDiscount" , this::bluBonustowerActionValue6WithDiscount);
		typeHashMap.put("privilege" , this::privilege);
		typeHashMap.put("harvOrProdAct" , this::harvOrProdAct);
		typeHashMap.put("reduceValueOfAction" , this::reduceValueOfAction);
		typeHashMap.put("councilSpecial" , this::councilSpecial);
		typeHashMap.put("marketSpecial" , this::marketSpecial);
		typeHashMap.put("bonusHarvesterOrProduction" , this::bonusHarvesterOrProduction);
		typeHashMap.put("discountForTakingYellowCard" , this::discountForTakingYellowCard);
		typeHashMap.put("discountForTakingBlueCard" , this::discountForTakingBlueCard);
		typeHashMap.put("increaseDiceValueForTakingCard" , this::increaseDiceValueForTakingCard);
		typeHashMap.put("noImmediateEffectTwoHighestPositionsTower" , this::noImmediateEffectTwoHighestPositionsTower);
	}

	private void fillPointsForEachCardHasMap(){
		pointsForEachCardHasMap.put("militaryPoint",this::militaryPoint);
		pointsForEachCardHasMap.put("greenCard",this::greenCard);
		pointsForEachCardHasMap.put("purpleCard",this::purpleCard);
		pointsForEachCardHasMap.put("yellowCard",this::yellowCard);
		pointsForEachCardHasMap.put("blueCard",this::blueCard);
	}

	private Effects blueCard(int quantity) {
		return new VictoryPointForEachBlueCard(quantity);
	}

	private Effects yellowCard(int quantity) {
		return new TwoVictoryPointsForEachYellowCard();
	}

	private Effects purpleCard(int quantity) {
		return new VictoryPointsForEachPurpleCard(quantity);
	}

	private Effects greenCard(int quantity) {
		return new TwoVictoryPointsForEachGreenCard();
	}

	private Effects militaryPoint(int quantity) {
		return new VictoryPointsForEachTwoMilitaryPoints();
	}

	private interface PointsForEachCardBuilder {
		/**
		 * method that search in PointsForEachCardBuilder effects
		 * @param quantity parameter
		 * @return effect chosen
		 */
    	Effects getEffect(int quantity);
	}
	private Effects pointsForEachCardOrMP(String s, int i) {
		return pointsForEachCardHasMap.get(s).getEffect(i);
	}

	private Effects noImmediateEffectTwoHighestPositionsTower(String parameter, int quantity) {
		return new NoImmediateEffectTwoHighestPositionsTower();
	}

	private Effects increaseDiceValueForTakingCard(String parameter, int quantity) {
		return new IncreaseDicevalueForTakingCards( quantity, parameter );
	}

	private Effects discountForTakingBlueCard(String parameter, int quantity) {
		return new DiscountForTakingBlueCards(quantity, parameter);
	}

	private Effects discountForTakingYellowCard(String parameter, int quantity) {
		return new DiscountForTakingYellowCards(quantity, parameter);
	}

	private Effects bonusHarvesterOrProduction(String parameter, int quantity) {
		return new IncreaseDiceValueForHoP(quantity, parameter );
	}

	private Effects marketSpecial(String parameter, int quantity) {
		return new AddMilitaryPointsAndCoins();
	}

	private Effects councilSpecial(String parameter, int quantity) {
		return new AddWoodAndStone(quantity);
	}

	private Effects reduceValueOfAction(String parameter, int quantity) {
    	return new ReduceValueOnAction(parameter, quantity);
	}

	private Effects harvOrProdAct(String parameter, int quantity) {
		return new ProductionHarvesterAction(parameter, quantity);
	}

	private Effects privilege(String parameter, int quantity) {
		return new UsePrivilege(quantity);
	}

	private Effects bluBonustowerActionValue6WithDiscount(String parameter, int quantity) {
		return new BonusTowerActionBlue(parameter, quantity);
	}

	private Effects yellowBonustowerActionValue6WithDiscount(String parameter, int quantity) {
		return new BonusTowerActionYellow(parameter,quantity);
	}

	private Effects bonusTowerAction(String parameter, int quantity) {
		return new BonusTowerAction (parameter, quantity);
	}

	private Effects takeRop(String parameter, int quantity) {
		return new TakeRop().chooseRoP(parameter, quantity);
	}

	private interface typeBuilder{
		/**
		 * method that search type
		 * @param parameter parameter
		 * @param quantity quantity
		 * @return effect
		 */
		Effects searchType(String parameter, int quantity);
	}


}








