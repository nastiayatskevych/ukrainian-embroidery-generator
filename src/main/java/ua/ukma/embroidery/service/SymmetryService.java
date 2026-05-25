package ua.ukma.embroidery.service;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SymmetryService {

    private SymmetryService() {
    }

    public static void applyHorizontalSymmetry(Rectangle[][] cells) {
        int rows = cells.length;
        int cols = cells[0].length;

        for (int row = 0; row < rows / 2; row++) {
            int mirroredRow = rows - 1 - row;

            for (int col = 0; col < cols; col++) {
                Color color = (Color) cells[row][col].getFill();
                cells[mirroredRow][col].setFill(color);
            }
        }
    }

    public static void applyVerticalSymmetry(Rectangle[][] cells) {
        int rows = cells.length;
        int cols = cells[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols / 2; col++) {
                int mirroredCol = cols - 1 - col;

                Color color = (Color) cells[row][col].getFill();
                cells[row][mirroredCol].setFill(color);
            }
        }
    }
}