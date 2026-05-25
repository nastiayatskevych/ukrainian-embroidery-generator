package ua.ukma.embroidery.canvas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ua.ukma.embroidery.model.EmbroideryConfig;

public class EmbroideryCanvas {

    private final GridPane grid;
    private final Rectangle[][] cells;
    private Color currentColor;
    private final Color[][] pattern;

    public EmbroideryCanvas() {
        this.grid = new GridPane();
        this.cells = new Rectangle[EmbroideryConfig.ROWS][EmbroideryConfig.COLS];
        this.currentColor = Color.BLACK;
        this.pattern = new Color[EmbroideryConfig.ROWS][EmbroideryConfig.COLS];

        createGrid();
    }

    private void createGrid() {
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("embroidery-grid");

        for (int row = 0; row < EmbroideryConfig.ROWS; row++) {
            for (int col = 0; col < EmbroideryConfig.COLS; col++) {
                Rectangle cell = new Rectangle(
                        EmbroideryConfig.CELL_SIZE,
                        EmbroideryConfig.CELL_SIZE
                );

                cell.setFill(Color.WHITE);
                cell.setStroke(Color.LIGHTGRAY);
                cell.getStyleClass().add("grid-cell");

                pattern[row][col] = Color.WHITE;

                final int currentRow = row;
                final int currentCol = col;

                cell.setOnMouseClicked(event -> {
                    setCellColor(currentRow, currentCol, currentColor);
                });

                cells[row][col] = cell;
                grid.add(cell, col, row);
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void useEraser() {
        this.currentColor = Color.WHITE;
    }
    public Color[][] getPattern() {
        return pattern;
    }

    public void setCellColor(int row, int col, Color color) {
        cells[row][col].setFill(color);
        pattern[row][col] = color;
    }

    public void clear() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                setCellColor(row, col, Color.WHITE);
            }
        }
    }
}