package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public class Foe extends AnimatedThing{

    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;
    private boolean touched;



    public Foe(double x,double y){
        super("img/enemy.png",x,y,new Point2D(50,50),0,0,0);
    }



    @Override
    public void updateAttitude() {

    }
    public void touched(Hero hero){
        if (attitute!=Attitude.DEAD) {
            attitute = Attitude.DEAD;
            hero.ouch();
            if (hero.numberOfLives!=0){
                System.out.println("Touché !  nb de vie : " + hero.numberOfLives);
            }else{
                System.out.println("GAME OVER");
            }
        }
    }

    @Override
    public void update(double t) {
        super.update(t);



    }
}
