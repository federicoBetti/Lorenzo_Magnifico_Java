package project.client.ui.cli;

import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.*;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TowerAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by raffaelebongo on 01/06/17.
 */
public class Cli extends AbstractUI {


    ClientSetter clientSetter; //all the operation have to pass across this class
    AbstractContext context;

    public Cli(ClientSetter clientSetter) {
        this.clientSetter = clientSetter;
        context = new ConnectionContext(this);
    }

    //context methods
    @Override
    public void bothPaymentsAvailable() {
        context = new BothPaymentsVentureCardsContext(this);
    }

    public void mainContext() {
        context = new MainContext(this);
    }

    @Override
    public void choicePe() {
        context = new ChoicePeContext(this);
    }

    @Override
    public void sendChoicePe( String input ) throws InputException, IOException, ClassNotFoundException {
        context.checkValidInput(input);
        clientSetter.sendChoicePe(input);
    }

    @Override
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        context = new BonusHarvesterContext(bonusHarv, this);
    }

    @Override
    public void bonusHarvesterParameters(String input) throws InputException, IOException, ClassNotFoundException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.bonusHarvesterAction(parameters[0]);

    }

    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) throws InputException, IOException, ClassNotFoundException {
        context = new BonusProductionContext(bonusProd, this);
    }

    @Override
    public void bonusProductionParameters(String lineFromKeyBoard) throws InputException, IOException, ClassNotFoundException {
        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.bonusProductionAction(parameters);
    }

    public void takeDevCard() throws IOException, ClassNotFoundException, InputException {
        context = new TowersContext(this);
    }

    public void harvester() {
        context = new HarvesterContext(this);
    }

    public void goToCouncil() {
        context = new CouncilContext(this);
    }

    public void production() {
        context = new ProductionContext(this);
    }

    public void leaderCardContext() {
        context = new LeaderCardContext(this);
    }

    public void discardLeaderCardContext(){
        context = new DiscardLeaderCardContext(this);
    }

    public void excomunicationContext() {
        context = new ExcomunicationContext(this);
    }

    public void marketContext() {
        context = new MarketContext(this);
    }

    @Override
    public void loginRequest( String lineFromKeyBoard ) throws IOException, ClassNotFoundException {
        clientSetter.loginRequest(lineFromKeyBoard);
    }

    @Override
    public void startUI() {
        new Keyboard().start();
    }

    @Override
    public void takeBonusCard(TowerAction towerAction) {
        context = new TakeBonusCard(this, towerAction);
        context.printHelp();
    }

    public void choseAndTakeDevCard( String lineFromKeyBoard ) throws IOException, ClassNotFoundException, InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.takeDevCard(parameters[0], parameters[1], parameters[2]);

    }

    //todo aggiustare come parametri giusti la chiamata
    public void chooseProductionParameters(String lineFromKeyBoard) throws IOException, ClassNotFoundException, InputException {
        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.productionAction(parameters);
    }

    public void chooseHarversterParameters( String lineFromKeyBoard ) throws InputException, IOException, ClassNotFoundException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.harvesterAction(parameters[0], parameters[1], parameters[2]);
    }

    public void chooseMarketActionParameters( String lineFromKeyBoard ) throws InputException, IOException, ClassNotFoundException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.marketAction(parameters[0], parameters[1]);
    }

    public void chooseCouncilParameters( String lineFromKeyBoard ) throws InputException, IOException, ClassNotFoundException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.councilAction(parameters[0], parameters[1]);
    }

    public void chooseLeaderCardToPlay(String action) throws IOException, ClassNotFoundException {
        clientSetter.playLeaderCard(action);
    }

    public void discardLeaderCard(String name) throws IOException, ClassNotFoundException {
        clientSetter.discardLeaderCard(name);
    }

    public void prayOrNot( String action ) throws IOException, ClassNotFoundException {
        try {
            context.checkValidInput(action);
        } catch (InputException e) {
            e.printStackTrace();
        }
        clientSetter.prayOrNot(action);
    }

    public void choosePayment(String action) {
        try {
            context.checkValidInput(action);
        } catch (InputException e) {
            context.printHelp();
        }

    }

    public void showTowers() {
        //todo fare il metodo di print per le varie caratteristiche delle carte e per gli effetti
        //clientSetter.getUiBoard().getAllTowers().printTowers();
    }

    public void sendExitToBonusAction() throws IOException, ClassNotFoundException {
        clientSetter.sendExitToBonusAction();
    }

    private class Keyboard extends Thread {

        @Override
        public void run() {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {

                    String lineFromKeyBoard = keyboard.readLine();
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


