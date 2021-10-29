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
    private final double InitialSpeed=1;

    private final int desertSizeX=800;
    private final int desertSizeY=400;

    private final int numberOfEnemy=3;



    public GameScene(Pane pane,int v,int v1,Boolean b,Camera cam,Hero hero) {
        super(pane,v,v1,b);
        this.camera=cam;


        this.left=new staticThing("img/desert.png",0,0);
        //this.left=new staticThing("img/desert.png",0,0);

        left.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(left.getImageView());

        this.right=new staticThing("img/desert.png",desertSizeX,0);
        //this.right=new staticThing("img/desert.png",800,0);

        right.getImageView().setViewport(new Rectangle2D(0,0,desertSizeX,desertSizeY));
        pane.getChildren().add(right.getImageView());

        this.hero=hero;
        int numberOfLives=hero.numberOfLives;
        int maxNumberOfLives=hero.maxNumberOfLives;

        /*
        ImageView[]  lifeslist=new ImageView[numberOfLives];
        for(int i=0;i<numberOfLives;i++){
            lifeslist[i]=new ImageView("img/heart.png");
            lifeslist[i].setX(48*i);
            lifeslist[i].setY(0);
            lifeslist[i].setViewport(new Rectangle2D(0,0,48,48));
            pane.getChildren().add(lifeslist[i]);
        }
        */



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

        for (int i=0;i<numberOfEnemy;i++){
            double x= Math.random()*2000-1000;
            Foe foe=new Foe(1000*i+x,300);
            System.out.println(foe.getX());
            Foes.add(foe);
            pane.getChildren().add(foe.getImageView());
        }




    }

    public void update(double t){



        right.getImageView().setY(-camera.getY());
        left.getImageView().setY(-camera.getY());


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
        //ALED
        hero.getImageView().setX(hero.getX()-camera.getX());
        hero.getImageView().setY(hero.getY()-camera.getY());


        for(Heart heart:Life){
            if (heart.getIndex()+1> hero.numberOfLives){
                heart.getImageView().setViewport(new Rectangle2D(heart.sizeX,0,heart.sizeX,heart.sizeY));
            }
        }





        for(Foe foe:Foes){
            foe.getImageView().setX(foe.getX()-camera.getX());
            foe.getImageView().setY(foe.getY()-camera.getY());
            if (hero.Interserct(foe.hitbox)){
                foe.touched(hero);
                //System.out.println(hero.numberOfLives);
            }


        }
        if (hero.numberOfLives==0){
            getWindow().hide();
        }
        if (hero.getX()>numberOfEnemy*1000){
            System.out.println("C'est WIN !!");
        }

    }

}
