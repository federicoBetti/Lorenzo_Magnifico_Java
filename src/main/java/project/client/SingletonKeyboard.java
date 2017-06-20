package project.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by raffaelebongo on 20/06/17.
 */
public class SingletonKeyboard {

    private static SingletonKeyboard singletonKeyboard = new SingletonKeyboard();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private SingletonKeyboard(){}

    public static SingletonKeyboard getInstance(){
        return singletonKeyboard;
    }

     public static String readLine() throws IOException {
        return reader.readLine();
    }
}
