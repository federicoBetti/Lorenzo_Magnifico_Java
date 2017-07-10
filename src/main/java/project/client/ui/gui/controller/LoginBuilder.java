package project.client.ui.gui.controller;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.server.PlayerFile;
import project.PrinterClass.UnixColoredPrinter;
import project.client.ui.cli.CliConstants;
import project.controller.cardsfactory.LeaderCard;
import project.model.Score;
import project.model.Tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * main gui class. this is the Application class that host the gui application. there are instance of all scene and all controllers
 * used to switch between scene in gui.
 */
public class LoginBuilder extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;
    private AnchorPane initialLoginScene;
    private AnchorPane waitingLoginScene;
    private MainController mainController;


    private AnchorPane generalScene;
    private AnchorPane endGameScene;

    private GeneralMainGameController generalMainGameController;

    private SceneType lastScene;
    private BorderPane rootLayoutMainGame;
    private StringBuilder stringBuffer;
    private int choiceDone;
    private Stage lastStageOpened;
    private Score uiScore;

    private String errorIOException = "error loading fxml file";
    private String choiceFXMLFile = "/fileXML/mainGame/choice.fxml";

    /**
     * method called by Application.launch() to start the application
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        this.primaryStage.setResizable(true);
        mainController = MainController.getInstance();
        mainController.setLoginBuilder(this);

        initRootLayout();

        initializeInitialLogin();
        initializeWaitingLogin();
        showFirstPage();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/login/rootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * method that initialize the initial login scene
     */
    private void initializeInitialLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/login.fxml"));
            initialLoginScene = loader.load();

            InitialLogin initialLogin = loader.getController();
            initialLogin.setMainController(mainController);

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     *  method that initialize the waiting login scene
     */
    private void initializeWaitingLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/attesaInizioPartita.fxml"));
            waitingLoginScene = loader.load();


        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }

    }

    /**
     * method used to initialize all the scenes of main game
     */
    void initializeMainGame() {
        initializeGeneralMainGame();
        initializeCouncil();
        initializeTowers();
        initializeHarvester();
        initializeMarket();
        initializePersonalBoard();
        initializeProduction();
        initializeLeaderCard();
        initializeEndGame();
    }

    /**
     *  method that initialize the general main game scene
     */
    private void initializeGeneralMainGame() {
        try {
            stringBuffer = new StringBuilder();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/generalMainGame.fxml"));
            generalScene = loader.load();

            generalMainGameController = loader.getController();
            generalMainGameController.setLoginBuilder(this);
            generalMainGameController.setMainController(mainController);
            generalMainGameController.uploadImages();

            SceneType.MAIN.setController(generalMainGameController);
            SceneType.MAIN.setScene(generalScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " general");
        }
    }

    /**
     *  method that initialize the root layout for main game scenes
     */
    protected void initRootLayoutMainGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/mainGame/rootLayout.fxml"));
            rootLayoutMainGame = loader.load();

            primaryStage.close();
            Scene scene = new Scene(rootLayoutMainGame);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " rootLayout");
        }
    }

    /**
     *  method that initialize the council scene
     */
    private void initializeCouncil() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/councilPalaceMainGame.fxml"));
            AnchorPane councilScene = loader.load();

            CouncilPalaceController councilPalaceController = loader.getController();
            councilPalaceController.setLoginBuilder(this);
            councilPalaceController.setMainController(mainController);
            councilPalaceController.uploadImages();


            SceneType.COUNCIL.setController(councilPalaceController);
            SceneType.COUNCIL.setScene(councilScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " council");
        }
    }

    /**
     *  method that initialize the leader scene
     */
    private void initializeLeaderCard() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/leaderCardMainGame.fxml"));
            AnchorPane leaderScene = loader.load();

            LeaderCardController leaderCardController = loader.getController();
            leaderCardController.setLoginBuilder(this);
            leaderCardController.setMainController(mainController);
            leaderCardController.uploadImages();

            SceneType.LEADER.setController(leaderCardController);
            SceneType.LEADER.setScene(leaderScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " leader");
        }
    }

    /**
     *  method that initialize the tower scene
     */
    private void initializeTowers() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/towersMainGame.fxml"));
            AnchorPane towersScene = loader.load();

            TowersController towersController = loader.getController();
            towersController.setLoginBuilder(this);
            towersController.setMainController(mainController);
            towersController.uploadImages();

            SceneType.TOWERS.setController(towersController);
            SceneType.TOWERS.setScene(towersScene);

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " towers");
        }
    }

    /**
     *  method that initialize the market scene
     */
    private void initializeMarket() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/marketMainGame.fxml"));
            AnchorPane marketScene = loader.load();

            MarketController marketController = loader.getController();
            marketController.setLoginBuilder(this);
            marketController.setMainController(mainController);
            marketController.uploadImages();

            SceneType.MARKET.setController(marketController);
            SceneType.MARKET.setScene(marketScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " market");
        }
    }

    /**
     *  method that initialize the personal board scene
     */
    private void initializePersonalBoard() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/personalBoardMainGame.fxml"));
            AnchorPane personalBoardScene = loader.load();

            PersonalBoardController personalBoardController = loader.getController();
            personalBoardController.setLoginBuilder(this);
            personalBoardController.setMainController(mainController);
            personalBoardController.uploadImages();

            SceneType.PERSONAL_BOARD.setController(personalBoardController);
            SceneType.PERSONAL_BOARD.setScene(personalBoardScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " personalBoard");
        }
    }

    /**
     *  method that initialize the harvester scene
     */
    private void initializeHarvester() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/harvesterMainGame.fxml"));
            AnchorPane harvesterScene = loader.load();

            HarvesterController harvesterController = loader.getController();
            harvesterController.setLoginBuilder(this);
            harvesterController.setMainController(mainController);
            harvesterController.uploadImages();

            SceneType.HARVESTER.setController(harvesterController);
            SceneType.HARVESTER.setScene(harvesterScene);
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " harvester");
        }
    }

    /**
     *  method that initialize the production scene
     */
    private void initializeProduction() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/productionMainGame.fxml"));
            AnchorPane productionScene = loader.load();

            ProductionController productionController = loader.getController();
            productionController.setLoginBuilder(this);
            productionController.setMainController(mainController);
            productionController.uploadImages();

            SceneType.PRODUCTION.setController(productionController);
            SceneType.PRODUCTION.setScene(productionScene);

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " production");
        }
    }

    /**
     *  method that initialize the end game scene
     */
    private void initializeEndGame() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/endMatch.fxml"));
            endGameScene = loader.load();

            EndGameController endGameController = loader.getController();
            endGameController.setLoginBuilder(this);
            endGameController.setMainController(mainController);
            endGameController.uploadImages();

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException + " endGame");
        }
    }

    /**
     * method used to start the gui app and set aat the center of the stage the login scene
     */
    void showFirstPage() {
        rootLayout.setCenter(initialLoginScene);
    }

    /**
     * main used to launch the application
     * @param args args
     */
    public void main(String[] args) {
        launch(args);
    }

    /**
     * method used to start the main game scenes and set in the center of the stage the main game scene
     */
    void startMainGame() {
        initRootLayoutMainGame();
        showMainScene();
    }

    /**
     * method used to set in the center the general main game scene
     */
    void showMainScene() {
        rootLayoutMainGame.setCenter(generalScene);
    }

    /**
     * method called to show a card zoomed
     * @param imageView imageView of the card to zoom
     */
    void showCardZoomed(Image imageView) {
        if (imageView == null) return;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/cardZoom.fxml"));
            AnchorPane cardZoomed = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Card");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(cardZoomed);
            dialogStage.setScene(scene);

            CardZoomController controller = loader.getController();
            controller.setImage(imageView);

            dialogStage.show();

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * method used to initialize and show thanksgiving scene
     */
    void showThanksgiving() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/ringraziamenti.fxml"));
            AnchorPane greetings = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ringraziamenti");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(greetings);
            dialogStage.setScene(scene);

            Greetings controller = loader.getController();
            controller.updateText();
            lastStageOpened = dialogStage;
            dialogStage.showAndWait();
            lastStageOpened = null;
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }


    }

    /**
     * method used to show standings scene
     */
    void showStandings() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(choiceFXMLFile));
            AnchorPane standings = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Standings");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(standings);
            dialogStage.setScene(scene);

            List<PlayerFile> playerFiles = mainController.getRanking();

            ChoiceController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setRankings(playerFiles);
            controller.setLabel("rankings");
            controller.setChoice1("ok");
            lastStageOpened = dialogStage;
            dialogStage.showAndWait();
            lastStageOpened = null;
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }

    }

    /**
     * method used to show statistics scene
     */
    void showStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(choiceFXMLFile));
            AnchorPane statistics = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(statistics);
            dialogStage.setScene(scene);

            PlayerFile playerFile = mainController.getStatistics();
            ChoiceController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setStatistics(playerFile);
            controller.setLabel("statistics");
            controller.setChoice1("ok");
            lastStageOpened = dialogStage;
            dialogStage.showAndWait();
            lastStageOpened = null;
        } catch (IOException e) {

            UnixColoredPrinter.Logger.print(errorIOException);
        }

    }

    /**
     * method used to show choice popUp
     * @param message message of the popUP
     * @param choice1 first choice
     * @param choice2 second choice
     */
    void showChoice(String message, String choice1, String choice2) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(choiceFXMLFile));
            AnchorPane choice = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Choice");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(choice);
            dialogStage.setScene(scene);

            ChoiceController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setLabel(message);
            controller.setChoice1(choice1);
            controller.setChoice2(choice2);
            if (message.equals(CliConstants.ASK_FOR_PRAYING))
                controller.setImage(mainController.getCurrentPeriod());

            lastStageOpened = dialogStage;
            dialogStage.showAndWait();
            lastStageOpened = null;

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * method that pass form leader card to their names, used for uploading images
     * @param leaderName list of leader to draft
     */
    void setLeaderDraft(List<LeaderCard> leaderName) {
        List<String> stringLeaderName = new ArrayList<>();
        for (LeaderCard l : leaderName)
            stringLeaderName.add(l.getName());

        showDraft(mainController.getUsernameChosen() + ": draft of Leader Card", stringLeaderName, "leader");
    }

    /**
     * method used to show draft of tiles
     * @param tiles tiles t draft
     */
    void setTileDraft(List<Tile> tiles) {
        List<String> stringTile = new ArrayList<>();
        for (Tile t : tiles)
            stringTile.add(String.valueOf(t.getTileNumber()));
        showDraft(mainController.getUsernameChosen() + ": choose one tile", stringTile, "tile");
    }

    /**
     * method that show draft stage
     * @param labelMessage titile of draft
     * @param name list of name of images to show
     * @param type draft type
     */
    private void showDraft(String labelMessage, List<String> name, String type) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/draft.fxml"));
            AnchorPane draft = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Draft");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(draft);
            dialogStage.setScene(scene);

            DraftController controller = loader.getController();
            controller.setMainController(mainController);
            controller.setLabel(labelMessage);

            if (type.equals("tile")) controller.uploadImagesTile(name);
                else controller.uploadImagesLeader(name);

            lastStageOpened = dialogStage;
            dialogStage.setAlwaysOnTop(true);
            dialogStage.showAndWait();
            lastStageOpened = null;
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * method to show a popUp stage
     * @param s message in the popUp
     */
    void popUp(String s) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/error/popUp.fxml"));
            AnchorPane popUp = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(popUp);
            dialogStage.setScene(scene);

            PopUpController controller = loader.getController();
            controller.setLabel(s);
            controller.setMainController(mainController);

            dialogStage.showAndWait();

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * method used to swich scene in gui application
     * @param nextScene scene to set in the center of the stage
     * @param lastScene scene that called this action, used for the goBack button
     */
    void setScene(SceneType nextScene, SceneType lastScene) {
        this.lastScene = lastScene;
        setScene(nextScene.getController(), nextScene.getScene());
    }

    /**
     * method used to show a scene chosen
     * @param controller controller of the scene
     * @param scene root anchor pane of the scene
     */
    private void setScene(AbstractController controller, AnchorPane scene) {
        controller.refresh();
        rootLayoutMainGame.setCenter(scene);
    }

    /**
     * getter
     * @return last scene showed
     */
    SceneType getLastScene() {
        return lastScene;
    }

    /**
     * getter
     * @return chat text
     */
    StringBuilder getChat() {
        return stringBuffer;
    }

    /**
     * getter
     * @return primary stage
     */
    private Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * method that show the waiting login scene and called the method the set the connection to the server
     */
    void waitingScene() {
        rootLayout.setCenter(waitingLoginScene);
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                mainController.takeNickname();
                return null;
            }
        };

        new Thread(task).start();
    }

    /**
     * method used to appen strings in the chat
     * @param s string to append
     */
    void stringBufferAppend(String s) {
        stringBuffer.append(s).append("\n");
    }

    /**
     * setter
     * @param choiceDone choice to set
     */
    void setChoiceDone(int choiceDone) {
        this.choiceDone = choiceDone;
    }

    /**
     * getter
     * @return choice done
     */
    int getChoiceDone() {
        return choiceDone;
    }

    /**
     * method used to write in the chat of all scenes
     * @param s string to write
     */
    void writeOnMyChat(String s) {
        stringBuffer.append(s);
        if (mainController!= null)
            mainController.updateChat();
    }

    /**
     * method used to set a scene to front
     */
    void inFront() {
        Stage s = getPrimaryStage();
        s.toFront();
        s.toFront();
        s.toFront();
    }

    /**
     * getter
     * @return last stage open
     */
    Stage getLastStageOpened() {
        return lastStageOpened;
    }

    /**
     * method used to show the points stage
     */
    void showPoints() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/showPoints.fxml"));
            AnchorPane popUp = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Points");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(popUp);
            dialogStage.setScene(scene);

            PointsController controller = loader.getController();
            controller.updatePoints(uiScore);

            dialogStage.showAndWait();
        } catch (IOException e) {
            UnixColoredPrinter.Logger.print(errorIOException);
        }
    }

    /**
     * setter
     * @param uiScore player score
     */
    void setUiScore(Score uiScore) {
        this.uiScore = uiScore;
    }

    /**
     * method used to get the excommunication tile image of the right period
     * @param currentPeriod period
     * @return image
     */
    Image getExcommunicationImage(int currentPeriod) {
        return generalMainGameController.getExcommunicationImage(currentPeriod);
    }

    /**
     * method used to set the after game scene
     */
    void setAfterGame() {
        if (rootLayoutMainGame != null)
            rootLayoutMainGame.setCenter(endGameScene);
        else{
            initRootLayoutMainGame();
            initializeEndGame();
            rootLayoutMainGame.setCenter(endGameScene);
        }

    }
}


