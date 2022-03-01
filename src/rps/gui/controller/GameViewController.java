package rps.gui.controller;

// Java imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
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
    private static final double MAX_PLAYER_ROTATION = -30;
    private static final double MAX_BOT_ROTATION = 37;

    private Image botRock;
    private Image botPaper;
    private Image botScissor;

    private Image playerRock;
    private Image playerPaper;
    private Image playerScissor;

    private Rotate playerRotate;
    private Rotate botRotate;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //imgViewBot.setRotate(DEFAULT_BOT_ROTATION);
        //imgViewPlayer.setRotationAxis(new Point3D(0, 0, (imgViewBot.getX() - imgViewBot.getFitWidth())));
       //imgViewBot.setRotationAxis(new Point3D(imgViewBot.getX() + imgViewBot.getX(), imgViewBot.getX() + imgViewBot.getX(), (imgViewBot.getX() + imgViewBot.getFitWidth())));
        //imgViewBot.setRotationAxis(new Point3D(0, 0,(imgViewBot.getX() + imgViewBot.getFitWidth())));


        initImages();
        initRotate();
    }

    private void initRotate(){
        playerRotate = new Rotate();
        playerRotate.setPivotX(imgViewPlayer.getX());
        playerRotate.setPivotY(imgViewPlayer.getX() + (imgViewPlayer.getFitHeight() / 2));
        imgViewPlayer.getTransforms().add(playerRotate);

        botRotate = new Rotate(DEFAULT_BOT_ROTATION);
        botRotate.setPivotX(imgViewBot.getX() + imgViewBot.getFitWidth());
        botRotate.setPivotY(imgViewBot.getY() + (imgViewPlayer.getFitHeight() / 2));
        imgViewBot.getTransforms().add(botRotate);

    }

    private void initImages(){
        botRock = new Image(getClass().getResource("/rps/resources/img/bot_rock.png").toExternalForm());
        botPaper = new Image(getClass().getResource("/rps/resources/img/bot_paper.png").toExternalForm());
        botScissor = new Image(getClass().getResource("/rps/resources/img/bot_scissor.png").toExternalForm());

        playerRock = new Image(getClass().getResource("/rps/resources/img/player_rock.png").toExternalForm());
        playerPaper = new Image(getClass().getResource("/rps/resources/img/player_paper.png").toExternalForm());
        playerScissor = new Image(getClass().getResource("/rps/resources/img/player_scissor.png").toExternalForm());


    }

    public void onRock(ActionEvent event) {
        animateHands(200).setOnFinished(event1 -> {
            imgViewBot.setImage(botPaper);
        });
    }

    public void onPaper(ActionEvent event) {
    }

    public void onScissor(ActionEvent event) {
    }

    public void onReset(ActionEvent event) {
    }


    private Timeline animateHands(int durationMillis){
        imgViewBot.setImage(botRock);
        imgViewPlayer.setImage(playerRock);

        Timeline raise1 = new Timeline();
        Timeline lower1 = new Timeline();

        Timeline raise2 = new Timeline();
        Timeline lower2 = new Timeline();

        Timeline raise3 = new Timeline();
        Timeline lower3 = new Timeline();

        Timeline raise4 = new Timeline();
        Timeline lower4 = new Timeline();


        KeyFrame lift1Player = new KeyFrame(Duration.millis(durationMillis), new KeyValue(playerRotate.angleProperty(), MAX_PLAYER_ROTATION));
        KeyFrame lift1Bot = new KeyFrame(Duration.millis(durationMillis), new KeyValue(botRotate.angleProperty(), MAX_BOT_ROTATION));
        KeyFrame lower1Player = new KeyFrame(Duration.millis(durationMillis), new KeyValue(playerRotate.angleProperty(), DEFAULT_PLAYER_ROTATION));
        KeyFrame lower1Bot = new KeyFrame(Duration.millis(durationMillis), new KeyValue(botRotate.angleProperty(), DEFAULT_BOT_ROTATION));

        raise1.getKeyFrames().add(lift1Player);
        raise1.getKeyFrames().add(lift1Bot);

        lower1.getKeyFrames().add(lower1Player);
        lower1.getKeyFrames().add(lower1Bot);

        raise2.getKeyFrames().add(lift1Player);
        raise2.getKeyFrames().add(lift1Bot);

        lower2.getKeyFrames().add(lower1Player);
        lower2.getKeyFrames().add(lower1Bot);

        raise3.getKeyFrames().add(lift1Player);
        raise3.getKeyFrames().add(lift1Bot);

        lower3.getKeyFrames().add(lower1Player);
        lower3.getKeyFrames().add(lower1Bot);

        raise4.getKeyFrames().add(lift1Player);
        raise4.getKeyFrames().add(lift1Bot);

        lower4.getKeyFrames().add(lower1Bot);
        lower4.getKeyFrames().add(lower1Player);

        raise1.setOnFinished(event -> lower1.play());
        lower1.setOnFinished(event -> raise2.play());
        raise2.setOnFinished(event -> imageChangeOnAnimation(lower2, imgViewPlayer, imgViewBot, playerPaper, botPaper));
        lower2.setOnFinished(event -> raise3.play());
        raise3.setOnFinished(event -> imageChangeOnAnimation(lower3, imgViewPlayer, imgViewBot, playerScissor, botScissor));
        lower3.setOnFinished(event -> raise4.play());
        raise4.setOnFinished(event -> imageChangeOnAnimation(lower4, imgViewPlayer, imgViewBot, playerRock, botRock));


        raise1.play();

        return lower4;
    }

    private void imageChangeOnAnimation(Timeline timeline, ImageView imgView1, ImageView imgView2, Image img1, Image img2){
        //Set hands to rock, paper, scissor

        imgView1.setImage(img1);
        imgView2.setImage(img2);
        timeline.play();
    }
}
