package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.PlayerFile;

import java.util.List;

/**
 * class that control the scene of choice
 */
public class ChoiceController {

    @FXML
    private HBox boxButton;
    @FXML
    private HBox forImage;
    private  Button buttonChoiche1;
    @FXML
    private Text message;

    private LoginBuilder loginBuilder;

    /**
     * setter
     * @param mainController main controller used to communicate whit ClientSetter
     */
    public void setMainController(MainController mainController) {
        mainController.setChoiceController(this);
    }


    /**
     * method called if the choice number 1 is chosen
     */
    @FXML
    private void choice1() {

        loginBuilder.setChoiceDone(0);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    /**
     * method called if the choice number 2 is chosen
     */
    @FXML
    private void choice2() {
        loginBuilder.setChoiceDone(1);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    /**
     * methos that set the main message of the stage
     * @param message message
     */
    public void setLabel(String message) {
        this.message.setText(message);
    }

    /**
     * methos used to customize the choice number 1
     * @param choice1 message in the choice button
     */
    void setChoice1(String choice1) {
        buttonChoiche1 = new Button(choice1);
        buttonChoiche1.setFont(new Font("Lucida Blackletter", 25.0));
        buttonChoiche1.setOnAction(event -> choice1());
        boxButton.getChildren().add(buttonChoiche1);
    }

    /**
     * methos used to customize the choice number 2
     * @param choice2 message in the choice button
     */
    void setChoice2(String choice2) {
        Button buttonChoice2 = new Button(choice2);
        buttonChoice2.setFont(new Font("Lucida Blackletter", 25.0));
        buttonChoice2.setOnAction(event -> choice2());
        boxButton.getChildren().add(buttonChoice2);
    }

    /**
     * setter
     * @param loginBuilder loginbuilder instance used to communicate with main app
     */
    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    /**
     * methos used to set the image of the excommunication
     * @param currentPeriod period of the excommunication to place
     */
    public void setImage(int currentPeriod) {
        Image image = loginBuilder.getExcommunicationImage(currentPeriod);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(400);
        forImage.getChildren().add(imageView);
    }

    /**
     * methos used to set the statistics in the stage, used at the end of the game
     * @param playerFile player statistics
     */
    public void setStatistics(PlayerFile playerFile) {
        Text text = new Text();
        String s = "\n\n"  + playerFile.getPlayerName() + "\n"
                + "\t" + "number of games played: " + playerFile.getNumberOfGames() + "\n"
                + "\t" + "number of games won: " + playerFile.getNumberOfVictories() + "\n"
                + "\t" + "number of games lost: " + playerFile.getNumberOfDefeats() + "\n"
                + "CONGRATULATIONS!\n\n";
        text.setText(s);

        ScrollPane scrollPane = new ScrollPane(text);
        forImage.getChildren().add(scrollPane);
    }

    /**
     * methos used to set the rankings in the stage, used at the end of the game
     * @param playerFiles rankings of the server
     */
    void setRankings(List<PlayerFile> playerFiles) {
        StringBuilder buffer = new StringBuilder();
        for (PlayerFile playerFile: playerFiles) {
            String s = "\n\n" + playerFile.getPlayerName() + "\n" + "\t" + "number of games played: " + playerFile.getNumberOfGames() + "\n" + "\t" + "number of games won: " + playerFile.getNumberOfVictories() + "\n" + "\t" + "number of games lost: " + playerFile.getNumberOfDefeats() + "\n\n";
            buffer.append(s);
        }

        Text text = new Text(buffer.toString());

        ScrollPane scrollPane = new ScrollPane(text);
        forImage.getChildren().add(scrollPane);

    }
}
