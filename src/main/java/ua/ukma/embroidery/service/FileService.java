package ua.ukma.embroidery.service;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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
}