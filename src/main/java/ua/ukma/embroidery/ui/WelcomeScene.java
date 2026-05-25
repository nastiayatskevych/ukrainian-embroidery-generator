package ua.ukma.embroidery.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScene {

    public static Scene create(Stage stage) {
        VBox root = new VBox();
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root-pane");

        Label title = new Label("Вітаємо у редакторі української вишивки!");
        title.getStyleClass().add("welcome-title");

        Button startButton = new Button("Почати роботу");
        Button exitButton = new Button("Вийти");

        startButton.getStyleClass().add("menu-button");
        exitButton.getStyleClass().add("menu-button");

        startButton.setPrefSize(250, 60);
        exitButton.setPrefSize(250, 60);

        startButton.setOnAction(event -> {
            stage.setScene(DrawingScene.create(stage));
        });

        exitButton.setOnAction(event -> {
            stage.close();
        });

        root.getChildren().addAll(title, startButton, exitButton);

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(
                WelcomeScene.class.getResource("/styles.css").toExternalForm()
        );

        return scene;
    }
}