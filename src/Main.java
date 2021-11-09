import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage stage){
        stage.setTitle("Runner");
        Group root = new Group();
        StartScene start = new StartScene(root, 600, 400, stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}