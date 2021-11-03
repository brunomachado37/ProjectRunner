import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GameScene extends Scene{
    private Camera cam;
    private NotMovingThing leftBg;
    private NotMovingThing rightBg;
    private NotMovingThing lives;
    private Integer numberOfLives = 3;
    private Hero hero;
    private ArrayList<Foe> foes;
    private Integer numberOfFoes;


    public GameScene(Parent root, Integer w, Integer h) {
        super(root, w, h);

        this.leftBg = new NotMovingThing("desert.png", 0, 0, 800, 400);
        this.rightBg = new NotMovingThing("desert.png", 800, 0, 800, 400);

        this.lives = new NotMovingThing("3hearts.png", 10, 10, 204, 50);

        this.hero = new Hero(0, 240, "heroes.png", 8, 0, 80, 100);
        this.cam = new Camera(300, 0, this.hero);

        this.foes = new ArrayList<Foe>();
        Integer FoeX = 2000;
        Random r = new Random();

        this.numberOfFoes = ThreadLocalRandom.current().nextInt(10, 21);
        for (Integer f = 0; f <= numberOfFoes; f++){
            double offset = 1 + (3 - 1) * r.nextDouble();               // Random double between 1 and 3
            offset = offset*400;
            FoeX = FoeX + (int) offset;
            this.foes.add(new Foe(FoeX, 260, "jinn.png", 0, 0, 50, 80));
        }


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long time){
                hero.update(time);
                for (Foe foe: foes) {
                    foe.update();
                }
                cam.update();
                render();
                }
        };

        this.setOnMouseClicked( (event)->{
            hero.jump();
        });

        timer.start();

    }

    private void render(){

        double offset = this.cam.getX() % 800;
        this.leftBg.getSprite().setX(- offset);
        this.rightBg.getSprite().setX(800 - offset);

        this.hero.getSprite().setX(this.hero.getX() - this.cam.getX());
        this.hero.getSprite().setY(this.hero.getY());

        for (Foe foe: foes) {
            foe.getSprite().setX(foe.getX() - this.cam.getX());
        }

        if (this.hero.isNotInvincible()) {
            for (Foe foe : this.foes) {
                if (foe.getHitBox().intersects(hero.getHitBox())) {
                    hero.setInvincibility(3000000000.0);
                    loseLife();
                    lives.getSprite().setViewport(new Rectangle2D(0,0,(this.numberOfLives) * 70 + 1,50));
                }
            }
        }

        if (this.hero.getX() > this.foes.get(numberOfFoes).getX() + 1000){
            System.out.println("You Won!");
            // CALL WIN GAME
        }

    }

    public void loseLife() {
        this.numberOfLives--;

        if (this.numberOfLives == 0) {
            System.out.println("Game Over!");
            // CALL LOSE GAME
        }
    }

    public NotMovingThing getLeftBg() {
        return leftBg;
    }

    public NotMovingThing getLives() {
        return lives;
    }

    public NotMovingThing getRightBg() {
        return rightBg;
    }

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Foe> getFoes() {
        return foes;
    }
}
