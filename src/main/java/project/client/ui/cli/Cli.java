package project.client.ui.cli;

import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.AbstractContext;
import project.client.ui.cli.context.LoginContext;
import project.client.ui.cli.context.MainContext;
import project.client.ui.cli.context.TowersContext;
import project.messages.TowerAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by raffaelebongo on 01/06/17.
 */
public class Cli extends AbstractUI {

    public String lineFromKeyBoard = null;
    ClientSetter clientSetter; //all the operation have to pass across this class
    AbstractContext context;


    public Cli(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
        context = new LoginContext(this);
    }

    @Override
    public void startUI() {
        new Keyboard().start();
    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {

    }

    public void mainContext() {
        context = new MainContext(this);
    }

    public void waitingForNewInteraction() throws IOException, ClassNotFoundException {
        clientSetter.waitingForNewIntraction();
    }

    public void takeDevCard() throws IOException, ClassNotFoundException {
        context = new TowersContext(this);
    }

    public void loginRequest() throws IOException, ClassNotFoundException {
        clientSetter.loginRequest(lineFromKeyBoard);
    }

    public void choseAndTakeDevCard() throws IOException, ClassNotFoundException {
        //insert these parameters for taking the card: [towerColour-floor(int)-familyColour];

        String[] parameters = lineFromKeyBoard.split("-");
        if( parameters.length == 3 )
            clientSetter.takeDevCard(parameters);
    }

    public void chooseProductionParameters() throws IOException, ClassNotFoundException {

    }



    //todo fare mappa con help per la schemata iniziale con i vari contesti disponibili ( all'inizio solo login )

    private class Keyboard extends Thread {

        @Override
        public void run() {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try {

                    lineFromKeyBoard = keyboard.readLine();
                    if (context != null) {
                        context.doAction(lineFromKeyBoard);
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


