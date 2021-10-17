package sample;

import javafx.geometry.Point2D;

public class Hero extends AnimatedThing{

    public Hero(double x,double y){
        super("img/heros.png",20,0,new Point2D(65,100),5,10,0);
        this.getImageView().setX(x);
        this.getImageView().setY(y);

    }
}
