
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main extends Application {
    enum EtatJeu{GAME,WIN,LOSE}

    protected final int gameSizeX=800;
    protected final int gameSizeY=300;
    protected String affichage;
    EtatJeu etatJeu=EtatJeu.GAME;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Runner By Yohan ISMAËL");
        Group root=new Group();
        Pane pane=new Pane(root);

        Camera cam=new Camera(100,0);
        Hero hero=new Hero(0,0);

        GameScene gameScene=new GameScene(pane,gameSizeX,gameSizeY,true,cam,hero);

        primaryStage.setScene(gameScene);
        primaryStage.show();

        Canvas canvas=new Canvas(pane.getWidth(),pane.getHeight());
        pane.getChildren().add(canvas);
        GraphicsContext gc=canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setFont(Font.font(30));

        final long startNanoTime = System.nanoTime();
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long time) {
                double t = (time - startNanoTime) / 1000000000.0;
                gc.clearRect(0,0,pane.getWidth(),pane.getHeight());

                hero.update(t);
                cam.update(t,hero);
                gameScene.update(t);

                int score;
                if (etatJeu!=EtatJeu.WIN && etatJeu!=EtatJeu.LOSE) {
                    score = (int) hero.getX();
                    BigDecimal t2 = new BigDecimal(t);
                    t2 = t2.setScale(3, RoundingMode.FLOOR);

                    if (hero.numberOfLives == 0) {
                        etatJeu = EtatJeu.LOSE;
                    } else if (hero.getX() > GameScene.getNumberOfEnemyMax() * 1000) {
                        etatJeu = EtatJeu.WIN;
                        score = GameScene.getNumberOfEnemyMax() * 1000;
                    }
                    affichage="Accélérer :D , Freiner :Q , Sauter :Z/Espace\n";
                    affichage+="Score : " + score+"\t";
                    affichage+="Temps : " + t2+"\t";
                    if (etatJeu==EtatJeu.WIN){
                        affichage=affichage+" \nVICTORY !\t Press X to exit";
                    }
                    if (etatJeu==EtatJeu.LOSE){
                        affichage=affichage+" \nGAME OVER !\t Press X to exit";
                    }
                }
                gc.setFill(Color.RED);
                gc.setFont(Font.font(30));
                gc.fillText(affichage, 200, 30);
            }

        };timer.start();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
