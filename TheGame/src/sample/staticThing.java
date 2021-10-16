package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double x,y;
    private ImageView imageView;

    public staticThing(String fileName,double x,double y){
        this.imageView=new ImageView(new Image(fileName));
        this.x=x;
        this.y=y;
        this.imageView.setY(y);
        this.imageView.setX(x);
    }
    public staticThing(String fileName){
        this.imageView=new ImageView(new Image(fileName));
    }

    public ImageView getImageView() {
        return imageView;
    }
}
