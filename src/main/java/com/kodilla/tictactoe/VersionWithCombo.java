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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class VersionWithCombo extends Application {
    private Tile[][] board = new Tile[3][3];
    private List<Combo> combos = new LinkedList<>();
    private boolean keepPlaying = true;
    private boolean turnX = true;


    GridPane root = new GridPane();

    private Parent createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(600, 600);
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                root.getChildren().add(tile);
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
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }
        //diagonals
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

        return root;
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

    public void verifyResult() {
        for (Combo combo : combos) {
            if (combo.isComplete()) {
                keepPlaying = false;
                createAnimationLineForWinner(combo);
                break;
            }
        }
    }

    public void createAnimationLineForWinner(Combo combo) {
        Line line = new Line();
        line.setStartX(combo.tiles[0].getCenterX());
        line.setStartY(combo.tiles[0].getCenterY());
        line.setEndX(combo.tiles[0].getCenterX());
        line.setEndY(combo.tiles[0].getCenterY());

        root.getChildren().add(line);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5),
                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
        timeline.play();
    }

    public void endOfGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End of Game");
        alert.setHeaderText("Thank you for playing Tic Tac Toe");
        alert.setContentText("Would you like to play new Game?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            createContent();
        } else {
            keepPlaying = false;
        }
    }

    private class Combo {
        private Tile[] tiles;

        public Combo(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty())
                return false;

            return tiles[0].getValue().equals(tiles[1].getValue()) &&
                    tiles[0].getValue().equals(tiles[2].getValue());
        }
    }

    public class Tile extends StackPane {
        private boolean turnX = true;
        private boolean keepPlaying = true;
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            // Text text = new Text();
            text.setFont(Font.font(100));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
           // setOnMouseClicked(this::handle);
        }


        public void drawX() {
            text.setText("X");
            text.setFont(Font.font(100));
        }

        public void drawO() {
            text.setText("O");
            text.setFont(Font.font(100));
        }

        public String getValue() {
            return text.getText();
        }

        public double getCenterX() {
            return getTranslateX() + 100;
        }

        public double getCenterY() {
            return getTranslateY() + 100;
        }
        }}

//        private void handle(MouseEvent event) {
//            System.out.println("Kliknieto");
//
//            if (!keepPlaying) {
//                return;
//                if (event.getButton() == MouseButton.PRIMARY) {
//                    if (!turnX)
//                        return;
//                    drawX();
//                    turnX = false;
//                    verifyResult();
//                } else if (event.getButton() == MouseButton.SECONDARY) {
//                    if (turnX)
//                        return;
//
//                    drawO();
//                    turnX = true;
//                    verifyResult();
//                }
//            }
//        }
//    }




