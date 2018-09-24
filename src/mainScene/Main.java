package mainScene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      /* === Username Scene === */
        // load layout
        Parent user = FXMLLoader.load(getClass().getResource("/usernameScene/username.fxml"));
        // username scene
        Scene userScene = new Scene(user, 600, 475);

        /* === Main scene === */
        primaryStage.setTitle("MJ Simon Game");
        primaryStage.setScene(userScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
