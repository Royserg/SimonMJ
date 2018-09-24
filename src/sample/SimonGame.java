package sample;

import java.util.ArrayList;

public class SimonGame {
    private ArrayList<Integer> simonSequence = new ArrayList<>();
    private ArrayList<Integer> userSequence = new ArrayList<>(); // better to check on the go?

    private static int gameLevel;

    public static void setLevel(int level) {
        gameLevel = level;
    }

    public static int levelUp() {
        return ++gameLevel;
    }

    public static int getLevel() {
        return gameLevel;
    }

    public static void startGame() {
        // create array for holding sequece
        // create array for user sequence

        // randomly choose a button, add to sequence, call activate voice
        // other class for buttons


    }

    /*
    * Whenever player guesses right sequence, fire function that adds another random sound
    * After every player move, check if correctly guessed, stop game if player's wrong
    *
    *
    * */


    /* === Generate random number from 1 - 4 (inclusive) === */
    public static int generateRandom() {
        return (int) Math.ceil(Math.random() * 4);
    }
}
