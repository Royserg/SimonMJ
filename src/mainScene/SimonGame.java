package mainScene;

import java.util.ArrayList;

public class SimonGame {
    // make audioClips for every sound
    public SoundButton soundBtn1 = new SoundButton("btn_1", "btn_1.mp3");
    public SoundButton soundBtn2 = new SoundButton("btn_2", "btn_2.wav");
    public SoundButton soundBtn3 = new SoundButton("btn_3", "btn_3.wav");
    public SoundButton soundBtn4 = new SoundButton("btn_4", "btn_4.wav");

    public SoundButton[] sounds = { soundBtn1, soundBtn2, soundBtn3, soundBtn4 };

    private ArrayList<Integer> simonSequence = new ArrayList<>();
    //private ArrayList<Integer> userSequence = new ArrayList<>(); // better to check on the go?

    private int gameLevel;

    public void setLevel(int level) {
        gameLevel = level;
    }

    public int levelUp() {
        return ++gameLevel;
    }

    public int getLevel() {
        return gameLevel;
    }

    public void startGame() {
        // create array for holding sequece
        int random = generateRandomNum();
        // create array for user sequence
        sounds[random].play();
        System.out.println(random);
        // randomly choose a button, add to sequence, call activate voice
        // other class for buttons


    }

    /*
    * Whenever player guesses right sequence, fire function that adds another random sound
    * After every player move, check if correctly guessed, stop game if player's wrong
    *
    *
    * */


    /* === Generate random number from 0 - 3 (inclusive) === */
    private int generateRandomNum() {
        return (int) Math.floor(Math.random() * 4);
    }
}
