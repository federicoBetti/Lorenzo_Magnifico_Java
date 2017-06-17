package project.server.network.socket;

import project.server.network.AbstractServer;
import project.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer extends AbstractServer {
    SocketServer socketServer;
    ServerSocket serverSocket;


    public SocketServer(Server server) throws IOException {
        super(server);
        this.socketServer = this;
    }

    public void startServer(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
        System.out.println("server socket started!");
        new RequestHandler().start();
    }

    private class RequestHandler extends Thread {

        /**
         * todo chiudere il socket con finally
         */
        @Override
        public void run() {
            System.out.println("i'm waiting for another client...");

            while (true) {
                try {

                    Socket socket = serverSocket.accept();
                    System.out.println("new socket request!");
                    SocketPlayerHandler socketPlayerHandler = new SocketPlayerHandler(socketServer, socket);
                    new Thread(socketPlayerHandler).start();

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
