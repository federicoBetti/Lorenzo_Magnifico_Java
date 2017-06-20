package project.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by raffaelebongo on 20/06/17.
 */
public class SingletonKeyboard {

    private static SingletonKeyboard singletonKeyboard = new SingletonKeyboard();

    private SingletonKeyboard(){}

    public static SingletonKeyboard getInstance(){
        return singletonKeyboard;
    }

     public static String readLineFromKeyboard() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
