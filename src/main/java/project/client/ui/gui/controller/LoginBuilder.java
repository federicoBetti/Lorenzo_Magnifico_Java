package project.client.ui.gui.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.client.ui.ClientSetter;

import java.io.IOException;

public class LoginBuilder extends Application {

    private BorderPane rootLayout;
    protected Stage primaryStage;
    private AnchorPane initialLoginScene;
    private AnchorPane waitingLoginScene;
    private ClientSetter clientSetter;
    private MainController mainController;

    private InitialLogin initialLogin;
    private WaitingLogin waitingLogin;


    private  AnchorPane generalScene;
    private  AnchorPane towersScene;
    private  AnchorPane marketScene;
    private  AnchorPane harvesterScene;
    private  AnchorPane personalBoardScene;
    private  AnchorPane productionScene;
    private  AnchorPane councilScene;
    private  AnchorPane leaderScene;

    private HarvesterController harvesterController;

    private SceneType lastScene;
    private String card;
    private TextField chatText; //la chat di tutti
    private String colour;
    private int faithPoints;
    private int turnOrder;
    private BorderPane rootLayoutMainGame;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        this.primaryStage.setResizable(false);
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
            // Load root layout from fxml file.
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

    /**
     * Shows the person overview inside the root layout.
     */
    private void initializeInitialLogin() {
        try {
            // Load person overview.
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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/attesaInizioPartita.fxml"));
            waitingLoginScene = (AnchorPane) loader.load();

            this.waitingLogin = loader.getController();
            waitingLogin.setMainController(mainController);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void switchScene() {
        rootLayout.setCenter(waitingLoginScene);
        return;
    }

    private void showFirstPage() {
        rootLayout.setCenter(initialLoginScene);
    }

    public void main(String[] args) {
        launch(args);
    }


    public void startMainGame() {
        chatText = new TextField();
        colour = "rosso";
        faithPoints = 3;
        turnOrder = 2;

        initRootLayoutMainGame();
        inizializzaGeneralMainGame();
        showPrimo();

        inizializzaTowers();
        inizializzaHarvester();
        inizializzaMarket();
        inizializzaPersonalBoard();
        inizializzaProduction();
        inizializzaCouncil();
        inizializzaLeaderCard();

        System.out.println("sono in start");
    }

    private  void showPrimo() {
        rootLayoutMainGame.setCenter(generalScene);
        System.out.print("faccio vedere il primo");
    }

    private  void inizializzaGeneralMainGame() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/generalMainGame.fxml"));
            generalScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            GeneralMainGameController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaTowers() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/towersMainGame.fxml"));
            towersScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.controller/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            TowersController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaMarket() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/marketMainGame.fxml"));
            marketScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            MarketController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaPersonalBoard() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/personalBoardMainGame.fxml"));
            personalBoardScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.controller/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            PersonalBoardController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaHarvester() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/harvesterMainGame.fxml"));
            harvesterScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            //harvesterController = loader.getController();
            //harvesterController.setLoginBuilder(this);
            //harvesterController.inizializeWithMain();
            //harvesterController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaProduction() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/productionMainGame.fxml"));
            productionScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            ProductionController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayoutMainGame() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/mainGame/rootLayout.fxml"));
            rootLayoutMainGame = (BorderPane) loader.load();

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
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/councilPalaceMainGame.fxml"));
            councilScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.controller/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            councilPalaceController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void inizializzaLeaderCard() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/leaderCardMainGame.fxml"));
            leaderScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            LeaderCardController controller = loader.getController();
            controller.setLoginBuilder(this);
            controller.setMainController(mainController);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLastScene(SceneType lastScene) {
        this.lastScene = lastScene;
    }


    public void setScene(SceneType nextScene, SceneType lastScene) {
        this.lastScene = lastScene;
        switch (nextScene) {
            case MAIN: {
                rootLayoutMainGame.setCenter(generalScene);
                break;
            }
            case TOWERS: {
                rootLayoutMainGame.setCenter(towersScene);
                break;
            }
            case MARKET:{
                rootLayoutMainGame.setCenter(marketScene);
                break;
            }
            case HARVESTER:{
                harvesterController.aggiornaChat();
                rootLayoutMainGame.setCenter(harvesterScene);
                break;
            }
            case PERSONAL_BOARD:{
                rootLayoutMainGame.setCenter(personalBoardScene);
                break;
            }
            case PRODUCTION:{
                rootLayoutMainGame.setCenter(productionScene);
                break;
            }
            case COUNCIL:{
                rootLayoutMainGame.setCenter(councilScene);
                break;
            }
            case LEADER:{
                rootLayoutMainGame.setCenter(leaderScene);
                break;
            }
            default:
                break;
        }
    }

    public void sendChat(String text) {
        //todo manda in chat, con iil riferiemnto al client setter
        chatText.appendText(text);
    }

    public SceneType getLastScene() {
        return lastScene;
    }

    public String getCard() {
        return "commercialHub";
    }

    public void showCardZoomed(Image imageView) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/cardZoom.fxml"));
            AnchorPane card = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Card");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(card);
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

    public TextField getChat() {
        return chatText;
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}


