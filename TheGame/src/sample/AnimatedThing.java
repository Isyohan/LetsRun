package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    enum Attitude{JUMPING_UP,JUMPING_DOWN,RUNNING}
    protected double x,y;

    protected ImageView imageView;
    protected Attitude attitute;



    protected int index;
    protected int indexMax;
    protected double duration;
    protected Point2D windowSize;
    protected int offset;

    public AnimatedThing(String fileName,double x,double y,Point2D windowSize,int indexMax,double duration,int offset){
        this.imageView=new ImageView(new Image(fileName));
        this.windowSize=windowSize;
        this.indexMax=indexMax;
        this.duration=duration;
        this.offset=offset;

        this.imageView.setViewport(new Rectangle2D(0,0,this.windowSize.getX(),this.windowSize.getY()));
        this.x=x;
        this.y=y;
        this.imageView.setX(x);
        this.imageView.setY(y);

    }

    public ImageView getImageView() {
        return imageView;
    }
    public void update(double t){
        index=(int) ((t%(indexMax*duration))/duration);
        if(attitute==Attitude.RUNNING){
            this.imageView.setViewport(new Rectangle2D(index*(windowSize.getX()+offset),0, windowSize.getX()+offset,100));
        }else if (attitute==Attitude.JUMPING_UP){
            this.imageView.setViewport(new Rectangle2D(offset,160,windowSize.getX()+offset,windowSize.getY()));
        }else if(attitute==Attitude.JUMPING_DOWN){
            this.imageView.setViewport(new Rectangle2D(95,160,windowSize.getX()+offset,windowSize.getY()));
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void updateAttitude();
}
