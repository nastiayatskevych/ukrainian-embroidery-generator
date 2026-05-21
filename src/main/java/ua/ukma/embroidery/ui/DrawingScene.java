package ua.ukma.embroidery.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DrawingScene {

    public static Scene create() {
        BorderPane root = new BorderPane();

        VBox leftPanel = new VBox();
        VBox rightPanel = new VBox();
        HBox topPanel = new HBox();
        HBox bottomPanel = new HBox();

        GridPane grid = createEmbroideryGrid();


        //left
        Button eraserButton = new Button("Гумка");
        Button clearAllButton = new Button("Очистити все");

        //top
        Button horizontalButton = new Button("Горизонтально");
        Button verticalButton = new Button("Вертикально");

        //right
        Button openButton = new Button("Відкрити");
        Button saveButton = new Button("Зберегти");

        // bottom
        Button finishButton = new Button("Завершити малювання");


        setButtonSize(eraserButton);
        setButtonSize(clearAllButton);
        setButtonSize(horizontalButton);
        setButtonSize(verticalButton);
        setButtonSize(openButton);
        setButtonSize(saveButton);

        finishButton.setPrefSize(230, 45);

        //left panel
        leftPanel.getChildren().addAll(eraserButton, clearAllButton);
        leftPanel.setSpacing(20);
        leftPanel.setPadding(new Insets(120, 20, 20, 20));
        leftPanel.setPrefWidth(200);
        leftPanel.setAlignment(Pos.TOP_CENTER);

        //right panel
        rightPanel.getChildren().addAll(openButton, saveButton);
        rightPanel.setSpacing(20);
        rightPanel.setPadding(new Insets(120, 20, 20, 20));
        rightPanel.setPrefWidth(200);
        rightPanel.setAlignment(Pos.TOP_CENTER);


        // top panel
        topPanel.getChildren().addAll(horizontalButton, verticalButton);
        topPanel.setSpacing(20);
        topPanel.setPadding(new Insets(20));
        topPanel.setAlignment(Pos.CENTER);


        bottomPanel.getChildren().add(finishButton);
        bottomPanel.setPadding(new Insets(20));
        bottomPanel.setAlignment(Pos.CENTER);

        root.setTop(topPanel);
        root.setLeft(leftPanel);
        root.setCenter(grid);
        root.setRight(rightPanel);
        root.setBottom(bottomPanel);

        return new Scene(root, 1200, 800);

    }
    private static void setButtonSize(Button button) {
        button.setPrefSize(200, 60);
    }
    private static GridPane createEmbroideryGrid() {
        GridPane grid = new GridPane();

        int rows = 20;
        int cols = 20;
        int cellSize = 25;

        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle cell = new Rectangle(cellSize, cellSize);

                cell.setFill(Color.WHITE);
                cell.setStroke(Color.LIGHTGRAY);

                grid.add(cell, col, row);
            }
        }

        return grid;
    }

}