package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Created by federico on 08/06/17.
 */
public class InitialLogin {
    public ImageView sfondo;
    public AnchorPane anchr;
    public TextField IP;
    @FXML
    private  TextField username;
    @FXML
    private  TextField password;
    @FXML
    private  RadioButton socket;
    @FXML
    private  RadioButton rmi;
    @FXML
    private  Button connect;
    @FXML
    private  ToggleGroup connection;
    @FXML
    private  Button aia;
    private int i = 0;

    private String connectionType;
    private String usernameChosen;
    private String passwordChosen;

    private MainController mainController;
    private LoginBuilder loginBuilder;
    private String ipChosen;


    public InitialLogin(){
        connectionType = "RMI";
    }

    //questo è il metodo che viene chiamato quando il file fxml viene creato quindi ci possono essere tutte le inizializzazioni

    public void initialize(){
        //style="-fx-background-image: url('/images/immaginiSetUp/login.jpg'); -fx-background-repeat: stretch; -fx-background-position: center;"
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
        /*
        anchr.resize(loginBuilder.getRootLayout().getWidth(), loginBuilder.getRootLayout().getHeight());

        sfondo.fitWidthProperty().bind(anchr.widthProperty());
        sfondo.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/leaders.jpg"))));
        sfondo.setPreserveRatio(true);
        */
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setInitialLoginController(this);
        sfondo.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/login.jpg"))));
    }

    public void socketClicked() {
        connectionType = "socket";
    }

    public void rmiClicked() {
        connectionType = "RMI";
    }

    public void doConnection() {
            usernameChosen = username.getText();
            ipChosen = IP.getText();
            if (!checkValidInputIp(ipChosen))
                return;

            System.out.println("invio richiesta connessione");
            mainController.setConnectionType(connectionType, ipChosen,usernameChosen);
    }

    private boolean checkValidInputIp(String ip) {
        String[] numbers = ip.split("\\.");

        if (numbers.length != 4)
            return false;

        try {

            for (String num : numbers) {
                int n = Integer.parseInt(num);
                if (n < 0 || n > 255) return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }

        return true;
    }


    public void nicknameUsed() {
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usernameChosen = username.getText();
                mainController.takeNickname(usernameChosen);
            }
        });
    }
}
