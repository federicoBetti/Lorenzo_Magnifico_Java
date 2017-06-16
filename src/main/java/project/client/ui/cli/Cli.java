package project.client.ui.cli;

import project.client.ui.AbstractUI;
import project.client.ui.ClientSetter;
import project.client.ui.cli.context.*;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
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

    public void actionOk(){

    }

    public void cantDoAction(){

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
    public void bonusHarvesterParameters(String input) throws InputException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.bonusHarvesterAction(parameters[0]);

    }

    @Override
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) {
        context = new BonusProductionContext(bonusProd, this);
    }

    @Override
    public void bonusProductionParameters(String lineFromKeyBoard) {
        try {
            context.checkValidInput(lineFromKeyBoard);
        } catch (InputException e) {
            e.printStackTrace();
        }
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.bonusProductionAction(parameters);
    }

    @Override
    public void takeBonusCardParameters(String input) throws InputException {
        context.checkValidInput(input);
        String[] parameters = input.split("-");
        clientSetter.takeBonusCardAction(parameters[0], parameters[1]);
    }

    @Override
    public void immediatePriviledgeAction(String input) throws InputException {
        String[] privileges = input.split("-");
        clientSetter.immediatePriviledgeAction(privileges);
    }

    @Override
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction) {
        context = new ImmediatePriviledgesContext(this, privilegesAction);
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

    public void discardLeaderCardContext() {
        context = new DiscardLeaderCardContext(this);
    }

    public void askForPraying() {
        context = new ExcomunicationContext(this);
    }

    public void marketContext() {
        context = new MarketContext(this);
    }

    @Override
    public void loginRequest(String lineFromKeyBoard) throws InputException {
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

    public void choseAndTakeDevCard(String lineFromKeyBoard) throws InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.takeDevCard(parameters[0], parameters[1], parameters[2]);

    }

    //todo aggiustare come parametri giusti la chiamata
    public void chooseProductionParameters(String lineFromKeyBoard) throws InputException {
        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.productionAction(parameters);
    }

    public void chooseHarversterParameters(String lineFromKeyBoard) throws InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.harvesterAction(parameters[0], parameters[1], parameters[2]);
    }

    public void chooseMarketActionParameters(String lineFromKeyBoard) throws InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.marketAction(parameters[0], parameters[1]);
    }

    public void chooseCouncilParameters(String lineFromKeyBoard) throws InputException {

        context.checkValidInput(lineFromKeyBoard);
        String[] parameters = lineFromKeyBoard.split("-");
        clientSetter.councilAction(parameters[0], parameters[1]);
    }

    public void chooseLeaderCardToPlay(String action) throws InputException {
        clientSetter.playLeaderCard(action);
    }

    public void discardLeaderCard(String name) throws InputException {
        clientSetter.discardLeaderCard(name);
    }

    public void prayOrNot(String action) throws InputException {
        context.checkValidInput(action);
        clientSetter.prayOrNot(action);
    }

    public void choosePayment(String payment) throws InputException {
        context.checkValidInput(payment);
        clientSetter.sendChoicePaymentVc(payment);
    }

    public void sendExitToBonusAction() throws InputException {
        clientSetter.sendExitToBonusAction();
    }

    //todo show methods
    public void showTowers() {
        //todo fare il metodo di print per le varie caratteristiche delle carte e per gli effetti
        //clientSetter.getUiBoard().getAllTowers().printTowers();
    }

    private class Keyboard extends Thread {

        @Override
        public void run() {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String lineFromKeyBoard;

                try {
                    lineFromKeyBoard = keyboard.readLine();
                    if (context != null) {
                        context.doAction(lineFromKeyBoard);
                    }
                } catch (InputException e) {
                    context.printHelp();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}


