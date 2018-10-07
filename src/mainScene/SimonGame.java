package mainScene;

import animatefx.animation.BounceIn;


import animatefx.animation.BounceInRight;
import animatefx.animation.Flip;
import animatefx.animation.Hinge;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class SimonGame {

    private Node btnNode;

    // make audioClips for every sound
    private SoundButton soundBtn1 = new SoundButton("btn_1", "btn_1.mp3");
    private SoundButton soundBtn2 = new SoundButton("btn_2", "btn_2.wav");
    private SoundButton soundBtn3 = new SoundButton("btn_3", "btn_3.wav");
    private SoundButton soundBtn4 = new SoundButton("btn_4", "btn_4.wav");
    private int delay = 1200;
    private int gameLevel;

    private int userSequenceCounter = 0;

    private SoundButton[] sounds = { soundBtn1, soundBtn2, soundBtn3, soundBtn4 };

    private ArrayList<Integer> soundSequence = new ArrayList<>();

    public SimonGame() {
    }

    /* === setters === */
    public void setLevel(int level) {
        gameLevel = level;
    }

//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

    public void setBtnNode(Node btnNode){
//        new BounceIn(btnNode).play();
    }

    public int levelUp() {
        return ++gameLevel;
    }

    /* === getters === */
    int getLevel() {
        return gameLevel;
    }



    /* === Methods === */
    void startGame(Button[] btnNodes) {
        // get random sound
        ChooseRandomSound();
//        ChooseRandomSound();

        // works here
//        new Hinge(btnNodes[2]).play();
//        new BounceInRight(btnNodes[0]).play();
//        new Flip(btnNodes[1]).play();

        // play sequence of sounds
        playSoundSequence(btnNodes);

        // wait for user to choose correct sounds
        // --- how to implement waiting ---
    }

    /* === Generate random number from 0 - 3 (inclusive) === */
    private int generateRandomNum() {
        return (int) Math.floor(Math.random() * 4);
    }

    /* === choose sound at random and add to sequence array === */
    private void ChooseRandomSound() {
        // get random number 0-3
        int random = generateRandomNum();
        // add to array
        soundSequence.add(random);
    }

    final DoubleProperty redColor = new SimpleDoubleProperty();
    final DoubleProperty greenColor = new SimpleDoubleProperty();
    final DoubleProperty blueColor = new SimpleDoubleProperty();




    private void playSoundSequence(Button[] btnNodes) {

        // TODO create Thread and runnable
        Runnable runnable = () -> {
            for (int soundNumber : soundSequence) {
                new BounceIn(btnNodes[soundNumber]).play();
                sounds[soundNumber].play();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        };

        Thread thread = new Thread(runnable);


        thread.start();

        // play each song saved to the sequence
//        for (int soundNumber : soundSequence) {
//            BounceIn animation = new BounceIn(btnNodes[soundNumber]);
//            animation.play();
//            sounds[soundNumber].play();

//        }
//            // TODO animating the button
//
//
//            System.out.println("Node: " + btnNodes[soundNumber]);
//
//
//
//            /* ==== */
//            sounds[soundNumber].play();
//
//            // wait until playing next sound
//            try {
//                Thread.sleep(delay);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println("sequence " + soundSequence);
    }

    void pressedSoundButton(Node btnNode) {
        String btn_id = btnNode.getId();
        int soundNum = Character.getNumericValue(btn_id.charAt(btn_id.length() - 1));
        System.out.println("btn index: " + soundNum);

        // animate button
        new BounceIn(btnNode).play();

        // play sound
        sounds[soundNum].play();

        // check if is matching position in sequence
// below line works, just improve it a little TODO
//        if (soundNum == soundSequence.get(userSequenceCounter)) {
//            System.out.println("sounds the same");
//
//            // play sound
//            sounds[soundNum].play();
//
//            userSequenceCounter++;
//        } else {
//            // guessed wrong
//            System.out.println("Game Over");
//        }
//
//        if (userSequenceCounter >= soundSequence.size()) {
//            userSequenceCounter = 0;
//        }
    }

}



// ideas for extra features - choose how many songs should be added each level