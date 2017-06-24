package project.controller.effects.realeffects;


public class TakeRop  {

    public Effects chooseRoP(String parameter, int quantity){
        switch (parameter) {
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
            case "coin":
                return new AddCoin(quantity);
            case "privilege":
                return new UsePrivilege(quantity);
            default:
                return null;
        }
    }
}
