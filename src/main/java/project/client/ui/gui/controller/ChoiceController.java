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
 * Created by federico on 19/06/17.
 */
public class ChoiceController {

    public HBox boxButton;
    public HBox forImage;
    private  Button buttonChoiche1;
    @FXML
    private  Button buttonChoiche2;
    @FXML
    private Text message;

    private MainController mainController;
    private LoginBuilder loginBuilder;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        mainController.setChoiceController(this);
    }



    public void choice1() {

        loginBuilder.setChoiceDone(0);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void choice2() {
        loginBuilder.setChoiceDone(1);
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        stage.hide();
    }

    public void closeStage(){
        Stage stage = (Stage) buttonChoiche1.getScene().getWindow();
        mainController.setChoiceController(null);
        stage.hide();
    }

    public void setLabel(String message) {
        this.message.setText(message);
    }

    public void setChoice1(String choice1) {
        buttonChoiche1 = new Button(choice1);
        buttonChoiche1.setFont(new Font("Lucida Blackletter", 25.0));
        buttonChoiche1.setOnAction(event -> choice1());
        boxButton.getChildren().add(buttonChoiche1);
    }

    public void setChoice2(String choice2) {
        buttonChoiche2 = new Button(choice2);
        buttonChoiche2.setFont(new Font("Lucida Blackletter", 25.0));
        buttonChoiche2.setOnAction(event -> choice2());
        boxButton.getChildren().add(buttonChoiche2);
    }

    public void setLoginBuilder(LoginBuilder loginBuilder) {
        this.loginBuilder = loginBuilder;
    }

    public void setImage(int currentPeriod) {
        Image image = loginBuilder.getExcommunicationImage(currentPeriod);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(400);
        forImage.getChildren().add(imageView);
    }

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

    public void setRankings(List<PlayerFile> playerFiles) {
        StringBuffer buffer = new StringBuffer();
        for (PlayerFile playerFile: playerFiles) {
            String s = "\n\n" + playerFile.getPlayerName() + "\n" + "\t" + "number of games played: " + playerFile.getNumberOfGames() + "\n" + "\t" + "number of games won: " + playerFile.getNumberOfVictories() + "\n" + "\t" + "number of games lost: " + playerFile.getNumberOfDefeats() + "\n\n";
            buffer.append(s);
        }

        Text text = new Text(buffer.toString());

        ScrollPane scrollPane = new ScrollPane(text);
        forImage.getChildren().add(scrollPane);

    }
}
