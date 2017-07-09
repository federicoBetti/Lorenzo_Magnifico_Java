package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * controller of first login scene
 */
public class InitialLogin {

    @FXML
    private ImageView sfondo;
    @FXML
    private TextField ipAddress;
    @FXML
    private  TextField username;
    @FXML
    private  Button connect;

    private String connectionType;
    private String usernameChosen;

    private MainController mainController;

    /**
     * constructor
     */
    public InitialLogin(){
        connectionType = "RMI";
    }

    /**
     * setter
     * @param mainController main controller used to communicate with server
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setInitialLoginController(this);
        sfondo.setImage(new Image(String.valueOf(getClass().getResource("/images/immaginiSetUp/login.jpg"))));
    }

    /**
     * method called from radio button when socket connection is chosen
     */
    public void socketClicked() {
        connectionType = "socket";
    }

    /**
     * method called from radio button when RMI connection is chosen
     */
    public void rmiClicked() {
        connectionType = "RMI";
    }

    /**
     * method called to connect client and server
     */
    public void doConnection() {
            usernameChosen = username.getText();
        String ipChosen = ipAddress.getText();
            if (!checkValidInputIp(ipChosen))
                return;

            mainController.setConnectionType(connectionType, ipChosen,usernameChosen);

    }

    /**
     * methods that check if the ipAddress address is valid
     * @param ip ip address written
     * @return boolean if the ip address is correct
     */
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

    /**
     * method called from the second attempt of connection, following an NicknameAlreadyUsed message
     */
    void nicknameUsed() {
        connect.setOnAction(event -> {
            usernameChosen = username.getText();
            mainController.takeNickname(usernameChosen);
        });
    }
}
