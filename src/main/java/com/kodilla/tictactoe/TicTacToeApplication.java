package com.kodilla.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicTacToeApplication extends Application {

    private Tile [][] board = new Tile[3][3];
    private List<Tile> tiles = new LinkedList<>();
    private boolean keepPlaying;
    private boolean isComplete;


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

//        Button button1 = new Button("Button 1");
//        Button button2 = new Button("Button 2");
//        Button button3 = new Button("Button 3");
//        Button button4 = new Button("Button 4");
//        Button button5 = new Button("Button 5");
//        Button button6 = new Button("Button 6");
//        Button button7 = new Button("Button 7");
//        Button button8 = new Button("Button 8");
//        Button button9 = new Button("Button 9");
//
//        root.add(button1, 0, 0, 1, 1);
//        root.add(button2, 1, 0, 1, 1);
//        root.add(button3, 2, 0, 1, 1);
//        root.add(button4, 0, 1, 1, 1);
//        root.add(button5, 1, 1, 1, 1);
//        root.add(button6, 2, 1, 1, 1);
//        root.add(button7, 0, 2, 1, 1);
//        root.add(button8, 1, 2, 1, 1);
//        root.add(button9, 2, 2, 1, 1);
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
       }}



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

 public void endOfGame(){
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("End of Game");
     alert.setHeaderText("Thank you for playing Tic Tac Toe");
     alert.setContentText("Would you like to play new Game?");

     Optional<ButtonType> result = alert.showAndWait();
     if (result.get() == ButtonType.OK){
         createContent();
     } else {
         keepPlaying = false;
     }
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
