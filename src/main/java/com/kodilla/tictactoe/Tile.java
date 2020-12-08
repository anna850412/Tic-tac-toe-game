package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private static boolean isLastX = true;
    private static boolean keepPlaying;
    Text text = new Text();

    private Tile[] tiles;

    public Tile[] getTiles() {
        return this.tiles;
    }

    public Tile(Tile... tiles) {
        this.tiles = tiles;
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        // Text text = new Text();
        text.setFont(Font.font(100));
        setOnMouseClicked(event -> {
            System.out.println("Kliknieto");

            if (isLastX) {
                drawO();
                //    text.setText("O");
                verifyResult();
            } else {
                drawX();
                //  text.setText("X");
                this.verifyResult();
            }
            isLastX = !isLastX;
        });
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);
    }

    public void verifyResult() {
        for (Tile tile : tiles) {
            if (tile.isComplete()) {
                keepPlaying = false;
                //createAnimationLineForWinner(tile);
                break;
            }
        }
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

    public boolean isComplete() {
        if (tiles[0].getValue().isEmpty()) {
            return false;
        }
        return tiles[0].getValue().equals(tiles[1].getValue()) &&
                tiles[0].getValue().equals(tiles[2].getValue());
    }
}
