package mainScene;

import animatefx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button startBtn;

    @FXML
    private StackPane mainStage;

    @FXML
    private Label mainMessage;

    @FXML
    private TextField usernameInput;

    // initialize SimonGame
    private SimonGame game = new SimonGame();
    // initialize Player
    private Player player = new Player();

    @FXML
    void closeProgram() {
        System.out.println("Closing Program");
        Platform.exit();
    }

    @FXML
    void startGame(ActionEvent event) {
        // set 1st level
        game.setLevel(1);
        // set Label text level
        mainMessage.setText("Level " + game.getLevel());

        // hide Start Button
        new FadeOut(startBtn).setSpeed(200).play();

        // get btnNodes
        Button[] btnNodes = {
                    (Button) mainStage.lookup("#btn_0"),
                    (Button) mainStage.lookup("#btn_1"),
                    (Button) mainStage.lookup("#btn_2"),
                    (Button) mainStage.lookup("#btn_3")
                    };

        // launch the game
        game.startGame(btnNodes);

    }

    public void pressSoundButton(ActionEvent event) {
        // receive boolean from pressed button
        String correctSequence;

        // check sequence on game part
        correctSequence = game.pressedSoundButton((Node)event.getSource());

        if (correctSequence.equals("true")) {
            // level up
            game.levelUp();
            // update label
            mainMessage.setText("Level " + game.getLevel());
            // add random sound to the sequence
            game.AddRandomSound();
            // play sound sequence
            game.playSoundSequence();

        } else if (correctSequence.equals("false")){
            // stop the game
            System.out.println("Game stopping, You lost");

            // reset the sound sequence
            game.clearSequence();

            // show start button ("restart" label)
            new FadeIn(startBtn).setSpeed(400).play();
            startBtn.setText("Restart?");
            mainMessage.setText("Game Over :(\nYour Score: " + game.getLevel());
        }

    }

    public void changeScene(ActionEvent event) throws Exception {
        // no username - choose random
        if (usernameInput.getText().length() == 0) {
            usernameInput.setText("randomGuy");
        } else {

            Parent main = FXMLLoader.load(getClass().getResource("main.fxml"));

            Scene mainScene = new Scene(main, 600, 600);
            mainScene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
            mainScene.setFill(Color.TRANSPARENT);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // initialize player with provided username
            player.setName(usernameInput.getText());

            stage.setScene(mainScene);

            // animate scene
            new FadeIn(main).play();
        }
    }

    // init method - doesn't seem that I need it
    public void initialize(URL url, ResourceBundle resource) {
    }
}
