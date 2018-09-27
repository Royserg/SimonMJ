package mainScene;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
    void startGame() {
        // set initial level
        mainMessage.setText("Level " + game.getLevel());
        // TODO hide Start Button

        // launch the game
        game.startGame();
    }

    public void pressSoundButton(ActionEvent event) {
        String btn_id = ((Button)event.getSource()).getId();

        // check if correct and play sound
        game.pressedSoundButton(btn_id);

        // TODO:
        /* `while` loop running until user choose GameLevel times the sound */
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
        }
    }

    // init method - doesn't seem that I need it
    public void initialize(URL url, ResourceBundle resource) {
        /* === set level  === */
        game.setLevel(1);
    }
}
