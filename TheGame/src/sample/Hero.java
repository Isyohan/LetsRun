package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{


    private final double g=0.1;
    //private final double f=100000;
    private final double m=20;
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
        if (y>=150+windowSize.getY()){
            f_y+=125;
        }
    }
    public void speed_var(double var){
        if (v_x+var>0) {
            v_x += var;
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
    }



    @Override
    public void update(double t) {
        super.update(t);
        updateAttitude();

        //a_y=(g-f_y-v_y/f)/m;
        a_y=g-f_y/m;
        v_y+=a_y;
        y+=v_y;
        if (y>150+windowSize.getY()){
            if (v_y>0){
                v_y=0;
            }
            y=150+windowSize.getY();
        }

        x+=v_x;
        setForces(0,0);
    }
}
