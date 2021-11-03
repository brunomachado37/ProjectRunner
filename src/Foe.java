public class Foe extends MovingThing{

    Foe(double x_pos, double y_pos, String filepath, Integer x, Integer y, Integer width, Integer height){
        super(x_pos, y_pos, filepath, x, y, width, height);

        Integer[] offset = new Integer[4];

        offset[0] = 0;
        offset[1] = 125;
        offset[2] = 256;
        offset[3] = 384;

        this.setOffset(offset);
        this.setMaxAnimationIndex(offset.length - 1);
    }

    public void update(){
        super.update();

        this.setX(this.getX() - 2);
    }

}
