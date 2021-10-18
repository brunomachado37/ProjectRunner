import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovingThing {
    private double x;
    private double y;

    public ImageView getSprite() {
        return sprite;
    }

    private ImageView sprite;

    MovingThing(double x_pos, double y_pos, String filepath, Integer x, Integer y, Integer height, Integer width){
        Image img = new Image(filepath);
        sprite = new ImageView(img);
        sprite.setViewport(new Rectangle2D(x, y, height, width));
    }
}
