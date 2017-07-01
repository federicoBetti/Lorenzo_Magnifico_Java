package project.client.ui.gui.controller;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.client.ui.ClientSetter;
import project.controller.cardsfactory.LeaderCard;
import project.model.Tile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO FARE ALTEZZA MAX 900
public class LoginBuilder extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;
    private AnchorPane initialLoginScene;
    private AnchorPane waitingLoginScene;
    private ClientSetter clientSetter;
    private MainController mainController;

    private InitialLogin initialLogin;
    private WaitingLogin waitingLogin;


    private AnchorPane generalScene;
    private AnchorPane towersScene;
    private AnchorPane marketScene;
    private AnchorPane harvesterScene;
    private AnchorPane personalBoardScene;
    private AnchorPane productionScene;
    private AnchorPane councilScene;
    private AnchorPane leaderScene;
    private AnchorPane draftScene;

    private HarvesterController harvesterController;
    private GeneralMainGameController generalMainGameController;
    private CouncilPalaceController councilPalaceController;
    private LeaderCardController leaderCardController;
    private MarketController marketController;
    private PersonalBoardController personalBoardController;
    private ProductionController productionController;
    private TowersController towersController;

    private SceneType lastScene;
    private String card;
    private TextField chatText; //la chat di tutti
    private String colour;
    private int faithPoints;
    private int turnOrder;
    private BorderPane rootLayoutMainGame;
    private StringBuffer stringBuffer;
    private int choiceDone;
    private DraftController draftController;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        this.primaryStage.setResizable(true);
        mainController = MainController.getInstance();
        mainController.setLoginBuilder(this);

        initRootLayout();

        initializeInitialLogin();
        initializeWaitingLogin();
        //initalizeMainGame();
        showFirstPage();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Configuration root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/login/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.print("qua prendo l'eccezzione di chiusura");
            e.printStackTrace();
        }
    }

    private void initializeInitialLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/login.fxml"));
            initialLoginScene = (AnchorPane) loader.load();

            this.initialLogin = loader.getController();
            initialLogin.setMainController(mainController);
            initialLogin.setLoginBuilder(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeWaitingLogin() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/attesaInizioPartita.fxml"));
            waitingLoginScene = (AnchorPane) loader.load();

            this.waitingLogin = loader.getController();
            waitingLogin.setMainController(mainController);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initalizeMainGame() {
        System.out.println("inizio inizializzazione");
        inizializzaGeneralMainGame();
        inizializzaCouncil();
        inizializzaTowers();
        inizializzaHarvester();
        inizializzaMarket();
        inizializzaPersonalBoard();
        inizializzaProduction();
        inizializzaLeaderCard();
        System.out.println("finita inizializzazione");
    }


    private void inizializzaGeneralMainGame() {
        try {
            stringBuffer = new StringBuffer();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/generalMainGame.fxml"));
            generalScene = (AnchorPane) loader.load();

            generalMainGameController = loader.getController();
            generalMainGameController.setLoginBuilder(this);
            generalMainGameController.setMainController(mainController);
            generalMainGameController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayoutMainGame() {
        try {
            // Configuration root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/mainGame/rootLayout.fxml"));
            rootLayoutMainGame = (BorderPane) loader.load();

            primaryStage.close();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayoutMainGame);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.print("qua prendo l'eccezzione di chiusura");
            e.printStackTrace();
        }
    }

    private  void inizializzaCouncil() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/councilPalaceMainGame.fxml"));
            councilScene = (AnchorPane) loader.load();

            councilPalaceController = loader.getController();
            councilPalaceController.setLoginBuilder(this);
            councilPalaceController.setMainController(mainController);
            councilPalaceController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaLeaderCard() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/leaderCardMainGame.fxml"));
            leaderScene = (AnchorPane) loader.load();

            leaderCardController = loader.getController();
            leaderCardController.setLoginBuilder(this);
            leaderCardController.setMainController(mainController);
            leaderCardController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaTowers() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/towersMainGame.fxml"));
            towersScene = (AnchorPane) loader.load();

            towersController = loader.getController();
            towersController.setLoginBuilder(this);
            towersController.setMainController(mainController);
            towersController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaMarket() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/marketMainGame.fxml"));
            marketScene = (AnchorPane) loader.load();

            marketController = loader.getController();
            marketController.setLoginBuilder(this);
            marketController.setMainController(mainController);
            marketController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaPersonalBoard() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/personalBoardMainGame.fxml"));
            personalBoardScene = (AnchorPane) loader.load();

            personalBoardController = loader.getController();
            personalBoardController.setLoginBuilder(this);
            personalBoardController.setMainController(mainController);
            personalBoardController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaHarvester() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/harvesterMainGame.fxml"));
            harvesterScene = (AnchorPane) loader.load();

            harvesterController = loader.getController();
            harvesterController.setLoginBuilder(this);
            harvesterController.setMainController(mainController);
            harvesterController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaProduction() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/productionMainGame.fxml"));
            productionScene = (AnchorPane) loader.load();

            productionController = loader.getController();
            productionController.setLoginBuilder(this);
            productionController.setMainController(mainController);
            productionController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void showFirstPage() {
        rootLayout.setCenter(initialLoginScene);
    }

    public void main(String[] args) {
        launch(args);
    }


    public void startMainGame() {

        initRootLayoutMainGame();

        showPrimo();

        System.out.println("sono in start");
    }


    public  void showPrimo() {
        rootLayoutMainGame.setCenter(generalScene);
        System.out.print("faccio vedere il primo");
    }


    public void showCardZoomed(Image imageView) {
        if (imageView==null)
            return;
        try {
            // Configuration the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/cardZoom.fxml"));
            AnchorPane cardZoomed = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Card");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(cardZoomed);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            cardZoomController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            controller.setImage(imageView);

            // Show the dialog and wait until the user closes it
            dialogStage.show();

            return;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sono uscito dalla visione della carta");
            return;
        }
    }

    public void showChoice(String message, String choice1, String choice2) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/choice.fxml"));
            AnchorPane choice = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Choice");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(choice);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ChoiceController controller = loader.getController();
            controller.setMainController(mainController);
            //controller.setDialogStage(dialogStage);
            controller.setLoginBuilder(this);
            controller.setLabel(message);
            controller.setChoice1(choice1);
            controller.setCoiche2(choice2);
            System.out.println("sto per disegnare lo stage");
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            System.out.println("sono dopo che ho disegnato lo stage");
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sono uscito dalla visione della carta");
            return ;
        }
    }

    public void setDraft(List<LeaderCard> leaderName) {
        List<String> stringLeaderName = new ArrayList<>();
        for (LeaderCard l: leaderName)
            stringLeaderName.add(l.getName());

        showDraft("draft of Leader Card", stringLeaderName, "leader");
    }


    public void setDraft(ArrayList<Tile> tiles) {
        List<String> stringTile = new ArrayList<>();
        for (Tile t: tiles)
            stringTile.add(String.valueOf(t.getTileNumber()));
        showDraft("choose one tile", stringTile, "tile");
    }


    private void showDraft(String labelMessage, List<String> name, String type) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/draft.fxml"));
            AnchorPane draft = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Draft");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(draft);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DraftController controller = loader.getController();
            controller.setMainController(mainController);
            controller.setLabel(labelMessage);

            if (type.equals("tile"))
                controller.uploadImagesTile(name);
            else
                controller.uploadImagesLeader(name);

            dialogStage.setAlwaysOnTop(true);
            dialogStage.showAndWait();
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sono uscito dalla visione della carta");
            return ;
        }
    }




    public void popUp(String s) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/error/popUp.fxml"));
            AnchorPane popUp = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Warning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(popUp);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PopUpController controller = loader.getController();
            controller.setLabel(s);
            System.out.println("sto per disegnare lo stage");
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            System.out.println("sono dopo che ho disegnato lo stage");
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("sono uscito dalla visione della carta");
            return ;
        }
    }

    public void setLastScene(SceneType lastScene) {
        this.lastScene = lastScene;
    }

    public void setScene(SceneType nextScene, SceneType lastScene) {
        this.lastScene = lastScene;
        switch (nextScene) {
            case MAIN: {
                generalMainGameController.refresh();
                rootLayoutMainGame.setCenter(generalScene);
                break;
            }
            case TOWERS: {
                towersController.refresh();
                rootLayoutMainGame.setCenter(towersScene);
                break;
            }
            case MARKET:{
                marketController.refresh();
                rootLayoutMainGame.setCenter(marketScene);
                break;
            }
            case HARVESTER:{
                harvesterController.refresh();
                rootLayoutMainGame.setCenter(harvesterScene);
                break;
            }
            case PERSONAL_BOARD:{
                personalBoardController.refresh();
                rootLayoutMainGame.setCenter(personalBoardScene);
                break;
            }
            case PRODUCTION:{
                productionController.refresh();
                rootLayoutMainGame.setCenter(productionScene);
                break;
            }
            case COUNCIL:{
                councilPalaceController.refresh();
                rootLayoutMainGame.setCenter(councilScene);
                break;
            }
            case LEADER:{
                leaderCardController.refresh();
                rootLayoutMainGame.setCenter(leaderScene);
                break;
            }
            default:
                break;
        }
    }

    public SceneType getLastScene() {
        return lastScene;
    }

    public StringBuffer getChat() {
        return stringBuffer;
    }

    public String getColour() {
        return colour;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    private Stage getPrimaryStage() {
        return primaryStage;
    }

    public void waitingScene() {
        rootLayout.setCenter(waitingLoginScene);
        Task task = new Task<Void>() {
            @Override public Void call() {
                mainController.takeNickname();
                return null;
            }
        };

        new Thread(task).start();
        System.out.println("ho messo la scena di wait");
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void stringBufferAppend(String s) {
        stringBuffer.append(s + "\n");
    }

    public void setChoiceDone(int choiceDone) {
        this.choiceDone = choiceDone;
    }

    public int getChoiceDone() {
        return choiceDone;
    }

    public void writeOnMyChat(String s) {
        stringBuffer.append(s);
        mainController.updateChat();
    }

    public void inFront() {
        Stage s = getPrimaryStage();
        s.toFront();
        s.toFront();
        s.toFront();
    }
}


