package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


import static java.lang.Boolean.*;

public class Hero extends AnimatedThing{


    protected final int maxNumberOfLives =3;
    protected int numberOfLives =maxNumberOfLives;
    private final double g=0.2;
    //private final double f=100000;
    private final double m=15;
    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;
    protected final int yGround=150;

    public Hero(double x,double y){
        super("img/heros.png",x,y,new Point2D(75,100),5,0.075,10);
    }

    public void ouch(){
        numberOfLives--;
    }

    public void setForces(double Fx,double Fy){
        f_x=Fx;
        f_y=Fy;
    }
    public void jump(){
        if (y>=yGround+windowSize.getY()){
            f_y+=125;
        }
    }
    public void speed_var(double var){
        v_x += var;
    }
    public void forcex_var(double f_var){
        f_x+=f_var;
        if (v_x<-1){
            v_x=-1;
        }
    }
    public void setSpeedx(double vx){
        v_x=vx;
    }

    @Override
    public void updateAttitude() {
        if (v_y>=0.1){
            attitute=Attitude.JUMPING_DOWN;
        }
        else if (v_y<=-0.1){
            attitute=Attitude.JUMPING_UP;
        }
        else{
            attitute=Attitude.RUNNING;
        }
        if (v_x==0){
            attitute=Attitude.IDLE;
        }
    }



    @Override
    public void update(double t) {
        super.update(t);
        updateAttitude();

        a_y =( g - f_y / m);
        v_y += a_y;
        y += v_y;
        if (y > yGround + windowSize.getY()) {
            if (v_y > 0) {
                v_y = 0;
            }
            y = yGround + windowSize.getY();
        }
        a_x=f_x/m;
        v_x+=a_x;
        x += v_x;

        setForces(0, 0);

    }
}
