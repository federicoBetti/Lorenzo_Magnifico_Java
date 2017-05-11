package ServerModel;

public interface Effects {
    int quantity = 0;
    BonusInteraction doEffect(Player player);
}
