package controllers;

import application.SimonGame;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Shake;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Thread soundSequenceThread;

    @FXML
    private GridPane mainGrid;

    @FXML
    private Button startBtn;

    @FXML
    private StackPane mainStage;

    @FXML
    private Label mainMessage;

    // instantiate SimonGame
    private SimonGame game = new SimonGame();
    // will hold reference to each sound button
    private Button[] btnNodes = new Button[4];
    // variables
    private int delay = 1200;


    @FXML
    void closeProgram() {
        System.out.println("Closing Program");

        Platform.exit();
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) mainStage.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void startGame(ActionEvent event) {
        // set 1st level
        game.setLevel(1);
        // set Label text level
        mainMessage.setText("Level " + game.getLevel());

        // hide Start Button
        new FadeOut(startBtn).setSpeed(200).play();

        // disable the button
        startBtn.setDisable(true);

        // add random sound to sequence
        game.AddRandomSound();
        // play sequence
        playSoundSequence();
    }

    public void pressSoundButton(ActionEvent event) {
        // receive boolean from pressed button
        String correctSequence;

        // check sequence on game part
        correctSequence = game.pressedSoundButton((Node)event.getSource());

        if (correctSequence.equals("true")) {
            // level up
            game.levelUp();
            // play sounds faster
            delay -= 75;
            // update label
            mainMessage.setText("Level " + game.getLevel());
            // add random sound to the sequence
            game.AddRandomSound();
            // play sound sequence
            playSoundSequence();

        } else if (correctSequence.equals("false")){
            // stop the game
            System.out.println("Game stopping, You lost");

            // stop soundSequence thread - doesn't stop sequence after closing program
            soundSequenceThread.interrupt();

            // shake the Grid
            new Shake(mainGrid).play();

            // enable button
            startBtn.setDisable(false);

            // reset the sound sequence
            game.clearSequence();

            // show start button ("restart" label)
            new FadeIn(startBtn).setSpeed(500).play();
            startBtn.setText("Restart?");

            mainMessage.setText("Game Over :(\nYour Score: " + game.getLevel());
        }

    }

    // playing soundSequence here
    private void playSoundSequence() {
        // create new Thread
        Runnable runnable = () -> {
            ArrayList<Integer> sequence = game.getSoundSequence();

            for (int i = 0; i < sequence.size(); i++) {
                try {
                    if (i == 0 && sequence.size() > 1) {
                        // sleep after user guessed sequence
                        Thread.sleep(1500);
                    }

                    // play animation
                    new BounceIn(btnNodes[sequence.get(i)]).play();
                    // play sound
                    game.playSound(sequence.get(i));
                    // pause thread
                    Thread.sleep(delay);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        soundSequenceThread = new Thread(runnable);
        soundSequenceThread.start();
    }

    // init method - doesn't seem that I need it
    public void initialize(URL url, ResourceBundle resource) {

        // lookup btn nodes and assign to Button array
        for (int i = 0; i < btnNodes.length; i++) {
            btnNodes[i] = (Button) mainStage.lookup("#btn_" + i);
        }

    }
}
