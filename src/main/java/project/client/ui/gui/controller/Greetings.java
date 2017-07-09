package project.client.ui.gui.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Created by federico on 09/07/17.
 */
public class Greetings {
    public Label text;
    public TextArea scroll;


    public void updateText() {

        String s = getFile("endMatchFile/greetings.txt");
        System.err.println(s);
        scroll.setText(s);
        scroll.setBackground(Background.EMPTY);


    }

    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

    public void close(ActionEvent actionEvent) {

        Stage stage = (Stage) scroll.getScene().getWindow();
        stage.hide();
    }
}
