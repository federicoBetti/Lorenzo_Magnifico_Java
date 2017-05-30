package Project.Server.Network.Socket;

import Project.Server.Network.AbstractServer;
import Project.Server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer extends AbstractServer {
    SocketServer socketServer;
    ServerSocket serverSocket;
    Server server;


    public SocketServer(Server server) throws IOException {
        super(server);
        this.server = server;
        this.socketServer = this;
    }

    public void startServer(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
        System.out.println("Server Socket Started!");
        new RequestHandler().start();
    }

    private class RequestHandler extends Thread {

        @Override
        public void run() {
            System.out.println("i'm waiting for another client...");

            while (true) {
                try {

                    Socket socket = serverSocket.accept();
                    System.out.println("new Socket Request!");
                    SocketPlayerHandler socketPlayerHandler = new SocketPlayerHandler(socketServer, socket);
                    new Thread(socketPlayerHandler).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loginRequest(String nickname, SocketPlayerHandler player) throws IOException {
        server.loginRequest(nickname, player);
    }
}
