package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button btn_1;

    @FXML
    private Button btn_2;

    @FXML
    private Button btn_3;

    @FXML
    private Button btn_4;

    @FXML
    private Label mainMessage;

    @FXML
    private MenuItem menu_close;

    @FXML
    void closeProgram(ActionEvent event) {
        System.out.println("Closing Program");
    }


    public void pressButton(ActionEvent event) {
        System.out.println(event.getSource());
    }

    public void startGame(ActionEvent event) {
        System.out.println("Starting game");
        mainMessage.setText("Level 1");
    }

}
