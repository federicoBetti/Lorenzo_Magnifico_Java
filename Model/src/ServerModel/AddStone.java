package ServerModel;


public class AddStone implements Effects {
    private int quantity;

    public AddStone (int quantity){
        this.quantity = quantity;
    }


    @Override
    public BonusInteraction doEffect(Player player) {
        player.getPersonalBoardReference().setStone(player.getPersonalBoardReference().getStone() + quantity);
        OkOrNo Ok = new OkOrNo();
        Ok.setOk(true);
        return Ok;
    }
}
