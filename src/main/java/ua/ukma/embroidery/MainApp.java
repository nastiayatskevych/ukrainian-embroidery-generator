package ua.ukma.embroidery;
//mvn javafx:run
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.ukma.embroidery.ui.DrawingScene;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = DrawingScene.create();

        stage.setTitle("Ukrainian Embroidery Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}