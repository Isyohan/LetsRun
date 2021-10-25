package sample;

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

import java.util.ArrayList;

public class GameScene extends Scene {
    int numberOfLives=3;
    int rep=1;
    Camera camera;
    staticThing left,right;
    Hero hero;
    ArrayList<Foe> Foes;
    private final double InitialSpeed=1;

    public GameScene(Pane pane,int v,int v1,Boolean b,Camera cam,Hero hero) {
        super(pane,v,v1,b);
        this.camera=cam;


        this.left=new staticThing("img/desert.png",0,0);
        //this.left=new staticThing("img/desert.png",0,0);

        left.getImageView().setViewport(new Rectangle2D(0,0,800,400));
        pane.getChildren().add(left.getImageView());

        this.right=new staticThing("img/desert.png",800,0);
        //this.right=new staticThing("img/desert.png",800,0);

        right.getImageView().setViewport(new Rectangle2D(0,0,800,400));
        pane.getChildren().add(right.getImageView());

        ImageView[]  lifeslist=new ImageView[numberOfLives];
        for(int i=0;i<numberOfLives;i++){
            lifeslist[i]=new ImageView("img/heart.png");
            lifeslist[i].setX(48*i);
            lifeslist[i].setY(0);
            lifeslist[i].setViewport(new Rectangle2D(0,0,48,48));
            pane.getChildren().add(lifeslist[i]);
        }
        Foes = new ArrayList<>();
        Foes.add(new Foe(1000,300));
        Foes.add(new Foe(600,300));
        for(Foe foe :Foes){
            //pane.getChildren().add(foe.getImageView());
        }


        this.hero=hero;
        this.hero.setSpeedx(InitialSpeed);
        pane.getChildren().add(this.hero.getImageView());

        this.setOnMouseClicked(event -> hero.jump());
        this.setOnKeyPressed(keyEvent -> {
            String key=keyEvent.getCode().toString();
            if (key.equals("SPACE") || key.equals("Z")){
                hero.jump();
            }
            if(key.equals("D")){
                //hero.speed_var(1);
                hero.forcex_var(100);
            }
            if (key.equals("Q")){
                //hero.speed_var(-1);
                hero.forcex_var(-100);
            }

        });




    }

    public void update(double t){
        right.getImageView().setY(-camera.getY());
        left.getImageView().setY(-camera.getY());


        if(camera.getX()>800*rep) {
            rep+=1;
        }
        if (rep % 2 == 1) {
            left.getImageView().setX(800 * (rep-1) - camera.getX());
            right.getImageView().setX(800 * (rep) - camera.getX());

        }
        else{
            right.getImageView().setX(800 * (rep-1) - camera.getX());
            left.getImageView().setX(800 * (rep) - camera.getX());
        }


        hero.getImageView().setX(hero.getX()-camera.getX());
        hero.getImageView().setY(hero.getY()-camera.getY());

        for(Foe foe:Foes){
            foe.getImageView().setX(foe.getX()-camera.getX());
            foe.getImageView().setY(foe.getY()-camera.getY());

            //System.out.println(hero.hitbox+"\t"+foe.hitbox);

        }







    }
}
