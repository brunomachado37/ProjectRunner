// add any usefull package line

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage primaryStage){
        primaryStage.setTitle("Demo");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        MovingThing hero = new MovingThing(0, 0,"..\\img\\heros.png", 20, 0, 100, 50);
        //root.getChildren().add(hero.getSprite());
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}