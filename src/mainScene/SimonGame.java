package mainScene;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class SimonGame {
    // make audioClips for every sound
    private SoundButton soundBtn1 = new SoundButton("btn_1", "btn_1.mp3");
    private SoundButton soundBtn2 = new SoundButton("btn_2", "btn_2.wav");
    private SoundButton soundBtn3 = new SoundButton("btn_3", "btn_3.wav");
    private SoundButton soundBtn4 = new SoundButton("btn_4", "btn_4.wav");
    private int delay = 1200;
    private int gameLevel;

    private int userSequenceCounter = 0;

    // array holding Button Nodes
    private Button[] btnNodes = new Button[4];

    private SoundButton[] sounds = { soundBtn1, soundBtn2, soundBtn3, soundBtn4 };

    private ArrayList<Integer> soundSequence = new ArrayList<>();

    public SimonGame() {
    }

    /* === setters === */
    public void setLevel(int level) {
        gameLevel = level;
    }


    public void levelUp() {
        // play sounds faster
        delay -= 50;
        // increment game level
        ++gameLevel;
    }

    /* === getters === */
    int getLevel() {
        return gameLevel;
    }


    /* === Methods === */
    void startGame(Button[] btnNodes) {
        // get random sound
        AddRandomSound();

        // save reference to btnNodes into private array
        System.arraycopy(btnNodes, 0, this.btnNodes, 0, btnNodes.length);

        // play sequence of sounds
        playSoundSequence();
    }

    /* === Generate random number from 0 - 3 (inclusive) === */
    private int generateRandomNum() {
        return (int) Math.floor(Math.random() * 4);
    }

    /* === choose sound at random and add to sequence array === */
    public void AddRandomSound() {
        // get random number 0-3
        int random = generateRandomNum();
        // add to array
        soundSequence.add(random);
    }

    public void playSoundSequence() {
        // New thread for executing animation and sound
        Runnable runnable = () -> {
            for (int i = 0; i < soundSequence.size(); i++) {

                try {
                    if ( i == 0 && soundSequence.size() > 1) {
                        // sleep after user chose sequence
                        Thread.sleep(1500);
                    }

                    // play animation
                    new BounceIn(btnNodes[soundSequence.get(i)]).play();
//                    new FadeIn(btnNodes[soundSequence.get(i)]).play();
                    // play sound
                    sounds[soundSequence.get(i)].play();
                    // pause thread
                    Thread.sleep(delay);

                } catch (InterruptedException e) {
                    // when the thread gets interrupted - can check something
                    e.printStackTrace();
                }
            }

        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    String pressedSoundButton(Node btnNode) {
        String btn_id = btnNode.getId();
        int soundNum = Character.getNumericValue(btn_id.charAt(btn_id.length() - 1));
//        System.out.println("btn index: " + soundNum);


        // check if is matching position in sequence
        if (soundNum == soundSequence.get(userSequenceCounter)) {
            System.out.println("sounds the same");

            // animate button
//            new BounceIn(btnNode).play();
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



// ideas for extra features - choose how many songs should be added each level