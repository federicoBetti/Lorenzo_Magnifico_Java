package project.client.clientexceptions;

/**
 * Created by raffaelebongo on 05/06/17.
 */
public class NotAllowedSelection extends Exception {
    public NotAllowedSelection(Exception e) {
        super(e);
    }
    public NotAllowedSelection(){
        super();
    }
}
