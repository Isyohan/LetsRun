package sample;

public class Camera {
    private int x;
    private int y;

    public Camera(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Camera(){
        this.x=0;
        this.y=0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(double t){
        x++;

    }

    @Override
    public String toString() {
        return this.x+","+this.y;
    }
}
