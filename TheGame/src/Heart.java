
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heart {
    protected double x,y;
    protected final int sizeX=48,sizeY=48;
    protected int index;
    protected ImageView imageView;

    public Heart(int i){
        index=i;
        y=0;
        x=48*i;
        imageView=new ImageView(new Image("hearts.png"));
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getIndex() {
        return index;
    }
}
