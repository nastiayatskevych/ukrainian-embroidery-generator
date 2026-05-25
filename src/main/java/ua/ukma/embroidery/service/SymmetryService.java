package ua.ukma.embroidery.service;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ua.ukma.embroidery.canvas.EmbroideryCanvas;

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
    public static void duplicateFragment(EmbroideryCanvas canvas) {
        Color[][] pattern = canvas.getPattern();

        int rows = pattern.length;
        int cols = pattern[0].length;

        int minRow = rows;
        int maxRow = -1;
        int minCol = cols;
        int maxCol = -1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!pattern[row][col].equals(Color.WHITE)) {
                    minRow = Math.min(minRow, row);
                    maxRow = Math.max(maxRow, row);
                    minCol = Math.min(minCol, col);
                    maxCol = Math.max(maxCol, col);
                }
            }
        }

        if (maxRow == -1) {
            return;
        }

        int fragmentRows = maxRow - minRow + 1;
        int fragmentCols = maxCol - minCol + 1;

        Color[][] fragment = new Color[fragmentRows][fragmentCols];

        for (int row = 0; row < fragmentRows; row++) {
            for (int col = 0; col < fragmentCols; col++) {
                fragment[row][col] = pattern[minRow + row][minCol + col];
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color color = fragment[row % fragmentRows][col % fragmentCols];
                canvas.setCellColor(row, col, color);
            }
        }
    }
}