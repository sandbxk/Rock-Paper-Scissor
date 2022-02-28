package rps;

//Project imports;
import rps.gui.launcher.ConsoleApp;
import rps.gui.launcher.JavaFXApp;

import java.io.IOException;


/**
 * Main class where we start
 *
 * @author smsj
 */
public class Main {


    /**
     * Main start
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //Console version
        startRPSConsoleGame();

        //JavaFX version
        //startRPSJavaFXGame();
    }

    /**
     * Start a JavaFX version of the game
     */
    private static void startRPSJavaFXGame() {
        JavaFXApp.launch();
    }

    /**
     * Start a console version of the game
     */
    public static void startRPSConsoleGame() throws IOException {
        new ConsoleApp().startGame();
    }
}



