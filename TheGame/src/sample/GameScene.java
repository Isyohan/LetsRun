package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    int lifes=3;
    Camera camera;
    staticThing left,right;

    public GameScene(Pane pane,int v,int v1,Boolean b,Camera cam) {
        super(pane,v,v1,b);
        this.camera=cam;
        this.left=new staticThing("img/desert.png",0,0);
        left.getImageView().setViewport(new Rectangle2D(0,0,800,400));
        pane.getChildren().add(left.getImageView());

        this.right=new staticThing("img/desert.png",800,0);
        right.getImageView().setViewport(new Rectangle2D(0,0,800,400));
        pane.getChildren().add(right.getImageView());

        staticThing[]  lifeslist=new staticThing[lifes];
        for(int i=0;i<lifes;i++){
            lifeslist[i]=new staticThing("img/heart.png",48*i,0);
            lifeslist[i].getImageView().setViewport(new Rectangle2D(0,0,48,48));
            pane.getChildren().add(lifeslist[i].getImageView());
        }


    }
}