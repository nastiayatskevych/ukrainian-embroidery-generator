package ua.ukma.embroidery.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DrawingScene {

    public static Scene create() {

        VBox root = new VBox();

        Button button = new Button("Тест");

        root.getChildren().add(button);

        return new Scene(root, 800, 600);
    }
}