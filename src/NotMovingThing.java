import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NotMovingThing {

    private double x;
    private double y;

    private ImageView sprite;

    NotMovingThing(String filepath, Integer x, Integer y, Integer width, Integer height){
        Image img = new Image(filepath);
        this.sprite = new ImageView(img);
        this.sprite.setViewport(new Rectangle2D(0, 0, width, height));
        this.sprite.setX(x);
        this.sprite.setY(y);
    }

    public ImageView getSprite() {
        return this.sprite;
    }

}
