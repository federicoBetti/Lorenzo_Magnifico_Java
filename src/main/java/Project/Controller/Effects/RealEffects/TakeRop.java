package Project.Controller.Effects.RealEffects;


public class TakeRop  {
    public TakeRop() {
    }

    public Effects ChooseRoP(String parameter, int quantity){
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
        return null;
    }
}
