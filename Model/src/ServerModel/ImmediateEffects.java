package ServerModel;

public class ImmediateEffects {
	
	private int x;
    public ImmediateEffects() {
    }

    public static void SearchImmediateEffects (TrisIE effect, Player player){
        TowerAction TA = new TowerAction();
        SearchImmediateEffects(effect,player,TA);
    }

    public static void SearchImmediateEffects(TrisIE effect, Player player, TowerAction variableToFill) {
    	
        switch ( effect.getType() ){
        	case "takeRos":
        		switch ( effect.getParameter() ){
        			case "wood":
        				addWood( effect, player );
        			case "bauMiao":
        				addStone ( effect, player );
        			case "servants":
        				addServants( effect, player );
        			case "victoryPoints":
        				addVictoryPoints ( effect, player );
        			case "faithPoints":
        				addFaithPoints( effect, player );
        			case "militaryPoints":
        				addMilitaryPoints( effect, player );
        			case "coins":
        				addCoins( effect, player );
        			case "privilege" :
        				//addPrivilege(effect, player); 
        		}
        	
        	case "pointsForEachCardOrMP":
        		switch ( effect.getParameter() ){
        			case "militaryPoints":
        				victoryPointsForEachTwoMilitaryPoints( effect, player );
        			case "greenCard":
        				twoVictoryPointsForEachGreenCard( player );
        			case "purpleCard":
        				twoVictoryPointsForEachPurpleCard(  player ) ;
        			case "yellowCard":
        				twoVictoryPointsForEachYellowCard ( player );
        			case "blueCard":
        				twoVictoryPointsForEachBlueCard ( player );	
        		}
        	
        	case "BonusTowerActionWithDiscount":
        		TowerAction towerA= new TowerAction();
        		towerA = getDiscountForTowerAction ( effect, player, towerA );
        		towerA = getDiceValueForTowerAction ( effect, player, towerA );
        		//mando indietro al client
        	
        	case "BonusTowerAction":
        		TowerAction towerAct= new TowerAction();
        		towerAct = getDiceValueForTowerAction ( effect, player, towerAct );
        		//manda indietro al client
        	
        	case "harvOrProdAct":
        		BonusProductionOrHarvesterAction bph = new BonusProductionOrHarvesterAction ();
        		bph = bonusProdHarv ( effect, player, bph );
        		//mando indietro al client
        }
       
    }
    
    
    public static void addFaithPoints(TrisIE effect, Player player){ //
    	Score score = player.getScore();
    	int faithPoints = score.getFaithPoints();
  		faithPoints += effect.getQuantity();
  		score.setFaithPoints(faithPoints);
  		player.setScore(score);
    }
    
    public static void addWood(TrisIE effect, Player player){  //
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	int wood = pboard.getWood();
    	wood += effect.getQuantity();
    	pboard.setWood(wood);
    	player.setPersonalBoardReference(pboard);
    }
    
    public static void addVictoryPoints(TrisIE effect, Player player){ //
    	Score score = player.getScore();
    	int victoryPoints = score.getVictoryPoints();
    	victoryPoints += effect.getQuantity();
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    }
    
    public static void addMilitaryPoints(TrisIE effect, Player player){ //
    	Score score = player.getScore();
    	int militaryPoints = score.getMilitaryPoints();
    	militaryPoints += effect.getQuantity();
    	score.setMilitaryPoints(militaryPoints);
    	player.setScore(score);
    }
     
    public static void addServants(TrisIE effect, Player player){ //
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	int servants = pboard.getServants();
    	servants += effect.getQuantity();
    	pboard.setServants(servants);
    	player.setPersonalBoardReference(pboard);
    }
    
    public static void addCoins(TrisIE effect, Player player){  //
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	int coins = pboard.getCoins();
    	coins += effect.getQuantity();
    	pboard.setCoins(coins);
    	player.setPersonalBoardReference(pboard);
    }
    
    public static void addStone(TrisIE effect, Player player){ //
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	int stone = pboard.getStone();
    	stone += effect.getQuantity();
    	pboard.setStone(stone);
    	player.setPersonalBoardReference(pboard);
    }
    
    //Public void usePrivilege ( TrisIE effect, Player player ){
    
    //PUNTI VITTORIA IN BASE A CARTE O PUNTI MILIARI POSSEDUTI
    
    public static void victoryPointsForEachTwoMilitaryPoints(TrisIE effect, Player player){
    	Score score = player.getScore();
    	int militaryPoints = score.getMilitaryPoints();
    	int victoryPoints = score.getVictoryPoints();
    	int bonus = militaryPoints / 2;
    	victoryPoints += bonus;
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    }
    
    public static void twoVictoryPointsForEachGreenCard(Player player){
    	int count = 0;
    	Score score = player.getScore();
    	int victoryPoints = score.getVictoryPoints();
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	TerritoryCard[] territories = pboard.getTerritories();
    	for ( count = 0; territories[count] != null; count++ );
    	int bonus = count*2;
    	
    	victoryPoints += bonus;
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    }
    
    public static void twoVictoryPointsForEachPurpleCard(Player player){
    	int count = 0;
    	Score score = player.getScore();
    	int victoryPoints = score.getVictoryPoints();
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	VenturesCard[] ventures = pboard.getVentures();
    	for ( count = 0; ventures[count] != null; count++ );
    	int bonus = count*2;
    	
    	victoryPoints += bonus;
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    }
    
    public static void twoVictoryPointsForEachYellowCard(Player player){
    	int count = 0;
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	BuildingCard[] buildings = pboard.getBuildings();
    	Score score = player.getScore();
    	int victoryPoints = score.getVictoryPoints();
    	for ( count = 0; buildings[count] != null; count++ );
    	int bonus = count*2;
    	
    	victoryPoints += bonus;
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    }
    
    public static void twoVictoryPointsForEachBlueCard(Player player){
    	int count = 0;
    	Score score = player.getScore();
    	int victoryPoints = score.getVictoryPoints();
    	PersonalBoard pboard = player.getPersonalBoardReference();
    	CharacterCard[] characters = pboard.getCharacters();
    	for ( count = 0; characters[count] != null; count++ );
    	int bonus = count*2;
    	
    	victoryPoints += bonus;
    	score.setVictoryPoints(victoryPoints);
    	player.setScore(score);
    	
    }
    
    //GET DISCOUNT
    
    public static TowerAction getDiscountForTowerAction(TrisIE effect, Player player, TowerAction towerA){
    	
    	if ( towerA.getDiscountedResource1().equals("empty") ){
    		towerA.setDiscountedResource1(effect.getParameter());
    		towerA.setQuantityDiscounted1(effect.getQuantity());
    	}
    	else{
    		towerA.setDiscountedResource2(effect.getParameter());
    		towerA.setQuantityDiscounted2(effect.getQuantity());
    	}
    	return towerA;
    }
    
    public static TowerAction getDiceValueForTowerAction(TrisIE effect, Player player, TowerAction towerA){
    	
    	towerA.setKindOfCard(effect.getParameter());
    	towerA.setNewCardDicevalue(effect.getQuantity());
    	
    	return towerA;
    }
    
    public static BonusProductionOrHarvesterAction bonusProdHarv(TrisIE effect, Player player, BonusProductionOrHarvesterAction bph){
    	
    	bph.setKinfOfAction(effect.getParameter());
    	bph.setDiceValue(effect.getQuantity());
    	
    	return bph;
    }
}








