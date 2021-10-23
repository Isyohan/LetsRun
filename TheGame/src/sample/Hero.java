package sample;

import javafx.geometry.Point2D;

public class Hero extends AnimatedThing{

    private final double g=3;
    private final double f=100000;
    private final double m=1;
    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;

    public Hero(double x,double y){
        super("img/heros.png",x,y,new Point2D(75,100),5,0.075,10);
    }

    public void setForces(double Fx,double Fy){
        f_x=Fx;
        f_y=Fy;
    }
    public void jump(){
        f_y+=150;
    }

    @Override
    public void update(double t) {
        super.update(t);

        a_y=(g-f_y-v_y/f)/m;
        v_y+=a_y;
        y+=v_y;
        if (y>150+windowSize.getY()){
            y=150+windowSize.getY();
        }
        v_x=5;
        v_y=0;
        x+=v_x;
        setForces(0,0);
    }
}
