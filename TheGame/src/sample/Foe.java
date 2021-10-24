package sample;

import javafx.geometry.Point2D;

public class Foe extends AnimatedThing{
    public Foe(double x,double y){
        super("img/Foe.png",x,y,new Point2D(50,50),0,0,0);
    }



    @Override
    public void updateAttitude() {

    }
}
