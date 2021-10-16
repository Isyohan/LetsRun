package sample;

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
        primaryStage.setTitle("Hello Worlds !!!");
        Group root=new Group();
        Pane pane=new Pane(root);
        Camera cam=new Camera(100,30);
        GameScene RealScene=new GameScene(pane,600,400,true,cam);
        primaryStage.setScene(RealScene);
        /*
        ImageView backGround = new ImageView(new Image("img/desert.png"));
        backGround.setViewport(new Rectangle2D(0,0,800,700));
        backGround.setX(0);
        backGround.setY(0);
        root.getChildren().add(backGround);
        */
        ImageView sprite = new ImageView(new Image("img/heros.png"));
        sprite.setViewport(new Rectangle2D(20,0,65,100));
        sprite.setX(200);
        sprite.setY(250);
        root.getChildren().add(sprite);



        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
