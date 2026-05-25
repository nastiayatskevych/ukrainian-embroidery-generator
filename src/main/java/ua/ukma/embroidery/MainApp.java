package ua.ukma.embroidery;
//mvn javafx:run
import javafx.application.Application;
import javafx.stage.Stage;
import ua.ukma.embroidery.ui.WelcomeScene;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ukrainian Embroidery Generator");
        stage.setScene(WelcomeScene.create(stage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}