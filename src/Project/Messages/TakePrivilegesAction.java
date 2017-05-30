package Project.Messages;

/**
 * Created by federico on 24/05/17.
 */
public class TakePrivilegesAction extends BonusInteraction {
    int quantityOfDifferentPrivileges;
    public TakePrivilegesAction(int quantityOfDifferentPrivileges){
        this.quantityOfDifferentPrivileges = quantityOfDifferentPrivileges;
    }
}
