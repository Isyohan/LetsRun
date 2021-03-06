
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public class Foe extends AnimatedThing{

    private double v_x,v_y;
    private double a_x,a_y;
    private double f_x,f_y;
    private boolean touched;
    protected final int yGround=250;
    private final double g=0.2;
    private final double m=20;



    public Foe(double x,double y){

        super("enemy.png",x,y,new Point2D(50,50),0,0,0);
        imageView.setViewport(new Rectangle2D( 0,0,50,50));
    }



    @Override
    public void updateAttitude() {

    }
    public void touched(Hero hero){
        if (attitute!=Attitude.DEAD ) {
            attitute = Attitude.DEAD;
            hero.ouch();
            if (hero.numberOfLives==0 ){
                System.out.println("GAME OVER");
            }
        }
    }

    @Override
    public void update(double t) {
        super.update(t);

        a_y =( g - f_y / m);
        v_y += a_y;
        y += v_y;
        if (y > yGround + windowSize.getY()) {
            if (v_y > 0) {
                v_y = 0;
            }
            y = yGround + windowSize.getY();
        }



    }
}
