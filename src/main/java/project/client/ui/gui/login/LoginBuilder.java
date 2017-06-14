package project.client.ui.gui.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.client.ui.ClientSetter;

import java.io.IOException;

public class LoginBuilder extends Application {

    private BorderPane rootLayout;
    private Stage primaryStage;
    private AnchorPane initialLoginScene;
    private AnchorPane waitingLoginScene;
    private static ClientSetter clientSetter;

    private InitialLogin initialLogin;
    private WaitingLogin waitingLogin;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");

        initRootLayout();

        initializeInitialLogin();
        initializeWaitingLogin();
        showFirstPage();
    }

    public static ClientSetter getClientSetter() {
        return clientSetter;
    }

    public static void setClientSetter(ClientSetter clientSetter) {
        LoginBuilder.clientSetter = clientSetter;
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
            initialLogin.setMainController(this);

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
            waitingLogin.setMainController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void switchScene() {
        rootLayout.setCenter(waitingLoginScene);
        return;
    }

    private void showFirstPage(){
        rootLayout.setCenter(initialLoginScene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    void connect(String username, String password) {
        clientSetter.connect(username,password);
    }

    void setConnectionType(String connectionType) {
        clientSetter.setConnectionType(connectionType);
    }
}
