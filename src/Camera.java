public class Camera {

    private Integer x;
    private Integer y;
    private MovingThing follow;

    private double ax;                  // Acceleration
    private double vx = 0;              // Speed
    private double pvx = 0;             // Previous Speed
    private double dvx;                 // Speed variation
    private double px;                  // Previous position
    private double dx;                  // Position variation
    private static double dt = 0.016;   // Time variation

    Camera(Integer x, Integer y, MovingThing f){
        this.x = x;
        this.px = x;
        this.y = y;
        this.follow = f;
    }

    public void update(){
        ax = 5 * (follow.getX() - x) - 1 * vx;
        dvx = ax * dt;
        vx = pvx + dvx;
        dx = vx * dt;
        x = (int) Math.round(px + dx);

        px = x;
        pvx = vx;
    }

    public Integer getX(){
        return x;
    }


    @Override
    public String toString(){
        return x.toString() + "," + y.toString();
    }

}
