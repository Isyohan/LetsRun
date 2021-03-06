
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


import static java.lang.Boolean.*;

public class Hero extends AnimatedThing{


    protected final int maxNumberOfLives =3;
    protected int numberOfLives =maxNumberOfLives;
    private final double g=0.3;
    private final double m=15;
    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;
    protected final int yGround=150;

    protected final double invincibilityTime=1.5;
    protected double oldtime=0;

    protected boolean flag=FALSE;

    public Hero(double x,double y){
        super("heros.png",x,y,new Point2D(75,100),5,0.075,10);
    }

    public void ouch(){
        if (!invincibilityOn) {
            numberOfLives--;
            invincibilityOn = TRUE;
            flag=TRUE;
        }
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
    public void stop(){
        v_x=0;
        v_y=0;
    }
    public void reset(){
        x=0;
        y=0;
    }
    public void speed_var(double var){
        v_x += var;
    }
    public void forcex_var(double f_var){
        f_x+=f_var;
        if (v_x<0){
            v_x=1;
        }
        int v_max=100;
        if (v_x>v_max){
            v_x=v_max;
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
            attitute = Attitude.RUNNING;
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
        if (v_x<0){
            v_x=1;
        }
        if (invincibilityOn==TRUE){
            if (flag){
                oldtime=t;
                flag=FALSE;
            }else if (t-oldtime>invincibilityTime){
                invincibilityOn=FALSE;
                flag=TRUE;
            }
        }
    }
}
