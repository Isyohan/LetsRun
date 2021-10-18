package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The game");
        Group root=new Group();
        Pane pane=new Pane(root);
        Camera cam=new Camera(200,100);
        GameScene gameScene=new GameScene(pane,400,200,true,cam);
        primaryStage.setScene(gameScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
