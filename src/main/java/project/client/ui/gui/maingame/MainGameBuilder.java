package project.client.ui.gui.maingame;/**
 * Created by federico on 10/06/17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGameBuilder extends Application {


    BorderPane rootLayout;
    Stage primaryStage;
    AnchorPane generalScene;
    AnchorPane towersScene;
    AnchorPane marketScene;
    AnchorPane harvesterScene;
    AnchorPane personalBoardScene;
    AnchorPane productionScene;
    AnchorPane councilScene;
    AnchorPane leaderScene;

    HarvesterController harvesterController;

    private SceneType lastScene;
    private String card;
    private TextField chatText; //la chat di tutti
    private String colour;
    private int faithPoints;
    private int turnOrder;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        chatText = new TextField();
        colour = "rosso";
        faithPoints = 3;
        turnOrder=2;

        initRootLayout();

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

    private void showPrimo() {
        rootLayout.setCenter(generalScene);
    }

    private void inizializzaGeneralMainGame() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/generalMainGame.fxml"));
            generalScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.login/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            GeneralMainGameController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaTowers() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/towersMainGame.fxml"));
            towersScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.login/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            TowersMainGameController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaMarket() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/marketMainGame.fxml"));
            marketScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            MarketController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaPersonalBoard() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/personalBoardMainGame.fxml"));
            personalBoardScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.login/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            PersonalBoardController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaHarvester() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/harvesterMainGame.fxml"));
            harvesterScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
             harvesterController = loader.getController();
            harvesterController.setMainController(this);
            harvesterController.inizializeWithMain();
            harvesterController.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaProduction() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/productionMainGame.fxml"));
            productionScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            ProductionController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fileXML/mainGame/rootLayout.fxml"));
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

    private void inizializzaCouncil() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/councilPalaceMainGame.fxml"));
            councilScene = (AnchorPane) loader.load();

            //generalScene.getStylesheets().add(("fileXML.login/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            councilPalaceController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inizializzaLeaderCard() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/mainGame/leaderCardMainGame.fxml"));
            leaderScene = (AnchorPane) loader.load();

            // Give the controller access to the main app.
            LeaderCardController controller = loader.getController();
            controller.setMainController(this);
            controller.uploadImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScene(SceneType nextScene, SceneType lastScene) {
        this.lastScene = lastScene;
        switch (nextScene) {
            case MAIN: {
                rootLayout.setCenter(generalScene);
                break;
            }
            case TOWERS: {
                rootLayout.setCenter(towersScene);
                break;
            }
            case MARKET:{
                rootLayout.setCenter(marketScene);
                break;
            }
            case HARVESTER:{
                harvesterController.aggiornaChat();
                rootLayout.setCenter(harvesterScene);
                break;
            }
            case PERSONAL_BOARD:{
                rootLayout.setCenter(personalBoardScene);
                break;
            }
            case PRODUCTION:{
                rootLayout.setCenter(productionScene);
                break;
            }
            case COUNCIL:{
                rootLayout.setCenter(councilScene);
                break;
            }
            case LEADER:{
                rootLayout.setCenter(leaderScene);
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
            loader.setLocation(getClass().getResource("/finestreSupporto/cardZoom.fxml"));
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
}
