package mainScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class SimonGame {

    @FXML
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;


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


    public void setLevel(int level) {
        gameLevel = level;
    }

    public int levelUp() {
        return ++gameLevel;
    }

    int getLevel() {
        return gameLevel;
    }

    void startGame() {
        // get random sound
        ChooseRandomSound();
        ChooseRandomSound();
        // play sequence of sounds
        playSoundSequence();

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

    private void playSoundSequence() {
        // play each song saved to the sequence
        for (int soundNumber : soundSequence) {
            // TODO animating the button

            sounds[soundNumber].play();

            // wait until playing next sound
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("sequence " + soundSequence);
    }

    void pressedSoundButton(String btn_id) {
        int soundNum = Character.getNumericValue(btn_id.charAt(btn_id.length() - 1));
        System.out.println("btn index: " + soundNum);

        // check if is matching position in sequence
        if (soundNum == soundSequence.get(userSequenceCounter)) {
            System.out.println("sounds the same");

            // play sound
            sounds[soundNum].play();

            btn_0.applyCss();

            userSequenceCounter++;
        } else {
            // guessed wrong
            System.out.println("Game Over");
        }
    }

}



// ideas for extra features - choose how many songs should be added each level