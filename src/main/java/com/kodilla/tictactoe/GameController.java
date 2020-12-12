package com.kodilla.tictactoe;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.*;

public class GameController {
    GridPane root;
    boolean isLastX = false;
    Set<Integer> markedTilesX = new HashSet<>();
    Set<Integer> markedTilesO = new HashSet<>();
    List<HashSet<Integer>> winningCombinations = new ArrayList<>();

    public GameController(GridPane root) {
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
        //       HashSet<Integer> hashSet4 = new HashSet();
//        hashSet4.add(2);
//        hashSet.add(5);
//        hashSet.add(8);
//        winningCombinations[4] = hashSet;
//        hashSet.add(3);
//        hashSet.add(6);
//        hashSet.add(9);
//        winningCombinations[5] = hashSet;
//        hashSet.add(3);
//        hashSet.add(5);
//        hashSet.add(7);
//        winningCombinations[6] = hashSet;
//        hashSet.add(1);
//        hashSet.add(5);
//        hashSet.add(9);
//        winningCombinations[7] = hashSet;


    }

    public boolean ifFieldWasUsedBefore(Tile tile) {

        boolean result = markedTilesO.contains(tile.idNumber) || markedTilesX.contains(tile.idNumber);
        return result;
    }

    public void runAGame(Tile tile) {
        if (!ifFieldWasUsedBefore(tile)) {
            if (isLastX) {
                tile.text.setText("O");
                markedTilesO.add(tile.idNumber);
                verifyResult(markedTilesO);
                isLastX = false;
            } else {
                tile.text.setText("X");
                markedTilesX.add(tile.idNumber);
                verifyResult(markedTilesX);
                isLastX = true;
            }

        }
    }

    public void verifyResult(Set<Integer> hashSet) {
        if (winningCombinations.contains(hashSet)) {
            endOfGame();
        }

    }

    public void endOfGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("End of Game");
        String message;
        alert.setHeaderText("Thank you for playing Tic Tac Toe");
        if (!isLastX) {
            message = "Wygrał X, Czy checsz nowa gre?";
        } else {
            message = "Wuygfrał O chcesz jeszcze raz?";
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

        }
    }
}


