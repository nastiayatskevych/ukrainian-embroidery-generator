package ua.ukma.embroidery.service;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileService {

    private FileService() {
    }

    public static void saveNodeAsPng(Node node, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Зберегти схему");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG files", "*.png")
        );
        fileChooser.setInitialFileName("embroidery.png");

        File file = fileChooser.showSaveDialog(window);

        if (file == null) {
            return;
        }

        WritableImage image = node.snapshot(new SnapshotParameters(), null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openImageAsBackground(GridPane grid, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрити зображення");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG images", "*.png")
        );

        File file = fileChooser.showOpenDialog(window);

        if (file == null) {
            return;
        }

        Image image = new Image(file.toURI().toString());

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100,
                        100,
                        true,
                        true,
                        true,
                        false
                )
        );

        grid.setBackground(new Background(backgroundImage));
    }

    public static void openImage(ImageView imageView, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Відкрити зображення");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PNG images", "*.png")
        );

        File file = fileChooser.showOpenDialog(window);

        if (file == null) {
            return;
        }

        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

}