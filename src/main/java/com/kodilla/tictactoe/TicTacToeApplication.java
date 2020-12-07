package com.kodilla.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

public class TicTacToeApplication extends Application {
    private Tile [][] board = new Tile[3][3];
    private List<Tile> tiles = new LinkedList<>();
    private boolean keepPlaying = true;
    private boolean isComplete = true;
    GridPane root = new GridPane();

    private Parent createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(600, 600);
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                root.add(tile, j, i);
                tiles.add(tile);
                board[j][i] = tile;
            }
        }
        //horizontal
        for (int y = 0; y < 3; y++){
            tiles.add(new Tile(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for(int x = 0; x < 3; x++){
            tiles.add(new Tile(board[x][0], board[x][1], board[x][2]));
        }
        //diagonals
        tiles.add(new Tile(board[0][0], board[1][1], board[2][2]));
        tiles.add(new Tile(board[2][0], board[1][1], board[0][2]));

        return root;
    }
    public void verifyResult(){
        for(Tile tile: tiles){
            if(tile.isComplete()){
                keepPlaying = false;
                createAnimationLineForWinner(tile);
                break;
            }
       }

    }

    public void createAnimationLineForWinner(Tile tile){
    Line line = new Line();
    line.setStartX(tile.getTiles()[0].getCenterX());
    line.setStartY(tile.getTiles()[0].getCenterY());
    line.setEndX(tile.getTiles()[0].getCenterX());
    line.setEndY(tile.getTiles()[0].getCenterY());

    root.getChildren().add(line);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5),
        new KeyValue(line.endXProperty(), board[2][2].getCenterX()),
        new KeyValue(line.endYProperty(), board[2][2].getCenterY())));
        timeline.play();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("TicTacToe");
        primaryStage.show();
    }
}
