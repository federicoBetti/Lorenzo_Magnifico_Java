package project.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Singleton Keyboard class
 */
public class SingletonKeyboard {

    private static SingletonKeyboard singletonKeyboard = new SingletonKeyboard();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private SingletonKeyboard(){}

    /**
     * Get a keyboard's instance
     *
     * @return the keyboard's instance
     */
    public static SingletonKeyboard getInstance(){
        return singletonKeyboard;
    }

    /**
     * Read a line from keyboard
     *
     * @return the line read
     * @throws IOException Signals that an I/O exception of some sort has occurred
     */
     public static String readLine() throws IOException {
        return reader.readLine();
    }
}
