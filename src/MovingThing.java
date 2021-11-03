import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovingThing {
    private double x;
    private double y;
    private ImageView sprite;

    private Integer animationIndex = 0;
    private static Integer maxAnimationIndex;
    private Integer[] offset;

    private static Integer timeStepsBetweenFrames = 5;
    private Integer timeStepIndex = 0;

    private Integer windowWidth;
    private Integer windowHeight;


    MovingThing(double x_pos, double y_pos, String filepath, Integer x, Integer y, Integer width, Integer height){
        this.x = x_pos;
        this.y = y_pos;
        this.windowWidth = width;
        this.windowHeight = height;

        Image img = new Image(filepath);
        this.sprite = new ImageView(img);
        this.sprite.setViewport(new Rectangle2D(x, y, this.windowWidth, this.windowHeight));

        this.sprite.setX(this.x);
        this.sprite.setY(this.y);

    }

    public void update(){

        // Animation
        if (this.timeStepIndex >= this.timeStepsBetweenFrames) {
            this.getSprite().setViewport(new Rectangle2D(this.offset[this.animationIndex], 0, this.windowWidth, this.windowHeight));

            if (this.animationIndex < maxAnimationIndex) {
                this.animationIndex++;
            } else {
                this.animationIndex = 0;
            }

            this.timeStepIndex = 0;
        }
        else{
            this.timeStepIndex ++;
        }
    }

    public Rectangle2D getHitBox(){

        return new Rectangle2D(this.x, this.y, this.windowWidth, this.windowHeight);

    }

    public void setOffset(Integer[] offset) {
        this.offset = offset;
    }

    public ImageView getSprite() {
        return this.sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static void setMaxAnimationIndex(Integer maxAnimationIndex) {
        MovingThing.maxAnimationIndex = maxAnimationIndex;
    }

    public Integer getAnimationIndex() {
        return animationIndex;
    }

}
