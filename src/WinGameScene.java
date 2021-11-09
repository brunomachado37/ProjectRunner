import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinGameScene extends Scene {

    public WinGameScene(Group root, Integer w, Integer h, Stage stage) {
        super(root, w, h);

        NotMovingThing bg = new NotMovingThing("desert.png", 0, 0, 800, 400);
        NotMovingThing grey = new NotMovingThing("grey.png", 0, 0, 800, 400);
        Text title = new Text (80, 180, "You Won!");
        title.setFont(Font.font ("TimesNewRoman", 100));
        title.setFill(Color.DARKGREEN);

        Text instruction = new Text(155, 250, "Click anywhere to restart!");
        instruction.setFont(Font.font ("TimesNewRoman", FontWeight.THIN, FontPosture.ITALIC, 25));
        instruction.setFill(Color.DARKGREEN);


        root.getChildren().add(bg.getSprite());
        root.getChildren().add(grey.getSprite());
        root.getChildren().add(title);
        root.getChildren().add(instruction);

        stage.setScene(this);
        stage.show();

        this.setOnMouseClicked( (event)->{
            Group root2 = new Group();
            GameScene game = new GameScene(root2, 600, 400, stage);
            stage.setScene(game);
            stage.show();
        });


    }

}
