package com.kodilla.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {
    GridPane root;
    boolean isLastX = false;
    Set<Integer> markedTilesX = new HashSet<>();
    Set<Integer> markedTilesO = new HashSet<>();
    List<HashSet<Integer>> winningCombinations = new ArrayList<>();

    public GameController(GridPane root, Stage stage) {
        this.root = root;
        HashSet<Integer> hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        winningCombinations.add(hashSet);
        HashSet<Integer> hashSet1 = new HashSet();
        hashSet1.add(4);
        hashSet1.add(5);
        hashSet1.add(6);
        winningCombinations.add(hashSet1);
        HashSet<Integer> hashSet2 = new HashSet();
        hashSet2.add(7);
        hashSet2.add(8);
        hashSet2.add(9);
        winningCombinations.add(hashSet2);
        HashSet<Integer> hashSet3 = new HashSet();
        hashSet3.add(1);
        hashSet3.add(4);
        hashSet3.add(7);
        winningCombinations.add(hashSet3);
        HashSet<Integer> hashSet4 = new HashSet();
        hashSet4.add(2);
        hashSet4.add(5);
        hashSet4.add(8);
        winningCombinations.add(hashSet4);
        HashSet<Integer> hashSet5 = new HashSet();
        hashSet5.add(3);
        hashSet5.add(6);
        hashSet5.add(9);
        winningCombinations.add(hashSet5);
        HashSet<Integer> hashSet6 = new HashSet();
        hashSet6.add(3);
        hashSet6.add(5);
        hashSet6.add(7);
        winningCombinations.add(hashSet6);
        HashSet<Integer> hashSet7 = new HashSet();
        hashSet7.add(1);
        hashSet7.add(5);
        hashSet7.add(9);
        winningCombinations.add(hashSet7);
    }
    Text text = new Text();

    public void makeComputerMove() {
        List<Tile> tiles = root.getChildren().stream()
                .filter(node -> node instanceof Tile)
                .map(node -> ((Tile) node))
                .filter(newTile -> newTile.text.getText().equals(""))
                .collect(Collectors.toList());
        Random randomGenerator = new Random();
        int computerTileIndex = randomGenerator.nextInt(tiles.size()) + 1;
        tiles.get(computerTileIndex).text.setText("O");
        Tile computerTile = tiles.set(computerTileIndex, tiles.get(computerTileIndex));
    }

    public boolean ifFieldWasUsedBefore(Tile tile) {
        boolean result = markedTilesO.contains(tile.idNumber) || markedTilesX.contains(tile.idNumber);
        return result;
    }

    public void runAGame(Tile tile, Stage stage) {
        if (!ifFieldWasUsedBefore(tile)) {
            if (isLastX) {
                System.out.println("this last x is false");
                markedTilesO.add(tile.idNumber);
                verifyResult(markedTilesO, stage);
                isLastX = false;
            } else {
                System.out.println("this last x is true");
                tile.text.setText("X");
                markedTilesX.add(tile.idNumber);
                verifyResult(markedTilesX, stage);
                isLastX = true;
            }
        }
    }

    public void verifyResult(Set<Integer> hashSet, Stage stage) {
        if (winningCombinations.contains(hashSet)) {
            endOfGame(stage);
        }
    }

    public void endOfGame(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End of Game");
        String message;
        alert.setHeaderText("Thank you for playing Tic Tac Toe");
        if (!isLastX) {
            message = "Won X, Would you like to play new game?";
        } else {
            message = "Won O, Would you like to play new game?";
        }
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.markedTilesX = new HashSet<>();
            this.markedTilesO = new HashSet<>();
            isLastX = false;
            for (int i = 0; i < root.getChildren().size(); i++) {
                ((Tile) root.getChildren().get(i)).text.setText("");
            }
        } else {
            stage.close();
        }

    }
}


