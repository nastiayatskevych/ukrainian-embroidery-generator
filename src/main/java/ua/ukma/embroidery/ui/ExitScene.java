package ua.ukma.embroidery.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.ukma.embroidery.canvas.EmbroideryCanvas;
import ua.ukma.embroidery.service.FileService;

public class ExitScene {

    public static Scene create(Stage stage, EmbroideryCanvas embroideryCanvas) {
        VBox root = new VBox();
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root-pane");

        Label question = new Label("Бажаєте зберегти малюнок перед виходом?");
        question.getStyleClass().add("welcome-title");

        Button saveButton = new Button("Так, зберегти");
        Button exitButton = new Button("Ні, вийти");
        Button backButton = new Button("Повернутись до роботи");

        saveButton.getStyleClass().add("menu-button");
        exitButton.getStyleClass().add("menu-button");
        backButton.getStyleClass().add("menu-button");

        saveButton.setPrefSize(280, 60);
        exitButton.setPrefSize(280, 60);
        backButton.setPrefSize(280, 60);

        saveButton.setOnAction(event -> {
            FileService.saveNodeAsPng(
                    embroideryCanvas.getGrid(),
                    stage.getScene().getWindow()
            );
            stage.close();
        });

        exitButton.setOnAction(event -> {
            stage.close();
        });

        backButton.setOnAction(event -> {
            stage.setScene(DrawingScene.create(stage));
        });

        root.getChildren().addAll(
                question,
                saveButton,
                exitButton,
                backButton
        );

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(
                ExitScene.class.getResource("/styles.css").toExternalForm()
        );

        return scene;
    }
}