package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    GridPane root;
    GameController controller;

    public Parent createContent(Stage stage) {
        root = new GridPane();
        controller = new GameController(root, stage);
        root.setPrefSize(600, 600);

        int tileCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(controller, stage);
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                tileCounter++;
                tile.idNumber = tileCounter;
                root.getChildren().add(tile);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent(primaryStage)));
        primaryStage.setTitle("TicTacToe");
        primaryStage.show();

    }
}
