package project.controller.effects.realeffects;

/**
 * This class conatins all the effects that has as type "TakeRop"
 */
public class TakeRop  {

    /**
     * Creates the right effect using the parameters
     *
     * @param parameter kind of resource to add
     * @param quantity to add
     * @return the right effect's object
     */
    public Effects chooseRoP(String parameter, int quantity){
        switch (parameter) {
            case "wood":
                return new AddWood(quantity);
            case "stone":
                return new AddStone(quantity);
            case "servant":
                return new AddServants(quantity);
            case "victoryPoint":
                return new AddVictoryPoints(quantity);
            case "faithPoint":
                return new AddFaithPoints(quantity);
            case "militaryPoint":
                return new AddMilitaryPoints(quantity);
            case "coin":
                return new AddCoin(quantity);
            case "privilege":
                return new UsePrivilege(quantity);
            default:
                return null;
        }
    }
}
