package project.client.ui;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.SingletonKeyboard;
import project.client.network.AbstractClient;
import project.client.clientexceptions.ClientConnectionException;
import project.client.network.rmi.RMIClient;
import project.client.network.socket.SocketClient;
import project.client.ui.cli.Cli;
import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.messages.BonusProductionOrHarvesterAction;
import project.messages.TakePrivilegesAction;
import project.messages.TowerAction;
import project.messages.updatesmessages.ExcommunicationTaken;
import project.messages.updatesmessages.Updates;
import project.model.*;

import java.io.IOException;
import java.util.List;

/**
 * this class is the "bridge" between user interface and client.
 */
public class ClientSetter {
    private AbstractClient client;
    private AbstractUI ui;

    private Board uiBoard;
    private PersonalBoard uiPersonalBoard;
    private Score uiScore;
    private FamilyMember[] uiFamilyMembers;
    private String nickname;

    /**
     * Constructor
     *
     * @param kindOfUI kind of ui string
     */
    public ClientSetter(String kindOfUI ) {
        uiBoard = new Board();
        uiPersonalBoard = new PersonalBoard();
        uiScore = new Score();
        uiFamilyMembers = new FamilyMember[4];

        if ( kindOfUI.equals("CLI"))
            ui = new Cli(this);
        else
            ui = new Gui(this);
        ui.startUI();
    }


    /**
     * This method is used for asking to the player if he wants to play with the Cli or Gui user interface
     *
     * @return the string that represents the choice
     */
    private static String selectUi() {

        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.YELLOW);
        UnixColoredPrinter p = new UnixColoredPrinter(builder);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.BLUE);
        UnixColoredPrinter p1 = new UnixColoredPrinter(builder);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.RED);
        UnixColoredPrinter p2 = new UnixColoredPrinter(builder);
        UnixColoredPrinter.Logger logger = new UnixColoredPrinter.Logger();
        logger.toString();
        while (true) {
            p1.println("Welcome to LORENZO IL MAGNIFICO!");
            p2.println("Please choose the kind of User interface experience between: ");
            p.println("1: CLI");
            p.println("2: GUI");
            String choice;
            int choiceNum = 0;
            try {
                choice = SingletonKeyboard.readLine();

                for (int i = 0; i < choice.length(); i++)
                    if (!(Character.isDigit(choice.charAt(i))))
                        continue;

                choiceNum = Integer.parseInt(choice);

            } catch (IOException e) {
                UnixColoredPrinter.Logger.print(Constants.IO_EXCEPTION);
            } catch (NumberFormatException e) {
                UnixColoredPrinter.Logger.print("wrong choice");
                continue;
            }
            switch (choiceNum) {
                case 1:
                    return "CLI";
                case 2:
                    return "GUI";
                default:

            }
        }
    }

    /**
     * main methods
     *
     * @param args args
     */
    public static void main(String[] args) {
        new ClientSetter(selectUi());
    }


    /**
     * This method set the connection type
     *
     * @param connectionType connection tyoe
     * @param IP ip choosen
     */
    public void setConnectionType(String connectionType, String IP) {
        switch (connectionType) {
            case "socket":
                try {
                    this.client = new SocketClient(this, IP);
                } catch (ClientConnectionException e) {
                    System.out.println("errore di connessione");
                }
                break;
            case "RMI":
                try {
                    this.client = new RMIClient(this, IP);
                } catch (ClientConnectionException e) {
                    System.out.println("errore di connessione RMI");
                }
                break;
            default:
                break;
        }
    }


    /**
     * Save the nickname in a variable and call loginRequest on the client
     *
     * @param loginParameter login name as a string
     */
    public void loginRequest(String loginParameter){
        this.nickname = loginParameter;
        client.loginRequest(loginParameter);
    }

    /**
     * This method calls takeDevCard on the client
     *
     * @param towerColour tower colour as a string
     * @param floor floor number as an int
     * @param familiarColour familiar colour as a string
     */
    public void takeDevCard(String towerColour, int floor, String familiarColour )  {
        client.takeDevCard(towerColour, floor, familiarColour);
    }

    /**
     * This method calls harvesterAction on the client
     *
     * @param familyMemberColour familiar colour as a string
     * @param servantsNumber servants number as an int
     */
    public void harvesterAction( String familyMemberColour, int servantsNumber)  {
        client.harvesterAction(familyMemberColour, servantsNumber);
    }

    /**
     * This method calls marketAction on the client
     *
     * @param position the position in the market as an int
     * @param familyColour familiar colour as an int
     */
    public void marketAction(int position, String familyColour)  {
        client.marketAction(position, familyColour);
    }

    /**
     * This method calls councilAction on the client
     *
     * @param priviledgeNumber privilege number choosen as an int
     * @param familiarColour familiar colour as a String
     */
    public void councilAction(int priviledgeNumber, String familiarColour )  {
        client.councilAction( priviledgeNumber, familiarColour);
    }

    /**
     * This method calls productionAction on the client
     *
     * @param familiarColor familiar color as a String
     * @param buidingCards list of building cards on which the player wants to perform the production
     */
    public void productionAction(String familiarColor, List<String> buidingCards)   {
        client.productionAction(familiarColor,buidingCards);
    }

    /**
     * This method calls takeBonusCardAction on the client
     *
     * @param floor floor's number as an int
     * @param towerColour towerColour as a string
     */
    public void takeBonusCardAction(int floor, String towerColour )  {
        System.out.println("il thead per l'azione bonus è partito");
        client.takeBonusCardAction(floor, towerColour);
    }

    /**
     * This method calls playLeaderCard on the client
     *
     * @param leaderCardName the name of the leader that the player wants to play
     */
    public void playLeaderCard(String leaderCardName)   {
        client.playLeaderCard(leaderCardName);
    }

    /**
     * This method calls discardLeaderCard on the client
     *
     * @param name the name of the leader that the player wants to discard
     */
    public void discardLeaderCard(String name)   {
        client.discardLeaderCard(name);
    }

    /**
     * This method calls sendExitToBonusAction on the client
     */
    public void sendExitToBonusAction()   {
        client.sendExitToBonusAction();
    }

    /**
     * This method calls bonusHarvesterAction on the client
     *
     * @param servantsNumber number of servants that the player wants to use for the bonus harvester
     */
    public void bonusHarvesterAction(int servantsNumber)   {
        client.bonusHarvesterAction( servantsNumber );
    }

    /**
     * This method calls immediatePriviledgeAction on the client
     *
     * @param privileges list of the integers that represents the provileges that the player wants to take
     */
    public void immediatePriviledgeAction(List<Integer> privileges)  {
        client.immediatePriviledgeAction( privileges );
    }

    /**
     * This method calls bonusProductionAction on the client
     *
     * @param buildingCards list of building cards on which the client wants to perform the production
     */
    public void bonusProductionAction(List<String> buildingCards)   {
        client.bonusProductionAction(buildingCards);
    }

    /**
     * This method calls bonusProductionAction on the client
     */
    public void skipTurn() {
        client.skipTurn();

    }

    /**
     * This method calls mainContext on the ui
     */
    public void itsMyTurn() {
        ui.mainContext();
    }

    /**
     * This method calls takeBonusCard on the ui
     *
     * @param towerAction the object that contains all bonus action's characteristics
     */
    public void takeBonusCard(TowerAction towerAction ){
        ui.takeBonusCard(towerAction);
    }

    /**
     * This method calls choicePe on the ui
     *
     * @return the int that represent the choice
     */
    public int choicePe() {
        return ui.choicePe();

    }

    /**
     * This method calls bonusHarvester on the ui
     *
     * @param bonusHarv the object that contains all bonus action's characteristics
     */
    public void bonusHarvester(BonusProductionOrHarvesterAction bonusHarv) {
        ui.bonusHarvester(bonusHarv);
    }

    /**
     * This method calls bonusProduction on the ui
     *
     * @param bonusProd the object that contains all bonus action's characteristics
     */
    public void bonusProduction(BonusProductionOrHarvesterAction bonusProd) {
        ui.bonusProduction(bonusProd);
    }

    /**
     * This method calls askForPraying on the ui
     *
     * @return the int that represent the choice
     */
    public int askForPraying(){
        return ui.askForPraying();
    }

    /**
     * This method calls actionOk on the ui
     */
    public void actionOk() {
        ui.actionOk();
    }

    /**
     * This method calls cantDoAction on the ui
     */
    public void cantDoAction() {
        ui.cantDoAction();
    }

    /**
     * This method calls takeImmediatePrivilege on the ui
     *
     * @param privilegesAction the object that contains all bonus action's characteristics
     */
    public void takeImmediatePrivilege(TakePrivilegesAction privilegesAction)  {
        ui.takeImmediatePrivilege(privilegesAction);
    }

    /**
     * This method calls the nicknameAlreadyUsed on the ui
     */
    public void nicknameAlreadyUsed() {
        ui.nicknameAlreadyUsed();
    }

    /**
     * This method updates the score variable in this class with the value contained in "update" and call the
     * scoreUpdate method on the ui
     *
     * @param update the object that contains current value of the variables that has to be updated
     */
    public synchronized void scoreUpdate(Updates update) {
        uiScore = update.doUpdate(uiScore);
        ui.scoreUpdate(update);
    }

    /**
     * This method updates the personalBoard variable in this class with the value contained in "update" and call the
     * personalBoardUpdate method on the ui
     *
     * @param update the object that contains current value of the variables that has to be updated
     */
    public synchronized void personalBoardUpdate(Updates update) {
        uiPersonalBoard = update.doUpdate(uiPersonalBoard);
        ui.personalBoardUpdate(update);
    }

    /**
     * This method updates the familyMember variable in this class with the value contained in "update" and call the
     * familyMemberUpdate method on the ui
     *
     * @param update the object that contains current value of the variables that has to be updated
     */
    public synchronized void familyMemberUpdate(Updates update) {
        uiFamilyMembers = update.doUpdateFamilyMembers();
        ui.familyMemberUpdate(update);
    }

    /**
     * This method updates the board variable in this class with the value contained in "update" and call the
     * boardUpdate method on the ui
     *
     * @param update the object that contains current value of the variables that has to be updated
     */
    public synchronized void boardUpdate(Updates update) {
        update.doUpdate(uiBoard);
        ui.boardUpdate(update);
    }


    /**
     * Get the board variable
     * @return the board variable
     */
    public Board getUiBoard() {
        return uiBoard;
    }

    /**
     * Get the personal board variable
     *
     * @return the personal board varibale
     */
    public PersonalBoard getUiPersonalBoard() {
        return uiPersonalBoard;
    }

    /**
     * Get the score variable
     *
     * @return the score variable
     */
    public Score getUiScore() {
        return uiScore;
    }

    /**
     * Get the uiFamilyMebers variable
     *
     * @return the uifamilyMembers variable
     */
    public FamilyMember[] getUiFamilyMembers() {
        return uiFamilyMembers;
    }


    /**
     * This method calls the waitingForYourTurn method on the ui
     */
    public void waitingForYourTurn() {
        ui.waitingForYourTurn();
    }

    /**
     * This method calls the goToLogin method on the ui
     */
    public void goToLogin() {
        ui.goToLogin();
    }

    /**
     * Get the client
     *
     * @return the client variable
     */
    public AbstractClient getClient() {
        return client;
    }

    /**
     * Set the client
     *
     * @param client client variable to set
     */
    public void setClient(AbstractClient client) {
        this.client = client;
    }

    /**
     * This method calls the loginSucceded on the ui
     */
    public void loginSucceded() {
        ui.loginSucceded();
    }

    /**
     * This method calls the bothPaymentsAvailable method on the ui
     *
     * @return the int that represent the choice
     */
    public int bothPaymentsAvailable() {
        return  ui.bothPaymentsAvailable();
    }

    /**
     * This method calls the timerDelayed method on the ui
     */
    public void timerTurnDelayed() {
        ui.timerDelayed();
    }

    /**
     * Get a specific leader card from a list calling getLeaderCardChosen on the ui
     *
     * @param leaders list of leader cards
     * @return leader card choosen's name
     */
    public String getLeaderCardChosen(List<LeaderCard> leaders) {
        return ui.getLeaderCardChosen(leaders);
    }

    /**
     * This method calls the tileDraft method on the ui
     *
     * @param tiles list of tiles
     * @return the int that represent the choice
     */
    public int tileDraft(List<Tile> tiles) {
        return ui.tileDraft(tiles);
    }

    /**
     * This method calls the matchStarted method on the ui
     *
     * @param roomPlayers the number of players in the room
     * @param familyColour colour of the family releted to the player
     */
    public void matchStarted(int roomPlayers, String familyColour) {
        ui.matchStarted(roomPlayers,familyColour);
    }


    /**
     * This method calls the newNickname method on the client
     *
     * @param username player's username
     */
    public void newNickname(String username ) {
        client.newNickname( username );
    }

    /**
     * This method calls the prayed method on the ui
     */
    public void prayed() {
        ui.prayed();
    }

    /**
     * This method calls the excommunicationTaken method on the ui
     *
     * @param update the object that contains current value of the variables that has to be updated
     */
    public void excommunicationTake(Updates update) {
        ui.excommunicationTaken((ExcommunicationTaken)update);
    }

    /**
     * This method calls recconnect method on the client
     */
    public void reconnect() {
        client.reconnect();
    }

    /**
     * This method calls afterGame method on the ui
     */
    public void afterGame() {
        ui.afterGame();
    }

    /**
     * This method calls showStatistic method on the client
     */
    public void showStatistic() {
        client.showStatistic();
    }

    /**
     * This method calls newGameRequest method on the client
     */
    public void newGameRequest() {
        client.newGameRequest(nickname);
    }

    /**
     * This method makes the program terminates
     */
    public void terminate() {
        System.exit(1);
    }

    /**
     * This method calls receiveStatistics method on the ui
     *
     * @param statistics PlayerFile.json object that contains all the information about all the games played by the client
     */
    public void receiveStatistics(PlayerFile statistics) {
        ui.receiveStatistics(statistics);
    }

    /**
     * This method calls showRanking on the client
     */
    public void showRanking() {
        client.showRanking();
    }

    /**
     * This method calls ranking method on the ui
     *
     * @param ranking list of player file ordered as a ranking
     */
    public void ranking(List<PlayerFile> ranking) {
        ui.ranking(ranking);
    }

    public String getNickname() {
        return nickname;
    }
    /**
     * This method calls disconnesionMessage method on the ui
     *
     * @param message message of disconnession as a string
     */
    public void disconnessionMessage(String message) {
        ui.disconnesionMessage(message);
    }

    /**
     * This method calls winnerComunication method on the ui
     *
     * @param winner string with the winner's name
     */
    public void winnerComunication(String winner) {
        ui.winnerComunication(winner);
    }
}
