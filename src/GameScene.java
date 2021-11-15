import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    private Stage stage;
    private AnimationTimer timer;


    public GameScene(Group root, Integer w, Integer h, Stage stage) {
        super(root, w, h);

        this.leftBg = new NotMovingThing("desert.png", 0, 0, 800, 400);
        this.rightBg = new NotMovingThing("desert.png", 800, 0, 800, 400);

        this.lives = new NotMovingThing("3hearts.png", 10, 10, 204, 50);
        this.stage = stage;

        this.hero = new Hero(0, 240, "heroes.png", 8, 0, 80, 100);
        this.cam = new Camera(300, 0, this.hero);

        this.foes = new ArrayList<>();
        Integer FoeX = 2000;                                                            // Starting x position for the foes
        Random r = new Random();

        this.numberOfFoes = ThreadLocalRandom.current().nextInt(10, 21);    // Random integer between 10 and 20
        for (Integer f = 0; f <= numberOfFoes; f++){
            double offset = 1 + (3 - 1) * r.nextDouble();                               // Random double between 1 and 3
            offset = offset*400;                                                        // Minimum distance between 2 foes = 400
            FoeX = FoeX + (int) offset;
            this.foes.add(new Foe(FoeX, 260, "jinn.png", 0, 0, 50, 80));
        }


        this.timer = new AnimationTimer() {
            public void handle(long time){
                hero.update(time);
                for (Foe foe: foes) {
                    foe.update();
                }
                cam.update();
                render();
                }
        };

        this.setOnMouseClicked( (event) -> hero.jump());

        timer.start();


        root.getChildren().add(this.leftBg.getSprite());
        root.getChildren().add(this.rightBg.getSprite());
        root.getChildren().add(this.lives.getSprite());
        root.getChildren().add(this.hero.getSprite());
        for (Foe foe : this.foes) {
            root.getChildren().add(foe.getSprite());
        }
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

        if ((this.hero.getX() > this.foes.get(numberOfFoes).getX() + 1000) && (this.numberOfLives > 0)){

            // Call Win Game
            this.timer.stop();
            Group root2 = new Group();
            WinGameScene game = new WinGameScene(root2, 600, 400, this.stage);
            this.stage.setScene(game);
            this.stage.show();
        }

    }

    public void loseLife() {
        this.numberOfLives--;

        if (this.numberOfLives == 0) {
            // Call Game Over
            this.timer.stop();
            Group root2 = new Group();
            GameOverScene game = new GameOverScene(root2, 600, 400, this.stage);
            this.stage.setScene(game);
            this.stage.show();
        }
    }
}
