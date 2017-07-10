package project.server.network.socket;

import project.server.network.AbstractServer;
import project.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket Server that starts a Thread always managing the new socket client requests.
 */
public class SocketServer extends AbstractServer {
    private SocketServer socketServer;
    private ServerSocket serverSocket;

    /**
     * Constructor
     *
     * @param server server's reference
     */
    public SocketServer(Server server)  {
        super(server);
        this.socketServer = this;
    }

    /**
     * It starts the Server's Thread for receiving the new Socket's client requests.
     * @param serverPort
     * @throws IOException
     */
    public void startServer(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
        new RequestHandler().start();
    }

    private class RequestHandler extends Thread {

        /**
         * Thread for receiving the new Socket's client requests.
         */
        //todo chiudere il socket con finally
        @Override
        public void run() {

            while (true) {
                try {

                    Socket socket = serverSocket.accept();
                    SocketPlayerHandler socketPlayerHandler = new SocketPlayerHandler(socketServer, socket);
                    new Thread(socketPlayerHandler).start();

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
