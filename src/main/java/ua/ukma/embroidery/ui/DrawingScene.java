package ua.ukma.embroidery.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ua.ukma.embroidery.canvas.EmbroideryCanvas;
import ua.ukma.embroidery.service.SymmetryService;
import ua.ukma.embroidery.service.FileService;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DrawingScene {

    public static Scene create(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");

        EmbroideryCanvas embroideryCanvas = new EmbroideryCanvas();

        StackPane centerPane = new StackPane();

        ImageView openedImageView = new ImageView();
        openedImageView.fitWidthProperty().bind(centerPane.widthProperty());
        openedImageView.fitHeightProperty().bind(centerPane.heightProperty());
        openedImageView.setPreserveRatio(true);
        openedImageView.setMouseTransparent(true);

        centerPane.getChildren().addAll(
                embroideryCanvas.getGrid(),
                openedImageView
        );

        HBox palette = createPalette(embroideryCanvas);

        VBox leftPanel = new VBox();
        VBox rightPanel = new VBox();
        HBox topPanel = new HBox();
        HBox bottomPanel = new HBox();

        //left
        Button eraserButton = new Button("Гумка");
        Button clearAllButton = new Button("Очистити все");

        //top
        Button horizontalButton = new Button("Горизонтально");
        Button verticalButton = new Button("Вертикально");
        Button duplicateButton = new Button("Дублювати");

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
        setButtonSize(duplicateButton);

        finishButton.setPrefSize(260, 55);
        finishButton.getStyleClass().add("finish-button");

       Label toolsTitle = new Label("Інструменти");
        toolsTitle.getStyleClass().add("panel-title");


        Label paletteTitle = new Label("Палітра");
        VBox.setMargin(
                palette,
                new Insets(10, 0, 0, 0)
        );
        paletteTitle.getStyleClass().add("panel-title");

        eraserButton.setOnAction(event -> embroideryCanvas.useEraser());
        clearAllButton.setOnAction(event -> embroideryCanvas.clear());

        horizontalButton.setOnAction(event ->
                SymmetryService.applyHorizontalSymmetry(embroideryCanvas.getCells())
        );

        verticalButton.setOnAction(event ->
                SymmetryService.applyVerticalSymmetry(embroideryCanvas.getCells())
        );

        saveButton.setOnAction(event -> {
            FileService.saveNodeAsPng(
                    embroideryCanvas.getGrid(),
                    root.getScene().getWindow()
            );
        });

        openButton.setOnAction(event -> {
            FileService.openImage(
                    openedImageView,
                    root.getScene().getWindow()
            );
        });


        duplicateButton.setOnAction(event ->
                SymmetryService.duplicateFragment(embroideryCanvas)
        );

        finishButton.setOnAction(event -> {
            stage.setScene(ExitScene.create(stage, embroideryCanvas));
        });

        //left panel

        leftPanel.getChildren().addAll( toolsTitle, eraserButton, clearAllButton, paletteTitle, palette);
        leftPanel.setSpacing(20);
        leftPanel.setPadding(new Insets(120, 20, 20, 20));
        leftPanel.setPrefWidth(240);
        leftPanel.setAlignment(Pos.TOP_CENTER);
        leftPanel.getStyleClass().add("side-panel");



        //right panel
        rightPanel.getChildren().addAll(openButton, saveButton);
        rightPanel.setSpacing(20);
        rightPanel.setPadding(new Insets(120, 20, 20, 20));
        rightPanel.setPrefWidth(240);
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.getStyleClass().add("side-panel");


        // top panel
        topPanel.getChildren().addAll(horizontalButton, verticalButton, duplicateButton);
        topPanel.setSpacing(20);
        topPanel.setPadding(new Insets(20));
        topPanel.setAlignment(Pos.CENTER);
        topPanel.getStyleClass().add("top-panel");


        bottomPanel.getChildren().add(finishButton);
        bottomPanel.setPadding(new Insets(20));
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.getStyleClass().add("bottom-panel");

        root.setTop(topPanel);
        root.setLeft(leftPanel);
        root.setCenter(centerPane);
        root.setRight(rightPanel);
        root.setBottom(bottomPanel);

        Scene scene = new Scene(root, 1200, 800);

        scene.getStylesheets().add(
                DrawingScene.class.getResource("/styles.css").toExternalForm()
        );

        return scene;

    }
    private static void setButtonSize(Button button) {
        button.setPrefSize(200, 60);
        button.getStyleClass().add("menu-button");
    }

    private static HBox createPalette(EmbroideryCanvas embroideryCanvas) {
        HBox palette = new HBox();
        palette.setSpacing(8);
        palette.setAlignment(Pos.CENTER);

        Button black = createColorButton("black", Color.BLACK, embroideryCanvas);
        Button red = createColorButton("red", Color.RED, embroideryCanvas);
        Button blue = createColorButton("blue", Color.BLUE, embroideryCanvas);
        Button green = createColorButton("green", Color.GREEN, embroideryCanvas);
        Button yellow = createColorButton("yellow", Color.YELLOW, embroideryCanvas);
        Button white = createColorButton("white", Color.WHITE, embroideryCanvas);

        palette.getChildren().addAll(black, red, blue, green, yellow, white);

        return palette;
    }

    private static Button createColorButton(String cssColor, Color color, EmbroideryCanvas embroideryCanvas) {
        Button button = new Button();
        button.setPrefSize(28, 28);
        button.getStyleClass().add("color-button");
        button.setStyle("-fx-background-color: " + cssColor + ";");

        button.setOnAction(event -> {
            embroideryCanvas.setCurrentColor(color);
        });

        return button;
    }
}