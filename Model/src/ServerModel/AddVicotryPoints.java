package ServerModel;


public class AddVicotryPoints implements Effects {

    private int quantity;

    public AddVicotryPoints (int quantity){
        this.quantity = quantity;
    }

    public BonusInteraction doEffect(Player player) {
        player.getScore().setVictoryPoints(player.getScore().getVictoryPoints() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
