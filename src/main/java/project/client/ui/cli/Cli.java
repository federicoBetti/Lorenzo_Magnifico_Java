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

    public volatile String lineFromKeyBoard = null;
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
        clientSetter.waitingForNewInteraction();
    }

    public void takeDevCard() throws IOException, ClassNotFoundException, InputException {
        context = new TowersContext(this);
    }

    public void loginRequest() throws IOException, ClassNotFoundException {
        clientSetter.loginRequest(lineFromKeyBoard);
    }

    //ok
    public void choseAndTakeDevCard() throws IOException, ClassNotFoundException, InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.takeDevCard(parameters[0], parameters[1], parameters[2]);

    }

    public void chooseProductionParameters() throws IOException, ClassNotFoundException {
    }

    public void chooseHarversterParameters() throws InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        //methodOnClientSetter
    }


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
                } catch ( InputException e ){
                    context.printHelp();
                }

            }
        }
    }
}


