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
        // set initial level
        mainMessage.setText("Level " + game.getLevel());
        // TODO hide Start Button

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
        // check if correct and play sound
        game.pressedSoundButton((Node)event.getSource());

        // TODO:
        /* return something from game.pressedSoundBtn
            most probably change Simon Game button activities so
            all is controlled from Controller
        *  level up
        *  set new level to Label
        *
        * */
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
        /* === set level  === */
        game.setLevel(1);
    }
}
