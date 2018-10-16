package application;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    // define offsets
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load layout
//        Parent main = FXMLLoader.load(getClass().getResource("view/main.fxml"));

        FXMLLoader loader = new FXMLLoader();
        Parent main = loader.load(getClass().getClassLoader().getResourceAsStream("view/main.fxml"));

        // create scene
        Scene mainScene = new Scene(main, 600, 600);
        // connect stylesheet
        mainScene.getStylesheets().add(getClass().getResource("/css/main.css").toExternalForm());
        // set background to transparent
        mainScene.setFill(Color.TRANSPARENT);

        /* https://medium.com/@keeptoo/making-a-borderless-javafx-window-movable-f7855eb33a51 */
        // grab main Stage
        main.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // move around
        main.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });


        // hide default action buttons
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("MJ Simon Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        // animate the Stage
        new FadeIn(main).play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
