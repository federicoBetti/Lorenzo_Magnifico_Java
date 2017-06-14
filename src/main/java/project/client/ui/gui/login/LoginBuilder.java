package project.client.ui.gui.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginBuilder extends Application {

    BorderPane rootLayout;
    Stage primaryStage;
    AnchorPane primoScene;
    AnchorPane secondoScene;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        inizializzaPrimo();
        inizializzaSecondo();
        showPrimo();
        System.out.println("sono in start");
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
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
    public void inizializzaPrimo() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/login.fxml"));
            primoScene = (AnchorPane) loader.load();

            primoScene.getStylesheets().add(("fileXML/login/primoCSS.css"));
            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            LoginIniziale controller = loader.getController();
            controller.setMainController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inizializzaSecondo() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fileXML/login/attesaInizioPartita.fxml"));
            secondoScene = (AnchorPane) loader.load();

            secondoScene.getStylesheets().add(("fileXML/login/secondoCSS.css"));


            // Set person overview into the center of root layout.
            //rootLayout.setCenter(primoScene);

            // Give the controller access to the main app.
            LoginAttesa controller = loader.getController();
            controller.setMainController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScene(Finestre finestre) {
        if (finestre.equals(Finestre.PRIMO)) {
            showPrimo();
        } else if (finestre.equals(Finestre.SECONDO)) {
            showSecondo();
        }
        return;
    }

    private void showPrimo(){
        rootLayout.setCenter(primoScene);
    }

    private void showSecondo(){
        rootLayout.setCenter(secondoScene);
    }

    public static void avvia(String[] args) {
        launch(args);
    }

}
