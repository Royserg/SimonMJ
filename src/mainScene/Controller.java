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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label mainMessage;

    @FXML
    private TextField usernameInput;

    @FXML
    private MenuItem menu_close;

    // initialize SimonGame
    private SimonGame game = new SimonGame();
    // initialize Player
    private Player player = new Player();


    @FXML
    void closeProgram() {
        System.out.println("Closing Program");
        Platform.exit();
    }

    public void pressButton(ActionEvent event) {
        String btn_id = ((Button)event.getSource()).getId();

        switch (btn_id) {
            case "btn_1":
                game.soundBtn1.play();
                System.out.println("Auhhh");
                // add btn id to the sequence array;
                break;
            case "btn_2":
                game.soundBtn2.play();
                System.out.println("Dada Da");
                break;
            case "btn_3":
                game.soundBtn3.play();
                System.out.println("Yeee Yeahh");
                break;
            case "btn_4":
                game.soundBtn4.play();
                System.out.println("Doubidu to be bad!");
                break;
            case "btn_start":
                mainMessage.setText("Level " + game.getLevel());
                game.startGame();
                System.out.println("Player: " + player.getName());
                // TODO make magic
                break;
            default:
                System.out.println("Default Action - shouldn't happen");
        }

        // TODO:
        /* `while` loop running until user choose GameLevel times the sound */
    }

    public void changeScene(ActionEvent event) throws Exception {
        // TODO checking if username field has any input
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
