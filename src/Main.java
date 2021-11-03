import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.management.MonitorInfo;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("First Stage");
        Group root = new Group();
        Pane pane = new Pane(root);

        GameScene scene = new GameScene(pane, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();

        root.getChildren().add(scene.getLeftBg().getSprite());
        root.getChildren().add(scene.getRightBg().getSprite());
        root.getChildren().add(scene.getLives().getSprite());
        root.getChildren().add(scene.getHero().getSprite());
        for (Foe foe : scene.getFoes()) {
            root.getChildren().add(foe.getSprite());
        }

        //root.getChildren().add(scene.getFoes().get(0).getSprite());

    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}