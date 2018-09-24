package sample;

import javafx.scene.media.AudioClip;

public class SoundButton {

    private String id;
    private AudioClip sound;

    public SoundButton(String id, String filename) {
        this.id = id;
        this.sound = new AudioClip(getClass().getResource("./assets/" + filename).toString());
    }

    public void play() {
        sound.play();
    }

}


