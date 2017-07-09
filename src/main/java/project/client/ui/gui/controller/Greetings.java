package project.client.ui.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import project.PrinterClass.UnixColoredPrinter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * controller of thanksgiving scene
 */
public class Greetings {
    @FXML
    private TextArea scroll;

    /**
     * method used to update text from the file
     */
    void updateText() {

        String s = getFile();
        scroll.setText(s);
        scroll.setBackground(Background.EMPTY);


    }

    /**
     * private method to conver file.txt in a string
     * @return text.toString()
     */
    private String getFile() {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = null;

        try {
            file = new File(classLoader.getResource("endMatchFile/greetings.txt").getFile());
        }
        catch (NullPointerException e){
            UnixColoredPrinter.Logger.print("error uploading greetings file");
        }

        assert file != null;
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            UnixColoredPrinter.Logger.print("error uploading greetings file");
        }

        return result.toString();

    }

    /**
     * method use to close the stage
     */
    public void close() {
        Stage stage = (Stage) scroll.getScene().getWindow();
        stage.hide();
    }
}
