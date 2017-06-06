package project.client.ui.cli;

import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.AbstractContext;
import project.client.ui.cli.context.LoginContext;
import project.client.ui.cli.context.MainContext;
import project.client.ui.cli.context.TowersContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by raffaelebongo on 01/06/17.
 */
public class Cli extends AbstractUI {

    ClientSetter clientSetter; //all the operation have to pass across this class
    AbstractContext context;
    MessagesFromServerHandler messageHandler;


    public Cli(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
        messageHandler = new MessagesFromServerHandler(this);
        context = new LoginContext(this);
    }

    @Override
    public void startUI() {
        new Keyboard().start();
    }

    public void loginRequest(String loginParameter) throws IOException, ClassNotFoundException {
        clientSetter.loginRequest(loginParameter);
    }

    @Override
    public void handleMessage(String message) throws IOException, ClassNotFoundException {
        messageHandler.handleMessage(message);
    }

    public void mainContext() {
        context = new MainContext(this);
    }

    public void waitingForNewInteraction() throws IOException, ClassNotFoundException {
        clientSetter.waitingForNewIntraction();
    }

    public void takeDevCard() {
        context = new TowersContext(this);
    }

    //todo fare mappa con help per la schemata iniziale con i vari contesti disponibili ( all'inizio solo login )

    private class Keyboard extends Thread {

        @Override
        public void run() {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try {
                    String line = null;
                    line = keyboard.readLine();
                    if (context != null) {
                        context.doAction(line);
                    }
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


