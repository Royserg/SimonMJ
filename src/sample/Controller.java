package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label mainMessage;

    @FXML
    private MenuItem menu_close;

    // make audioClips for every sound
    private SoundButton soundBtn1 = new SoundButton("btn_1", "btn_1.mp3");
    private SoundButton soundBtn2 = new SoundButton("btn_2", "btn_2.wav");
    private SoundButton soundBtn3 = new SoundButton("btn_3", "btn_3.wav");
    private SoundButton soundBtn4 = new SoundButton("btn_4", "btn_4.wav");


    @FXML
    void closeProgram() {
        System.out.println("Closing Program");
    }

    public void pressButton(ActionEvent event) {
        String btn_id = ((Button)event.getSource()).getId();

        switch (btn_id) {
            case "btn_1":
                soundBtn1.play();
                System.out.println("Auhhh");
                // add btn id to the sequence array;
                break;
            case "btn_2":
                soundBtn2.play();
                System.out.println("Dada Da");
                break;
            case "btn_3":
                soundBtn3.play();
                System.out.println("Yeee Yeahh");
                break;
            case "btn_4":
                soundBtn4.play();
                System.out.println("Doubidu to be bad!");
                break;
            case "btn_start":
                mainMessage.setText("Level 1");
                System.out.println("starting the game: TODO");
                break;
            default:
                System.out.println("Default Action - shouldn't happen");
        }

        // TODO:
        /* `while` loop running until user choose GameLevel times the sound */

        //AudioClip note = new AudioClip(getClass().getResource("assets/" + btn_id + ".mp3").toString());

    }

    // init method - doesn't seem that I need it
    public void initialize(URL url, ResourceBundle resource) {
        /* === Initialize SimonGame  === */
        // Get username
    }
}
