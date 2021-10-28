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

    protected final int gameSizeX=800;
    protected final int gameSizeY=300;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The game");
        Group root=new Group();
        Pane pane=new Pane(root);

        Camera cam=new Camera(100,0);
        Hero hero=new Hero(100,0);

        GameScene gameScene=new GameScene(pane,gameSizeX,gameSizeY,true,cam,hero);

        primaryStage.setScene(gameScene);
        primaryStage.show();



        final long startNanoTime = System.nanoTime();
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long time) {
                double t = (time - startNanoTime) / 1000000000.0;

                hero.update(t);
                cam.update(t,hero);
                gameScene.update(t);
                //System.out.println(cam.getX());

            }

        };timer.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
