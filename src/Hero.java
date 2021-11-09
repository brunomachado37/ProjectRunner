import javafx.geometry.Rectangle2D;

public class Hero extends MovingThing {

    // X axis variables
    private double vx = 500;            // Speed
    private double px;                  // Previous position
    private double dx;                  // Position variation
    private static double dt = 0.016;   // Time variation

    // Y axis variables
    private static double g = 2000;     // Gravity
    private double ay = 0;              // Acceleration
    private double vy;                  // Speed
    private double pvy = 0;             // Previous Speed
    private double dvy;                 // Speed variation
    private double py;                  // Previous position
    private double dy;                  // Position variation

    private long previousTime;
    private double invincibility = 0;

    private Integer lives = 3;


    Hero(double x_pos, double y_pos, String filepath, Integer x, Integer y, Integer width, Integer height) {
        super(x_pos, y_pos, filepath, x, y, width, height);

        this.px = x_pos;
        this.py = y_pos;

        Integer[] offset = new Integer[6];

        offset[0] = 8;
        offset[1] = 88;
        offset[2] = 172;
        offset[3] = 261;
        offset[4] = 340;
        offset[5] = 424;

        this.setOffset(offset);
        super.setMaxAnimationIndex(offset.length - 1);
    }

    public void jump() {

        if (this.getY() == 240) {
            this.ay = 45000;
        }
    }

    public void update(long time) {
        super.update();

        // X vatriation
        dx = vx * dt;
        this.setX(px + dx);
        px = px + dx;


        // Y vatriation
        dvy = (g - ay) * dt;
        ay = 0;
        vy = pvy + dvy;
        dy = vy * dt;
        this.setY(py + dy);

        if (this.getY() >= 240) {
            py = 240;
            this.setY(240);
            pvy = 0;
        }

        else {
            py = this.getY();
            pvy = vy;
            if (vy < 0){
                this.getSprite().setViewport(new Rectangle2D(0, 162, 80, 100));
            }
            else{
                this.getSprite().setViewport(new Rectangle2D(81, 162, 80, 100));
            }
        }

        if (this.invincibility > 0) {
            this.invincibility -= (time - this.previousTime);
            if (this.getAnimationIndex() % 2 == 0) {
                this.getSprite().setViewport(new Rectangle2D(0, 0, 1, 1));
            }
        }

        this.previousTime = time;

    }

    public boolean isNotInvincible() {
        return !(invincibility > 0);
    }

    public void setInvincibility(double invincibility) {
        this.invincibility = invincibility;
    }
}
