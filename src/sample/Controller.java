package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    /* For now they are not used for anything */
    @FXML
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;

    @FXML
    private Label mainMessage;

    @FXML
    private MenuItem menu_close;

    // make audioClips for every sound
    private AudioClip btn1Sound = new AudioClip(getClass().getResource("assets/btn_1.mp3").toString());
    private AudioClip btn2Sound = new AudioClip(getClass().getResource("assets/btn_2.wav").toString());
    private AudioClip btn3Sound = new AudioClip(getClass().getResource("assets/btn_3.wav").toString());
    private AudioClip btn4Sound = new AudioClip(getClass().getResource("assets/btn_4.wav").toString());


    @FXML
    void closeProgram() {
        System.out.println("Closing Program");
    }

    public void pressButton(ActionEvent event) {
        String btn_id = ((Button)event.getSource()).getId();

        switch (btn_id) {
            case "btn_1":
                btn1Sound.play();
                System.out.println("Auhhh");
                // add btn id to the sequence array;
                break;
            case "btn_2":
                btn2Sound.play();
                System.out.println("Dada Da");
                break;
            case "btn_3":
                btn3Sound.play();
                System.out.println("Yeee Yeahh");
                break;
            case "btn_4":
                btn4Sound.play();
                System.out.println("Doubidu to be bad!");
                break;
        }

        // TODO:
        /* `while` loop running until user choose GameLevel times the sound */

        //AudioClip note = new AudioClip(getClass().getResource("assets/" + btn_id + ".mp3").toString());

        // SimonGame.levelUp();
        // System.out.println(SimonGame.getLevel());
    }

    public void startGame(ActionEvent event) {
        System.out.println("Starting game");
        mainMessage.setText("Level 1");

        /* set 1st level of the SimonGame */
        SimonGame.startGame();
        SimonGame.setLevel(1);
    }

    // init method - doesn't seem that I need it
    public void initialize(URL url, ResourceBundle resource) {
        /* === Initialize SimonGame  === */
        // SimonGame game = new SimonGame(1);
    }

}
