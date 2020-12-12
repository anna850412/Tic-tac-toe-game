package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class TicTacToeApplication extends Application {

    private boolean keepPlaying;
    private boolean isComplete;
    GridPane root;
    GameController controller;


    public Parent createContent() {
        root = new GridPane();
        controller = new GameController(root);
        root.setPrefSize(600, 600);
//        root.setAlignment(Pos.CENTER);
//        root.setGridLinesVisible(true);
//        ObservableList<Node> current = root.getChildren().stream()
//                .map(Node::getOnMouseClicked)
//                .flatMap(node ->node.lookup(createContent().getAccessibleText()))
//                .filter(text -> text.equals("X"));

        int tileCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(controller);
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);
                tileCounter++;
                tile.idNumber = tileCounter;
                root.getChildren().add(tile);
            }
        }


        return root;
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
