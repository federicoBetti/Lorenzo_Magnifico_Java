package ServerModel;

public class SetFamiliarFixedBonus implements Effects{
    String parameter;
    int quantity;

    SetFamiliarFixedBonus (String parameter, int quantity){
        this.parameter = parameter;
        this.quantity = quantity;
    }

    public BonusInteraction doEffect (Player player){
        switch (parameter){
            case "neutral":
                player.getPedone()[0].setFixedBonus(quantity);
            case "white":
                player.getPedone()[1].setFixedBonus(quantity);
            case "orange":
                player.getPedone()[2].setFixedBonus(quantity);
            case "black":
                player.getPedone()[0].setFixedBonus(quantity);
        }

        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
