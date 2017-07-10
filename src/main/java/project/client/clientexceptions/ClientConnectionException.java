package project.client.clientexceptions;

import java.io.IOException;

/**
 * Exception due to errors in client connection
 */
public class ClientConnectionException extends IOException {

    /**
     * Constructor
     *
     * @param e exception's variable
     */
    public ClientConnectionException(Exception e) {
        super(e);
    }
}
