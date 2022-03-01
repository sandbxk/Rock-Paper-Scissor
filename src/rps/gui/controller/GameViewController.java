package rps.gui.controller;

// Java imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
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

    private static final double DEFAULT_PLAYER_ROTATION = 0;
    private static final double DEFAULT_BOT_ROTATION = 7;
    private static final double MAX_PLAYER_ROTATION = 30;
    private static final double MAX_BOT_ROTATION = 37;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgViewPlayer.setRotationAxis(Point3D.ZERO);
        imgViewBot.setRotationAxis(Point3D.ZERO);

        // https://www.tutorialspoint.com/how-to-rotate-a-node-in-javafx
    }

    public void onRock(ActionEvent event) {
        animateHands();
    }

    public void onPaper(ActionEvent event) {
    }

    public void onScissor(ActionEvent event) {
    }

    public void onReset(ActionEvent event) {
    }

    private Timeline animateHands(){
        Timeline timeline = new Timeline();

        KeyFrame lift1Player = new KeyFrame(Duration.millis(300), new KeyValue(imgViewPlayer.rotateProperty(), MAX_PLAYER_ROTATION));
        KeyFrame lift1Bot = new KeyFrame(Duration.millis(300), new KeyValue(imgViewBot.rotateProperty(), MAX_BOT_ROTATION));
        KeyFrame lower1Player = new KeyFrame(Duration.millis(300), new KeyValue(imgViewPlayer.rotateProperty(), DEFAULT_PLAYER_ROTATION));
        KeyFrame lower1Bot = new KeyFrame(Duration.millis(300), new KeyValue(imgViewBot.rotateProperty(), DEFAULT_BOT_ROTATION));
        timeline.getKeyFrames().add(lift1Player);
        timeline.getKeyFrames().add(lift1Bot);
        timeline.getKeyFrames().add(lower1Player);
        timeline.getKeyFrames().add(lower1Bot);

        timeline.play();
        return timeline;
    }
}
