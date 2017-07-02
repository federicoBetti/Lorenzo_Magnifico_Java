package project.messages;

import project.controller.Constants;
import project.messages.updatesmessages.Updates;

/**
 * questa classe la usiamo per notificare a tutti una certa cosa da mettere sulla chat di gioco.
 * todo questa è quella che manderà a tutti gli update e verrà interpretata da gui e cli
 */
public class Notify extends Updates {
    private String notification;

    public Notify (String notification){
        super(Constants.TO_EVERYONE);
        this.notification = notification;
    }

    @Override
    public String toString() {
        return Constants.NOTIFY;
    }

    @Override
    public String toScreen() {
        return notification;
    }
}
