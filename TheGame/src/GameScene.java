
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.text.Font;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GameScene extends Scene {
    protected boolean end=false;
    int rep=1;
    Camera camera;
    staticThing left,right;
    Hero hero;
    ArrayList<Foe> Foes;
    ArrayList<Heart> Life;

    private final int desertSizeX=800;
    private final int desertSizeY=400;

    protected static final int numberOfEnemyMax=30;

    public GameScene(Pane pane,int v,int v1,Boolean b,Camera cam,Hero hero) {
        super(pane,v,v1,b);
        this.camera=cam;


        this.left=new staticThing("desert.png",0,0);

        left.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(left.getImageView());

        this.right=new staticThing("desert.png",desertSizeX,0);

        right.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(right.getImageView());

        this.hero=hero;
        int numberOfLives=hero.numberOfLives;
        int maxNumberOfLives=hero.maxNumberOfLives;

        double initialSpeed = 1;

        this.hero.setSpeedx(initialSpeed);
        pane.getChildren().add(this.hero.getImageView());

        this.setOnMouseClicked(event -> hero.jump());
        this.setOnKeyPressed(keyEvent -> {
            String key=keyEvent.getCode().toString();
            if (key.equals("SPACE") || key.equals("Z")){
                hero.jump();
            }
            if(key.equals("D") && !hero.invincibilityOn){
                hero.forcex_var(100);
            }
            if (key.equals("Q")){
                hero.forcex_var(-100);
            }
            if (key.equals("X") && (hero.numberOfLives==0 || hero.getX() > GameScene.getNumberOfEnemyMax() * 1000)){
                this.getWindow().hide();
            }

        });

        Life = new ArrayList<>();
        Life.add(new Heart(0));
        Life.add(new Heart(1));
        Life.add(new Heart(2));
        for (Heart heart:Life){
            heart.getImageView().setViewport(new Rectangle2D(0,0,heart.sizeX,heart.sizeY));
            heart.getImageView().setX(heart.x);
            heart.getImageView().setY(0);
            pane.getChildren().add(heart.getImageView());
        }

        Foes = new ArrayList<>();

        for (int i=0;i<numberOfEnemyMax;i++){
            double x= Math.random()*2000-1000;
            if (1000*i+x>500) {
                Foe foe = new Foe(1000 * i + x, 0);
                Foes.add(foe);
                pane.getChildren().add(foe.getImageView());
            }
        }
    }

    public void update(double t){

        right.getImageView().setY(-camera.getY());
        left.getImageView().setY(-camera.getY());

        hero.getImageView().setX(hero.getX()-camera.getX());
        hero.getImageView().setY(hero.getY()-camera.getY());


        if(camera.getX()>desertSizeX*rep) {
            rep+=1;
        }
        if (rep % 2 == 1) {
            left.getImageView().setX(desertSizeX * (rep-1) - camera.getX());
            right.getImageView().setX(desertSizeX * (rep) - camera.getX());

        }
        else{
            right.getImageView().setX(desertSizeX * (rep-1) - camera.getX());
            left.getImageView().setX(desertSizeX * (rep) - camera.getX());
        }


        for(Heart heart:Life){
            if (heart.getIndex()+1> hero.numberOfLives){
                heart.getImageView().setViewport(new Rectangle2D(heart.sizeX,0,heart.sizeX,heart.sizeY));
            }
        }

        for(Foe foe:Foes){
            foe.update(t);
            foe.getImageView().setX(foe.getX()-camera.getX());
            foe.getImageView().setY(foe.getY()-camera.getY());
            if (hero.Intersect(foe.hitbox)){
                foe.touched(hero);
            }
        }
        if (hero.numberOfLives==0){
            hero.stop();
        }
    }

    public static int getNumberOfEnemyMax() {
        return numberOfEnemyMax;
    }
}
