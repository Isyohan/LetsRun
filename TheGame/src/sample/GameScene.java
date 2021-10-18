package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    int numberOfLives=3;
    Camera camera;
    staticThing left,right;
    Hero hero;

    public GameScene(Pane pane,int v,int v1,Boolean b,Camera cam) {
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

        this.hero=new Hero(100,250);
        pane.getChildren().add(this.hero.getImageView());

        final long startNanoTime = System.nanoTime();

        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long time) {
                double t = (time - startNanoTime) / 1000000000.0;

                cam.update(t);
                hero.update(t);

            }

        };
        timer.start();



    }

    public void update(long time){
        hero.update(time);
    }
}
