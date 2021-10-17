package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    private double x,y;
    private ImageView imageView;
    private int attitute;

    private int index;
    private int indexMax;
    private double duration;
    private Point2D windowSize;
    private int offset;

    public AnimatedThing(String fileName,double x,double y,Point2D windowSize,int indexMax,double duration,int offset){
        this.imageView=new ImageView(new Image(fileName));
        this.x=x;
        this.y=y;
        this.windowSize=windowSize;
        this.indexMax=indexMax;
        this.duration=duration;
        this.offset=offset;

        this.imageView.setViewport(new Rectangle2D(this.x,this.y,this.windowSize.getX(),this.windowSize.getY()));

    }

    public ImageView getImageView() {
        return imageView;
    }
    public void update(double t){
        //this.imageView.setViewport(new Rectangle2D(offset,0,200,200));
        index=(int) ((t%(indexMax*duration))/duration);
        this.imageView.setViewport(new Rectangle2D(index*(windowSize.getX()+offset),0, windowSize.getX()+offset,100));
        System.out.println(index);
    }
}
