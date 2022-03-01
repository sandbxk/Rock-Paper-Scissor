package rps.gui.controller;

// Java imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML public Button btnRock;

    @FXML public Button btnPaper;

    @FXML public Button btnScissor;

    @FXML public Button btnReset;

    @FXML public Label lblHumanWins;

    @FXML public Label lblBotWins;

    @FXML public Label lblTies;

    @FXML public Label lblRoundNumber;

    @FXML public ImageView imgViewBot;

    @FXML public ImageView imgViewPlayer;

    private static final double DEFAULT_BOT_ROTATION = 7;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void onRock(ActionEvent event) {
    }

    public void onPaper(ActionEvent event) {
    }

    public void onScissor(ActionEvent event) {
    }

    public void onReset(ActionEvent event) {
    }

    private Timeline animateHands(){
        Timeline timeline = new Timeline();

        KeyFrame shakePlayer = new KeyFrame(Duration.millis(300), new KeyValue(imgViewPlayer.rotateProperty(), 0));
        return timeline;
    }
}
