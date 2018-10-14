package mainScene;

import animatefx.animation.FadeIn;
import javafx.scene.Node;

import java.util.ArrayList;

public class SimonGame {
    // make audioClips for every sound
    private SoundButton soundBtn1 = new SoundButton("btn_1", "btn_1.mp3");
    private SoundButton soundBtn2 = new SoundButton("btn_2", "btn_2.wav");
    private SoundButton soundBtn3 = new SoundButton("btn_3", "btn_3.wav");
    private SoundButton soundBtn4 = new SoundButton("btn_4", "btn_4.wav");
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

    public void clearSequence() {
        soundSequence.clear();
    }


    public void levelUp() {
        // increment game level
        ++gameLevel;
    }

    /* === getters === */
    int getLevel() {
        return gameLevel;
    }

    ArrayList<Integer> getSoundSequence() {
        return soundSequence;
    }

    /* === Generate random number from 0 - 3 (inclusive) === */
    private int generateRandomNum() {
        return (int) Math.floor(Math.random() * 4);
    }

    /* === choose sound at random and add to sequence array === */
    public void AddRandomSound() {
        // randomize all sounds
        if (soundSequence.size() > 0) {
            for (int i = 0; i < soundSequence.size(); i++) {
                soundSequence.set(i, generateRandomNum());
            }
        }
        // get random number 0-3
        int random = generateRandomNum();
        // add additional random sound
        soundSequence.add(random);
    }

    public void playSound(int index) {
        sounds[index].play();
    }

    String pressedSoundButton(Node btnNode) {

        if (soundSequence.size() == 0) {
            /* return nothing when game is not started
               buttons not activated    */
            return "";
        }

        String btn_id = btnNode.getId();
        int soundNum = Character.getNumericValue(btn_id.charAt(btn_id.length() - 1));

        // check if is matching position in sequence
        if (soundNum == soundSequence.get(userSequenceCounter)) {
            // animate button
            new FadeIn(btnNode).play();
            // play sound
            sounds[soundNum].play();

            userSequenceCounter++;
        } else {
            // guessed wrong
            return "false";
        }

        // zero counter after correctly guessed sequence
        if (userSequenceCounter >= soundSequence.size()) {
            // zero the counter
            userSequenceCounter = 0;
            // pass info to controller
            return "true";
        }

        // to return anything and continue
        return "";
    }

}
