package project.client.clientexceptions;

import java.io.IOException;

/**
 * Created by federico on 06/06/17.
 */
public class ClientConnectionException extends IOException {
    public ClientConnectionException(Exception e) {
        super(e);
    }
}
